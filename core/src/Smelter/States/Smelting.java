package src.Smelter.States;

import java.awt.Graphics2D;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.SceneObject;
import com.epicbot.api.shared.entity.WidgetChild;

import lib.Bank.Deposit;
import lib.Bank.Withdraw;
import lib.Player.IPlayerState.IPlayerState;
import lib.Player.IPlayerState.SharedStates;
import lib.Player.IPlayerState.StatusFrame;
import lib.antiban.AntiBan;
import lib.antiban.Time.TypicalActionTime;
import src.Smelter.Constants;
import src.Smelter.Profiles.ISmeltingProfile;

public class Smelting extends IPlayerState {
  private StatusFrame status_ = new StatusFrame("Smelter");
  private int actionTime_ = 1000;

  private ISmeltingProfile currentProfile = null;
  private boolean startInitiated = false;
  private boolean uiItemSelected = false;
  @Override
  public int actionTime() { return AntiBan.time.MaskActionTime(actionTime_, TypicalActionTime.MEDIUM); }
  @Override
  public String status() { return ""; }
  @Override
  public String stateName() { return "Smelting"; }


  @Override
  public IPlayerState update() {
    if(!currentProfile.canMake()){
      startInitiated = false;
      uiItemSelected = false;

      status_.update("status", "Cannot make anymore ".concat(currentProfile.outputItem()));
      SharedStates.Banking.nextState = this;
      SharedStates.Banking.add(new Deposit(currentProfile.outputItem()));
      SharedStates.Banking.add(new Withdraw("Steel bar"));
      return SharedStates.Banking;
      // APIContext.get().script().stop("Cannot make anymore ".concat(currentProfile.outputItem()));
    }

    if(startInitiated && this.amIdle() && !uiItemSelected){
      status_.update("status", "Started but idle");
      if(!ready() && !uiItemSelected){
        if(APIContext.get().localPlayer().isMoving()){
          return this;
        }
        actionTime_ = 6000;
        status_.update("status", "Profile not running or UI selection needed");
        selectFromUI();
      }
      return this;
    }
    if(ready()){
      actionTime_ = 6000;
      status_.update("status", "Running");
      return this;
    }

    status_.update("status", "Locating furnace...");
    SceneObject furnace = currentProfile.getFurnace();
    if(furnace == null){
      APIContext.get().script().stop("Unable to obtain furnace object");
    }
    if(furnace.canReach(APIContext.get()) && !startInitiated){
      status_.update("status", "Going to furnace");
      // APIContext.get().camera().turnTo(furnace.getLocation());
      furnace.click();
      startInitiated = true;
    }
    return this;
  }


  @Override
  public void draw(Graphics2D g, APIContext ctx){
    if(currentProfile == null)
      return;
    status_.draw(g, ctx);
  }


  public void setProfile(ISmeltingProfile profile){ 
    currentProfile = profile; 
    status_.add("profile", status_.new LineData("Profile Selected", currentProfile.outputItem()));
    status_.add("status", status_.new LineData("Status:", "Init..."));
  }
  private boolean ready(){
    return APIContext.get().inventory().getCount(currentProfile.reagents()) < currentProfile.maxReagents();
  }
  private void selectFromUI(){
    APIContext.get().mouse().move(currentProfile.smeltingUI().getRandomPoint());
    APIContext.get().mouse().click();
    uiItemSelected = true;
    status_.update("status", "Selected from smelt UI");
  }
}

package src.Miner.States;

import java.util.function.Predicate;
import java.util.function.Supplier;
import java.awt.*;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.SceneObject;
import com.epicbot.api.shared.model.Model;
import com.epicbot.api.x.h.l.g;

import lib.Bank.Deposit;
import lib.Player.IPlayerState.IPlayerState;
import lib.Player.IPlayerState.RelocatingState;
import lib.Player.IPlayerState.SharedStates;
import lib.Script.StatusFrame.StatusFrame;
import lib.antiban.AntiBan;
import lib.antiban.Time.TypicalActionTime;
import src.Miner.Constants;

public class MiningState extends IPlayerState {
  SceneObject targetRock;
  Color rockModelColor = Color.WHITE;

  int pickableRockId = 0;
  int rockSearchAreaLengh = 2;
  boolean rockClicked = false;

  @Override
  public void draw(Graphics2D g, APIContext ctx){
    this.status.draw(g, ctx);
    if(targetRock != null){
      g.setColor(rockModelColor);
      targetRock.draw(g);
    }
  }

  public MiningState(){
    this.updateStrategy = updater;
    status = new StatusFrame("Mining");
    status.add("target", status.new LineData("Target Rock ID:", ""));
    status.add("target-visible", status.new LineData("Target Rock Visible:", false));
    status.add("clicked", status.new LineData("Interacted", rockClicked));
    status.add("status", status.new LineData("Status:", ""));
    status.add("idle", status.new LineData("Idle:", ""));
  }
  private Predicate<SceneObject> notCurrentTarget = (SceneObject obj) -> {
    return obj.equals(targetRock);
  };
  private Supplier<IPlayerState> updater = () -> {
    if(!amIdle()){
      status.update("idle", "No");
      status.update("status", "Mining");
      return this;
    }
    String targetVisible = targetRock != null
      ? String.valueOf(targetRock.isVisible())
      : "NULL";
    
    status.update("target-visible", targetVisible);
    status.update("idle", "Yes");
    rockClicked = false;

    if(inventoryFull()){
      status.update("status", "Banking");
      SharedStates.Banking.setDepositInventory(true);
      SharedStates.Banking.nextState = new RelocatingState(
        Constants.nextRockTile(),
        this
      );
      return new RelocatingState(
        Constants.bankChestArea,
        SharedStates.Banking
      );
    }
    else{
      if(!rockClicked){
        status.update("status", "Getting new rock");
        targetRock = targetRock == null
          ? getNewRock()
          : getNewRock(notCurrentTarget);
          
        pickableRockId = targetRock.getId();
        status.update("target", pickableRockId);
        APIContext.get().mouse().click(targetRock);
        rockClicked = true;
        AntiBan.mouse.CheckMoveOffscreen();
        return this;
      }
    }
      
    
    return this;
  };
  @Override
  public void onEnter(){

  }
  @Override
  public int actionTime() {
    return AntiBan.time.MaskActionTime(1000, TypicalActionTime.SHORT);
  }
  
  private SceneObject getNewRock(){
    return APIContext.get()
      .objects()
      .query()
      .nameMatches("Iron rocks")
      .results()
      .nearest();
  } 

  private SceneObject getNewRock(Predicate<SceneObject> queryPredicate){
    return APIContext.get()
      .objects()
      .query()
      .filter(queryPredicate)
      .nameMatches("Iron rocks")
      .results()
      .nearest();
  }
  private boolean inventoryFull(){
    return APIContext.get().inventory().isFull();
  }

  // @Override
  // public void draw(Graphics2Dg, APIContext ctx){
  //   this.status.draw(g, ctx);
  //   this.targetRock.draw(g);
  // }
}

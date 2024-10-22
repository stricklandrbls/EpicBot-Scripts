package src.Miner.States;

import java.util.function.Supplier;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.SceneObject;

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
  int pickableRockId = 0;
  int rockSearchAreaLengh = 2;
  boolean rockClicked = false;

  public MiningState(){
    this.updateStrategy = updater;
    status = new StatusFrame("Mining");
    status.add("target", status.new LineData("Target Rock ID:", ""));
  }
  private Supplier<IPlayerState> updater = () -> {
    if(!amIdle())
      return this;
    
    targetRock = getNewRock();
    status.update("target", pickableRockId);
    if(!rockClicked){
      APIContext.get().mouse().click(targetRock);
      rockClicked = true;
      AntiBan.mouse.CheckMoveOffscreen();
    }
    
    if(inventoryFull()){
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
    if( targetRock.getId() != pickableRockId){
      targetRock = getNewRock();
      pickableRockId = targetRock.getId();
      rockClicked = false;
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
      .first();
  }
  private boolean inventoryFull(){
    return APIContext.get().inventory().isFull();
  }
}

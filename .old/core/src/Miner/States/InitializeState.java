package src.Miner.States;

import java.util.function.Predicate;
import java.util.function.Supplier;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.Item;
import com.epicbot.api.shared.entity.ItemWidget;

import lib.Player.IPlayerState.IPlayerState;
import lib.Player.IPlayerState.RelocatingState;
import lib.Player.IPlayerState.SharedStates;
import lib.Script.StatusFrame.StatusFrame;
import src.Miner.Constants;

public class InitializeState extends IPlayerState {
  public InitializeState(){
    this.updateStrategy = initialUpdater;
  }
  protected Supplier<IPlayerState> initialUpdater = ()->{
    if(!hasEquipment())
      throw new UnsupportedOperationException("No equipment");
    if(!isInventoryReady())
      return this; // Bank inv
    else{
      RelocatingState relocating = new RelocatingState(
        Constants.nextRockTile(),
        Constants.Mining
      );
      return IPlayerState.Enter(relocating); // Run to spot
    }
    // return this;
  };

  @Override
  public int actionTime() {
    return 250;
  }
  @Override
  public void onEnter(){
    status = new StatusFrame("Initializing");
    System.out.println("IS::onEnter");
    status.add("pickaxe", status.new LineData("Pickaxe", "None"));
  }

  private Predicate<Item> PickaxeCheck = (Item i) -> { return i.getName().contains("pickaxe"); };
  private boolean hasEquipment(){
    Item ePick = APIContext.get().equipment().getItem(PickaxeCheck);
    if(ePick != null){
      status.update("pickaxe", ePick.getName());
      return true;
    }
    ItemWidget invPick = APIContext.get().inventory().getItem(PickaxeCheck);
    if(invPick != null) {
      status.update("pickaxe", invPick.getName());
      APIContext.get().mouse().click(invPick);
      return true;
    }
    return false;
  }
  private Predicate<Item> Ore = (Item i) -> { return i.getName().equals("Iron ore"); };
  private boolean isInventoryReady(){
    return !APIContext.get().inventory().isFull();
  }
}

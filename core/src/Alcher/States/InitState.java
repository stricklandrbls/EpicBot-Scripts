package src.Alcher.States;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;
import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.ItemWidget;

import lib.Player.IPlayerState.IPlayerState;
import lib.Script.StatusFrame.FrameButton;
import lib.Script.StatusFrame.StatusFrame;
import src.Alcher.Constants;

public class InitState extends IPlayerState{
  private String statusStr_ = "Awaiting Item Selection";
  private Supplier<IPlayerState> updater = () -> {
    return this;
  };
  public InitState(){
    this.updateStrategy = updater;
    this.status = new StatusFrame("Unique Items");
    this.status.add("item", this.status.new LineData("Item Selected: ", "None"));
  }
  @Override
  public void onEnter(){
    List<ItemWidget> alchables = APIContext.get().inventory().getItems();
    ArrayList<String> uniques = new ArrayList<String>();
    
    for(ItemWidget item : alchables){
      if(!uniques.contains(item.getName())){
        uniques.add(item.getName());

        status.add(
          new FrameButton(status, item.getName(), () -> {
            this.updateStrategy = () -> { 
              Constants.Alching.setItem(item.getName());
              return IPlayerState.Enter(Constants.Alching); 
            };
          })
        );
      }
    }
  }

  @Override
  public int actionTime() {
    return 1500;
  }

  @Override
  public String status() {
    return this.statusStr_;
  }

  @Override
  public String stateName() {
    return "Initial State";
  }

}

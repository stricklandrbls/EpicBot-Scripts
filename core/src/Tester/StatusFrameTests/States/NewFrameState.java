package src.Tester.StatusFrameTests.States;

import java.util.ArrayList;
import java.util.List;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.ItemWidget;

import lib.Player.IPlayerState.IPlayerState;
import lib.Player.IPlayerState.SharedStates;
import lib.Script.StatusFrame.FrameButton;
import lib.Script.StatusFrame.StatusFrame;

public class NewFrameState extends IPlayerState {
  @Override
  public void onEnter(){
    this.status = new StatusFrame("Unique Items");
    this.status.add("item", this.status.new LineData("Item Selected: ", "None"));
    List<ItemWidget> alchables = APIContext.get().inventory().getItems();
    ArrayList<String> uniques = new ArrayList<String>();

    for(ItemWidget item : alchables){
      if(!uniques.contains(item.getName())){
        uniques.add(item.getName());

        status.add(
          new FrameButton(status, item.getName(), () -> {
            this.updateStrategy = () -> { return IPlayerState.Enter(SharedStates.Idling); };
          })
        );
      }
    }
  }

  @Override
  public int actionTime() {
    return 500;
  }

  @Override
  public String status() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'status'");
  }

  @Override
  public String stateName() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'stateName'");
  }
  
}

package src.Pickpocketer.States;

import java.util.List;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.NPC;

import lib.Player.IPlayerState.IPlayerState;
import lib.Script.StatusFrame.StatusFrame;

import java.awt.*;

public class InitializeState extends IPlayerState {
  private StatusFrame status_ = new StatusFrame("Initializing");
  private NPC target = null;
  private int actionTime_ = 1000;
  @Override
  public void onEnter(){
    status_.add("target", status_.new LineData("Target: ", "Need to select target"));
    status_.add("food", status_.new LineData("Food: ", "No food in inventory"));
  }
  @Override
  public IPlayerState update() {
    if(target == null){
      List<NPC> targets = findTargets();
      for(NPC t : targets){
        System.out.println(t.getName());
      }
      if(targets.isEmpty())
        System.out.println("No targets found");
    }
    return this;
  }

  @Override
  public int actionTime() { return actionTime_; }

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
  
  List<NPC> findTargets(){
    return APIContext.get()
      .npcs()
      .query()
      .actions("Pickpocket")
      .results()
      .nearestList();
  }

}

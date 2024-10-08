package src.StallStealer.States;

import java.util.List;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.SceneObject;

import lib.Player.IPlayerState.IPlayerState;
import src.StallStealer.Constants;

public class Initializing extends IPlayerState {

  SceneObject target;
  @Override
  public void onEnter(){
    setTargetStall();
  }

  @Override
  public IPlayerState update() {
    if(target != null){
      Constants.StealingState.setTarget(target);
      return Constants.StealingState;
    }
      
    return this;
  }

  @Override
  public int actionTime() {
    return 2000;
  }

  @Override
  public String status() {
    return "Initalizing";
  }

  @Override
  public String stateName() {
    return "Initalizing";
  }
  
  public void setTargetStall(){
    List<SceneObject> stalls = APIContext.get().objects().getAll(obj -> {
      return obj.isOnMap(APIContext.get())
        && obj.getName().contains("Stall")
        && obj.getActions().contains("Steal-from");
    });
    SceneObject closestStall = stalls.get(0);
    for(int i = 1; i < stalls.size(); i++){
      if(stalls.get(i).distanceTo(APIContext.get()) < closestStall.distanceTo(APIContext.get()))
        closestStall = stalls.get(i);
    }
    target = closestStall;
  }
}

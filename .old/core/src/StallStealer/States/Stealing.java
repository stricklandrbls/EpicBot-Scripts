package src.StallStealer.States;

import java.util.ArrayList;
import java.util.List;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.ItemWidget;
import com.epicbot.api.shared.entity.SceneObject;

import lib.Player.IPlayerState.IPlayerState;

public class Stealing extends IPlayerState {
  public SceneObject target;
  int stallHasFruit = 28823;
  int stealCount = 0;
  public ArrayList<String> itemsToKeep = new ArrayList<String>();
  public void setTarget(SceneObject stall){
    target = stall;
  }
  @Override
  public IPlayerState update() {
    // Player is getting stuck for some reaons
    if(APIContext.get().objects().getAt(target.getLocation()).size() > 0){
      status_ = "Getting Target";
      target = APIContext.get().objects().getAt(target.getLocation()).get(0);
    }
    if(APIContext.get().inventory().isFull()){
      dropUnwantedItems();
      return this;
    }
    if(target != null){
      if(target.getId() == 28823 && !APIContext.get().localPlayer().isAnimating()){
        status_ = "Stealing";
        if(stealCount % 4 == 0){
          APIContext.get().mouse().click(target.getRandomPoint());
          stealCount = 0;
          return this;
        }
        target.click();
        stealCount++;
      }
    }
    return this;
  }

  @Override
  public int actionTime() {
    return 1000;
  }

  @Override
  public String status() {
    return status_;
  }

  public String status_ = "";
  @Override
  public String stateName() {
    return "Stealing";
  }
  
  @Override
  public void onEnter(){
    itemsToKeep.add("Golovanova fruit top");
    itemsToKeep.add("Strange fruit");
  }
  public String keepMe[] = {
    "Golovanova fruit top",
    "Strange fruit"
  };

  public void dropUnwantedItems(){
    status_ = "Dropping Items";
    APIContext.get().inventory().dropAllExcept(keepMe);
    if(APIContext.get().inventory().isFull())
      APIContext.get().script().stop("Inventory full of wanted items");
  }
}

package lib.Player.IPlayerState;

import java.util.List;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.SceneObject;
import com.epicbot.api.shared.methods.ITabsAPI.Tabs;

import lib.antiban.AntiBan;

public class IdleState extends IPlayerState{
  private String statusStr = "Idling";
  private boolean keepAlive = false;

  public static int Iterations = 50;
  private int       count = Iterations;
  @Override
  public IPlayerState update() {
    if(keepAlive){
      count--;
      int val = AntiBan.Randomizer.nextInt(count);
      if(val == 1){
        APIContext.get().tabs().open(Tabs.SKILLS);
        count = Iterations;
      }
      if(val == 2){
        APIContext.get().tabs().open(Tabs.COMBAT_OPTIONS);
        count = Iterations;
      }
      if(val == 3){
        APIContext.get().tabs().open(Tabs.INVENTORY);
        count = Iterations;
      }
      if(val == 4){
        APIContext.get().tabs().open(Tabs.SKILLS);
        count = Iterations;
      }
      if(val % 5 == 0){
        APIContext.get().mouse().moveRandomly();
        count = Iterations;
      }
      if(val == 6){
        List<SceneObject> objects = APIContext.get().objects().query().asList();
        APIContext.get().camera().turnTo(
          objects.get(
            AntiBan.Randomizer.nextInt(objects.size() - 1)
          )
        );
      }
    }
    return this;
  }

  public void keepAlive(boolean val){
    this.keepAlive = val;
  }

  @Override
  public int actionTime() {
    return 2000;
  }

  @Override
  public String status() {
    return this.statusStr;
  }

  @Override
  public String stateName() {
    return "Idle";
  }
}

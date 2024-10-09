package src.Smelter.Profiles;

import java.util.List;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.SceneObject;
import com.epicbot.api.shared.entity.WidgetChild;

public abstract class ISmeltingProfile {
  public abstract String   outputItem();
  public abstract String[] reagents();
  public abstract int      maxReagents();
  public abstract int      uiButtonNumber();
  
  public static int SmeltingWidgetUINumber = 270;

  public boolean canMake(){
    return APIContext.get().inventory().containsAll(this.reagents());
  }
  public WidgetChild smeltingUI(){
    return APIContext.get()
      .widgets()
      .get(SmeltingWidgetUINumber)
      .getChild(uiButtonNumber());
  }
  public SceneObject getFurnace(){
    return APIContext.get()
      .objects()
      .query()
      .actions("Smelt")
      .results()
      .first();
  }
}

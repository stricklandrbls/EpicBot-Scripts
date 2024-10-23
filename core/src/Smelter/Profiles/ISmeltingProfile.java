package src.Smelter.Profiles;

import java.util.List;
import java.util.function.*;
import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.SceneObject;
import com.epicbot.api.shared.entity.WidgetChild;

public abstract class ISmeltingProfile {
  public abstract String   outputItem();
  public abstract String[] reagents();
  public abstract int      maxReagents();
  public abstract int      uiButtonNumber();
  public abstract int      currentInventoryAmount();
  protected int   outputMultiplier = 1;
  public static int SmeltingWidgetUINumber = 270;
  protected int amountMade = 0;
  protected int inventoryIterations = 0;
  public void addIteration(){
    inventoryIterations++;
    amountMade += currentInventoryAmount();
  }
  public void updateAmountMade(int amount){
    if(amount != amountMade)
      amountMade += amount;
  }
  public int getAmountMade(){ return amountMade; }
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

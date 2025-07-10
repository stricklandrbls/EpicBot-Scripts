package src.Smelter.Profiles;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.Item;

public class CannonballProfile extends ISmeltingProfile{
  private String[] requiredItems_ = {"Steel bar", "Ammo mould"};
  public CannonballProfile(){
    this.outputMultiplier = 4;
  }
  @Override
  public int uiButtonNumber(){ return 14; }
  @Override
  public int maxReagents(){ return 28; }
  @Override
  public String outputItem(){ return "Cannonball"; }
  @Override
  public String[] reagents(){ return requiredItems_; }
  @Override
  public int currentInventoryAmount() {
    return APIContext.get().inventory().getItem((Item i) -> {
      return i.getName().equals(this.outputItem());
    }).getStackSize();
  }
}

package src.Smelter.Profiles;

public class CannonballProfile extends ISmeltingProfile{
  private String[] requiredItems_ = {"Steel bar", "Ammo mould"};

  @Override
  public int uiButtonNumber(){ return 14; }
  @Override
  public int maxReagents(){ return 28; }
  @Override
  public String outputItem(){ return "Cannonball"; }
  @Override
  public String[] reagents(){ return requiredItems_; }
}

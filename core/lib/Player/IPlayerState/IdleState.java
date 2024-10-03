package lib.Player.IPlayerState;

public class IdleState extends IPlayerState{
  private String statusStr = "Idling";
  @Override
  public IPlayerState update() {
    System.out.println(this.statusStr);
    return this;
  }

  @Override
  public int actionTime() {
    return 1000;
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

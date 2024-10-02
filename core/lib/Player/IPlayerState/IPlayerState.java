package lib.Player.IPlayerState;

public abstract class IPlayerState {
  protected void onEnter(){
    System.out.println(this.status());
  }
  protected void onExit(){
  }
  public abstract IPlayerState update();
  public abstract int  actionTime();
  public abstract String status();
}
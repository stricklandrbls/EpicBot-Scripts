package lib.Player;
import lib.Player.IPlayerState.*;

public class MyPlayer {
  private IPlayerState currentState_;
  public MyPlayer(IPlayerState initState){
    this.currentState_ = initState;
  }
  public void setState(IPlayerState nextState){
    this.currentState_ = nextState;
  }
  public void update() {
    this.currentState_ = this.currentState_.update();
  }

  public int actionTime() {
    return 1000;
  }

  public String status() {
    return this.currentState_.status();
  }
}
package lib.Player;
import lib.Player.IPlayerState.*;

public class MyPlayer {

  private IPlayerState currentState_;
  
  public MyPlayer(IPlayerState initState){
    this.currentState_ = initState;
  }
  public MyPlayer(IPlayerState initState, boolean runOnEnter){
    if(runOnEnter)
      this.currentState_ = IPlayerState.Enter(initState);
    else
      this.currentState_ = initState;
  }
  
  public IPlayerState currentState(){
    return this.currentState_;
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
  public IPlayerState getState(){ return currentState_; }
  public String status() {
    return this.currentState_.status();
  }
}

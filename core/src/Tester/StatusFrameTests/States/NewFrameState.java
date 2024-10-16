package src.Tester.StatusFrameTests.States;

import com.epicbot.api.shared.APIContext;

import lib.Player.IPlayerState.IPlayerState;
import lib.Script.StatusFrame.FrameButton;
import lib.Script.StatusFrame.StatusFrame;

public class NewFrameState extends IPlayerState {
  
  public NewFrameState(){
    this.status = new StatusFrame("Next State");
  }
  @Override
  public void onEnter(){
    status.add(new FrameButton(status, "New Button", () -> {
      APIContext.get().script().stop("Done!");
    }));
  }
  @Override
  public IPlayerState update() {
    return this;
  }

  @Override
  public int actionTime() {
    return 500;
  }

  @Override
  public String status() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'status'");
  }

  @Override
  public String stateName() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'stateName'");
  }
  
}

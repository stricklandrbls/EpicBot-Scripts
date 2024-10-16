package src.Tester.StatusFrameTests.States;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.util.paint.frame.*;

import lib.Player.IPlayerState.IPlayerState;
import lib.Script.StatusFrame.FrameButton;
import lib.Script.StatusFrame.StatusFrame;

public class StatusFrameTestState extends IPlayerState {
  @Override
  public void onEnter(){
    this.status = new StatusFrame("Test Frame");
    
    status.add("frame", status.new LineData("Frame dimensions: ", ""));
    status.add("btn1", status.new LineData("Btn1 (x,y): ", ""));
    status.add("btn3", status.new LineData("Btn3 (x,y): ", ""));
    status.add("btn2", status.new LineData("Btn2 (x,y): ", ""));
    status.add("mouse", status.new LineData("Mouse (x,y): ", ""));
    
    status.getFrame().addLine(new Seperator(status.getFrame()));
    
    status.add(new FrameButton(status, "To NewState", () -> {
      this.updateStrategy = () -> { return IPlayerState.Enter(Constants.NewState); };
    }));

    status.add(new FrameButton(status, "Btn3", ()->{
      APIContext.get().script().stop(null);
    }));
    status.add(new FrameButton(status, "Some Fn", ()->{
      APIContext.get().script().stop(null);
    }));
  }

  @Override
  public int actionTime() { return 200; }

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

package src.Tester.StatusFrameTests.States;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.util.paint.frame.*;

import lib.Player.IPlayerState.IPlayerState;
import lib.Player.IPlayerState.SharedStates;
import lib.Script.StatusFrame.FrameButton;
import lib.Script.StatusFrame.StatusFrame;

import java.awt.*;
public class StatusFrameTestState extends IPlayerState {
  FrameButton btn;
  FrameButton btn2;
  FrameButton btn3;
  private boolean firstEnter = true;
  public IPlayerState nextState = new NewFrameState();
  public StatusFrameTestState(){
    this.status = new StatusFrame("Test Frame");
  }
  @Override
  public void onEnter(){
    
    status.add("frame", status.new LineData("Frame dimensions: ", ""));
    status.add("btn1", status.new LineData("Btn1 (x,y): ", ""));
    status.add("btn3", status.new LineData("Btn3 (x,y): ", ""));
    status.add("btn2", status.new LineData("Btn2 (x,y): ", ""));
    status.add("mouse", status.new LineData("Mouse (x,y): ", ""));
    
    status.getFrame().addLine(new Seperator(status.getFrame()));
    status.add(new FrameButton(status, "Click Me!",() -> {
      this.next = nextState;
    }));
    status.add(new FrameButton(status, "Btn3", ()->{
      APIContext.get().script().stop(null);
    }));
    status.add(new FrameButton(status, "Some Fn", ()->{
      APIContext.get().script().stop(null);
    }));
    firstEnter = false;
  }

  // /**
  //  * @brief Invokes <code>StatsuFrame.draw()</code>
  //  */
  // @Override
  // public void draw(Graphics2D g, APIContext ctx){
  //   try{
  //   this.status.draw(g, ctx);
  //   }catch(Exception e){
  //     e.printStackTrace(System.out);
  //     APIContext.get().script().stop("Could not draw status frame");
  //   }
  // }




  private IPlayerState next = this;
  @Override
  public IPlayerState update() {

    // System.out.println("Running update()");
    if(firstEnter){
      // System.out.println("Running onEnter()");
      this.onEnter();
    }
    return next;
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

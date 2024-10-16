package src.Tester.StatusFrameTests.States;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.util.paint.Dimension2D;
import com.epicbot.api.shared.util.paint.frame.*;

import lib.Player.IPlayerState.IPlayerState;
import lib.Player.IPlayerState.SharedStates;
import lib.Script.StatusFrame.ButtonCallback;
import lib.Script.StatusFrame.FrameButton;
import lib.Script.StatusFrame.StatusFrame;

import java.awt.*;
import java.lang.*;
public class StatusFrameTestState extends IPlayerState {
  StatusFrame status_ = new StatusFrame("Test Frame");
  FrameButton btn;
  FrameButton btn2;
  FrameButton btn3;
  private boolean firstEnter = true;
  public IPlayerState nextState = SharedStates.Idling;
  @Override
  public void onEnter(){
    
    status_.add("frame", status_.new LineData("Frame dimensions: ", ""));
    status_.add("btn1", status_.new LineData("Btn1 (x,y): ", ""));
    status_.add("btn3", status_.new LineData("Btn3 (x,y): ", ""));
    status_.add("btn2", status_.new LineData("Btn2 (x,y): ", ""));
    status_.add("mouse", status_.new LineData("Mouse (x,y): ", ""));
    
    status_.getFrame().addLine(new Seperator(status_.getFrame()));
    btn = new FrameButton(status_, "Click Me!");
    btn3 = new FrameButton(status_, "Btn3");
    btn.onSelect(() -> {
      SharedStates.Idling.keepAlive(true);
      this.next = nextState;
    });
    btn2 = new FrameButton(status_, "Some Fn");
    btn2.onSelect(()->{
      APIContext.get().script().stop(null);
    });
    firstEnter = false;
  }

  @Override
  public void draw(Graphics2D g, APIContext ctx){
    try{
      // this.status_.getFrame().draw(g, 250, 250, ctx); // x,y args are location, not size;
    this.status_.draw(g, ctx);
    }catch(Exception e){
      e.printStackTrace(System.out);
      APIContext.get().script().stop("Could not draw status frame");
    }
  }




  private IPlayerState next = this;
  @Override
  public IPlayerState update() {

    // System.out.println("Running update()");
    if(firstEnter){
      // System.out.println("Running onEnter()");
      this.onEnter();
    }
    int frameH = (int)status_.getFrame().getLastBounds().getHeight();
    int frameW = (int)status_.getFrame().getLastBounds().getWidth();
    Rectangle frameRec = status_.getFrame().getLastBounds().getBounds();
    String status = String.valueOf(frameW)
      .concat(" x ")
      .concat(String.valueOf(frameH))
      .concat(" @ (")
      .concat(String.valueOf(frameRec.x)
      .concat(",")
      .concat(String.valueOf(frameRec.y)))
      .concat(")");
      
    status_.update("frame", status);
    status_.update("btn1", btn.toString());
    status_.update("btn2", btn2.toString());
    status_.update("btn3", btn3.toString());
    status_.update("mouse", "("+APIContext.get().mouse().getX() + ","+APIContext.get().mouse().getY()+")");
    //   System.out.println(status);
    // System.out.println(btn);
    // System.out.println(btn2);
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

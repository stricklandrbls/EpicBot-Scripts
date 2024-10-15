package src.Tester.StatusFrameTests.States;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.util.paint.Dimension2D;
import com.epicbot.api.shared.util.paint.frame.FramePart;

import javafx.scene.shape.Rectangle;
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
  public class ChangeState implements ButtonCallback{

    @Override
    public void execute() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

  }
  private boolean firstEnter = true;
  public IPlayerState nextState = SharedStates.Idling;
  @Override
  public void onEnter(){
    
    status_.add("txt", status_.new LineData("Text: ", "Some Text"));
    btn = new FrameButton(status_, "Click Me!");
    btn.onSelect(() -> {
      this.next = nextState;
    });
    status_.getFrame().addPart(btn);
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
    String status = String.valueOf(status_.getFrame().getLastBounds().getHeight())
      .concat("h x ")
      .concat(String.valueOf(status_.getFrame().getLastBounds().getWidth()))
      .concat("w");
    // System.out.println(status);
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

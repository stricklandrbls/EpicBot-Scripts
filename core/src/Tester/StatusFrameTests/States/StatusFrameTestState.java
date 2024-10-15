package src.Tester.StatusFrameTests.States;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.util.paint.Dimension2D;
import com.epicbot.api.shared.util.paint.frame.FramePart;

import javafx.scene.shape.Rectangle;
import lib.Player.IPlayerState.IPlayerState;
import lib.Script.StatusFrame.FrameButton;
import lib.Script.StatusFrame.StatusFrame;

import java.awt.*;
import java.lang.*;
public class StatusFrameTestState extends IPlayerState {
  StatusFrame status_ = new StatusFrame("Test Frame");
  
  private boolean firstEnter = true;
  @Override
  public void onEnter(){
    
    status_.add("txt", status_.new LineData("Text: ", "Some Text"));
    FrameButton btn = new FrameButton(status_, "Click Me!");
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
    return this;
  }

  @Override
  public int actionTime() { return 2000; }

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

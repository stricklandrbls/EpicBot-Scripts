package lib.Player.IPlayerState;

import java.awt.Graphics2D;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.util.paint.frame.PaintFrame;

public abstract class StatusFrame {
  public double x = 0.0;
  public double y = 0.0;
  protected PaintFrame frame_;

  protected abstract void __drawContent(PaintFrame frame);

  public StatusFrame(String title){
    frame_ = new PaintFrame(title);
  }
  public void draw(Graphics2D g, APIContext ctx){
    this.__drawContent(frame_);
    frame_.draw(g, y, x, ctx);
  }
}

package lib.Script.StatusFrame;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.script.Script;
import com.epicbot.api.shared.util.paint.frame.PaintFrame;
public class ButtonPaintListener extends Script{
  public boolean isHovered = false;

  public ButtonPaintListener(){
    super();
  }
  public void setMouseListener(){
    Point mousePos = APIContext.get().mouse().getLocation();
  }
  @Override
  public boolean onStart(String... arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'onStart'");
  }
}

package lib.Script.StatusFrame;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.util.paint.Dimension2D;
import com.epicbot.api.shared.util.paint.frame.FramePart;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class FrameButton extends FramePart {
  private Rectangle2D buttonBounds;
  public static int Height = 20;
  public static int Width  = 100;
  public static int CornerRadius = 2;
  public static int SelectLinePad = 2;
  public Color baseColor = Color.gray;
  public Color slctColor = Color.LIGHT_GRAY;
  private String text = "";
  private int hoverTime = 1;
  private Dimension2D dims = new Dimension2D(150.0, 30.0);
  public FrameButton(StatusFrame statusFrame, String text){
    super(statusFrame.getFrame());
    this.text = text;
    statusFrame.getFrame().addPart(this);
    
  }
  public boolean isHovered(){
    Point mousePos = APIContext.get().mouse().getLocation();
    return buttonBounds.contains(mousePos);
  }
  
  /**
   * @return Height of the button being drawn
   */
  @Override
  public double draw(Graphics2D g, double x, double y, Dimension2D dims, APIContext ctx) {
    if(buttonBounds == null){
      buttonBounds = new Rectangle2D.Double(x, y, Width, Height);
    }
    int lineX1 = (int)buttonBounds.getX();
    int lineY = (int)(buttonBounds.getY()+ buttonBounds.getHeight())+SelectLinePad;
    int lineX2 = lineX1 + Width;
    int fillX = lineX1 + (int)hoverTime;
    if(isHovered()){
      g.setColor(slctColor);
      
      if(hoverTime >= Width){
        isSelected_ = true;
        this.onSelect.run();
        g.drawLine(lineX1, lineY, lineX2, lineY);
      }
      else {
        hoverTime++;
        g.drawLine(lineX1, lineY, fillX, lineY);
      }
    }
    else{
      g.setColor(baseColor);
      hoverTime = 0;
    }
    g.drawRect((int)buttonBounds.getX(), (int)buttonBounds.getY(), Width, Height);
    g.setColor(slctColor);
    FontMetrics fm = g.getFontMetrics();
    int labelW = fm.stringWidth(this.text);
    int labelH = fm.getHeight();
    g.drawString(this.text, (int)x+(Width - labelW) / 2, (int)y+(Height + labelH / 4) / 2);
    g.setColor(baseColor);
    return Height + 5;
  }

  @Override
  public Dimension2D getSize(Graphics2D arg0, APIContext arg1) {
    return dims;
  }
  private Runnable onSelect;
  public void onSelect(Runnable onSelect){
    this.onSelect = onSelect;
  }
  private boolean isSelected_ = false;
  public boolean isSelected(){
    return isSelected_;
  }
  
  public String toString(){
    String ret = String.valueOf((int)buttonBounds.getWidth())
      .concat(" x ")
      .concat(String.valueOf((int)buttonBounds.getHeight()))
      .concat(" @ (")
      .concat(String.valueOf((int)buttonBounds.getX())
      .concat(",")
      .concat(String.valueOf((int)buttonBounds.getY())))
      .concat(") ["+hoverTime+"]");
    return ret;
  }
}
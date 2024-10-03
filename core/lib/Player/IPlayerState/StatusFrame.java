package lib.Player.IPlayerState;

import java.awt.Graphics2D;
import java.util.HashMap;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.util.paint.frame.Line;
import com.epicbot.api.shared.util.paint.frame.PaintFrame;

public class StatusFrame {
  PaintFrame frame_;
  private HashMap<String, StatusLine> lines = new HashMap<String, StatusLine>();

  public StatusFrame(String title){
    frame_ = new PaintFrame(title);
  }

  private class StatusLine extends Line {
    public String value = "";
    public StatusLine(PaintFrame frame, String txt, Object value){
      super(frame, txt, value);
      this.value = String.valueOf(value);
    }
    public StatusLine(PaintFrame frame, String txt, String value){
      super(frame, txt, value);
    }
    @Override
    public String getValue(APIContext ctx){
      return value;
    }
  }

  public class LineData {
    public String desc_ = "";
    public Object value_;
    public LineData(String desc, Object value){
      desc_ = desc;
      value_ = value;
    }
    public LineData(String desc, String value){
      desc_ = desc;
      value_ = value;
    }
  }
  public LineData createLine(String desc, Object value){
    return new LineData(desc, value);
  }
  public void add(String id, LineData text){
    StatusLine newLine = new StatusLine(frame_, text.desc_, text.value_);
    lines.put(id, newLine);
    frame_.addPart(newLine);
  }
  public void update(String id, Object value){
    StatusLine txt = lines.get(id);
    if(txt == null)
      return;
    txt.value = String.valueOf(value);
  }
  public void draw(Graphics2D g, APIContext ctx){
    frame_.draw(g, 0, 200, ctx);
  }
}

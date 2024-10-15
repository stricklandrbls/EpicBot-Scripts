package lib.Script.StatusFrame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.*;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.util.paint.Dimension2D;
import com.epicbot.api.shared.util.paint.frame.FramePart;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

// public class CustomPaintFrame {
//     private Rectangle2D rect;
//     private Color color;

//     public CustomPaintFrame() {
//         this.rect = new Rectangle2D.Double(100, 100, 100, 100);
//         this.color = Color.BLUE;
//     }

//     // Method to handle drawing in the frame using Graphics2D
//     public void draw(Graphics2D g2d) {
//         g2d.setColor(color);
//         g2d.fill(rect);
//     }

//     // Method to check if the mouse is inside the rectangle and change color
//     public void handleMouseMoved(int x, int y) {
//         if (rect.contains(x, y)) {
//             color = Color.RED;  // Change color on hover
//         } else {
//             color = Color.BLUE;  // Reset color when not hovering
//         }
//     }

//     // Main program to simulate mouse interaction
//     public static void main(String[] args) {
//         Frame frame = new Frame("Custom Paint Frame with Mouse Events");
//         frame.setSize(400, 400);
//         frame.setLayout(new BorderLayout());

//         // Create the custom PaintFrame instance
//         CustomPaintFrame customFrame = new CustomPaintFrame();

//         // Create a Canvas to handle drawing and events
//         Canvas canvas = new Canvas() {
//             @Override
//             public void paint(Graphics g) {
//                 Graphics2D g2d = (Graphics2D) g;
//                 customFrame.draw(g2d);
//             }
//         };

//         // Add mouse listener to the canvas
//         canvas.addMouseMotionListener(new MouseMotionAdapter() {
//             @Override
//             public void mouseMoved(MouseEvent e) {
//                 // Forward the mouse position to the custom frame
//                 customFrame.handleMouseMoved(e.getX(), e.getY());
//                 // Trigger a repaint to reflect the change
//                 canvas.repaint();
//             }
//         });

//         frame.add(canvas, BorderLayout.CENTER);
//         frame.setVisible(true);

//         // Add window close operation
//         frame.addWindowListener(new WindowAdapter() {
//             @Override
//             public void windowClosing(WindowEvent e) {
//                 frame.dispose();
//             }
//         });
//     }
// }

public class FrameButton extends FramePart {
  private Rectangle2D buttonBounds;
  public static int Height = 20;
  public static int Width  = 100;
  public static int CornerRadius = 2;
  public Color bgColor = Color.BLACK;
  private String text = "";
  private int hoverTime = 1;
  private int timeToAccept = 3000;
  private double fillRatio = 33.33;
  private Dimension2D dims = new Dimension2D(Width, Height);
  public FrameButton(StatusFrame statusFrame, String text){
    super(statusFrame.getFrame());
    this.text = text;
    statusFrame.getFrame().addPart(this);
  }
  public boolean isHovered(){
    Point mousePos = APIContext.get().mouse().getLocation();
    return buttonBounds.contains(mousePos);
  }
  
  @Override
  public double draw(Graphics2D g, double x, double y, Dimension2D dims, APIContext ctx) {

    int lineX1 = (int)x;
    int lineY = (int)y + Height;
    int lineX2 = lineX1 + Width;
    double fillPercentage = (hoverTime / 10) * 3.33;
    if(buttonBounds == null){
      buttonBounds = new Rectangle2D.Double(x, y, Width, Height);
    }
    if(isHovered()){
      if(fillPercentage >= Width){
        System.out.println("Button was selected!");
        g.setColor(Color.WHITE);
        g.drawLine(lineX1, lineY, lineX2, lineY);
      }
      else {
        hoverTime++;
        bgColor = Color.LIGHT_GRAY;
        System.out.println(String.valueOf(fillPercentage));
        g.setColor(Color.WHITE);
        g.drawLine(lineX1, lineY, lineX1 + (int)fillPercentage, lineY);
      }
    }
    else{
      hoverTime = 0;
    }

    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
    g.setColor(isHovered() ? Color.DARK_GRAY : Color.BLACK);
    g.drawRoundRect((int)x,(int) y, Width, Height, CornerRadius, CornerRadius);
    g.fill(buttonBounds);
    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    FontMetrics fm = g.getFontMetrics();
    int labelW = fm.stringWidth(this.text);
    int labelH = fm.getHeight();
    g.drawString(this.text, (int)x+(Width - labelW) / 2, (int)y+(Height + labelH / 4) / 2);
    return y;
  }

  @Override
  public Dimension2D getSize(Graphics2D arg0, APIContext arg1) {
    return dims;
  }
  
}
// Frame frame = new Frame("Mouse Position Example");
// frame.setSize(400, 400);
// frame.setLayout(new BorderLayout());

// // Create a canvas to listen for mouse events
// Canvas canvas = new Canvas() {
//     @Override
//     public void paint(Graphics g) {
//         // Optional painting logic
//     }
// };

// // Add a MouseMotionListener to print the mouse position
// canvas.addMouseMotionListener(new MouseMotionAdapter() {
//     @Override
//     public void mouseMoved(MouseEvent e) {
//         // Print the mouse position when it moves
//         System.out.println("Mouse moved to X: " + e.getX() + ", Y: " + e.getY());
//     }
// });

// frame.add(canvas, BorderLayout.CENTER);
// frame.setVisible(true);
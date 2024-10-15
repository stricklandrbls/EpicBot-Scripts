package src.Tester.StatusFrameTests.Graphics;

import java.awt.*;
import java.awt.event.*;

public class StatusFrameButton extends Canvas {
    private String label;
    private Color backgroundColor;
    private Color hoverColor;
    private Color textColor;
    private boolean isHovered;
    private boolean isClicked;

    public StatusFrameButton(String label) {
        this.label = label;
        this.backgroundColor = Color.LIGHT_GRAY;
        this.hoverColor = Color.GRAY;
        this.textColor = Color.BLACK;
        this.isHovered = false;
        this.isClicked = false;

        // Set preferred size
        setPreferredSize(new Dimension(100, 40));

        // Add event listeners for hover and click events
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isClicked = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isClicked = false;
                if (isHovered) {
                    onClick();
                }
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        // Set the background color based on hover and click states
        if (isClicked) {
            g.setColor(hoverColor.darker());
        } else if (isHovered) {
            g.setColor(hoverColor);
        } else {
            g.setColor(backgroundColor);
        }
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw the button label
        g.setColor(textColor);
        FontMetrics fm = g.getFontMetrics();
        int labelWidth = fm.stringWidth(label);
        int labelHeight = fm.getHeight();
        g.drawString(label, (getWidth() - labelWidth) / 2, (getHeight() + labelHeight / 4) / 2);
    }

    // Override this method to define what happens on click
    public void onClick() {
        System.out.println("Button clicked!");
    }

    // Main method for testing
    // public static void main(String[] args) {
    //     Frame frame = new Frame("Custom Button Example");
    //     StatusFrameButton customButton = new StatusFrameButton("Click Me");

    //     frame.add(customButton);
    //     frame.pack();
    //     frame.setVisible(true);

    //     // Add window close operation
    //     frame.addWindowListener(new WindowAdapter() {
    //         @Override
    //         public void windowClosing(WindowEvent e) {
    //             frame.dispose();
    //         }
    //     });
    // }
}

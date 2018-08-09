package view.gui;

import javax.swing.JComponent;
import java.awt.*;

public class PaintCanvas extends JComponent {

    private static PaintCanvas instance = new PaintCanvas();

    public static PaintCanvas getCanvasInstance(){
        return instance;
    }

    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }
}

package view.interfaces;

import view.gui.ShapeConfiguration;

import java.awt.*;

public interface IShape {
    void draw ();
    public Rectangle getRectangle();
    public Graphics2D getGraphics2D();
    void setX(int x);
    void setY(int y);
    public int getX();
    public int getY();
    public ShapeConfiguration getActiveShape();
}

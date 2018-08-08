package view.interfaces;

import model.ShapeType;
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
    public ShapeType getActiveShape();
    public void setXarr(int index, int newVal);
    public void setYarr(int index, int newVal);
    public int getXarrIndex(int index);
    public int getYarrIndex(int index);
    public void setRectangle(Rectangle rectangle);
    public int getWidth();
    public int getHeight();
    ShapeConfiguration getActiveShapeConfiguration();
}

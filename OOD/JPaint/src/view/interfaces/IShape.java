package view.interfaces;

import model.ShapeType;
import view.gui.ShapeConfiguration;

import java.awt.*;

public interface IShape {
    void draw ();
    Rectangle getRectangle();
    void setX(int x);
    void setY(int y);
    int getX();
    int getY();
    ShapeType getActiveShape();
    void setXarr(int index, int newVal);
    void setYarr(int index, int newVal);
    int getXarrIndex(int index);
    int getYarrIndex(int index);
    void setRectangle(Rectangle rectangle);
    int getWidth();
    int getHeight();
    ShapeConfiguration getActiveShapeConfiguration();
}

package view.gui;

import view.interfaces.IShape;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse implements IShape {

    private int x, y, width, height;
    Color Color;
    Graphics2D graphics2d;
    private PaintCanvas canvas;
    private ShapeConfiguration activeShape;
    Ellipse2D.Double ellipse;
    Rectangle rectangle;

    public Ellipse(int x, int y, int width, int height, PaintCanvas canvas, ShapeConfiguration activeShape){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.activeShape = activeShape;
        this.canvas = canvas;
        this.graphics2d = canvas.getGraphics2D();
        rectangle = new Rectangle(x,y,width,height);
    }
    @Override
    public void draw() {

        switch (activeShape.getActiveShapeShadingType()) {
            case FILLED_IN:

                graphics2d.setColor(ShapeColorMap.get(activeShape.getActivePrimaryColor()));
                ellipse = new Ellipse2D.Double(x,y,width,height);
                graphics2d.fill(ellipse);
                graphics2d.draw(ellipse);

                break;

            case OUTLINE:

                graphics2d.setColor(ShapeColorMap.get(activeShape.getActivePrimaryColor()));
                ellipse = new Ellipse2D.Double(x,y,width,height);
                graphics2d.setStroke(new BasicStroke(5));
                graphics2d.draw(ellipse);

                break;

            case OUTLINE_AND_FILLED_IN:

                graphics2d.setColor(ShapeColorMap.get(activeShape.getActivePrimaryColor()));
                ellipse = new Ellipse2D.Double(x,y,width,height);
                graphics2d.fill(ellipse);
                graphics2d.setStroke(new BasicStroke(5));
                graphics2d.setColor(ShapeColorMap.get(activeShape.getActiveSecondaryColor()));
                graphics2d.draw(ellipse);

                break;
        }
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public Graphics2D getGraphics2D() {
        return graphics2d;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public ShapeConfiguration getActiveShape() {
        return null;
    }
}

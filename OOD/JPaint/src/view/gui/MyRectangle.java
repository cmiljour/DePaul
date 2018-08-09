package view.gui;

import model.ShapeType;
import view.interfaces.IShape;

import java.awt.*;

import static model.ShapeType.RECTANGLE;

public class MyRectangle implements IShape {

    private int x;
    private int y;
    private int width;
    private int height;
    Color color;
    Graphics2D graphics2d;
    private PaintCanvas canvas;
    private ShapeConfiguration activeShape;



    Rectangle rectangle;

    public MyRectangle(int x, int y, int width, int height, ShapeConfiguration activeShape){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.activeShape = activeShape;
        this.canvas = canvas;
        this.graphics2d = PaintCanvas.getCanvasInstance().getGraphics2D();
        this.rectangle = new Rectangle(x,y,width,height);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ShapeConfiguration getActiveShapeConfiguration(){
        return this.activeShape;
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
    public ShapeType getActiveShape() {
        return activeShape.getActiveShapeType();
    }

    @Override
    public void setXarr(int index, int newVal) {

    }

    @Override
    public void setYarr(int index, int newVal) {

    }

    @Override
    public int getXarrIndex(int index) {
        return 0;
    }

    @Override
    public int getYarrIndex(int index) {
        return 0;
    }

    @Override
    public void draw() {

        switch (activeShape.getActiveShapeShadingType()) {
            case FILLED_IN:

                graphics2d.setColor(ShapeColorMap.get(activeShape.getActivePrimaryColor()));
                graphics2d.fillRect(x,y,width,height);
                graphics2d.drawRect(x,y,width,height);

                break;

            case OUTLINE:

                graphics2d.setColor(ShapeColorMap.get(activeShape.getActivePrimaryColor()));
                graphics2d.setStroke(new BasicStroke(5));
                graphics2d.drawRect(x,y,width,height);


                break;

            case OUTLINE_AND_FILLED_IN:

                graphics2d.setColor(ShapeColorMap.get(activeShape.getActivePrimaryColor()));
                graphics2d.fillRect(x,y,width,height);
                graphics2d.setStroke(new BasicStroke(5));
                graphics2d.setColor(ShapeColorMap.get(activeShape.getActiveSecondaryColor()));
                graphics2d.drawRect(x,y,width,height);


                break;
        }

    }
}

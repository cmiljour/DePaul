package view.gui;

import model.ShapeType;
import view.interfaces.IShape;

import java.awt.*;

import static model.ShapeType.RECTANGLE;

public class MyRectangle implements IShape, Cloneable {

    private int x;
    private int y;
    private int width;
    private int height;
    private Graphics2D graphics2d;
    private ShapeConfiguration activeShape;
    private Rectangle rectangle;

    public MyRectangle(ShapeConfiguration activeShape){
        this.x = Math.min(activeShape.getActivePointsPressed().getXpoint(), activeShape.getActivePointsReleased().getXpoint());
        this.y = Math.min(activeShape.getActivePointsPressed().getYpoint(), activeShape.getActivePointsReleased().getYpoint());
        this.width = Math.abs(activeShape.getActivePointsPressed().getXpoint() - activeShape.getActivePointsReleased().getXpoint());
        this.height = Math.abs(activeShape.getActivePointsPressed().getYpoint() - activeShape.getActivePointsReleased().getYpoint());
        this.activeShape = activeShape;
        this.graphics2d = PaintCanvas.getCanvasInstance().getGraphics2D();
        this.rectangle = new Rectangle(this.x,this.y,this.width,this.height);
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

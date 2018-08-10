package view.gui;

import model.ShapeType;
import view.interfaces.IShape;

import java.awt.*;

public class Triangle implements IShape {


    private ShapeConfiguration activeShape;
    Graphics2D graphics2d;
    int x, y, width, height;
    Rectangle rectangle;
    int[] xInts;
    int[] yInts;


    public Triangle(ShapeConfiguration activeShape) {

        this.activeShape = activeShape;
        this.graphics2d = PaintCanvas.getCanvasInstance().getGraphics2D();
//        this.x = activeShape.getActivePointsPressed().getXpoint();
//        this.y = activeShape.getActivePointsPressed().getYpoint();
//        this.width = width;
//        this.height = height;
        this.x = Math.min(activeShape.getActivePointsPressed().getXpoint(), activeShape.getActivePointsReleased().getXpoint());
        this.y = Math.min(activeShape.getActivePointsPressed().getYpoint(), activeShape.getActivePointsReleased().getYpoint());
        this.width = Math.abs(activeShape.getActivePointsPressed().getXpoint() - activeShape.getActivePointsReleased().getXpoint());
        this.height = Math.abs(activeShape.getActivePointsPressed().getYpoint() - activeShape.getActivePointsReleased().getYpoint());
        this.rectangle = new Rectangle(this.x,this.y,this.width,this.height);
        this.xInts = new int[]{
                activeShape.getActivePointsPressed().getXpoint(),
                activeShape.getActivePointsReleased().getXpoint(),
                activeShape.getActivePointsPressed().getXpoint()};

        this.yInts = new int[]{
                activeShape.getActivePointsPressed().getYpoint(),
                activeShape.getActivePointsReleased().getYpoint(),
                activeShape.getActivePointsReleased().getYpoint()};
    }

    @Override
    public void draw() {

        switch (activeShape.getActiveShapeShadingType()) {

            case FILLED_IN:

                graphics2d.setColor(ShapeColorMap.get(activeShape.getActivePrimaryColor()));
                graphics2d.fillPolygon(xInts, yInts, 3);

                break;

            case OUTLINE:

                graphics2d.setColor(ShapeColorMap.get(activeShape.getActivePrimaryColor()));
                graphics2d.setStroke(new BasicStroke(5));
                graphics2d.drawPolygon(xInts, yInts, 3);

                break;

            case OUTLINE_AND_FILLED_IN:

                graphics2d.setColor(ShapeColorMap.get(activeShape.getActivePrimaryColor()));
                graphics2d.fillPolygon(xInts, yInts, 3);
                graphics2d.setStroke(new BasicStroke(5));
                graphics2d.setColor(ShapeColorMap.get(activeShape.getActiveSecondaryColor()));
                graphics2d.drawPolygon(xInts, yInts, 3);

                break;
        }


    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
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
        xInts[index] = newVal;
    }

    @Override
    public void setYarr(int index, int newVal) {
        yInts[index] = newVal;
    }

    @Override
    public int getXarrIndex(int index) {
        return xInts[index];
    }

    @Override
    public int getYarrIndex(int index) {
        return yInts[index];
    }

    @Override
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public ShapeConfiguration getActiveShapeConfiguration() {
        return this.activeShape;
    }


}

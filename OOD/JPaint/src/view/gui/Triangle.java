package view.gui;

import view.interfaces.IShape;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class Triangle implements IShape {

    private ShapeConfiguration activeShape;
    Graphics2D graphics2d;


    public Triangle(PaintCanvas canvas, ShapeConfiguration activeShape) {

        this.activeShape = activeShape;
        this.graphics2d = canvas.getGraphics2D();
    }

    @Override
    public void draw() {
        int[] xInts = new int[]{
                activeShape.getActivePointsPressed().getXpoint(),
                activeShape.getActivePointsReleased().getXpoint(),
                activeShape.getActivePointsPressed().getXpoint()};

        int[] yInts = new int[]{
                activeShape.getActivePointsPressed().getYpoint(),
                activeShape.getActivePointsReleased().getYpoint(),
                activeShape.getActivePointsReleased().getYpoint()};

        graphics2d.setColor(ShapeColorMap.get(activeShape.getActivePrimaryColor()));
        graphics2d.setStroke(new BasicStroke(5));
        graphics2d.setColor(ShapeColorMap.get(activeShape.getActiveSecondaryColor()));
        graphics2d.fillPolygon(xInts, yInts, 3);

    }
}

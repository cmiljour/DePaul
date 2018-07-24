package view.gui;

import model.ShapeType;
import model.persistence.ApplicationState;
import view.interfaces.ICommand;

import java.awt.*;

public class DrawCommand implements ICommand {
    private PaintCanvas canvas;
    private ShapeConfiguration activeShape;

    public DrawCommand(PaintCanvas canvas, ShapeConfiguration activeShape){
        this.canvas = canvas;
        this.activeShape = activeShape;
    }

    @Override
    public void run() {
        Graphics2D graphics2D = canvas.getGraphics2D();

        switch (activeShape.getActiveShapeType()) {
            case TRIANGLE:
                int[] xInts = new int[]{
                        activeShape.getActivePointsPressed().getXpoint(),
                        activeShape.getActivePointsReleased().getXpoint(),
                        activeShape.getActivePointsPressed().getXpoint() + activeShape.getActivePointsReleased().getXpoint() / 2};
                int[] yInts = new int[]{
                        activeShape.getActivePointsPressed().getYpoint(),
                        activeShape.getActivePointsPressed().getYpoint(),
                        activeShape.getActivePointsReleased().getYpoint()};
                graphics2D.fillPolygon(xInts, yInts, 3);

                break;

            case ELLIPSE:

                break;

            case RECTANGLE:

                graphics2D.drawRect(10, 15, 90, 60);

                break;
        }

    }
}

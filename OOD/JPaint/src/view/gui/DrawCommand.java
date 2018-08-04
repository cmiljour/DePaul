package view.gui;

import model.ShapeColor;
import model.ShapeType;
import model.persistence.ApplicationState;
import view.interfaces.ICommand;

import java.awt.*;

public class DrawCommand implements ICommand {

    private PaintCanvas canvas;
    private ShapeConfiguration activeShape;
    private int x, y, width, height;


    public DrawCommand(PaintCanvas canvas, ShapeConfiguration activeShape){
        this.canvas = canvas;
        this.activeShape = activeShape;
        this.x = Math.min(activeShape.getActivePointsPressed().getXpoint(), activeShape.getActivePointsReleased().getXpoint());
        this.y = Math.min(activeShape.getActivePointsPressed().getYpoint(), activeShape.getActivePointsReleased().getYpoint());
        this.width = Math.abs(activeShape.getActivePointsPressed().getXpoint() - activeShape.getActivePointsReleased().getXpoint());
        this.height = Math.abs(activeShape.getActivePointsPressed().getYpoint() - activeShape.getActivePointsReleased().getYpoint());

    }


    @Override
    public void draw() {
        Graphics2D graphics2D = canvas.getGraphics2D();

        switch (activeShape.getActiveShapeType()) {
            case TRIANGLE:

                Triangle triangle = new Triangle(canvas, activeShape);
                triangle.draw();

                break;

            case ELLIPSE:

                Ellipse ellipse = new Ellipse(x, y, width, height, canvas, activeShape);
                ellipse.draw();

                break;

            case RECTANGLE:


                Rectangle rectangle = new Rectangle(x, y, width, height, canvas, activeShape);
                rectangle.draw();

                break;
        }

    }
}

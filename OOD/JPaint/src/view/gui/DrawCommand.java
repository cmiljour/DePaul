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
    ShapeList shapeList;


    public DrawCommand(ShapeList shapeList, PaintCanvas canvas, ShapeConfiguration activeShape){
        this.canvas = canvas;
        this.activeShape = activeShape;
        this.x = Math.min(activeShape.getActivePointsPressed().getXpoint(), activeShape.getActivePointsReleased().getXpoint());
        this.y = Math.min(activeShape.getActivePointsPressed().getYpoint(), activeShape.getActivePointsReleased().getYpoint());
        this.width = Math.abs(activeShape.getActivePointsPressed().getXpoint() - activeShape.getActivePointsReleased().getXpoint());
        this.height = Math.abs(activeShape.getActivePointsPressed().getYpoint() - activeShape.getActivePointsReleased().getYpoint());
        this.shapeList = shapeList;
    }


    @Override
    public void run() {
        Graphics2D graphics2D = canvas.getGraphics2D();

        switch (activeShape.getActiveShapeType()) {
            case TRIANGLE:

                Triangle triangle = new Triangle(x, y, width, height, canvas, activeShape);
                triangle.draw();
                shapeList.add(triangle);
                break;

            case ELLIPSE:

                Ellipse ellipse = new Ellipse(x, y, width, height, canvas, activeShape);
                ellipse.draw();
                shapeList.add(ellipse);
                break;

            case RECTANGLE:

                MyRectangle rectangle = new MyRectangle(x, y, width, height, canvas, activeShape);
                rectangle.draw();
                shapeList.add(rectangle);
                break;
        }

    }
}

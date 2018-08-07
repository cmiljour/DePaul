package view.gui;

import model.persistence.ApplicationState;
import view.interfaces.ICommand;
import view.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SelectCommand implements ICommand {
    ShapeList shapeList;
    SelectedShapeList selectedShapeList;
    PaintCanvas canvas;
    ShapeConfiguration activeShape;
    private int x, y, width, height;
    Point pointsPressed;
    Point pointsReleased;
    ArrayList<IShape> secondList;
    ArrayList<IShape> arraySelectedList;
    ApplicationState appState;



    public SelectCommand(Point pointsPressed, Point pointsReleased, ShapeList shapeList, SelectedShapeList selectedShapeList, PaintCanvas canvas, ShapeConfiguration activeShape, ApplicationState appState) {
        this.shapeList = shapeList;
        this.canvas = canvas;
        this.activeShape = activeShape;
        this.x = Math.min(activeShape.getActivePointsPressed().getXpoint(), activeShape.getActivePointsReleased().getXpoint());
        this.y = Math.min(activeShape.getActivePointsPressed().getYpoint(), activeShape.getActivePointsReleased().getYpoint());
        this.width = Math.abs(activeShape.getActivePointsPressed().getXpoint() - activeShape.getActivePointsReleased().getXpoint());
        this.height = Math.abs(activeShape.getActivePointsPressed().getYpoint() - activeShape.getActivePointsReleased().getYpoint());
        this.pointsPressed = pointsPressed;
        this.pointsReleased = pointsReleased;
        this.secondList = shapeList.getShapeList();
        this.selectedShapeList = selectedShapeList;
        this.arraySelectedList = selectedShapeList.getShapeList();
        this.appState = appState;
    }


    @Override
    public void run() {

        Boolean emptyPoint = true;
        Rectangle selectorRectangle = new Rectangle(x,y, width, height);
        Rectangle rectangle;

        for (IShape shape : secondList) {
            rectangle = shape.getRectangle();

            if (selectorRectangle.contains(shape.getX(),shape.getY()) && !(arraySelectedList.contains(shape))) {
                selectedShapeList.add(shape);
                emptyPoint = false;
            }

            if (rectangle.contains(x, y) && !(arraySelectedList.contains(shape))) {
                selectedShapeList.add(shape);
                emptyPoint = false;
            }
        }

        if (emptyPoint){
            arraySelectedList.clear();
        }

        selectedShapeList.printList();

    }
}

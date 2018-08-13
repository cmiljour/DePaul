package view.gui;

import model.persistence.ApplicationState;
import view.interfaces.ICommand;
import view.interfaces.IShape;
import java.awt.*;
import java.util.ArrayList;

public class SelectCommand implements ICommand {
    private ShapeList shapeList;
    private SelectedShapeList selectedShapeList;
    private ShapeConfiguration activeShape;
    private int x, y, width, height;
    private Point pointsPressed;
    private Point pointsReleased;
    private ArrayList<IShape> secondList;
    private ArrayList<IShape> arraySelectedList;
    private ApplicationState appState;

    public SelectCommand(ApplicationState appState, ShapeConfiguration activeShape) {
        this.activeShape = activeShape;
        this.x = Math.min(activeShape.getActivePointsPressed().getXpoint(), activeShape.getActivePointsReleased().getXpoint());
        this.y = Math.min(activeShape.getActivePointsPressed().getYpoint(), activeShape.getActivePointsReleased().getYpoint());
        this.width = Math.abs(activeShape.getActivePointsPressed().getXpoint() - activeShape.getActivePointsReleased().getXpoint());
        this.height = Math.abs(activeShape.getActivePointsPressed().getYpoint() - activeShape.getActivePointsReleased().getYpoint());
        this.appState = appState;
        this.selectedShapeList = appState.getSelectedShapeList();
        this.shapeList = appState.getShapeList();
        this.pointsPressed = activeShape.getActivePointsPressed();
        this.pointsReleased = activeShape.getActivePointsReleased();
        this.arraySelectedList = selectedShapeList.getShapeList();
        this.secondList = shapeList.getShapeList();
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

            if (selectorRectangle.contains(shape.getX(),shape.getY()) && arraySelectedList.contains(shape)) {
                emptyPoint = false;
            }

            if (rectangle.contains(x, y) && !(arraySelectedList.contains(shape))) {
                selectedShapeList.add(shape);
                emptyPoint = false;
            }

            if (rectangle.contains(x, y) && arraySelectedList.contains(shape)) {
                emptyPoint = false;
            }
        }

        if (emptyPoint){
            arraySelectedList.clear();
        }

        System.out.println("selectedShapeList:");
        selectedShapeList.printList();

        System.out.println("shapeList");
        shapeList.printList();
    }
}

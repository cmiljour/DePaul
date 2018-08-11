package view.gui;

import model.persistence.ApplicationState;
import view.interfaces.ICommand;
import view.interfaces.IShape;
import view.interfaces.IUndoable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import static model.ShapeType.TRIANGLE;

public class MoveCommand implements ICommand, IUndoable {

    ShapeList shapeList;
    ArrayList<IShape> arrayList;
    SelectedShapeList selectedShapeList;
    PaintCanvas canvas;
    ShapeConfiguration activeShape;
    Point pointsPressed;
    Point pointsReleased;
    ArrayList<IShape> arraySelectedList;
    ApplicationState appState;
    Graphics2D graphics2D;
    ArrayList<IShape> cloneShapeArrayList;
    private int width;
    private int height;

    public MoveCommand(ApplicationState appState, ShapeConfiguration activeShape) {
        this.activeShape = activeShape;
        this.appState = appState;
        this.selectedShapeList = appState.getSelectedShapeList();
        this.shapeList = appState.getShapeList();
        this.pointsPressed = activeShape.getActivePointsPressed();
        this.pointsReleased = activeShape.getActivePointsReleased();
        this.arraySelectedList = selectedShapeList.getShapeList();
        this.arrayList = shapeList.getShapeList();
        this.graphics2D = PaintCanvas.getCanvasInstance().getGraphics2D();
        this.cloneShapeArrayList = new ArrayList<IShape>();
        this.width = Math.abs(activeShape.getActivePointsPressed().getXpoint() - activeShape.getActivePointsReleased().getXpoint());
        this.height = Math.abs(activeShape.getActivePointsPressed().getYpoint() - activeShape.getActivePointsReleased().getYpoint());
    }

    @Override
    public void run() {

        int xDiff;
        int yDiff;
        int xDiff0;
        int yDiff0;
        int xDiff1;
        int yDiff1;
        int xDiff2;
        int yDiff2;

        for (IShape shape : arraySelectedList)
            if (shape.getActiveShape() == TRIANGLE) {
                xDiff0 = shape.getXarrIndex(0) - pointsPressed.getXpoint();
                xDiff1 = shape.getXarrIndex(1) - pointsPressed.getXpoint();
                xDiff2 = shape.getXarrIndex(2) - pointsPressed.getXpoint();
                yDiff0 = shape.getYarrIndex(0) - pointsPressed.getYpoint();
                yDiff1 = shape.getYarrIndex(1) - pointsPressed.getYpoint();
                yDiff2 = shape.getYarrIndex(2) - pointsPressed.getYpoint();

                shape.setXarr(0, pointsReleased.getXpoint() + xDiff0);
                shape.setXarr(1, pointsReleased.getXpoint() + xDiff1);
                shape.setXarr(2, pointsReleased.getXpoint() + xDiff2);
                shape.setYarr(0, pointsReleased.getYpoint() + yDiff0);
                shape.setYarr(1, pointsReleased.getYpoint() + yDiff1);
                shape.setYarr(2, pointsReleased.getYpoint() + yDiff2);

                shape.setRectangle(new Rectangle (pointsReleased.getXpoint() + xDiff0,  pointsReleased.getYpoint() + yDiff0, shape.getWidth(), shape.getHeight() ));

            } else {

                IShape newShape = IShapeFactory.getShape(shape.getActiveShape().toString(), shape.getActiveShapeConfiguration());

                shapeList.remove(shape);

//                xDiff = shape.getX() - pointsReleased.getXpoint();
//                yDiff = shape.getY() - pointsReleased.getYpoint();
//                newShape.setX(pointsReleased.getXpoint() + xDiff);
//                newShape.setY(pointsReleased.getYpoint() + yDiff);
//                newShape.setRectangle(new Rectangle(pointsReleased.getXpoint() + xDiff, pointsReleased.getYpoint() + yDiff, newShape.getWidth(), newShape.getHeight() ));
//                shapeList.add(newShape);


                xDiff = newShape.getX() - pointsPressed.getXpoint();
                yDiff = newShape.getY() - pointsPressed.getYpoint();
                newShape.setX(pointsReleased.getXpoint() + xDiff);
                newShape.setY(pointsReleased.getYpoint() + yDiff);
                newShape.setRectangle(new Rectangle(pointsReleased.getXpoint() + xDiff, pointsReleased.getYpoint() + yDiff, shape.getWidth(), shape.getHeight() ));
                shapeList.add(newShape);
            }

        graphics2D.setColor(Color.white);
        graphics2D.fillRect(0,0,100000,100000);

        appState.getShapeListReDrawCommandHandler().handleShapeListModification();
    }

    @Override
    public void undo() {

        Graphics2D graphics2D = PaintCanvas.getCanvasInstance().getGraphics2D();
        graphics2D.clearRect(0,0,10000,10000);
        shapeList.getShapeList().clear();



        for (IShape shape : cloneShapeArrayList){
            System.out.println(shape);
            shapeList.getShapeList().add(shape);
        }


        appState.getShapeListReDrawCommandHandler().handleShapeListModification();

        
    }

    @Override
    public void redo() {

    }
}

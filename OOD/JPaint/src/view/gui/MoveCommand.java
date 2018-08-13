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
    IShape newShape;

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
        this.cloneShapeArrayList = appState.getClonedShapeArrayList();
    }

    @Override
    public void run() {
        for (IShape shape : shapeList.getShapeList()){
            newShape = IShapeFactory.getShape(shape.getActiveShape().toString(), shape.getActiveShapeConfiguration());
            if(!(cloneShapeArrayList.contains(shape))){
                cloneShapeArrayList.add(newShape);
            }
        }

        for (IShape shape : arraySelectedList) {
            MoveShape.move(shape, activeShape);
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
            shapeList.add(shape);
        }

        cloneShapeArrayList.clear();
        appState.getShapeListReDrawCommandHandler().handleShapeListModification();
    }

    @Override
    public void redo() {

    }
}

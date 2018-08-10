package view.gui;

import model.persistence.ApplicationState;
import view.interfaces.ICommand;
import view.interfaces.IShape;
import view.interfaces.IUndoable;

import java.awt.*;

public class DrawCommand implements ICommand, IUndoable {

    private ShapeConfiguration activeShape;
    private ShapeList shapeList;
    private IShape shape;
    private String shapeType;
    private ApplicationState appState;

    public DrawCommand(ApplicationState appState, ShapeConfiguration activeShape){

        this.activeShape = activeShape;
        this.shapeList = appState.getShapeList();
        this.appState = appState;
        this.shapeType = activeShape.getActiveShapeType().toString();

    }

    @Override
    public void run() {

        shape = IShapeFactory.getShape(shapeType, activeShape);
        shape.draw();
        shapeList.add(shape);

    }

    @Override
    public void undo() {
        shapeList.remove(shape);
        Graphics2D graphics2D = PaintCanvas.getCanvasInstance().getGraphics2D();
        graphics2D.clearRect(0,0,10000,10000);
        appState.getShapeListReDrawCommandHandler().handleShapeListModification();
    }

    @Override
    public void redo() {
        shape = IShapeFactory.getShape(shapeType, activeShape);
        shape.draw();
        shapeList.add(shape);
    }
}

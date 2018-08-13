package view.gui;

import view.interfaces.ICommand;
import view.interfaces.IShape;
import view.interfaces.IUndoable;
import java.awt.*;
import java.util.ArrayList;

public class CopyShapeCommand implements IUndoable,ICommand {
    private ShapeConfiguration activeShape;
    ShapeList shapeList;
    IShape shape;
    ArrayList<IShape> shapeArrayList;
    ArrayList<IShape> cloneShapeArrayList;

    public CopyShapeCommand(IShape shape, ShapeList shapeList, ShapeConfiguration activeShapeConfiguration){
        this.shape = shape;
        this.shapeList = shapeList;
        this.activeShape = activeShapeConfiguration;
        this.shapeArrayList = shapeList.getShapeList();
        this.cloneShapeArrayList = new ArrayList<IShape>();
    }

    @Override
    public void run() {
        for(IShape shape : shapeArrayList){
            cloneShapeArrayList.add(shape);
        }

        IShape copiedShape = CopyShape.copyShape(shape, activeShape);
        shapeList.add(copiedShape);
    }

    @Override
    public void undo() {
        Graphics2D graphics2D = PaintCanvas.getCanvasInstance().getGraphics2D();
        graphics2D.clearRect(0,0,10000,10000);
        shapeList.getShapeList().clear();

        for (IShape shape : cloneShapeArrayList){
            shapeList.add(shape);
            shape.draw();
        }
    }

    @Override
    public void redo() {

    }
}

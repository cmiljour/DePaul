package view.gui;


import view.interfaces.ICommand;
import view.interfaces.IShape;
import view.interfaces.IUndoable;
import java.awt.*;
import java.util.ArrayList;

public class DeleteShapeCommand implements ICommand, IUndoable {
    ShapeList shapeList;
    SelectedShapeList selectedShapeList;
    ArrayList<IShape> cloneShapeArrayList;
    ShapeListReDrawCommandHandler shapeListReDrawCommandHandler;

    public DeleteShapeCommand(ShapeList shapeList, SelectedShapeList selectedShapeList,
                              ShapeListReDrawCommandHandler shapeListReDrawCommandHandler){
        this.selectedShapeList = selectedShapeList;
        this.shapeList = shapeList;
        this.cloneShapeArrayList = new ArrayList<>();
        this.shapeListReDrawCommandHandler = shapeListReDrawCommandHandler;
    }

    @Override
    public void run(){
        for(IShape shape : shapeList.getShapeList()){
            cloneShapeArrayList.add(shape);
        }

        for (IShape shape : selectedShapeList.getShapeList() ){
            shapeList.getShapeList().remove(shape);
        }

        selectedShapeList.getShapeList().clear();
        Graphics2D graphics2D = PaintCanvas.getCanvasInstance().getGraphics2D();
        graphics2D.clearRect(0,0,10000,10000);
        shapeListReDrawCommandHandler.handleShapeListModification();
    }

    @Override
    public void undo() {
        Graphics2D graphics2D = PaintCanvas.getCanvasInstance().getGraphics2D();
        graphics2D.clearRect(0,0,10000,10000);
        shapeList.getShapeList().clear();

        for (IShape shape : cloneShapeArrayList){
            shapeList.getShapeList().add(shape);
        }

        shapeListReDrawCommandHandler.handleShapeListModification();
    }

    @Override
    public void redo() {

    }
}

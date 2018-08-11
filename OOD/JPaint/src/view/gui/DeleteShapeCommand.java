package view.gui;

import model.interfaces.IApplicationState;
import model.persistence.ApplicationState;
import view.interfaces.ICommand;
import view.interfaces.IShape;
import view.interfaces.IUndoable;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class DeleteShapeCommand implements ICommand, IUndoable {
    IApplicationState appState;
    ArrayList<IShape> cloneShapeArrayList;

    public DeleteShapeCommand(IApplicationState appState){
        this.appState = appState;
        this.cloneShapeArrayList = new ArrayList<>();
    }

    @Override
    public void run() throws IOException {
        for(IShape shape : appState.getSelectedShapeList().getShapeList()){
            cloneShapeArrayList.add(shape);
        }

        for (IShape shape : appState.getSelectedShapeList().getShapeList() ){
            appState.getShapeList().remove(shape);
        }

        appState.getSelectedShapeList().getShapeList().clear();
        Graphics2D graphics2D = PaintCanvas.getCanvasInstance().getGraphics2D();
        graphics2D.clearRect(0,0,10000,10000);
        appState.getShapeListReDrawCommandHandler().handleShapeListModification();
    }

    @Override
    public void undo() {
        Graphics2D graphics2D = PaintCanvas.getCanvasInstance().getGraphics2D();
        graphics2D.clearRect(0,0,10000,10000);
        appState.getShapeList().getShapeList().clear();

        for (IShape shape : cloneShapeArrayList){
            appState.getShapeList().add(shape);
            shape.draw();
        }

    }

    @Override
    public void redo() {

    }
}

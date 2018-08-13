package view.gui;

import model.interfaces.IApplicationState;
import model.persistence.ApplicationState;
import view.interfaces.IShape;

public class ShapeCommand {
    ShapeList shapeList;
    SelectedShapeList selectedShapeList;
    CopyShapeList copyShapeList;
    ApplicationState appState;

    public ShapeCommand(ApplicationState appState){
        this.shapeList = appState.getShapeList();
        this.selectedShapeList = appState.getSelectedShapeList();
        this.copyShapeList = appState.getCopyShapeList();
        this.appState = appState;
    }

    public void modShape(String modCommand) {
        if(modCommand.equalsIgnoreCase("COPY")){
            for (IShape shape : shapeList.getShapeList()){
                copyShapeList.add(shape);
            }

        } else if(modCommand.equalsIgnoreCase("PASTE")){
            CopyShapeCommand command = null;

            for (IShape shape : copyShapeList.getShapeList()) {

                command = new CopyShapeCommand(shape, shapeList, shape.getActiveShapeConfiguration());
                CommandHistory.add(command);
                command.run();
            }

            appState.getShapeListReDrawCommandHandler().handleShapeListModification();

        } else if(modCommand.equalsIgnoreCase("DELETE")){
            DeleteShapeCommand deleteShapeCommand = new DeleteShapeCommand(shapeList, selectedShapeList,appState.getShapeListReDrawCommandHandler());
            CommandHistory.add(deleteShapeCommand);
            deleteShapeCommand.run();
        }
    }
}

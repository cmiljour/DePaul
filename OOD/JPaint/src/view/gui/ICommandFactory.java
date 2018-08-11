package view.gui;

import model.persistence.ApplicationState;
import view.interfaces.ICommand;


public class ICommandFactory {

    private ICommandFactory(){}

    public static ICommand getCommandType(String commandType, ApplicationState appState, ShapeConfiguration activeShape) {
        if (commandType == null) {
            return null;
        }
        if (commandType.equalsIgnoreCase("SELECT")) {
            return new SelectCommand(appState, activeShape);

        } else if (commandType.equalsIgnoreCase("DRAW")) {
            DrawCommand drawCommand = new DrawCommand(appState,activeShape);
            CommandHistory.add(drawCommand);
            return drawCommand;

        } else if (commandType.equalsIgnoreCase("MOVE")) {
            MoveCommand moveCommand = new MoveCommand(appState, activeShape);
            CommandHistory.add(moveCommand);
            return new MoveCommand(appState, activeShape);
        }

        return null;
    }
}

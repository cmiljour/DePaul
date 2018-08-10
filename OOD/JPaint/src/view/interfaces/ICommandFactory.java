package view.interfaces;

import model.persistence.ApplicationState;
import view.gui.DrawCommand;
import view.gui.MoveCommand;
import view.gui.SelectCommand;
import view.gui.ShapeConfiguration;
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
            return new DrawCommand(appState, activeShape);

        } else if (commandType.equalsIgnoreCase("MOVE")) {
            return new MoveCommand(appState, activeShape);
        }

        return null;
    }
}

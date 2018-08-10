package view.gui;

import model.persistence.ApplicationState;
import view.interfaces.ICommand;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;



public class MouseHandle extends MouseAdapter {

    Point pointsPressed;
    Point pointsReleased;
    ApplicationState appState;
    ShapeListReDrawCommandHandler shapeListReDrawCommandHandler;
    ShapeConfiguration activeShape;
    ICommand command = null;
    String commandType;

    public MouseHandle(ApplicationState appState){

        this.appState = appState;
        this.shapeListReDrawCommandHandler = appState.getShapeListReDrawCommandHandler();

    }

    public void mousePressed(MouseEvent e){

        this.activeShape = appState.getCurrentShapeConfiguration();
        pointsPressed = new Point(e.getX(), e.getY());
        activeShape.setActivePointsPressed(pointsPressed);

    }

    public void mouseReleased(MouseEvent e) {

        pointsReleased = new Point(e.getX(), e.getY());
        activeShape.setActivePointsReleased(pointsReleased);
        commandType = appState.getActiveStartAndEndPointMode().toString();
        command = ICommandFactory.getCommandType(commandType,appState,activeShape);
        try {
            command.run();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }
}

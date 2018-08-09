package view.gui;

import com.sun.org.apache.xalan.internal.xsltc.dom.ArrayNodeListIterator;
import model.StartAndEndPointMode;
import model.persistence.ApplicationState;
import view.interfaces.ICommand;
import view.interfaces.IShape;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class MouseHandle extends MouseAdapter {
    Point pointsPressed;
    Point pointsReleased;
    ApplicationState appState;
    PaintCanvas canvas;
    //ShapeList shapeList;
    //SelectedShapeList selectedShapeList;
    CopyShapeList copyShapeList;
    ShapeListReDrawCommandHandler shapeListReDrawCommandHandler;
    ShapeConfiguration activeShape;
    ICommand command = null;


    public MouseHandle(ApplicationState appState){
        this.appState = appState;
        this.shapeListReDrawCommandHandler = appState.getShapeListReDrawCommandHandler();
        //shapeList = new ShapeList();
        //selectedShapeList = new SelectedShapeList();
        //appState.setShapeList(shapeList);
        //appState.setSelectedShapeList(selectedShapeList);
        //appState.setCanvas(canvas);
        //appState.setDrawShape(new DrawShape(shapeList));
        //copyShapeList = appState.getCopyShapeList();
        //shapeListReDrawCommandHandler.registerObserver(appState.getDrawShape());
    }

    public void mousePressed(MouseEvent e){
        this.activeShape = appState.getCurrentShapeConfiguration();
        pointsPressed = new Point(e.getX(), e.getY());
        activeShape.setActivePointsPressed(pointsPressed);

    }


    public void mouseReleased(MouseEvent e) {
        pointsReleased = new Point(e.getX(), e.getY());
        activeShape.setActivePointsReleased(pointsReleased);

        switch (appState.getActiveStartAndEndPointMode()) {

            case DRAW:
                command = new DrawCommand(appState, activeShape);
                break;

            case MOVE:
                command = new MoveCommand(appState, activeShape);
                break;

            case SELECT:
                command = new SelectCommand(appState, activeShape);
                break;
        }

        try {
            command.run();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}

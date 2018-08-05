package view.gui;

import com.sun.org.apache.xalan.internal.xsltc.dom.ArrayNodeListIterator;
import model.StartAndEndPointMode;
import model.persistence.ApplicationState;
import view.interfaces.ICommand;
import view.interfaces.IShape;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;


public class MouseHandle extends MouseAdapter {
    Point pointsPressed;
    Point pointsReleased;
    ApplicationState appState;
    PaintCanvas canvas;
    ShapeList shapeList;
    SelectedShapeList selectedShapeList;


    public MouseHandle(ApplicationState appState, PaintCanvas canvas){
        this.appState = appState;
        this.canvas = canvas;
        shapeList = new ShapeList();
        selectedShapeList = new SelectedShapeList();
    }

    public void mousePressed(MouseEvent e){
        pointsPressed = new Point(e.getX(), e.getY());
        //System.out.println(Arrays.toString(pointsPressed.getPointList().toArray()));
    }


    public void mouseReleased(MouseEvent e) {
        pointsReleased = new Point(e.getX(), e.getY());
        //System.out.println(Arrays.toString(pointsReleased.getPointList().toArray()));
        ICommand command = null;
        ShapeConfiguration activeShape = appState.getCurrentShapeConfiguration();
        activeShape.setActivePointsPressed(pointsPressed);
        activeShape.setActivePointsReleased(pointsReleased);


        switch (appState.getActiveStartAndEndPointMode()) {

            case DRAW:
                command = new DrawCommand(shapeList, canvas, activeShape);
                break;

            case MOVE:
                command = new MoveCommand(pointsPressed, pointsReleased, shapeList, selectedShapeList, canvas, activeShape);
                break;

            case SELECT:
                command = new SelectCommand(pointsPressed, pointsReleased, shapeList, selectedShapeList, canvas, activeShape);
                break;
        }

        command.run();

    }
}

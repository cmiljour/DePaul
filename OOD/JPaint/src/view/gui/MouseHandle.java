package view.gui;

import model.StartAndEndPointMode;
import model.persistence.ApplicationState;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class MouseHandle extends MouseAdapter {
    Point pointsPressed;
    Point pointsReleased;
    ApplicationState appState;
    StartAndEndPointMode mode;

    public MouseHandle(ApplicationState appState){
        this.appState = appState;
    }

    public void mousePressed(MouseEvent e){

        pointsPressed = new Point(e.getX(), e.getY());
        System.out.println(Arrays.toString(pointsPressed.getPointList().toArray()));
    }


    public void mouseReleased(MouseEvent e){
        pointsReleased = new Point(e.getX(), e.getY());
        System.out.println(Arrays.toString(pointsReleased.getPointList().toArray()));
        mode = appState.getActiveStartAndEndPointMode();


    }


}

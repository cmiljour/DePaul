package view.gui;

import model.StartAndEndPointMode;
import model.persistence.ApplicationState;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;


public class MouseHandle extends MouseAdapter {
    Point pointsPressed;
    Point pointsReleased;
    ApplicationState appState;
    StartAndEndPointMode mode;
    PaintCanvas canvas;

    public MouseHandle(ApplicationState appState, PaintCanvas canvas){
        this.appState = appState;
        this.canvas = canvas;
    }

    public void mousePressed(MouseEvent e){

        pointsPressed = new Point(e.getX(), e.getY());
        System.out.println(Arrays.toString(pointsPressed.getPointList().toArray()));
    }


    public void mouseReleased(MouseEvent e) {
        pointsReleased = new Point(e.getX(), e.getY());
        System.out.println(Arrays.toString(pointsReleased.getPointList().toArray()));
        mode = appState.getActiveStartAndEndPointMode();
        switch (mode) {
            case DRAW:

                Graphics2D graphics2D = canvas.getGraphics2D();

                int[] xInts = new int[]{pointsPressed.getXpoint(), pointsReleased.getXpoint(), pointsPressed.getXpoint() + pointsReleased.getXpoint() / 2};
                int[] yInts = new int[]{pointsPressed.getYpoint(), pointsPressed.getYpoint(), pointsReleased.getYpoint()};
                graphics2D.fillPolygon(xInts, yInts, 3);

                break;

            case MOVE:

                break;

            case SELECT:

                break;
        }

    }
}

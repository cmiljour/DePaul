package view.gui;

import view.interfaces.IShape;

import java.awt.*;

public class MoveShape {
    private MoveShape(){};

    public static void move(IShape shape, ShapeConfiguration activeShape){
        int xDiff;
        int yDiff;
        int xDiff0;
        int yDiff0;
        int xDiff1;
        int yDiff1;
        int xDiff2;
        int yDiff2;

        if(shape.getActiveShape().toString().equalsIgnoreCase("TRIANGLE")){
            xDiff0 = shape.getXarrIndex(0) - activeShape.getActivePointsPressed().getXpoint();
            xDiff1 = shape.getXarrIndex(1) - activeShape.getActivePointsPressed().getXpoint();
            xDiff2 = shape.getXarrIndex(2) - activeShape.getActivePointsPressed().getXpoint();
            yDiff0 = shape.getYarrIndex(0) - activeShape.getActivePointsPressed().getYpoint();
            yDiff1 = shape.getYarrIndex(1) - activeShape.getActivePointsPressed().getYpoint();
            yDiff2 = shape.getYarrIndex(2) - activeShape.getActivePointsPressed().getYpoint();

            shape.setXarr(0, activeShape.getActivePointsReleased().getXpoint() + xDiff0);
            shape.setXarr(1, activeShape.getActivePointsReleased().getXpoint() + xDiff1);
            shape.setXarr(2, activeShape.getActivePointsReleased().getXpoint() + xDiff2);
            shape.setYarr(0, activeShape.getActivePointsReleased().getYpoint() + yDiff0);
            shape.setYarr(1, activeShape.getActivePointsReleased().getYpoint() + yDiff1);
            shape.setYarr(2, activeShape.getActivePointsReleased().getYpoint() + yDiff2);

            shape.setRectangle(new Rectangle (activeShape.getActivePointsReleased().getXpoint() + xDiff0,
                    activeShape.getActivePointsReleased().getYpoint() + yDiff0,
                    shape.getWidth(), shape.getHeight() ));

        } else {

            xDiff = shape.getX() - activeShape.getActivePointsPressed().getXpoint();
            yDiff = shape.getY() - activeShape.getActivePointsPressed().getYpoint();
            shape.setX(activeShape.getActivePointsReleased().getXpoint() + xDiff);
            shape.setY(activeShape.getActivePointsReleased().getYpoint() + yDiff);
            shape.setRectangle(new Rectangle(activeShape.getActivePointsReleased().getXpoint() + xDiff,
                    activeShape.getActivePointsReleased().getYpoint() + yDiff,
                    shape.getWidth(), shape.getHeight() ));
        }
    }
}

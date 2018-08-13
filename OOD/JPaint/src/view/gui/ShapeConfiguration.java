package view.gui;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;

public class ShapeConfiguration {

    private Point activePointsPressed;
    private Point activePointsReleased;
    private ShapeType activeShapeType;
    private ShapeColor activePrimaryColor;
    private ShapeColor activeSecondaryColor;
    private ShapeShadingType activeShapeShadingType;

    public ShapeConfiguration(ShapeType activeShapeType, ShapeColor activePrimaryColor,
                              ShapeColor activeSecondaryColor, ShapeShadingType activeShapeShadingType){
        this.activeShapeType = activeShapeType;
        this.activePrimaryColor = activePrimaryColor;
        this.activeSecondaryColor = activeSecondaryColor;
        this.activeShapeShadingType = activeShapeShadingType;
    }

    public Point getActivePointsPressed() {
        return activePointsPressed;
    }

    public Point getActivePointsReleased() {
        return activePointsReleased;
    }

    public ShapeType getActiveShapeType() {
        return activeShapeType;
    }

    public ShapeColor getActivePrimaryColor() { return activePrimaryColor; }

    public ShapeColor getActiveSecondaryColor() { return activeSecondaryColor; }

    public ShapeShadingType getActiveShapeShadingType() { return activeShapeShadingType; }

    public void setActivePointsPressed(Point activePointsPressed) {
        this.activePointsPressed = activePointsPressed;
    }

    public void setActivePointsReleased(Point activePointsReleased) {
        this.activePointsReleased = activePointsReleased;
    }
}

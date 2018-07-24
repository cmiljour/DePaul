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

    public void setShapeType(ShapeType activeShapeType){
        this.activeShapeType = activeShapeType;
    }

    public void setPrimaryShapeColor(ShapeColor activePrimaryColor){
        this.activePrimaryColor = activePrimaryColor;
    }

    public void setSetSecondaryShapeColor(ShapeColor activeSecondaryColor){
        this.activeSecondaryColor = activeSecondaryColor;
    }

    public void setActivePointsPressed(Point activePointsPressed) {
        this.activePointsPressed = activePointsPressed;
    }

    public void setActivePointsReleased(Point activePointsReleased) {
        this.activePointsReleased = activePointsReleased;
    }

    public void setActiveShapeShadingType(ShapeShadingType activeShapeShadingType) {
        this.activeShapeShadingType = activeShapeShadingType;
    }
}

package model.persistence;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.StartAndEndPointMode;
import model.dialogs.DialogProvider;
import model.interfaces.IApplicationState;
import model.interfaces.IDialogProvider;
import view.gui.*;
import view.interfaces.ICommand;
import view.interfaces.IShape;
import view.interfaces.IUiModule;

import java.awt.*;
import java.util.ArrayList;

import static model.ShapeType.TRIANGLE;

public class ApplicationState implements IApplicationState {
    private final IUiModule uiModule;
    private final IDialogProvider dialogProvider;

    private ShapeType activeShapeType;
    private ShapeColor activePrimaryColor;
    private ShapeColor activeSecondaryColor;
    private ShapeShadingType activeShapeShadingType;
    private StartAndEndPointMode activeStartAndEndPointMode;
    private SelectedShapeList selectedShapeList;
    private ShapeList shapeList;
    private PaintCanvas canvas;


    private CopyShapeList copyShapeList;


    public ApplicationState(IUiModule uiModule) {
        this.uiModule = uiModule;
        this.dialogProvider = new DialogProvider(this);
        copyShapeList = new CopyShapeList();
        setDefaults();
    }

    @Override
    public void setActiveShape() {
        activeShapeType = uiModule.getDialogResponse(dialogProvider.getChooseShapeDialog());
    }

    @Override
    public void setActivePrimaryColor() {
        activePrimaryColor = uiModule.getDialogResponse(dialogProvider.getChoosePrimaryColorDialog());
    }

    @Override
    public void setActiveSecondaryColor() {
        activeSecondaryColor = uiModule.getDialogResponse(dialogProvider.getChooseSecondaryColorDialog());
    }

    @Override
    public void setActiveShadingType() {
        activeShapeShadingType = uiModule.getDialogResponse(dialogProvider.getChooseShadingTypeDialog());
    }

    @Override
    public void setActiveStartAndEndPointMode() {
        activeStartAndEndPointMode = uiModule.getDialogResponse(dialogProvider.getChooseStartAndEndPointModeDialog());
    }

    @Override
    public ShapeType getActiveShapeType() {
        return activeShapeType;
    }

    @Override
    public ShapeColor getActivePrimaryColor() {
        return activePrimaryColor;
    }

    @Override
    public ShapeColor getActiveSecondaryColor() {
        return activeSecondaryColor;
    }

    @Override
    public ShapeShadingType getActiveShapeShadingType() {
        return activeShapeShadingType;
    }

    @Override
    public StartAndEndPointMode getActiveStartAndEndPointMode() {
        return activeStartAndEndPointMode;
    }

    public ShapeConfiguration getCurrentShapeConfiguration(){
        return new ShapeConfiguration(activeShapeType, activePrimaryColor, activeSecondaryColor, activeShapeShadingType);
    }

    public SelectedShapeList getSelectedShapeList() {
        return selectedShapeList;
    }

    public void setSelectedShapeList(SelectedShapeList selectedShapeList) {
        this.selectedShapeList = selectedShapeList;
    }

    public ShapeList getShapeList() {
        return shapeList;
    }

    public void setShapeList(ShapeList shapeList) {
        this.shapeList = shapeList;
    }

    public PaintCanvas getCanvas() {
        return canvas;
    }

    public void setCanvas(PaintCanvas canvas) {
        this.canvas = canvas;
    }

    public void setCopyShapeList(CopyShapeList copyShapeList) {
        this.copyShapeList = copyShapeList;
    }

    public void deleteShapes(){
        ArrayList<IShape> selectedArrayShapeList = getSelectedShapeList().getShapeList();
        ArrayList<IShape> shapeArrayList = getShapeList().getShapeList();

        for (IShape shape : selectedArrayShapeList){
            shapeList.remove(shape);
        }

        selectedArrayShapeList.clear();


        Graphics2D graphics2D = canvas.getGraphics2D();
        graphics2D.clearRect(0,0,10000,10000);

        for (IShape shape : shapeArrayList) {
            shape.draw();
        }
    }

    @Override
    public void copyShapes() {
        ArrayList<IShape> selectedArrayShapeList = getSelectedShapeList().getShapeList();
        for (IShape shape : selectedArrayShapeList){
            copyShapeList.add(shape);
        }
    }

    @Override
    public void pasteShapes() {
        ArrayList<IShape> copyArrayShapeList = copyShapeList.getShapeList();
        ICommand command = null;
        for (IShape shape : copyArrayShapeList) {


            if (shape.getActiveShape() == TRIANGLE) {
                int x0diffX1 = shape.getXarrIndex(1) - shape.getXarrIndex(0);
                int y0diffY1 = shape.getYarrIndex(1) - shape.getYarrIndex(0);

                shape.setXarr(0, 0);
                shape.setXarr(1, x0diffX1);
                shape.setXarr(2, 0);

                shape.setYarr(0, 0);
                shape.setYarr(1, y0diffY1);
                shape.setYarr(2, y0diffY1);

                shape.draw();
                shapeList.add(shape);

            } else {
                shapeList.add(shape);
                shape.setX(0);
                shape.setY(0);

                shape.draw();

            }
            //copyShapeList.printList();
        }

        for (IShape shape : shapeList.getShapeList()) {

            shape.draw();
            // shapeList.printList();
        }

    }
    private void setDefaults() {
        activeShapeType = ShapeType.RECTANGLE;
        activePrimaryColor = ShapeColor.BLUE;
        activeSecondaryColor = ShapeColor.GREEN;
        activeShapeShadingType = ShapeShadingType.FILLED_IN;
        activeStartAndEndPointMode = StartAndEndPointMode.DRAW;
    }

    public CopyShapeList getCopyShapeList() {
        return this.copyShapeList;
    }


}

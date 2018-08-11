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
import java.io.IOException;
import java.util.ArrayList;



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
    ShapeListReDrawCommandHandler shapeListReDrawCommandHandler;

    DrawShape drawShape;


    private CopyShapeList copyShapeList;


    public ApplicationState(IUiModule uiModule) {
        this.uiModule = uiModule;
        this.dialogProvider = new DialogProvider(this);
        this.shapeList = new ShapeList();
        this.selectedShapeList = new SelectedShapeList();
        this.copyShapeList = new CopyShapeList();
        setDefaults();
        this.shapeListReDrawCommandHandler = new ShapeListReDrawCommandHandler();
        this.drawShape = new DrawShape(shapeList);
        this.canvas = PaintCanvas.getCanvasInstance();
        shapeListReDrawCommandHandler.registerObserver(drawShape);
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

    public void setCopyShapeList(CopyShapeList copyShapeList) {
        this.copyShapeList = copyShapeList;
    }

    public ShapeListReDrawCommandHandler getShapeListReDrawCommandHandler() {
        return shapeListReDrawCommandHandler;
    }

    public void deleteShapes(){

//        for (IShape shape : getSelectedShapeList().getShapeList()){
//            shapeList.remove(shape);
//        }
//
//        getSelectedShapeList().getShapeList().clear();
//        Graphics2D graphics2D = canvas.getGraphics2D();
//        graphics2D.clearRect(0,0,10000,10000);
//
//        getShapeListReDrawCommandHandler().handleShapeListModification();
    }

    @Override
    public void copyShapes() {
        for (IShape shape : getSelectedShapeList().getShapeList()){
            copyShapeList.add(shape);
        }
    }

    @Override
    public void pasteShapes() {

        CopyShapeCommand command = null;
        for (IShape shape : copyShapeList.getShapeList()) {

            command = new CopyShapeCommand(shape, shapeList, shape.getActiveShapeConfiguration());
            CommandHistory.add(command);
            command.run();

        }

        getShapeListReDrawCommandHandler().handleShapeListModification();

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

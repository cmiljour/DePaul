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
    private ArrayList<IShape> clonedShapeArrayList;
    private ShapeListReDrawCommandHandler shapeListReDrawCommandHandler;
    private DrawShape drawShape;
    private CopyShapeList copyShapeList;


    public ApplicationState(IUiModule uiModule) {
        this.uiModule = uiModule;
        this.dialogProvider = new DialogProvider(this);
        this.shapeList = new ShapeList();
        this.selectedShapeList = new SelectedShapeList();
        this.copyShapeList = new CopyShapeList();
        this.shapeListReDrawCommandHandler = new ShapeListReDrawCommandHandler();
        this.drawShape = new DrawShape(shapeList);
        this.canvas = PaintCanvas.getCanvasInstance();
        this.clonedShapeArrayList = new ArrayList<>();
        this.shapeListReDrawCommandHandler.registerObserver(drawShape);
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

    public ShapeList getShapeList() {
        return shapeList;
    }

    public ShapeListReDrawCommandHandler getShapeListReDrawCommandHandler() {
        return shapeListReDrawCommandHandler;
    }

    public ArrayList<IShape> getClonedShapeArrayList() {
        return clonedShapeArrayList;
    }

    public CopyShapeList getCopyShapeList() {
        return copyShapeList;
    }

    private void setDefaults() {
        activeShapeType = ShapeType.RECTANGLE;
        activePrimaryColor = ShapeColor.BLUE;
        activeSecondaryColor = ShapeColor.GREEN;
        activeShapeShadingType = ShapeShadingType.FILLED_IN;
        activeStartAndEndPointMode = StartAndEndPointMode.DRAW;
    }

}

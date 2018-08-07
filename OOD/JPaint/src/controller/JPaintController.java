package controller;

import model.interfaces.IApplicationState;
import view.EventName;

import view.gui.PaintCanvas;

import view.interfaces.IShape;
import view.interfaces.IUiModule;


import java.util.ArrayList;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, () -> applicationState.setActiveStartAndEndPointMode());
        uiModule.addEvent(EventName.COPY, () -> applicationState.copyShapes());
        uiModule.addEvent(EventName.PASTE, () -> applicationState.pasteShapes());
        uiModule.addEvent(EventName.DELETE, () -> applicationState.deleteShapes());
        uiModule.addEvent(EventName.REDO, () -> System.out.println("redo button worked"));
        uiModule.addEvent(EventName.UNDO, () -> System.out.println("undo button worked"));
    }
}

package controller;

import model.interfaces.IApplicationState;
import view.EventName;

import view.gui.CommandHistory;
import view.gui.DeleteShapeCommand;
import view.gui.PaintCanvas;

import view.interfaces.ICommand;
import view.interfaces.IShape;
import view.interfaces.IUiModule;


import java.io.IOException;
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
        uiModule.addEvent(EventName.DELETE, () -> {
            try {
                DeleteShapeCommand command = new DeleteShapeCommand(applicationState);
                CommandHistory.add(command);
                command.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        uiModule.addEvent(EventName.REDO, () -> CommandHistory.redo());
        uiModule.addEvent(EventName.UNDO, () -> CommandHistory.undo());
    }
}

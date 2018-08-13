package controller;

import model.interfaces.IApplicationState;
import model.persistence.ApplicationState;
import view.EventName;
import view.gui.CommandHistory;
import view.gui.ShapeCommand;
import view.interfaces.IUiModule;


public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final ApplicationState applicationState;

    public JPaintController(IUiModule uiModule, ApplicationState applicationState) {
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
        uiModule.addEvent(EventName.COPY, () -> new ShapeCommand(applicationState).modShape("COPY"));
        uiModule.addEvent(EventName.PASTE, () -> new ShapeCommand(applicationState).modShape("PASTE"));
        uiModule.addEvent(EventName.DELETE, () -> new ShapeCommand(applicationState).modShape("DELETE"));
        uiModule.addEvent(EventName.REDO, () -> CommandHistory.redo());
        uiModule.addEvent(EventName.UNDO, () -> CommandHistory.undo());
    }
}

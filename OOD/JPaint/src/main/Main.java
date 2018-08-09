package main;

import controller.IJPaintController;
import controller.JPaintController;
import model.dialogs.DialogProvider;
import model.interfaces.IDialogProvider;
import model.persistence.ApplicationState;
import view.gui.*;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;

import java.awt.*;

public class Main {
    public static void main(String[] args){
        //PaintCanvas canvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(PaintCanvas.getCanvasInstance());
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        MouseHandle mouseHandler = new MouseHandle(appState);
        //((GuiWindow) guiWindow).addMouseListener(mouseHandler);
        PaintCanvas.getCanvasInstance().addMouseListener(mouseHandler);
        IJPaintController controller = new JPaintController(uiModule, appState);
        controller.setup();

    }
}

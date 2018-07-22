package main;

import controller.IJPaintController;
import controller.JPaintController;
import model.dialogs.DialogProvider;
import model.interfaces.IDialogProvider;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;
import view.gui.MouseHandle;

import java.awt.*;

public class Main {
    public static void main(String[] args){
        PaintCanvas canvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(canvas);

        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        MouseHandle mouseHandler = new MouseHandle(appState);
        ((GuiWindow) guiWindow).addMouseListener(mouseHandler);
        IJPaintController controller = new JPaintController(uiModule, appState);
        controller.setup();

        //Graphics2D graphics2D = canvas.getGraphics2D();

        //int[] xInts = new int[]{100, 100, 200};
        //int[] yInts = new int[]{300, 450, 450};
        //graphics2D.fillPolygon(xInts, yInts, 3);
    }
}

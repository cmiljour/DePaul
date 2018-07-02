package view.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandle extends MouseAdapter {
    public void mousePressed(MouseEvent e){
        System.out.println("mouse Pressed" + e.getX() + "," + e.getY());
    }


    public void mouseReleased(MouseEvent e){
        System.out.println("mouse Released" + e.getX() + "," + e.getY());
    }
}

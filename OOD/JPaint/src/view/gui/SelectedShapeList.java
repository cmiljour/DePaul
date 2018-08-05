package view.gui;

import view.interfaces.IShape;

import java.util.ArrayList;

public class SelectedShapeList {

    private ArrayList<IShape> selectedShapeList;

    public SelectedShapeList(){
        this.selectedShapeList = new ArrayList<IShape>();
    }

    public void add(IShape shape){
        selectedShapeList.add(shape);
    }

    public void printList(){
        selectedShapeList.forEach(System.out::println);
    }

    public ArrayList<IShape> getShapeList() {
        return selectedShapeList;
    }
}

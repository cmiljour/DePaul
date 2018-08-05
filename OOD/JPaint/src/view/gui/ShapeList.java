package view.gui;

import view.interfaces.IShape;

import java.util.ArrayList;

public class ShapeList {

    private ArrayList<IShape> shapeList;

    public ShapeList(){
        this.shapeList = new ArrayList<IShape>();
    }

    public void add(IShape shape){
        shapeList.add(shape);
    }

    public void remove(IShape shape) {shapeList.remove(shape); }

    public void printList(){
        shapeList.forEach(System.out::println);
    }

    public ArrayList<IShape> getShapeList() {
        return shapeList;
    }
}

package view.gui;

import view.interfaces.IShape;

import java.util.ArrayList;

public class CopyShapeList {

    private ArrayList<IShape> CopyShapeList;

    public CopyShapeList(){
        this.CopyShapeList = new ArrayList<IShape>();
    }

    public void add(IShape shape){
        CopyShapeList.add(shape);
    }

    public ArrayList<IShape> getShapeList() {
        return CopyShapeList;
    }
}
package view.gui;

import view.interfaces.IShape;
import view.interfaces.IShapeListReDrawObserver;

import java.util.ArrayList;

public class DrawShape implements IShapeListReDrawObserver {

    ArrayList<IShape> shapeArrayList;


    public DrawShape(ShapeList shapeList){
        this.shapeArrayList = shapeList.getShapeList();
    }

    public void draw (){
        for (IShape shape : shapeArrayList) {
            shape.draw();
        }
    }
}

package view.interfaces;

import view.gui.Ellipse;
import view.gui.MyRectangle;
import view.gui.ShapeConfiguration;
import view.gui.Triangle;

public class IShapeFactory {

    private IShapeFactory(){}

    public static IShape getShape(String shapeType, ShapeConfiguration activeShape){
        if (shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("ELLIPSE")){
            return new Ellipse(activeShape);

        } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new MyRectangle(activeShape);

        } else if(shapeType.equalsIgnoreCase("TRIANGLE")){
            return new Triangle(activeShape);
        }

        return null;
    }
}
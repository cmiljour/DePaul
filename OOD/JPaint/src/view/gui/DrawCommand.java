package view.gui;

import model.persistence.ApplicationState;
import view.interfaces.ICommand;
import view.interfaces.IShape;
import view.interfaces.IShapeFactory;

public class DrawCommand implements ICommand {

    private ShapeConfiguration activeShape;
    private ShapeList shapeList;
    private IShape shape;
    private String shapeType;

    public DrawCommand(ApplicationState appState, ShapeConfiguration activeShape){

        this.activeShape = activeShape;
        this.shapeList = appState.getShapeList();
        this.shapeType = activeShape.getActiveShapeType().toString();

    }

    @Override
    public void run() {

        shape = IShapeFactory.getShape(shapeType, activeShape);
        shape.draw();
        shapeList.add(shape);

    }
}

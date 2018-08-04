package view.gui;

import view.interfaces.IShape;

import java.awt.*;

public class Rectangle implements IShape {

    private int x, y, width, height;
    Color color;
    Graphics2D graphics2d;
    private PaintCanvas canvas;
    private ShapeConfiguration activeShape;

    public Rectangle(int x, int y, int width, int height, PaintCanvas canvas, ShapeConfiguration activeShape){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.activeShape = activeShape;
        this.canvas = canvas;
        this.graphics2d = canvas.getGraphics2D();
    }
    @Override
    public void draw() {

        switch (activeShape.getActiveShapeShadingType()) {
            case FILLED_IN:

                graphics2d.setColor(ShapeColorMap.get(activeShape.getActivePrimaryColor()));
                graphics2d.fillRect(x,y,width,height);
                graphics2d.drawRect(x,y,width,height);

                break;

            case OUTLINE:

                graphics2d.setColor(ShapeColorMap.get(activeShape.getActivePrimaryColor()));
                graphics2d.setStroke(new BasicStroke(5));
                graphics2d.drawRect(x,y,width,height);


                break;

            case OUTLINE_AND_FILLED_IN:

                graphics2d.setColor(ShapeColorMap.get(activeShape.getActivePrimaryColor()));
                graphics2d.fillRect(x,y,width,height);
                graphics2d.setStroke(new BasicStroke(5));
                graphics2d.setColor(ShapeColorMap.get(activeShape.getActiveSecondaryColor()));
                graphics2d.drawRect(x,y,width,height);


                break;
        }






    }
}

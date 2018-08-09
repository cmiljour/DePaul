package model.interfaces;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.StartAndEndPointMode;
import view.gui.CopyShapeList;
import view.gui.PaintCanvas;
import view.gui.SelectedShapeList;
import view.gui.ShapeList;

public interface IApplicationState {
    void setActiveShape();

    void setActivePrimaryColor();

    void setActiveSecondaryColor();

    void setActiveShadingType();

    void setActiveStartAndEndPointMode();

    ShapeType getActiveShapeType();

    ShapeColor getActivePrimaryColor();

    ShapeColor getActiveSecondaryColor();

    ShapeShadingType getActiveShapeShadingType();

    StartAndEndPointMode getActiveStartAndEndPointMode();

    void setSelectedShapeList(SelectedShapeList selectedShapeList);

    SelectedShapeList getSelectedShapeList();

    ShapeList getShapeList();

    void setShapeList(ShapeList shapeList);

    void deleteShapes();

    void copyShapes();

    void setCopyShapeList(CopyShapeList copyShapeList);

    CopyShapeList getCopyShapeList();

    void pasteShapes();
}

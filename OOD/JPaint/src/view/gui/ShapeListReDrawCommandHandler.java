package view.gui;

import view.interfaces.IShapeListReDrawHandler;
import view.interfaces.IShapeListReDrawObserver;
import java.util.ArrayList;
import java.util.List;

public class ShapeListReDrawCommandHandler implements IShapeListReDrawHandler {

    private List<IShapeListReDrawObserver> observers = new ArrayList<IShapeListReDrawObserver>();

    @Override
    public void registerObserver(IShapeListReDrawObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IShapeListReDrawObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void handleShapeListModification() {
        for(IShapeListReDrawObserver observer : observers) {
            observer.draw();
        }
    }
}

package view.interfaces;

public interface IShapeListReDrawHandler {
    void registerObserver(IShapeListReDrawObserver observer);
    void removeObserver(IShapeListReDrawObserver observer);
    void handleShapeListModification();
}

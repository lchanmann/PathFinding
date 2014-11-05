package ai.pathfinder.app;

import ai.pathfinder.framework.IController;
import ai.pathfinder.framework.IMainView;
import ai.pathfinder.framework.IExtendedViewModel;

public class Controller implements IController {

    private IExtendedViewModel model;

    @Override
    public void assignModel(IMainView view) {
        if (model == null) {
            model = new ViewModel();
        }
        view.setModel(model);
    }

    @Override
    public void search() {
        System.out.println("Finding ...");
    }

    @Override
    public void reset() {
        model.reset();
    }

    @Override
    public void updateNode(int x, int y) {
        model.updateNode(x, y);
    }

    @Override
    public void addWall(int x, int y) {
        model.addWall(x, y);
    }

    @Override
    public void removeWall(int x, int y) {
        model.removeWall(x, y);
    }
}

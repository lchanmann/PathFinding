package ai.pathfinder.app;

import ai.pathfinder.core.Solution;
import ai.pathfinder.framework.IController;
import ai.pathfinder.framework.IMainView;
import ai.pathfinder.framework.IExtendedViewModel;
import ai.pathfinder.utils.SearchFactory;

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
    public void search(String algorithm) {
        Solution solution = (Solution) SearchFactory.build(algorithm)
            .search(model.toProblem());
        model.setSolutionPath(solution.getPath());
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

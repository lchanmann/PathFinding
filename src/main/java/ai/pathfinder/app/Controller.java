package ai.pathfinder.app;

import ai.pathfinder.core.Solution;
import ai.pathfinder.framework.IController;
import ai.pathfinder.framework.IMainView;
import ai.pathfinder.framework.IExtendedViewModel;
import ai.pathfinder.search.Algorithm;
import ai.pathfinder.search.SearchAlgorithm;
import ai.pathfinder.search.SearchResult;

public class Controller implements IController {

    private IExtendedViewModel model;
    private Thread searchThread;

    @Override
    public void assignModel(IMainView view) {
        if (model == null) {
            model = new ViewModel();
            // NOTE: repaint the maze whenever the model changed.
            model.onStateChanged(model -> view.repaintMaze()); // NOTE: model is not being used
            model.onSearching(value -> view.searchingUI()); // NOTE: value is not being used
        }
        view.setModel(model);
    }

    @Override
    public void search(Algorithm algorithm) {
        searchThread = new Thread(new Runnable() {
            @Override
            public void run() {
                model.setSolutionPath(null);
                SearchAlgorithm search = algorithm.getSearchAlgorithm();
                search.onFrontierChanged((frontier) -> model.updateFrontier(frontier));
                search.onExploredChanged((explored) -> model.updateExplored(explored));

                model.searchingMode();
                SearchResult searchResult = search.search(model.toProblem());
                if (searchResult instanceof Solution) {
                    model.setSolutionPath(((Solution) searchResult).getPath());
                }
                model.operatingMode();
            }
        });
        searchThread.start();
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

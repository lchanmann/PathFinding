package ai.pathfinder.app;

import ai.pathfinder.framework.IController;
import ai.pathfinder.framework.IMainView;

public class Controller implements IController {

    private IMainView view;

    public Controller(IMainView view) {
        this.view = view;
    }

    @Override
    public void init() {
        view.init(this);
    }

    @Override
    public void search() {
        System.out.println("Finding ...");
    }

    @Override
    public void reset() {
        System.out.println("Reset!");
    }
}

package ai.pathfinder.app;

import ai.pathfinder.framework.IController;
import ai.pathfinder.framework.IView;

public class Controller implements IController {

    private IView view;

    public Controller(IView view) {
        this.view = view;
    }

    @Override
    public void init() {
        view.init(this);
    }

    @Override
    public void run() {
        System.out.println("Finding ...");
    }

    @Override
    public void reset() {
        System.out.println("Reset!");
    }
}

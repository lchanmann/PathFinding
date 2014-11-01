package ai.pathfinder.app;

import ai.pathfinder.core.IController;
import ai.pathfinder.core.IView;

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
    public void printHello() {
        System.out.println("Hello world!");
    }
}

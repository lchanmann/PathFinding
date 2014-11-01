package ai.pathfinder.app;

import ai.pathfinder.core.IController;
import ai.pathfinder.core.IView;

public class Application {

    private IView view;
    private IController controller;

    public Application() {
        this.view = new View();
        this.controller = new Controller(view);
    }

    public void run() {
        controller.init();
    }

    public static void main(String[] args) {
        new Application().run();
    }

}

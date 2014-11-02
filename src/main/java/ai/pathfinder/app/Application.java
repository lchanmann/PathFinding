package ai.pathfinder.app;

import ai.pathfinder.framework.IController;
import ai.pathfinder.framework.IMainView;

public class Application {

    private IMainView view;
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

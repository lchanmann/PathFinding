package ai.pathfinder.app;


import ai.pathfinder.framework.IMainView;

public class Application {

    public static void main(String[] args) {
        IMainView mainView = new MainView(new Controller());
        mainView.init();
    }
}

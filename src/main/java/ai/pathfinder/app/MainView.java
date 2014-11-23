package ai.pathfinder.app;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import ai.pathfinder.framework.IController;
import ai.pathfinder.framework.IMainView;
import ai.pathfinder.framework.IViewModel;
import ai.pathfinder.gui.Maze;
import ai.pathfinder.gui.ToolBar;
import ai.pathfinder.search.Algorithm;

public class MainView extends JFrame implements IMainView {

    private static final long serialVersionUID = 6872713207253485306L;

    private IController controller;
    private IViewModel model;

    private ToolBar toolbar;
    private Maze maze;

    public MainView(IController controller) {
        this.controller = controller;
        initializeComponent();
    }

    private void initializeComponent() {
        setTitle("Path Finder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 603);
        setLocationRelativeTo(null);

        getContentPane().setLayout(new BorderLayout());

        toolbar = new ToolBar();
        toolbar.setMainView(this);
        getContentPane().add(toolbar, BorderLayout.NORTH);
        
        maze = new Maze();
        maze.setMainView(this);
        getContentPane().add(maze, BorderLayout.CENTER);
    }

    @Override
    public void init() {
        controller.assignModel(this);
        setVisible(true);
    }

    @Override
    public void setModel(IViewModel model) {
        this.model = model;
        //NOTE: repaint the maze whenever the model changed.
        model.stateChanged(() -> {
            maze.paintImmediately(0, 0, model.getMazeWidth(), model.getMazeHeight());
        });
    }

    @Override
    public IViewModel getModel() {
        return model;
    }

    @Override
    public void startSearch(Algorithm algorithm) {
        controller.search(algorithm);
    }

    @Override
    public void resetView() {
        controller.reset();
    }

    @Override
    public void moveNode(int x, int y) {
        controller.updateNode(x, y);
    }

    @Override
    public void addWall(int x, int y) {
        controller.addWall(x, y);
    }

    @Override
    public void removeWall(int x, int y) {
        controller.removeWall(x, y);
    }
}

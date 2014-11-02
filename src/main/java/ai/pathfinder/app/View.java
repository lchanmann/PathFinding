package ai.pathfinder.app;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import ai.pathfinder.framework.IController;
import ai.pathfinder.framework.IMainView;
import ai.pathfinder.gui.Maze;
import ai.pathfinder.gui.ToolBar;

public class View extends JFrame implements IMainView {

    private static final long serialVersionUID = 6872713207253485306L;
    private IController controller;

    private ToolBar toolbar;
    private Maze maze;

    public View() {
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
        getContentPane().add(maze, BorderLayout.CENTER);
    }

    @Override
    public void init(IController controller) {
        this.controller = controller;
        setVisible(true);
    }

    @Override
    public void startSearch() {
        controller.search();
    }

    @Override
    public void resetView() {
        controller.reset();
    }
}

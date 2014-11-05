package ai.pathfinder.app;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import ai.pathfinder.framework.IStateChangedListener;
import ai.pathfinder.framework.IExtendedViewModel;

public class ViewModel implements IExtendedViewModel {

    private final int[] mazeSize = new int[] {22, 32};
    private final int gridSize = 25;
    private final Set<Point> wall = new HashSet<Point>();
    private final Point startNode = new Point(250, 250);
    private final Point goalNode = new Point(550, 250);
    private Point movingNode;

    @Override
    public void stateChanged(IStateChangedListener listener) {
        listener.notifyChanged();
    }

    @Override
    public int getGridSize() {
        return gridSize;
    }

    @Override
    public int getMazeRows() {
        return mazeSize[0];
    }

    @Override
    public int getMazeCols() {
        return mazeSize[1];
    }

    @Override
    public Set<Point> getWall() {
        return wall;
    }

    @Override
    public Point getStartNode() {
        /**
         * Instantiate new object to prevent direct update from view
         */
        return new Point(startNode.x, startNode.y);
    }

    @Override
    public Point getGoalNode() {
        return new Point(goalNode.x, goalNode.y);
    }

    @Override
    public boolean isMovableNode(int x, int y) {
        Point location = snapToGrid(x, y);
        
        if (startNode.distance(location) == 0) movingNode = startNode;
        else if (goalNode.distance(location) == 0) movingNode = goalNode;
        else movingNode = null;

        return movingNode != null;
    }

    @Override
    public void updateNode(int x, int y) {
        Point location = snapToGrid(x, y);
        
        movingNode.setLocation(location);
    }
    
    private Point snapToGrid(int x, int y) {
        return new Point(x - x % gridSize, y - y % gridSize);
    }

}

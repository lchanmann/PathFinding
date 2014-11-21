package ai.pathfinder.app;

import java.awt.Point;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ai.pathfinder.core.Problem;
import ai.pathfinder.framework.IStateChangedListener;
import ai.pathfinder.framework.IExtendedViewModel;

public class ViewModel implements IExtendedViewModel {

    private static int START_X = 250;
    private static int START_Y = 250;
    private static int GOAL_X = 550;
    private static int GOAL_Y = 250;

    private final int[] mazeSize = new int[] {22, 32};
    private final int gridSize = 25;
    private final Set<Point> wall = new HashSet<Point>();
    private final Point startNode = new Point(START_X, START_Y);
    private final Point goalNode = new Point(GOAL_X, GOAL_Y);
    private Point movingNode;

    private IStateChangedListener stateChangedListener;

    @Override
    public void stateChanged(IStateChangedListener listener) {
        this.stateChangedListener = listener;
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
    public Iterator<Point> getWall() {
        return wall.iterator();
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
    public boolean isWall(int x, int y) {
        Point location = snapToGrid(x, y);
        return wall.contains(location);
    }

    @Override
    public void updateNode(int x, int y) {
        Point location = snapToGrid(x, y);

        if (!location.equals(startNode))
            if (!location.equals(goalNode))
                if (!wall.contains(location)) {
                    movingNode.setLocation(location);
                    stateChangedListener.notifyChanged();
                }
    }

    @Override
    public void addWall(int x, int y) {
        Point location = snapToGrid(x, y);
        if (!location.equals(startNode))
            if (!location.equals(goalNode)) {
                wall.add(location);
                stateChangedListener.notifyChanged();
            }
    }

    @Override
    public void removeWall(int x, int y) {
        Point location = snapToGrid(x, y);
        wall.remove(location);
        stateChangedListener.notifyChanged();
    }

    @Override
    public void reset() {
        wall.clear();
        startNode.move(START_X, START_Y);
        goalNode.move(GOAL_X, GOAL_Y);

        stateChangedListener.notifyChanged();
    }
    
    private Point snapToGrid(int x, int y) {
        return new Point(x - x % gridSize, y - y % gridSize);
    }

    @Override
    public Problem toProblem() {
        return new Problem(startNode, goalNode);
    }

}

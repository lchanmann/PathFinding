package ai.pathfinder.app;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ai.pathfinder.core.Action;
import ai.pathfinder.core.Node;
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
    private final Set<Node> wall = new HashSet<Node>();
    private final Node startNode = new Node(START_X, START_Y);
    private final Node goalNode = new Node(GOAL_X, GOAL_Y);
    private Node movingNode;
    private Node solutionStartNode = null;
    private Action[] solutionPath = null;

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
    public Iterator<Node> getWall() {
        return wall.iterator();
    }

    @Override
    public Node getStartNode() {
        /**
         * Instantiate new object to prevent direct update from view
         */
        return new Node(startNode.getX(), startNode.getY());
    }

    @Override
    public Node getGoalNode() {
        return new Node(goalNode.getX(), goalNode.getY());
    }

    @Override
    public Action[] getSolutionPath() {
        return solutionPath;
    }

    @Override
    public Node getSolutionStartNode() {
        return solutionStartNode;
    }

    @Override
    public boolean isMovableNode(int x, int y) {
        Node node = snapToGrid(x, y);
        
        if (startNode.equals(node)) movingNode = startNode;
        else if (goalNode.equals(node)) movingNode = goalNode;
        else movingNode = null;

        return movingNode != null;
    }

    @Override
    public boolean isWall(int x, int y) {
        Node location = snapToGrid(x, y);
        return wall.contains(location);
    }

    @Override
    public void updateNode(int x, int y) {
        Node node = snapToGrid(x, y);

        if (!node.equals(startNode))
            if (!node.equals(goalNode))
                if (!wall.contains(node)) {
                    movingNode.setLocation(node);
                    stateChangedListener.notifyChanged();
                }
    }

    @Override
    public void addWall(int x, int y) {
        Node node = snapToGrid(x, y);
        if (!node.equals(startNode))
            if (!node.equals(goalNode)) {
                wall.add(node);
                stateChangedListener.notifyChanged();
            }
    }

    @Override
    public void removeWall(int x, int y) {
        Node location = snapToGrid(x, y);
        wall.remove(location);
        stateChangedListener.notifyChanged();
    }

    @Override
    public void reset() {
        wall.clear();
        startNode.setLocation(new Node(START_X, START_Y));
        goalNode.setLocation(new Node(GOAL_X, GOAL_Y));
        solutionStartNode = null;
        solutionPath = null;

        stateChangedListener.notifyChanged();
    }
    
    private Node snapToGrid(int x, int y) {
        return new Node(x - x % gridSize, y - y % gridSize);
    }

    @Override
    public Problem toProblem() {
        return new Problem(startNode, goalNode, wall, gridSize);
    }

    @Override
    public void setSolutionPath(Action[] solutionPath) {
        this.solutionStartNode = new Node(startNode.getX(), startNode.getY());
        this.solutionPath = solutionPath;
        stateChangedListener.notifyChanged();
    }

}

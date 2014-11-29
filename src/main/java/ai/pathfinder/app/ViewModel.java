package ai.pathfinder.app;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import ai.pathfinder.core.Action;
import ai.pathfinder.core.Node;
import ai.pathfinder.core.Problem;
import ai.pathfinder.framework.IExtendedViewModel;
import ai.pathfinder.framework.IViewModel;
import ai.pathfinder.utils.TimeUtils;

public class ViewModel implements IExtendedViewModel {

    private static int START_X = 250;
    private static int START_Y = 250;
    private static int GOAL_X = 550;
    private static int GOAL_Y = 250;

    private final int mazeWidth = 800;
    private final int mazeHeight = 550;
    private final int gridSize = 25;
    private final Set<Node> wall = new HashSet<Node>();
    private final Node startNode = new Node(START_X, START_Y);
    private final Node goalNode = new Node(GOAL_X, GOAL_Y);
    private Node movingNode;
    private Node solutionStartNode = null;
    private Action[] solutionPath = null;
    private List<Node> frontier = null;
    private List<Node> explored = null;
    private boolean searching = false;
    private long startTime;
    private double executionTime;

    private Consumer<IViewModel> stateChangedConsumer;
    private Consumer<Boolean> searchingConsumer;

    @Override
    public void onStateChanged(Consumer<IViewModel> consumer) {
        this.stateChangedConsumer = consumer;
    }

    @Override
    public void onSearching(Consumer<Boolean> consumer) {
        searchingConsumer = consumer;
    }

    @Override
    public int getGridSize() {
        return gridSize;
    }

    @Override
    public int getMazeWidth() {
        return mazeWidth;
    }

    @Override
    public int getMazeHeight() {
        return mazeHeight;
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
                    stateChangedConsumer.accept(this);
                }
    }

    @Override
    public void addWall(int x, int y) {
        Node node = snapToGrid(x, y);
        if (!node.equals(startNode))
            if (!node.equals(goalNode)) {
                wall.add(node);
                stateChangedConsumer.accept(this);
            }
    }

    @Override
    public void removeWall(int x, int y) {
        Node location = snapToGrid(x, y);
        wall.remove(location);
        stateChangedConsumer.accept(this);
    }

    @Override
    public void reset() {
        wall.clear();
        startNode.setLocation(new Node(START_X, START_Y));
        goalNode.setLocation(new Node(GOAL_X, GOAL_Y));
        solutionStartNode = null;
        solutionPath = null;
        frontier = null;
        explored = null;
        searching = false;
        executionTime = 0;

        stateChangedConsumer.accept(this);
    }
    
    private Node snapToGrid(int x, int y) {
        return new Node(x - x % gridSize, y - y % gridSize);
    }

    @Override
    public Problem toProblem() {
        return new Problem(mazeWidth, mazeHeight,
                startNode, goalNode, wall, gridSize);
    }

    @Override
    public void setSolutionPath(Action[] solutionPath) {
        this.solutionPath = solutionPath;
        this.solutionStartNode = new Node(startNode.getX(), startNode.getY());
        stateChangedConsumer.accept(this);
    }

    @Override
    public void updateFrontier(List<Node> frontier) {
        this.frontier = frontier;
        stateChangedConsumer.accept(this);
    }

    @Override
    public List<Node> getFrontier() {
        return frontier;
    }

    @Override
    public void updateExplored(List<Node> explored) {
        this.explored = explored;
        stateChangedConsumer.accept(this);
    }

    @Override
    public List<Node> getExplored() {
        return explored;
    }

    private void isSearching(boolean searching) {
        this.searching = searching;
        searchingConsumer.accept(searching);
    }

    @Override
    public boolean isSearching() {
        return searching;
    }

    @Override
    public int getSolutionPathLength() {
        return (solutionPath != null) ? solutionPath.length : 0;
    }

    @Override
    public double getExecutionTime() {
        return executionTime;
    }

    @Override
    public int getExpandedNodeCount() {
        return (explored != null) ? explored.size() : 0;
    }

    @Override
    public void searchingMode() {
        isSearching(true);
        startTime = System.nanoTime();
    }

    @Override
    public void operatingMode() {
        isSearching(false);
        computeExecutionTime();
        stateChangedConsumer.accept(this);
    }

    private void computeExecutionTime() {
        executionTime = TimeUtils.nanoSecondToSecond(System.nanoTime() - startTime, 4);
    }

}

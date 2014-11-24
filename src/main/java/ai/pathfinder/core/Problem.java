package ai.pathfinder.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Problem {

    private int width;
    private int height;
    private Node initialNode;
    private Node goalNode;
    private Set<Node> wall;
    private int nodeSize;
    

    public Problem(int width, int height, Node initialNode, Node goalNode,
            Set<Node> wall, int nodeSize) {
        this.width = width;
        this.height = height;
        this.initialNode = initialNode;
        this.goalNode = goalNode;
        this.wall = wall;
        this.nodeSize = nodeSize;
    }

    public Node getInitialNode() {
        return initialNode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Start: ").append(initialNode.toString()).append("\n")
          .append("Goal: ").append(goalNode.toString()).append("\n");
        return sb.toString();
    }

    /**
     * Get available actions
     * @param node
     * @return
     */
    public List<Action> getActions(Node node) {
        List<Action> actions = new ArrayList<Action>();

        for (Action action : Action.values()) {
            if (isMovable(node, action)) {
                actions.add(action);
            }
        }

        return actions;
    }

    private boolean isMovable(Node node, Action action) {
        int x = node.getX();
        int y = node.getY();

        switch (action) {
            case LEFT:
                x -= nodeSize; break;
            case DOWN:
                y += nodeSize; break;
            case RIGHT:
                x += nodeSize; break;
            case UP:
                y -= nodeSize; break;
        }
        return isInBoundary(x, y) && !wall.contains(new Node(x, y));
    }

    private boolean isInBoundary(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    /**
     * Get result for an action
     * @param node
     * @param action
     * @return
     */
    public Node getResult(Node node, Action action) {
        Node result = new Node(node.getX(), node.getY(), node, action);

        switch (action) {
            case LEFT:
                result.setX(result.getX() - nodeSize); break;
            case DOWN:
                result.setY(result.getY() + nodeSize); break;
            case RIGHT:
                result.setX(result.getX() + nodeSize); break;
            case UP:
                result.setY(result.getY() - nodeSize); break;
        }
        return result;
    }

    public boolean isGoal(Node node) {
        return goalNode.equals(node);
    }

    public Node getGoalNode() {
        return goalNode;
    }
}

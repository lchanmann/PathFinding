package ai.pathfinder.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Problem {

    private Node initialNode;
    private Node goalNode;
    private Set<Node> wall;
    private int nodeSize;
    

    public Problem(Node initialNode, Node goalNode, Set<Node> wall, int nodeSize) {
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

    public Action[] getActions(Node node) {
        List<Action> actions = new ArrayList<Action>();
        int x = node.getX();
        int y = node.getY();

        if (!wall.contains(new Node(x + nodeSize, y)))
            actions.add(Action.LEFT);
        if (!wall.contains(new Node(x, y + nodeSize)))
            actions.add(Action.DOWN);
        if (!wall.contains(new Node(x - nodeSize, y)))
            actions.add(Action.RIGHT);
        if (!wall.contains(new Node(x, y - nodeSize)))
            actions.add(Action.UP);

        return actions.toArray(new Action[] {});
    }

    public Node getResult(Node node, Action action) {
        switch (action) {
            case LEFT:
                return new Node(node.getX() + nodeSize, node.getY(), node, action);
            case DOWN:
                return new Node(node.getX(), node.getY() + nodeSize, node, action);
            case RIGHT:
                return new Node(node.getX() - nodeSize, node.getY(), node, action);
            case UP:
                return new Node(node.getX(), node.getY() - nodeSize, node, action);
            default:
                return null;
        }
    }

    public boolean isGoal(Node node) {
        return goalNode.equals(node);
    }
}

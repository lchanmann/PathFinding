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

    public List<Action> getActions(Node node) {
        List<Action> actions = new ArrayList<Action>();
        int x = node.getX();
        int y = node.getY();

        if (!wall.contains(new Node(x - nodeSize, y)))
            if (x > 0)
                actions.add(Action.LEFT);
        if (!wall.contains(new Node(x, y + nodeSize)))
            if (y + nodeSize < height)
            actions.add(Action.DOWN);
        if (!wall.contains(new Node(x + nodeSize, y)))
            if (x + nodeSize < width)
                actions.add(Action.RIGHT);
        if (!wall.contains(new Node(x, y - nodeSize)))
            if (y > 0)
                actions.add(Action.UP);

        return actions;
    }

    public Node getResult(Node node, Action action) {
        Node result = new Node(node.getX(), node.getY(), node, action);

        switch (action) {
            case LEFT:
                result.setX(result.getX() - nodeSize);
                break;
            case DOWN:
                result.setY(result.getY() + nodeSize);
                break;
            case RIGHT:
                result.setX(result.getX() + nodeSize);
                break;
            case UP:
                result.setY(result.getY() - nodeSize);
                break;
        }
        return result;
    }

    public boolean isGoal(Node node) {
        return goalNode.equals(node);
    }
}

package ai.pathfinder.search;

import ai.pathfinder.core.Node;

public class ManhattanDistanceHeuristic implements Heuristic {

    @Override
    public int evaluate(Node node, Node goal) {
        int x = Math.abs(node.getX() - goal.getX());
        int y = Math.abs(node.getY() - goal.getY());

        return x + y;
    }

}

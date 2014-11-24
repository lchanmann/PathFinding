package ai.pathfinder.search;

import ai.pathfinder.core.Node;

public interface Heuristic {

    /**
     * Evaluate the distance to goal
     * @param node
     * @param goal
     * @return
     */
    public int evaluate(Node node, Node goal);

}

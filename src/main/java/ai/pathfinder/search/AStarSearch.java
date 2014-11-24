package ai.pathfinder.search;

import ai.pathfinder.core.Action;
import ai.pathfinder.core.Failure;
import ai.pathfinder.core.Node;
import ai.pathfinder.core.Problem;
import ai.pathfinder.core.Solution;

public class AStarSearch extends SearchAlgorithm {

    private Heuristic h;
    private Problem problem;
    
    public AStarSearch() {
        this(new ManhattanDistanceHeuristic());
    }

    public AStarSearch(Heuristic h) {
        this.h = h;
        this.algorithmName = "A*";
    }

    @Override
    public SearchResult search(Problem problem) {
        this.problem = problem;
        Node node = problem.getInitialNode();
        reset();

        addFrontier(node);
        while (true) {
            if (frontier.isEmpty()) return new Failure();
            node = removeFrontier(getLowestCostNode());
            if (problem.isGoal(node)) return new Solution(node);
            addExplored(node);

            for (Action action : problem.getActions(node)) {
                Node child = problem.getResult(node, action);
                if (!explored.contains(child)) {
                    if (!frontier.contains(child)) {
                        addFrontier(child);
                    } else {
                        Node existing = frontier.get(frontier.indexOf(child));
                        if (getCost(existing) > getCost(child)){
                            removeFrontier(existing);
                            addFrontier(child);
                        }
                    }
                }
            }
        }
    }

    private Node getLowestCostNode() {
        Node bestNode = null;

        for (Node node : frontier) {
            if (bestNode == null || getCost(node) < getCost(bestNode)) {
                bestNode = node;
            }
        }
        return bestNode;
    }

    private int getCost(Node node) {
        return h.evaluate(node, problem.getGoalNode()) / problem.getNodeSize() + node.getCost();
    }

}

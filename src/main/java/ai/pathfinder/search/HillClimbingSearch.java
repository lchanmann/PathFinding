package ai.pathfinder.search;

import ai.pathfinder.core.Action;
import ai.pathfinder.core.Failure;
import ai.pathfinder.core.Node;
import ai.pathfinder.core.Problem;
import ai.pathfinder.core.Solution;

public class HillClimbingSearch extends SearchAlgorithm {

    private Heuristic h;
    private Problem problem;

    public HillClimbingSearch() {
        this(new ManhattanDistanceHeuristic());
    }

    public HillClimbingSearch(Heuristic h) {
        this.algorithmName = "Hill-Climbing Search";
        this.h = h;
    }

    @Override
    public SearchResult search(Problem problem) {
        this.problem = problem;
        Node current = problem.getInitialNode();

        reset();
        while (true) {
            Node neightbor = getHighestValueNode(current);
            addExplored(current);
            if (getValue(neightbor) <= getValue(current)) {
                break;
            }
            current = neightbor;
        }
        if (problem.isGoal(current)) return new Solution(current);
        return new Failure();
    }

    private int getValue(Node node) {
        return h.evaluate(node, problem.getGoalNode()) * -1;
    }

    private Node getHighestValueNode(Node node) {
        Node bestNode = null;

        for (Action action : problem.getActions(node)) {
            Node childNode = problem.getResult(node, action);

            addFrontier(childNode);
            if (bestNode == null || getValue(bestNode) < getValue(childNode)) {
                bestNode = childNode;
            }
            removeFrontier(childNode);
        }
        return bestNode;
    }

}

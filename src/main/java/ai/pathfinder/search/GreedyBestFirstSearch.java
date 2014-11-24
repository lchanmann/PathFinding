package ai.pathfinder.search;

import java.util.List;

import ai.pathfinder.core.Action;
import ai.pathfinder.core.Failure;
import ai.pathfinder.core.Node;
import ai.pathfinder.core.Problem;
import ai.pathfinder.core.Solution;

public class GreedyBestFirstSearch extends SearchAlgorithm {

    private Heuristic h;
    private Problem problem;

    public GreedyBestFirstSearch() {
        this(new ManhattanDistanceHeuristic());
    }

    public GreedyBestFirstSearch(Heuristic h) {
        this.algorithmName = "Greedy Best-First Search";
        this.h = h;
    }

    @Override
    public SearchResult search(Problem problem) {
        this.problem = problem;
        Node node = problem.getInitialNode();
        reset();

        addFrontier(node);
        while (true) {
            if (frontier.isEmpty()) return new Failure();
            node = removeFrontier(getClosestNode(frontier));
            if (problem.isGoal(node)) return new Solution(node);
            addExplored(node);

            for (Action action : problem.getActions(node)) {
                Node child = problem.getResult(node, action);
                if (!explored.contains(child)) {
                    if (!frontier.contains(child)) {
                        addFrontier(child);
                    }
                }
            }
        }
    }

    private int getValue(Node node) {
        return h.evaluate(node, problem.getGoalNode());
    }

    private Node getClosestNode(List<Node> nodes) {
        Node closestNode = null;

        for (Node node : nodes) {
            if (closestNode == null || getValue(node) < getValue(closestNode)) {
                closestNode = node;
            }
        }
        return closestNode;
    }
}

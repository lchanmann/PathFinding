package ai.pathfinder.search;

import ai.pathfinder.core.Action;
import ai.pathfinder.core.Failure;
import ai.pathfinder.core.Node;
import ai.pathfinder.core.Problem;
import ai.pathfinder.core.Solution;

public class DepthFirstSearch extends SearchAlgorithm {

    public DepthFirstSearch() {
        this.algorithmName = "Depth-First Search";
    }

    @Override
    public SearchResult search(Problem problem) {
        Node node = problem.getInitialNode();
        reset();

        addFrontier(node);
        while (true) {
            if (frontier.isEmpty()) return new Failure();
            node = removeFrontier(frontier.get(frontier.size() - 1));
            /**
             * GoalTest when selecting for expansion
             */
            if (problem.isGoal(node)) return new Solution(node);
            addExplored(node);

            for (Action action : problem.getReverseActions(node)) {
                Node child = problem.getResult(node, action);
                if (!explored.contains(child)) {
                    if (!frontier.contains(child)) {
                        addFrontier(child);
                    }
                }
            }
        }
    }

}

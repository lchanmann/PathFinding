package ai.pathfinder.search;

import ai.pathfinder.core.Action;
import ai.pathfinder.core.Failure;
import ai.pathfinder.core.Node;
import ai.pathfinder.core.Problem;
import ai.pathfinder.core.Solution;

public class BreathFirstSearch extends SearchAlgorithm {

    public BreathFirstSearch() {
        this.algorithmName = "Breath-First Search";
    }

    @Override
    public SearchResult search(Problem problem) {
        Node node = problem.getInitialNode();
        reset();

        addFrontier(node);
        while (true) {
            if (frontier.isEmpty()) return new Failure();
            node = removeFrontier(frontier.get(0));
            /**
             * GoalTest when expanded
             */
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

}

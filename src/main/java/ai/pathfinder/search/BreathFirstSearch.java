package ai.pathfinder.search;

import java.util.ArrayList;
import java.util.List;

import ai.pathfinder.core.Action;
import ai.pathfinder.core.Failure;
import ai.pathfinder.core.Node;
import ai.pathfinder.core.Problem;
import ai.pathfinder.core.Solution;

public class BreathFirstSearch implements SearchAlgorithm {

    @Override
    public SearchResult search(Problem problem) {
        Node node = problem.getInitialNode();
        List<Node> frontier = new ArrayList<Node>();
        List<Node> explored = new ArrayList<Node>();

        frontier.add(node);
        while (true) {
            if (frontier.isEmpty()) return new Failure();
            node = frontier.remove(0);
            explored.add(node);
            
            /**
             * GoalTest when expanded
             */
            if (problem.isGoal(node)) return new Solution(node);
            for (Action action : problem.getActions(node)) {
                Node child = problem.getResult(node, action);
                if (!explored.contains(child)) {
                    if (!frontier.contains(child)) {
                        frontier.add(child);
                    }
                }
            }
        }
    }

}

package ai.pathfinder.search;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import ai.pathfinder.core.Action;
import ai.pathfinder.core.Failure;
import ai.pathfinder.core.Node;
import ai.pathfinder.core.Problem;
import ai.pathfinder.core.Solution;

public class BreathFirstSearch implements SearchAlgorithm {

    private final String algorithmName = "Breath-First Search";
    private final List<Node> frontier = new ArrayList<Node>();
    private final List<Node> explored = new ArrayList<Node>();
    private Consumer<List<Node>> frontierChangedConsumer;

    @Override
    public SearchResult search(Problem problem) {
        Node node = problem.getInitialNode();
        reset();

        addFrontier(node);
        while (true) {
            if (frontier.isEmpty()) return new Failure();
            node = removeFrontier(frontier.get(0));
            explored.add(node);
            
            /**
             * GoalTest when expanded
             */
            if (problem.isGoal(node)) return new Solution(node);
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

    public void onFrontierChanged(Consumer<List<Node>> consumer) {
        this.frontierChangedConsumer = consumer;
    }

    private void addFrontier(Node node) {
        frontier.add(node);
        frontierChangedConsumer.accept(frontier);
    }

    private Node removeFrontier(Node node) {
        frontier.remove(node);
        frontierChangedConsumer.accept(frontier);
        return node;
    }

    private void reset() {
        frontier.clear();
        explored.clear();
    }

    @Override
    public String getAlgorithmName() {
        return algorithmName;
    }

}

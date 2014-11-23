package ai.pathfinder.search;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import ai.pathfinder.core.Node;
import ai.pathfinder.core.Problem;

public abstract class SearchAlgorithm {

    protected String algorithmName = "Search Algorithm";
    protected final List<Node> frontier = new ArrayList<Node>();
    protected final List<Node> explored = new ArrayList<Node>();
    private Consumer<List<Node>> frontierChangedConsumer;
    private Consumer<List<Node>> exploredChangedConsumer;

    /**
     * Search
     * @param problem
     * @return
     */
    public abstract SearchResult search(Problem problem);

    /**
     * Set frontier changed consumer
     * @param consumer
     */
    public void onFrontierChanged(Consumer<List<Node>> consumer) {
        this.frontierChangedConsumer = consumer;
    }

    /**
     * Set explored set changed consumer
     * @param consumer
     */
    public void onExploredChanged(Consumer<List<Node>> consumer) {
        this.exploredChangedConsumer = consumer;
    }

    /**
     * Get algorithm name
     * @return
     */
    public String getAlgorithmName() {
        return algorithmName;
    }

    protected void addExplored(Node node) {
        explored.add(node);
        exploredChangedConsumer.accept(explored);
    }

    protected void addFrontier(Node node) {
        frontier.add(node);
        frontierChangedConsumer.accept(frontier);
    }

    protected Node removeFrontier(Node node) {
        frontier.remove(node);
        frontierChangedConsumer.accept(frontier);
        return node;
    }

    protected void reset() {
        frontier.clear();
        explored.clear();
    }
}

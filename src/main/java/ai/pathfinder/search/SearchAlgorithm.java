package ai.pathfinder.search;

import java.util.List;
import java.util.function.Consumer;

import ai.pathfinder.core.Node;
import ai.pathfinder.core.Problem;

public interface SearchAlgorithm {

    /**
     * Search
     * @param problem
     * @return
     */
    public SearchResult search(Problem problem);

    /**
     * Set frontier changed consumer
     * @param frontier
     */
    public void onFrontierChanged(Consumer<List<Node>> consumer);

    /**
     * Get algorithm name
     * @return
     */
    public String getAlgorithmName();

}

package ai.pathfinder.search;

import ai.pathfinder.core.Problem;

public interface SearchAlgorithm {

    /**
     * Search
     * @param problem
     * @return
     */
    public SearchResult search(Problem problem);

    /**
     * Get algorithm name
     * @return
     */
    public String getAlgorithmName();

}

package ai.pathfinder.search;

import ai.pathfinder.core.Problem;

public class GreedyBestFirstSearch extends SearchAlgorithm {

    public GreedyBestFirstSearch() {
        this.algorithmName = "Greedy Best-First Search";
    }

    @Override
    public SearchResult search(Problem problem) {
        System.out.println("Starting: Greedy Best-First Search ...");
        System.out.println(problem.toString());
        return null;
    }

}

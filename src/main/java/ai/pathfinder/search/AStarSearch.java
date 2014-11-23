package ai.pathfinder.search;

import ai.pathfinder.core.Problem;

public class AStarSearch implements SearchAlgorithm {

    private final String algorithmName = "A*";

    @Override
    public SearchResult search(Problem problem) {
        System.out.println("Starting: A* Search ...");
        System.out.println(problem.toString());

        return null;
    }

    @Override
    public String getAlgorithmName() {
        return algorithmName;
    }

}

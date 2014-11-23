package ai.pathfinder.search;

import ai.pathfinder.core.Problem;

public class HillClimbingSearch implements SearchAlgorithm {

    private final String algorithmName = "Hill-Climbing Search";

    @Override
    public SearchResult search(Problem problem) {
        System.out.println("Starting: Hill-Climbing Search ...");
        System.out.println(problem.toString());
        return null;
    }

    @Override
    public String getAlgorithmName() {
        return algorithmName;
    }

}

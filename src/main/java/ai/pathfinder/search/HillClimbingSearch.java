package ai.pathfinder.search;

import ai.pathfinder.core.Problem;

public class HillClimbingSearch extends SearchAlgorithm {

    public HillClimbingSearch() {
        this.algorithmName = "Hill-Climbing Search";
    }

    @Override
    public SearchResult search(Problem problem) {
        System.out.println("Starting: Hill-Climbing Search ...");
        System.out.println(problem.toString());
        return null;
    }

}

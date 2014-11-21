package ai.pathfinder.search;

import ai.pathfinder.core.Problem;

public class HillClimbingSearch implements SearchAlgorithm {

    @Override
    public void search(Problem problem) {
        System.out.println("Starting: Hill-Climbing Search ...");
        System.out.println(problem.toString());
    }

}

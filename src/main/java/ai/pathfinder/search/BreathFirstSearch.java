package ai.pathfinder.search;

import ai.pathfinder.core.Problem;

public class BreathFirstSearch implements SearchAlgorithm {

    @Override
    public void search(Problem problem) {
        System.out.println("Starting: Breath-First Search ...");
        System.out.println(problem.toString());
    }

}

package ai.pathfinder.search;

import ai.pathfinder.core.Problem;

public class GreedyBestFirstSearch implements SearchAlgorithm {

    @Override
    public void search(Problem problem) {
        System.out.println("Starting: Greedy Best-First Search ...");
        System.out.println(problem.toString());
    }

}
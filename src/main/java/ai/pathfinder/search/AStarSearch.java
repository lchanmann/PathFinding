package ai.pathfinder.search;

import ai.pathfinder.core.Problem;

public class AStarSearch implements SearchAlgorithm {

    @Override
    public void search(Problem problem) {
        System.out.println("Starting: A* Search ...");
        System.out.println(problem.toString());
    }

}
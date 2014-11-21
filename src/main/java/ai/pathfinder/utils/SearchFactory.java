package ai.pathfinder.utils;

import ai.pathfinder.search.AStarSearch;
import ai.pathfinder.search.BreathFirstSearch;
import ai.pathfinder.search.GreedyBestFirstSearch;
import ai.pathfinder.search.HillClimbingSearch;
import ai.pathfinder.search.SearchAlgorithm;

public class SearchFactory {

    public static SearchAlgorithm build(String algorithm) {
        if ("Breath-First Search" == algorithm)
            return new BreathFirstSearch();
        else if ("Greedy Best-First Search" == algorithm)
            return new GreedyBestFirstSearch();
        else if ("A*" == algorithm)
            return new AStarSearch();
        else if ("Hill-Climbing" == algorithm)
            return new HillClimbingSearch();

        return null;
    }
}

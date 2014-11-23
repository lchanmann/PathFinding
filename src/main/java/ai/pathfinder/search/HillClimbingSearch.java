package ai.pathfinder.search;

import java.util.List;
import java.util.function.Consumer;

import ai.pathfinder.core.Node;
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

    @Override
    public void onFrontierChanged(Consumer<List<Node>> consumer) {
        // TODO Auto-generated method stub
        
    }

}

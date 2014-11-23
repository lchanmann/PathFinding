package ai.pathfinder.search;

import java.util.List;
import java.util.function.Consumer;

import ai.pathfinder.core.Node;
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

    @Override
    public void onFrontierChanged(Consumer<List<Node>> consumer) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onExploredChanged(Consumer<List<Node>> consumer) {
        // TODO Auto-generated method stub
        
    }

}

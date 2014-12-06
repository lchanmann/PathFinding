package ai.pathfinder.search;

import java.util.List;
import java.util.Random;

import ai.pathfinder.core.Action;
import ai.pathfinder.core.Failure;
import ai.pathfinder.core.Node;
import ai.pathfinder.core.Problem;
import ai.pathfinder.core.Solution;

public class SimulatedAnnealing extends SearchAlgorithm {

    private Heuristic h;
    private Problem problem;
    private int t_0 = 1000;

    public SimulatedAnnealing() {
        this(new ManhattanDistanceHeuristic());
    }

    public SimulatedAnnealing(Heuristic h) {
        this.algorithmName = "Simulated Annealing";
        this.h = h;
    }

    @Override
    public SearchResult search(Problem problem) {
        int temperature;
        this.problem = problem;
        Node current = problem.getInitialNode();

        reset();
        for (int k=0; ;k++) {
            temperature = coolingSchedule(k);
            if (temperature == 0) return new Failure();
            if (problem.isGoal(current)) return new Solution(current);

            Node nextNode = getRandomChildNode(current);
            addExplored(current);

            int deltaE = getValue(nextNode) - getValue(current);
            if (deltaE > 0) {
                current = nextNode;
            } else {
                double probability = Math.pow(Math.E, deltaE / temperature);
                if (probability > Math.random()) {
                    current = nextNode;
                }
            }
        }
    }

    private int getValue(Node node) {
        return h.evaluate(node, problem.getGoalNode()) * -1;
    }
    Random rand = new Random(System.nanoTime());
    private Node getRandomChildNode(Node node) {
        List<Action> actions = problem.getActions(node);
        Action action = actions.get(rand.nextInt(actions.size()));

        return problem.getResult(node, action);
    }

    /**
     * Cooling schedule : T(k) = T(0) / (1 + alpha * k )
     *  The higher the alpha the faster the temperature will decay (less tolerant to worst neighbor)  
     * @param k
     * @return
     */
    private int coolingSchedule(int k) {
        return (int) (t_0 / (1 + .1 * k));
    }

}

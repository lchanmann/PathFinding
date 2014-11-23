package ai.pathfinder.search;


import org.junit.Assert;
import org.junit.Test;

public class AlgorithmTest {

    @Test
    public void shouldGetBreathFirstSearch() {
        SearchAlgorithm search = Algorithm.BREATH_FIRST.getSearchAlgorithm();
        Assert.assertTrue(search instanceof BreathFirstSearch);
    }

    @Test
    public void shouldGetBestFirstSearch() {
        SearchAlgorithm search = Algorithm.BEST_FIRST.getSearchAlgorithm();
        Assert.assertTrue(search instanceof GreedyBestFirstSearch);    
    }

    @Test
    public void shouldGetAStarSearch() {
        SearchAlgorithm search = Algorithm.A_STAR.getSearchAlgorithm();
        Assert.assertTrue(search instanceof AStarSearch);
    }

    @Test
    public void shouldGetHillClimbingSearch() {
        SearchAlgorithm search = Algorithm.HILL_CLIMBING.getSearchAlgorithm();
        Assert.assertTrue(search instanceof HillClimbingSearch);
    }
}

package ai.pathfinder.core;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProblemTest {

    private Node initial;
    private Node goal;
    private Set<Node> wall;
    private Problem problem;

    @Before
    public void setup() {
        initial = new Node(2,2);
        goal = new Node(5,5);
        wall = new HashSet<Node>();
        problem = new Problem(5, 5, initial, goal, wall, 1);
    }

    @Test
    public void shouldGetResultForLeftAction() {
        Node node = problem.getResult(initial, Action.LEFT);
        Assert.assertEquals(1, node.getX());
        Assert.assertEquals(2, node.getY());
        Assert.assertEquals(initial, node.getParent());
        Assert.assertEquals(Action.LEFT, node.getAction());
    }

    @Test
    public void shouldGetResultForRightAction() {
        Node node = problem.getResult(initial, Action.RIGHT);
        Assert.assertEquals(3, node.getX());
        Assert.assertEquals(2, node.getY());
        Assert.assertEquals(initial, node.getParent());
        Assert.assertEquals(Action.RIGHT, node.getAction());
    }
    
    @Test
    public void shouldGetResultForDownAction() {
        Node node = problem.getResult(initial, Action.DOWN);
        Assert.assertEquals(2, node.getX());
        Assert.assertEquals(3, node.getY());
        Assert.assertEquals(initial, node.getParent());
        Assert.assertEquals(Action.DOWN, node.getAction());
    }

    @Test
    public void shouldGetResultForUpAction() {
        Node node = problem.getResult(initial, Action.UP);
        Assert.assertEquals(2, node.getX());
        Assert.assertEquals(1, node.getY());
        Assert.assertEquals(initial, node.getParent());
        Assert.assertEquals(Action.UP, node.getAction());
    }

    @Test
    public void shouldCheckIsGoal() {
        Assert.assertFalse(problem.isGoal(initial));
        Assert.assertTrue(problem.isGoal(goal));
    }
}

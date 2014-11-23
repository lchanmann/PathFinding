package ai.pathfinder.framework;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import ai.pathfinder.core.Action;
import ai.pathfinder.core.Node;

public interface IViewModel {

    /**
     * Set state changed listener
     * @param consumer
     */
    public void onStateChanged(Consumer<IViewModel> consumer);

    /**
     * Set on searching consumer
     * @param consumer
     */
    public void onSearching(Consumer<Boolean> consumer);

    /**
     * Get grid size
     * @return
     */
    public int getGridSize();

    /**
     * Get maze width
     * @return
     */
    public int getMazeWidth();

    /**
     * Get maze height
     */
    public int getMazeHeight();

    /**
     * Get wall
     * @return
     */
    public Iterator<Node> getWall();

    /**
     * Get start node
     * @return
     */
    public Node getStartNode();

    /**
     * Get goal node
     * @return
     */
    public Node getGoalNode();

    /**
     * Get solution path
     * @return 
     */
    public Action[] getSolutionPath();

    /**
     * Check if the position is a movable node (start or goal node)
     * @param x
     * @param y
     * @return
     */
    public boolean isMovableNode(int x, int y);

    /**
     * Check if the position is wall
     * @param x
     * @param y
     * @return
     */
    public boolean isWall(int x, int y);

    /**
     * Get solution start node
     * @return
     */
    public Node getSolutionStartNode();

    /**
     * Get frontier
     * @return
     */
    public List<Node> getFrontier();

    /**
     * Get explored set
     * @return
     */
    public List<Node> getExplored();

    /**
     * Check if searching
     * @return
     */
    public boolean isSearching();

}

package ai.pathfinder.framework;

import java.awt.Point;
import java.util.Set;

public interface IViewModel {

    /**
     * Set state changed listener
     * @param listener
     */
    public void stateChanged(IStateChangedListener listener);

    /**
     * Get grid size
     * @return
     */
    public int getGridSize();

    /**
     * Get maze rows
     * @return
     */
    public int getMazeRows();

    /**
     * Get maze columns
     */
    public int getMazeCols();

    /**
     * Get wall
     * @return
     */
    public Set<Point> getWall();

    /**
     * Get start node
     * @return
     */
    public Point getStartNode();

    /**
     * Get goal node
     * @return
     */
    public Point getGoalNode();

    /**
     * Check if the position is a movable node (start or goal node)
     * @param x
     * @param y
     * @return
     */
    public boolean isMovableNode(int x, int y);

}

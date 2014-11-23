package ai.pathfinder.framework;

import java.util.List;
import java.util.function.Consumer;

import ai.pathfinder.core.Action;
import ai.pathfinder.core.Node;
import ai.pathfinder.core.Problem;


public interface IExtendedViewModel extends IViewModel {
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
     * Update node position
     * @param x
     * @param y
     */
    public void updateNode(int x, int y);

    /**
     * Reset
     */
    public void reset();

    /**
     * Add wall
     * @param x
     * @param y
     */
    public void addWall(int x, int y);

    /**
     * Remove wall
     * @param x
     * @param y
     */
    public void removeWall(int x, int y);

    /**
     * Convert model to search problem
     * @return
     */
    public Problem toProblem();

    /**
     * Set solution path
     * @param path
     */
    public void setSolutionPath(Action[] solutionPath);

    /**
     * Update frontier
     * @param frontier
     */
    public void updateFrontier(List<Node> frontier);

    /**
     * Update explored set
     * @param explored
     * @return
     */
    public void updateExplored(List<Node> explored);

    /**
     * Set searching status
     * @param searching
     */
    public void isSearching(boolean searching);
}

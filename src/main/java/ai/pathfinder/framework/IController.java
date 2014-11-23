package ai.pathfinder.framework;

import ai.pathfinder.search.Algorithm;

public interface IController {
    
    /**
     * Assign model to view
     * @param view
     */
    public void assignModel(IMainView view);

    /**
     * Search
     */
    public void search(Algorithm algorithm);

    /**
     * Reset
     */
    public void reset();

    /**
     * Update node position
     * @param x
     * @param y
     */
    public void updateNode(int x, int y);

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

}

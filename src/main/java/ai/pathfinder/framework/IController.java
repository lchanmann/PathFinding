package ai.pathfinder.framework;

public interface IController {
    
    /**
     * Assign model to view
     * @param view
     */
    public void assignModel(IMainView view);

    /**
     * Search
     */
    public void search();

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

package ai.pathfinder.framework;

public interface IMainView {

    /**
     * Initialize
     */
    public void init();

    /**
     * Set model for view
     * @param model
     */
    public void setModel(IViewModel model);

    /**
     * Get model
     * @return
     */
    public IViewModel getModel();

    /**
     * Start search from view
     */
    public void startSearch(String algorithm);

    /**
     * Reset view
     */
    public void resetView();

    /**
     * Move node to (x, y)
     * @param x
     * @param y
     */
    public void moveNode(int x, int y);

    /**
     * Add wall to grid
     * @param x
     * @param y
     */
    public void addWall(int x, int y);

    /**
     * Remove wall from grid
     * @param x
     * @param y
     */
    public void removeWall(int x, int y);

}

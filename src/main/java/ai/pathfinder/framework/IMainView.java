package ai.pathfinder.framework;

import java.awt.Point;

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
    public void startSearch();

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

}

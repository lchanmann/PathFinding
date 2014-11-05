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

}

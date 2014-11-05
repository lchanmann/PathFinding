package ai.pathfinder.framework;


public interface IExtendedViewModel extends IViewModel {

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
}

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
}

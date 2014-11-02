package ai.pathfinder.framework;

public interface IMainView {

    /**
     * Initialize view
     * @param controller
     */
    public void init(IController controller);

    /**
     * Start search from view
     */
    public void startSearch();

    /**
     * Reset view
     */
    public void resetView();

}

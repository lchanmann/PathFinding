package ai.pathfinder.framework;

public interface IController {

    /**
     * Initialize controller
     */
    public void init();

    /**
     * Search
     */
    public void search();

    /**
     * Reset
     */
    public void reset();

}

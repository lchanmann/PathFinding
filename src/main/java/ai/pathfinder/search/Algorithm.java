package ai.pathfinder.search;

public enum Algorithm {

    BREATH_FIRST        (BreathFirstSearch.class),
    DEPTH_FIRST         (DepthFirstSearch.class),
    BEST_FIRST          (GreedyBestFirstSearch.class),
    A_STAR              (AStarSearch.class),
    HILL_CLIMBING       (HillClimbingSearch.class),
    SIMULATED_ANNEALING (SimulatedAnnealing.class);

    @Override
    public String toString() {
        return searchAlgorithm.getAlgorithmName();
    }
    
    private SearchAlgorithm searchAlgorithm;

    private Algorithm(Class<? extends SearchAlgorithm> klass) {
        try {
            this.searchAlgorithm = (SearchAlgorithm) klass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public SearchAlgorithm getSearchAlgorithm() {
        return searchAlgorithm;
    }
}

package ai.pathfinder.search;

public enum Algorithm {

    BREATH_FIRST    (BreathFirstSearch.class),
    BEST_FIRST      (GreedyBestFirstSearch.class),
    A_STAR          (AStarSearch.class),
    HILL_CLIMBING   (HillClimbingSearch.class);

    @Override
    public String toString() {
        return searchAlgorithm.getAlgorithmName();
    }
    
    private SearchAlgorithm searchAlgorithm;

    private Algorithm(Class<?> klass) {
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

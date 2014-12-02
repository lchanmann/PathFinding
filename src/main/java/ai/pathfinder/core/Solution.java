package ai.pathfinder.core;

import java.util.ArrayList;
import java.util.List;

import ai.pathfinder.search.SearchResult;

/**
 * Solution to a search problem is a sequence of actions from 
 * start node to goal node
 * @author mann
 *
 */
public class Solution implements SearchResult {

    private Node node;

    public Solution(Node node) {
        this.node = node;
    }

    public Action[] getPath() {
        List<Action> path = new ArrayList<Action>();
        Node parent = node;

        do {
            if (parent.getAction() != null) {
                path.add(0, parent.getAction());
            }
        } while ((parent = parent.getParent()) != null);
        return path.toArray(new Action[] {});
    }

    
}

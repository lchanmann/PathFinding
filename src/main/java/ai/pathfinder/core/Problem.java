package ai.pathfinder.core;

import java.awt.Point;

public class Problem {

    private Point start;
    private Point goal;

    public Problem(Point start, Point goal) {
        this.start = start;
        this.goal = goal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Start: (").append(start.x).append(",").append(start.y).append(")").append("\n")
          .append("Goal: (").append(goal.x).append(",").append(goal.y).append(")")
          .append("\n");
        return sb.toString();
    }
}

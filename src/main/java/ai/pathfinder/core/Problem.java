package ai.pathfinder.core;

public class Problem {

    public Node start;
    public Node goal;

    public Problem(Node start, Node goal) {
        this.start = start;
        this.goal = goal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Start: (").append(start.getX()).append(",").append(start.getY()).append(")").append("\n")
          .append("Goal: (").append(goal.getX()).append(",").append(goal.getY()).append(")")
          .append("\n");
        return sb.toString();
    }
}

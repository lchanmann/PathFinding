package ai.pathfinder.core;

public class Node {

    private int x;
    private int y;
    private Node parent;
    private Action action;

    public Node(int x, int y) {
        this(x, y, null, null);
    }

    public Node(int x, int y, Node parent, Action action) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.action = action;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Node getParent() {
        return parent;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Node) {
            Node other = (Node) obj;
            if (x == other.x && y == other.y) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + getX();
        result = 43 * result + getY();
        return result;
    }

    public void setLocation(Node node) {
        this.x = node.x;
        this.y = node.y;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("(").append(x).append(",").append(y).append(")");
        return sb.toString();
    }

    public Action getAction() {
        return action;
    }
}

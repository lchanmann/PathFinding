package ai.pathfinder.core;

public class Node {

    private int x;
    private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
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
}

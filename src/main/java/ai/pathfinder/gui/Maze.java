package ai.pathfinder.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComponent;

import ai.pathfinder.core.Action;
import ai.pathfinder.core.Node;
import ai.pathfinder.framework.IMainView;
import ai.pathfinder.framework.IViewModel;

public class Maze extends JComponent implements MouseMotionListener,
        MouseListener {

    private static final long serialVersionUID = 1L;

    private IMainView mainView;
    private IViewModel model;

    private boolean isMovingNode;
    private boolean isDrawingWall;
    private boolean isErasingWall;

    private final Color lightGreen = new Color(160, 220, 160);
    private final Color lightBlue = new Color(226, 238, 253);

    public Maze() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void setMainView(IMainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int containerWidth = getWidth();
        int containerHeight = getHeight();

        assignModel();

        g2.setBackground(Color.white);
        g2.clearRect(0, 0, containerWidth, containerHeight);

        drawGridline(g2);
        drawNodes(g2, model.getFrontier(), lightGreen);
        drawNodes(g2, model.getExplored(), lightBlue);
        drawWall(g2);
        drawNode(g2, model.getStartNode(), Color.GREEN);
        drawNode(g2, model.getGoalNode(), Color.RED);
        drawPath(g2, Color.ORANGE);
        drawStat(g2, Color.BLUE);
    }

    private void drawStat(Graphics2D g2, Color color) {
        double executionTime = model.getExecutionTime();

        if (!model.isSearching() && executionTime > 0) {
            int pathLength = model.getSolutionPathLength();
            int expandedNodes = model.getExpandedNodeCount();

            g2.setColor(new Color(.8f, .8f, .8f, .3f));
            g2.fillRoundRect(10, 460, 280, 80, 12, 12);

            g2.setColor(color);
            g2.drawString("Length: " + pathLength, 40, 490);
            g2.drawString("Execution time: " + executionTime, 40, 507);
            g2.drawString("Expanded nodes: " + expandedNodes, 40, 524);
        }
    }

    private void drawPath(Graphics2D g2, Color color) {
        Action[] path = model.getSolutionPath();
        if (path != null) {
            Node node = model.getSolutionStartNode();
            int gridSize = model.getGridSize();
            int offset = 12;
            int x = node.getX() + offset;
            int y = node.getY() + offset;
            

            g2.setColor(color);
            for (Action action : path) {
                switch (action) {
                case LEFT:
                    x -= gridSize;
                    g2.fillRect(x, y, gridSize, 2);
                    break;
                case RIGHT:
                    g2.fillRect(x, y, gridSize, 2);
                    x += gridSize;
                    break;
                case UP:
                    y -= gridSize;
                    g2.fillRect(x, y, 2, gridSize);
                    break;
                case DOWN:
                    g2.fillRect(x, y, 2, gridSize);
                    y += gridSize;
                    break;
                }
            }
        }
    }

    private void assignModel() {
        if (model == null) {
            model = mainView.getModel();
        }
    }

    private void drawWall(Graphics2D g2) {
        Iterator<Node> wall = model.getWall();
        while (wall.hasNext()) {
            drawNode(g2, wall.next(), Color.GRAY);
        }
    }

    private void drawGridline(Graphics2D g2) {
        int gridSize = model.getGridSize();
        int mazeWidth = model.getMazeWidth();
        int mazeHeight = model.getMazeHeight();

        g2.setColor(Color.LIGHT_GRAY);
        // horizontal lines
        for (int y = 0; y <= mazeHeight; y += gridSize) {
            g2.drawLine(0, y, mazeWidth, y);
        }
        // vertical lines
        for (int x = 0; x <= mazeWidth; x += gridSize) {
            g2.drawLine(x, 0, x, mazeHeight);
        }
    }

    private void drawNode(Graphics2D g2, Node node, Color color) {
        int gridSize = model.getGridSize();

        g2.setColor(color);
        g2.fillRect(node.getX(), node.getY(), gridSize, gridSize);

        g2.setColor(Color.LIGHT_GRAY);
        g2.drawRect(node.getX(), node.getY(), gridSize, gridSize);
    }

    private void drawNodes(Graphics2D g2, List<Node> nodes, Color color) {
        if (nodes != null) {
            for (Node node : nodes) {
                drawNode(g2, node, color);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (isMovingNode) {
            if (x > 0 && y > 0 &&
                    x < getWidth() && y < getHeight()) {
                mainView.moveNode(x, y);
            }
        } else if (isDrawingWall) {
            mainView.addWall(x, y);
        } else if (isErasingWall) {
            mainView.removeWall(x, y);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) { }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            int x = e.getX();
            int y = e.getY();

            if (!model.isMovableNode(x, y)) {
                if (model.isWall(x, y)) {
                    mainView.removeWall(x, y);
                } else {
                    mainView.addWall(x, y);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (model.isMovableNode(x, y)) isMovingNode = true;
        else {
            if (model.isWall(x, y)) {
                isErasingWall = true;
            } else {
                isDrawingWall = true;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isMovingNode = false;
        isDrawingWall = false;
        isErasingWall = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    public void isSearching(Boolean searching) {
        if (searching) {
            removeMouseListener(this);
            removeMouseMotionListener(this);
        } else {
            addMouseListener(this);
            addMouseMotionListener(this);
        }
    }
}

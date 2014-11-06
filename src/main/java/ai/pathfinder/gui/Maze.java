package ai.pathfinder.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

import javax.swing.JComponent;

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

        drawWall(g2);
        drawTarget(g2, model.getStartNode(), Color.GREEN);
        drawTarget(g2, model.getGoalNode(), Color.RED);
    }

    private void assignModel() {
        if (model == null) {
            model = mainView.getModel();
        }
    }

    private void drawWall(Graphics2D g2) {
        Iterator<Point> wall = model.getWall();
        while (wall.hasNext()) {
            drawTarget(g2, wall.next(), Color.GRAY);
        }
    }

    private void drawGridline(Graphics2D g2) {
        int gridSize = model.getGridSize();
        int mazeRows = model.getMazeRows();
        int mazeCols = model.getMazeCols();

        g2.setColor(Color.LIGHT_GRAY);
        // horizontal lines
        for (int i = 0; i <= mazeRows; i++) {
            g2.drawLine(0, i * gridSize, mazeCols * gridSize, i * gridSize);
        }
        // vertical lines
        for (int j = 0; j <= mazeCols; j++) {
            g2.drawLine(j * gridSize, 0, j * gridSize, mazeRows * gridSize);
        }
    }

    private void drawTarget(Graphics2D g2, Point position, Color color) {
        int gridSize = model.getGridSize();

        g2.setColor(color);
        g2.fillRect(position.x, position.y, gridSize, gridSize);

        g2.setColor(Color.LIGHT_GRAY);
        g2.drawRect(position.x, position.y, gridSize, gridSize);
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
    public void mouseClicked(MouseEvent e) { }

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
}

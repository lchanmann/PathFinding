package ai.pathfinder.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JComponent;

public class Maze extends JComponent implements MouseMotionListener,
        MouseListener {

    private static final long serialVersionUID = 1L;

    private final int blockSize = 25;
    private final int width = 32;
    private final int height = 22;

    private final Point startingPoint = new Point(250, 250);
    private final Point goalPoint = new Point(550, 250);
    private Point selectedPoint;
    private final Set<Point> wall = new HashSet<Point>();
    private boolean drawingWall;
    private boolean erasingWall;

    public Maze() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int containerWidth = getWidth();
        int containerHeight = getHeight();

        g2.setBackground(Color.white);
        g2.clearRect(0, 0, containerWidth, containerHeight);

        drawMaze(g2);

        drawWall(g2);
        drawTarget(g2, startingPoint, Color.GREEN);
        drawTarget(g2, goalPoint, Color.RED);
    }

    private void drawWall(Graphics2D g2) {
        for (Point block : wall) {
            drawTarget(g2, block, Color.GRAY);
        }
    }

    private void drawMaze(Graphics2D g2) {
        g2.setColor(Color.LIGHT_GRAY);
        // horizontal lines
        for (int i = 0; i <= height; i++) {
            g2.drawLine(0, i * blockSize, width * blockSize, i * blockSize);
        }
        // vertical lines
        for (int j = 0; j <= width; j++) {
            g2.drawLine(j * blockSize, 0, j * blockSize, height * blockSize);
        }
    }

    private void drawTarget(Graphics2D g2, Point position, Color color) {
        g2.setColor(color);
        g2.fillRect(position.x, position.y, blockSize, blockSize);

        g2.setColor(Color.LIGHT_GRAY);
        g2.drawRect(position.x, position.y, blockSize, blockSize);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Point dragged = snapToGrid(x, y);

        if (selectedPoint != null) {
            if (x > 0 && y > 0 &&
                    x < getWidth() && y < getHeight()) {
                selectedPoint.setLocation(dragged);
            }
        } else if (drawingWall) {
            wall.add(dragged);
        } else if (erasingWall) {
            wall.remove(dragged);
        }
        repaint();
    }

    private Point snapToGrid(int x, int y) {
        return new Point(x - x % blockSize, y - y % blockSize);
    }

    @Override
    public void mouseMoved(MouseEvent e) { }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Point pressed = snapToGrid(x, y);

        if (pressed.distance(startingPoint) == 0) selectedPoint = startingPoint;
        else if (pressed.distance(goalPoint) == 0) selectedPoint = goalPoint;
        else {
            if (wall.contains(pressed)) {
                erasingWall = true;
            } else {
                drawingWall = true;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        selectedPoint = null;
        drawingWall = false;
        erasingWall = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}

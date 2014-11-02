package ai.pathfinder.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JToolBar;

import ai.pathfinder.framework.IController;
import ai.pathfinder.framework.IView;

public class View extends JFrame implements IView {

    private static final long serialVersionUID = 6872713207253485306L;
    private IController controller;

    private ToolBar toolbar;
    private Maze maze;

    public View() {
        setTitle("Path Finder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 603);
        setLocationRelativeTo(null);

        getContentPane().setLayout(new BorderLayout());

        toolbar = new ToolBar();
        getContentPane().add(toolbar, BorderLayout.NORTH);
        
        maze = new Maze(32, 22);
        getContentPane().add(maze, BorderLayout.CENTER);
    }

    @Override
    public void init(IController controller) {
        this.controller = controller;
        setVisible(true);
    }

    /** TopPanel  */
    private class ToolBar extends JToolBar implements ActionListener {

        private static final long serialVersionUID = 1L;

        private JComboBox<String> algorithmCombo;
        private JButton runButton;
        private JButton resetButton;

        ToolBar() {
            algorithmCombo = new JComboBox<String>(new String[] { "A* - Manhattan", "Hill-Climbing", "Simulated Annealing" });
            add(algorithmCombo);

            runButton = new JButton("Run");
            runButton.addActionListener(this);
            add(runButton);

            resetButton = new JButton("Reset");
            resetButton.addActionListener(this);
            add(resetButton);

            setFloatable(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
           if (e.getSource() == runButton) {
               controller.run();
           } else if (e.getSource() == resetButton) {
               controller.reset();
           }
        }
    }
    
    /** Maze */
    private class Maze extends JComponent implements
        MouseMotionListener, MouseListener {

        private static final long serialVersionUID = 1L;

        private static final int BLOCK_SIZE = 25;
        private int width;
        private int height;

        private Point startingPoint;
        private Point goalPoint;
        private Point selectedPoint;

        Maze(int width, int height) {
            this.width = width;
            this.height = height;

            this.startingPoint = new Point(250, 250);
            this.goalPoint = new Point(550, 250);

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

            drawTarget(g2, startingPoint, Color.GREEN);
            drawTarget(g2, goalPoint, Color.RED);
        }

        private void drawMaze(Graphics2D g2) {
            g2.setColor(Color.GRAY);
            // horizontal lines
            for (int i = 0; i <= height; i++) {
                g2.drawLine(0, i * BLOCK_SIZE, width * BLOCK_SIZE, i * BLOCK_SIZE);
            }
            // vertical lines
            for (int j = 0; j <= width; j++) {
                g2.drawLine(j * BLOCK_SIZE, 0, j * BLOCK_SIZE, height * BLOCK_SIZE);
            }
        }

        private void drawTarget(Graphics2D g2, Point position, Color color) {
            g2.setColor(color);
            g2.fillRect(position.x, position.y, BLOCK_SIZE, BLOCK_SIZE);

            g2.setColor(Color.GRAY);
            g2.drawRect(position.x, position.y, BLOCK_SIZE, BLOCK_SIZE);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            if (selectedPoint != null) {
                if (x > 0 && y > 0) {
                    if (x < getWidth() && y < getHeight()) {
                        selectedPoint.setLocation(snapToGrid(x, y));
                    }
                }
            }
            repaint();
        }

        private Point snapToGrid(int x, int y) {
            return new Point(x - x % BLOCK_SIZE, y - y % BLOCK_SIZE);
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
            if (pressed.distance(goalPoint) == 0) selectedPoint = goalPoint;
        }

        @Override
        public void mouseReleased(MouseEvent e) { }

        @Override
        public void mouseEntered(MouseEvent e) { }

        @Override
        public void mouseExited(MouseEvent e) { }
    }
}

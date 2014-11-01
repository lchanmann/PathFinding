package ai.pathfinder.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ai.pathfinder.framework.IController;
import ai.pathfinder.framework.IView;

public class View extends JFrame implements IView {

    private static final long serialVersionUID = 6872713207253485306L;
    private IController controller;

    private TopPanel topPanel;
    private Maze maze;

    public View() {
        setTitle("Path Finder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        getContentPane().setLayout(new BorderLayout());

        topPanel = new TopPanel();
        getContentPane().add(topPanel, BorderLayout.NORTH);
        
        maze = new Maze();
        getContentPane().add(maze, BorderLayout.CENTER);
    }

    @Override
    public void init(IController controller) {
        this.controller = controller;
        setVisible(true);
    }

    /** TopPanel  */
    private class TopPanel extends JPanel implements ActionListener {

        private static final long serialVersionUID = 1L;

        private JButton helloButton;

        TopPanel() {
            helloButton = new JButton("Hello");
            helloButton.addActionListener(this);
            add(helloButton);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
           if (e.getSource() == helloButton) {
               controller.printHello();
           }
           repaint();
        }
    }
    
    /** Maze */
    private class Maze extends JComponent {

        private static final long serialVersionUID = 1L;
        
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();

            g2.setBackground(Color.white);
            g2.clearRect(0, 0, width, height);

            g2.setColor(Color.GRAY);
            for (int i = 0; i < height; i += 25) {
                g2.drawLine(0, i, width, i);
            }
            for (int j = 0; j < width; j += 25) {
                g2.drawLine(j, 0, j, height);
            }
        }
    }
}

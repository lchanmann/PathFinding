package ai.pathfinder.app;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ai.pathfinder.core.IController;
import ai.pathfinder.core.IView;

public class View extends JFrame implements IView {

    private static final long serialVersionUID = 6872713207253485306L;
    private IController controller;

    private TopPanel topPanel;

    public View() {
        setTitle("Path Finder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        getContentPane().setLayout(new BorderLayout());

        topPanel = new TopPanel();
        getContentPane().add(topPanel, BorderLayout.NORTH);
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
}

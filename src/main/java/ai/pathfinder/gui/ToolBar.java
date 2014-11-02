package ai.pathfinder.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

import ai.pathfinder.framework.IMainView;

public class ToolBar extends JToolBar implements ActionListener {

    private static final long serialVersionUID = 1L;
    private IMainView mainView;

    private JComboBox<String> algorithmCombo;
    private JButton runButton;
    private JButton resetButton;

    public ToolBar() {
        algorithmCombo = new JComboBox<String>(new String[] {
                "A* - Manhattan",
                "Hill-Climbing", "Simulated Annealing" });
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
            mainView.startSearch();
        } else if (e.getSource() == resetButton) {
            mainView.resetView();
        }
    }

    public void setMainView(IMainView mainView) {
        this.mainView = mainView;
    }
}
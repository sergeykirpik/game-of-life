package life.view;

import life.GameOfLifeModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatisticsPanel extends JPanel {

    private final GameOfLifeModel model;
    private final JLabel generationLabel;
    private final JLabel aliveLabel;

    public StatisticsPanel(GameOfLifeModel model) {
        this.model = model;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(0, 10, 5, 0));
        setAlignmentX(Component.LEFT_ALIGNMENT);

        generationLabel = new JLabel("Generation #1");
        generationLabel.setName("GenerationLabel");
        generationLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(generationLabel);

        aliveLabel = new JLabel("Alive: 0");
        aliveLabel.setName("AliveLabel");
        aliveLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(aliveLabel);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        String generationText = String.format("Generation #%d", model.generation());
        generationLabel.setText(generationText);

        String aliveText = String.format("Alive: %d", model.alive());
        aliveLabel.setText(aliveText);
    }
}

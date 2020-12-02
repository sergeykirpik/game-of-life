package life.view;

import life.GameOfLifeModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LifePanel extends JPanel {

    private static final Color ALIVE_CELL_COLOR = Color.getHSBColor(0.8f, 0.1f, 0.6f);
    private static final Color GRID_COLOR = ALIVE_CELL_COLOR;
    private static final Color DEAD_CELL_COLOR = Color.WHITE;

    private final GameOfLifeModel model;

    private Color activeSellColor = ALIVE_CELL_COLOR;

    public LifePanel(GameOfLifeModel model) {
        super();
        this.model = model;
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setAlignmentY(Component.TOP_ALIGNMENT);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int size = model.getSize();

        int hSize = getWidth() / size;
        int vSize = getHeight() / size;

        int xOffset = (getWidth() - hSize * size) / 2;
        int yOffset = (getHeight() - vSize * size) / 2;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {

                int x = col * hSize + xOffset;
                int y = row * vSize + yOffset;

                if (model.isAlive(row, col)) {
                    g.setColor(activeSellColor);
                } else {
                    g.setColor(DEAD_CELL_COLOR);
                }
                g.fillRect(x, y, hSize, vSize);

                g.setColor(GRID_COLOR);
                g.drawRect(x, y, hSize, vSize);
            }
        }
    }

    public Color getActiveSellColor() {
        return activeSellColor;
    }

    public void setActiveSellColor(Color activeSellColor) {
        this.activeSellColor = activeSellColor;
    }
}
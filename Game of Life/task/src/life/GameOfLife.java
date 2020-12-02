package life;

import life.view.ControlPanel;
import life.view.LifePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameOfLife extends JFrame {
    private static final String PLAY_BUTTON = "Play";
    private static final String PAUSE_BUTTON = "Pause";
    private static final String RESTART_BUTTON = "Restart";

    private static final int FIELD_SIZE = 30;
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 750;

    private final GameOfLifeModel model;
    private final GameOfLifeController controller;

    private final LifePanel lifePanel;

    public GameOfLife() {
        super("Game of Life");
        model = new GameOfLifeModel(FIELD_SIZE);
        controller = new GameOfLifeController(model, this::updateUI);
        lifePanel = new LifePanel(model);
        buildAndShowUI();
    }

    private void buildAndShowUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        addComponentsTo(getContentPane());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addComponentsTo(Container pane) {
        Box root = Box.createHorizontalBox();
        root.setBorder(new EmptyBorder(5, 5, 5, 5));

        root.add(createControlPanel());
        root.add(lifePanel);

        pane.add(root);
    }

    private Component createControlPanel() {
        var cp = new ControlPanel(model);
        cp.startPauseButton.addActionListener(e -> {
            if (cp.startPauseButton.getModel().isSelected()) {
                controller.start();
                cp.startPauseButton.setText(PAUSE_BUTTON);
            } else {
                controller.pause();
                cp.startPauseButton.setText(PLAY_BUTTON);
            }
        });
        cp.restartButton.setText(RESTART_BUTTON);
        cp.restartButton.addActionListener(e -> controller.restart());

        cp.speedSlider.setValue(controller.getSpeed());
        cp.speedSlider.addChangeListener(e -> controller.setSpeed(cp.speedSlider.getValue()));

        synchronizeSlidersWithColor(cp);

        cp.hueSlider.addChangeListener(e -> {
            float hue = cp.hueSlider.getValue() / 100.0f;
            float[] hsb = convertToHSB(lifePanel.getActiveSellColor());
            Color newColor = Color.getHSBColor(hue, hsb[1], hsb[2]);
            lifePanel.setActiveSellColor(newColor);
            updateUI();
        });
        cp.saturationSlider.addChangeListener(e -> {
            float saturation = cp.saturationSlider.getValue() / 100.0f;
            float[] hsb = convertToHSB(lifePanel.getActiveSellColor());
            Color newColor = Color.getHSBColor(hsb[0], saturation, hsb[2]);
            lifePanel.setActiveSellColor(newColor);
            updateUI();
        });
        cp.brightnessSlider.addChangeListener(e -> {
            float brightness = cp.brightnessSlider.getValue() / 100.0f;
            float[] hsb = convertToHSB(lifePanel.getActiveSellColor());
            Color newColor = Color.getHSBColor(hsb[0], hsb[1], brightness);
            lifePanel.setActiveSellColor(newColor);
            updateUI();
        });
        return cp;
    }

    private void synchronizeSlidersWithColor(ControlPanel cp) {
        float[] hsb = convertToHSB(lifePanel.getActiveSellColor());
        cp.hueSlider.setValue((int) (hsb[0] * 100));
        cp.saturationSlider.setValue((int) (hsb[1] * 100));
        cp.brightnessSlider.setValue((int) (hsb[2] * 100));
    }

    private static float[] convertToHSB(Color rgb) {
        float[] hsb =  new float[3];
        Color.RGBtoHSB(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), hsb);
        return hsb;
    }

    private void updateUI() {
        SwingUtilities.invokeLater(() -> getContentPane().repaint());
    }

    public static void main(String[] args) {
        new GameOfLife();
        //SwingUtilities.invokeLater(game::buildAndShowUI);
    }
}



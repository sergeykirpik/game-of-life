package life.view;

import life.GameOfLifeModel;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    public final JToggleButton startPauseButton;
    public final JButton restartButton;
    public final JSlider speedSlider;
    public final JSlider hueSlider;
    public final JSlider saturationSlider;
    public final JSlider brightnessSlider;

    public ControlPanel(GameOfLifeModel model) {
        super();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentY(Component.TOP_ALIGNMENT);
        addPaddingTo(this);

        startPauseButton = new JToggleButton("Start");
        restartButton = new JButton("Restart");
        speedSlider = new JSlider();
        hueSlider = new JSlider();
        saturationSlider = new JSlider();
        brightnessSlider = new JSlider();

        startPauseButton.setName("PlayToggleButton");
        restartButton.setName("ResetButton");

        JComponent buttonBox = createButtonBox();
        JComponent statistics = new StatisticsPanel(model);
        JComponent speedSliderBox = createSpeedSliderBox();
        JComponent hueSliderBox = createActiveCellColorPanel();

        buttonBox.setAlignmentX(CENTER_ALIGNMENT);
        statistics.setAlignmentX(CENTER_ALIGNMENT);
        addPaddingTo(statistics);

        speedSliderBox.setAlignmentX(CENTER_ALIGNMENT);

        add(buttonBox);
        add(vFiller());
        add(statistics);
        add(vFiller());
        add(speedSliderBox);
        add(vFiller());
        add(hueSliderBox);
    }

    private JComponent createButtonBox() {
        Box box = Box.createHorizontalBox();
        box.add(startPauseButton);
        box.add(restartButton);
        return box;
    }

    private JComponent createSpeedSliderBox() {
        Box box = Box.createVerticalBox();
        addPaddingTo(box);
        box.add(sliderBox(speedSlider, " Speed: "));
        return box;
    }

    private JComponent createActiveCellColorPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(CENTER_ALIGNMENT);
        panel.setBorder(BorderFactory.createTitledBorder("Alive cell color"));
        addPaddingTo(panel);

        panel.add(sliderBox(hueSlider, " Hue:"));
        panel.add(sliderBox(saturationSlider, " Saturation:"));
        panel.add(sliderBox(brightnessSlider, " Brightness:"));
        return panel;
    }

    private JComponent sliderBox(JSlider slider, String labelText) {
        Box box = Box.createVerticalBox();
        JLabel label = new JLabel(labelText);
        label.setAlignmentX(LEFT_ALIGNMENT);
        slider.setAlignmentX(LEFT_ALIGNMENT);
        slider.setMaximumSize(new Dimension(300, slider.getPreferredSize().height));

        box.add(label);
        box.add(slider);
        return box;
    }

    private static void addPaddingTo(JComponent component) {
        component.setBorder(BorderFactory.createCompoundBorder(
        component.getBorder(),
        BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
    }

    private static Component vFiller() {
        return Box.createRigidArea(new Dimension(0, 10));
    }

}


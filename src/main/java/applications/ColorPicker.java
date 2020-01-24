package applications;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class ColorPicker extends JFrame {

    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    StringSelection stringSelection;
    private String hex;

    protected <T extends Component> void make(T component, GridBagLayout gbag, GridBagConstraints gbc) {
        gbag.setConstraints(component, gbc);
        add(component);
    }

    public ColorPicker() {
        setTitle("Color Picker");
        setLayout(new GridBagLayout());
        setSize(new Dimension(450, 250));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setLocationRelativeTo(null);
        setVisible(true);

        GridBagLayout gbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

            setLayout(gbag);

            JButton button = new JButton();
            button.setEnabled(false);
            button.setBackground(new Color(125, 125, 125));

            JLabel labelSliderRed = new JLabel("Red:");
            JLabel labelSliderGreen = new JLabel("Green:");
            JLabel labelSliderBlue = new JLabel("Blue:");

            JSlider sliderRed = new JSlider(0, 255, 125);
            JSlider sliderGreen = new JSlider(0, 255, 125);
            JSlider sliderBlue = new JSlider(0, 255, 125);
            JSlider[] sliders = {sliderRed, sliderGreen, sliderBlue};

            for (JSlider slider : sliders) {
                slider.setMajorTickSpacing(255);
                slider.setMinorTickSpacing(17);
                slider.setPaintTicks(true);
                slider.setPaintLabels(true);
            }

            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 0;
            gbc.ipadx = 120;
            gbc.gridheight = GridBagConstraints.REMAINDER;
            gbc.gridwidth = 1;
            make(button, gbag, gbc);

            gbc.fill = GridBagConstraints.NONE;
            gbc.gridheight = 1;
            gbc.ipadx = 0;
            gbc.gridx = 1;
            gbc.gridy = GridBagConstraints.RELATIVE;
            gbc.insets = new Insets(0, 10, 0, 0);
            make(labelSliderRed, gbag, gbc);

            gbc.gridx = 1;
            gbc.insets = new Insets(20, 10, 0, 0);
            make(labelSliderGreen, gbag, gbc);

            gbc.gridx = 1;
            make(labelSliderBlue, gbag, gbc);

            gbc.gridx = 2;
            gbc.insets = new Insets(0, 10, 0, 0);
            make(sliderRed, gbag, gbc);

            gbc.gridx = 2;
            gbc.insets = new Insets(20, 10, 0, 0);
            make(sliderGreen, gbag, gbc);

            gbc.gridx = 2;
            gbc.insets = new Insets(20, 10, 0, 0);
            make(sliderBlue, gbag, gbc);


            button.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    button.setToolTipText(null);
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    hex = "#" + Integer.toHexString(sliderRed.getValue()) +
                            Integer.toHexString(sliderGreen.getValue()) +
                            Integer.toHexString(sliderBlue.getValue());
                    button.setToolTipText(hex.toUpperCase());
                }
            });

            sliderRed.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    button.setBackground(new Color(sliderRed.getValue(), sliderGreen.getValue(), sliderBlue.getValue()));
                    getHexadecimalValueToClipboard(sliderRed, sliderGreen, sliderBlue);
                }
            });

            sliderGreen.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    button.setBackground(new Color(sliderRed.getValue(), sliderGreen.getValue(), sliderBlue.getValue()));
                    getHexadecimalValueToClipboard(sliderRed, sliderGreen, sliderBlue);
                }
            });

            sliderBlue.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    button.setBackground(new Color(sliderRed.getValue(), sliderGreen.getValue(), sliderBlue.getValue()));
                    getHexadecimalValueToClipboard(sliderRed, sliderGreen, sliderBlue);
                }
            });
        }

        private void getHexadecimalValueToClipboard (JSlider sliderRed, JSlider sliderGreen, JSlider sliderBlue){
            hex = "#" + Integer.toHexString(sliderRed.getValue()) +
                    Integer.toHexString(sliderGreen.getValue()) +
                    Integer.toHexString(sliderBlue.getValue());
            stringSelection = new StringSelection(hex.toUpperCase());
            clipboard.setContents(stringSelection, null);
        }

    }


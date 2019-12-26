package applications;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Calculator extends JFrame {

    private class BasePane extends JPanel {

        JButton[] buttons = new JButton[17];
        JTextField field = new JTextField();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection;
        double firstValue = 0;
        String operation = "+";

        public BasePane() {
            setLayout(new GridBagLayout());
            setFocusable(true);
            grabFocus();

            JPanel buttonsPane = new JPanel(new GridBagLayout());

            field.setEditable(false);
            Font font = new Font("SenSerif", Font.BOLD, 15);
            field.setFont(font);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(3, 3, 3, 3);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 4;
            gbc.fill = java.awt.GridBagConstraints.BOTH;
            gbc.weightx = 0.25;
            gbc.weighty = 0.25;
            buttonsPane.add(field, gbc);

            String[] text = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "CE", "0", ".", "/"};
            gbc = new GridBagConstraints();
            gbc = new java.awt.GridBagConstraints();
            gbc.insets = new Insets(3, 3, 3, 3);
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.fill = java.awt.GridBagConstraints.BOTH;
            gbc.weightx = 0.25;
            gbc.weighty = 0.25;
            for (int index = 0; index < 16; index++) {
                if (index % 4 == 0) {
                    gbc.gridy++;
                    gbc.gridx = 0;
                } else {
                    gbc.gridx++;
                }
                JButton button = new JButton(text[index]);
                if(index == 12) {
                    button.setFont(new Font("SenSerif", Font.BOLD, 12));
                }
                else button.setFont(font);
                buttonsPane.add(button, gbc);
                buttons[index] = button;
            }

            JButton button = new JButton("=");
            gbc = new GridBagConstraints();
            gbc.insets = new Insets(3, 3, 3, 3);
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.gridwidth = 4;
            gbc.fill = java.awt.GridBagConstraints.BOTH;
            gbc.weightx = 0.25;
            gbc.weighty = 0.25;
            buttonsPane.add(button, gbc);
            buttons[16] = button;



            gbc = new GridBagConstraints();
            gbc = new java.awt.GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = java.awt.GridBagConstraints.BOTH;
            gbc.weightx = 1;
            gbc.weighty = 1;
            add(buttonsPane, gbc);

            buttons[14].addActionListener(new ActionListener() { // .
                @Override
                public void actionPerformed(ActionEvent e) {
                    field.setText(field.getText()+".");
                }
            });

            buttons[13].addActionListener(new ActionListener() { // 0
                @Override
                public void actionPerformed(ActionEvent e) {
                   field.setText(field.getText()+"0");
                }
            });
            buttons[8].addActionListener(new ActionListener() { // 1
                @Override
                public void actionPerformed(ActionEvent e) {
                    field.setText(field.getText()+"1");
                }
            });
            buttons[9].addActionListener(new ActionListener() { // 2
                @Override
                public void actionPerformed(ActionEvent e) {
                    field.setText(field.getText()+"2");
                }
            });
            buttons[10].addActionListener(new ActionListener() { // 3
                @Override
                public void actionPerformed(ActionEvent e) {
                    field.setText(field.getText()+"3");
                }
            });
            buttons[4].addActionListener(new ActionListener() { // 4
                @Override
                public void actionPerformed(ActionEvent e) {
                    field.setText(field.getText()+"4");
                }
            });
            buttons[5].addActionListener(new ActionListener() { // 5
                @Override
                public void actionPerformed(ActionEvent e) {
                    field.setText(field.getText()+"5");
                }
            });
            buttons[6].addActionListener(new ActionListener() { // 6
                @Override
                public void actionPerformed(ActionEvent e) {
                    field.setText(field.getText()+"6");
                }
            });
            buttons[0].addActionListener(new ActionListener() { // 7
                @Override
                public void actionPerformed(ActionEvent e) {
                    field.setText(field.getText()+"7");
                }
            });
            buttons[1].addActionListener(new ActionListener() { // 8
                @Override
                public void actionPerformed(ActionEvent e) {
                    field.setText(field.getText()+"8");
                }
            });
            buttons[2].addActionListener(new ActionListener() { // 9
                @Override
                public void actionPerformed(ActionEvent e) {
                    field.setText(field.getText()+"9");
                }
            });

            buttons[12].addActionListener(new ActionListener() { // CE
                @Override
                public void actionPerformed(ActionEvent e) {
                    String temp = field.getText();
                    if(temp.length() > 0)
                        field.setText(temp.substring(0,temp.length()-1));
                }
            });

            buttons[3].addActionListener(new ActionListener() { // +
                @Override
                public void actionPerformed(ActionEvent e) {
                    firstValue = Double.valueOf(field.getText());
                    field.setText("");
                    operation = "+";
                }
            });
            buttons[11].addActionListener(new ActionListener() { // *
                @Override
                public void actionPerformed(ActionEvent e) {
                    firstValue = Double.valueOf(field.getText());
                    field.setText("");
                    operation = "*";
                }
            });
            buttons[15].addActionListener(new ActionListener() { // /
                @Override
                public void actionPerformed(ActionEvent e) {
                    firstValue = Double.valueOf(field.getText());
                    field.setText("");
                    operation = "/";
                }
            });
            buttons[7].addActionListener(new ActionListener() { // -
                @Override
                public void actionPerformed(ActionEvent e) {
                    firstValue = Double.valueOf(field.getText());
                    field.setText("");
                    operation = "-";
                }
            });


            buttons[16].addActionListener(new ActionListener() { // =
                @Override
                public void actionPerformed(ActionEvent e) {
                    double secondValue = Double.valueOf(field.getText());
                    if("+".equals(operation)){
                        field.setText(firstValue+secondValue+"");
                        stringSelection = new StringSelection(String.valueOf(firstValue+secondValue));
                        clipboard.setContents(stringSelection, null);
                    }
                    if("-".equals(operation)){
                        field.setText((firstValue-secondValue)+"");
                        stringSelection = new StringSelection(String.valueOf(firstValue-secondValue));
                        clipboard.setContents(stringSelection, null);
                    }
                    if("*".equals(operation)){
                        field.setText((firstValue*secondValue)+"");
                        stringSelection = new StringSelection(String.valueOf(firstValue*secondValue));
                        clipboard.setContents(stringSelection, null);
                    }
                    if("/".equals(operation)){
                        field.setText((firstValue/secondValue)+"");
                        stringSelection = new StringSelection(String.valueOf(firstValue/secondValue));
                        clipboard.setContents(stringSelection, null);
                    }
                    firstValue = 0;
                    operation = "+";
                }
            });

            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    char ch = e.getKeyChar();

                    if(Character.isDigit(ch))
                        return;

                    field.setText(field.getText() + ch);
                }
            });

        }

        @Override
        public  Dimension getPreferredSize() {
            return new Dimension(400, 500);
        }

    }

    public Calculator() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        BasePane basePane = new BasePane();
        setSize(basePane.getPreferredSize());
        setPreferredSize(basePane.getPreferredSize());
        setLocationByPlatform(true);
        setLocationRelativeTo(null);
        add(basePane);

        setMinimumSize(new Dimension(200, 300));
        setMaximumSize(new Dimension(600, 700));
        revalidate();

        setVisible(true);

    }

}


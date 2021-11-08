package Piccross;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {

    static class ExitListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    static class AboutListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,"Chang luo and Jiayu Lin's Piccross");
        }
    }

    static class ColorListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //JDialog colorDialog = new JDialog();
            JFrame colorFrame = new JFrame("Color Model");
            colorFrame.setSize(500,200);
            colorFrame.setAlwaysOnTop(true);
            colorFrame.setVisible(true);

            colorFrame.setLayout(new GridLayout(2,3));
            JPanel correctColor = new JPanel();
            JPanel markedColor = new JPanel();
            JPanel errorColor = new JPanel();

            JButton correctColorButton = new JButton("Color1:Correct");
            JButton markedColorButton = new JButton("Color2:Marked");
            JButton errorColorButton = new JButton("Color3:Error");

            colorFrame.add(correctColor);
            colorFrame.add(markedColor);
            colorFrame.add(errorColor);
            colorFrame.add(correctColorButton);
            colorFrame.add(markedColorButton);
            colorFrame.add(errorColorButton);

            correctColor.setBackground(Color.green);
            markedColor.setBackground(Color.yellow);
            errorColor.setBackground(Color.red);

            correctColorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JColorChooser correctColorChooser = new JColorChooser();
                    Color newCorrectColor = JColorChooser.showDialog(null,"Color chooser",Color.green);

                    correctColor.setBackground(newCorrectColor);
                }
            });

            markedColorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JColorChooser markedColorChooser = new JColorChooser();
                    Color newMarkedColor = JColorChooser.showDialog(null,"Color chooser",Color.yellow);

                    markedColor.setBackground(newMarkedColor);
                }
            });

            errorColorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JColorChooser errorColorChooser = new JColorChooser();
                    Color newErrorColor = JColorChooser.showDialog(null,"Color chooser",Color.red);

                    errorColor.setBackground(newErrorColor);
                }
            });
        }
    }

}
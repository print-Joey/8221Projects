package Piccross;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {
    private GameModel gameModel;
    private GameView gameView;


    int markCheckBoxCount = 0;
    final String RESET_BUTTON_REACTION = "Reset Button pressed!!\n";
    final String UNCHECK_MARK_CHECKBOX_REACTION = "Check Box \"Mark\" UnChecked!!\n";
    final String CHECK_MARK_CHECKBOX_REACTION = "Check Box \"Mark\" Checked!!\n";
    final String BLANK_SPACE = "           ";
    public GameController(GameModel gameModel,GameView  gameView) {

        this.gameModel = gameModel;
        this.gameView = gameView;

        //add ActionListener
        this.gameView.resetButton.addActionListener(this);
        this.gameView.markCheckbox.addActionListener(this);


    }



        //new Game================



        @Override
        public void actionPerformed (ActionEvent e){

            if (e.getSource() == gameView.resetButton) {

                gameView.messageDisplayTextArea.append(BLANK_SPACE+RESET_BUTTON_REACTION);

            } else if (e.getSource() == gameView.markCheckbox) {
                //determine markCheckBoxCount is odd number or even number
                if (markCheckBoxCount % 2 != 0) {
                    //odd number behavior -> mark box unchecked
                    gameView.messageDisplayTextArea.append(UNCHECK_MARK_CHECKBOX_REACTION);
                } else {
                    //odd number behavior -> mark box checked
                    gameView.messageDisplayTextArea.append(CHECK_MARK_CHECKBOX_REACTION);

                }
                markCheckBoxCount++;
                //new Game
            } else if (e.getSource() == gameView.newGame) {
                gameModel.numberOfRow = 5;
                gameModel.numberOfColumn = 5;
                gameModel.initGame();

            }


            for (int i = 0; i < gameView.UnitOfBoardButton.length; i++) {
                for (int j = 0; j < gameView.UnitOfBoardButton[i].length; j++) {
                    this.gameView.UnitOfBoardButton[i][j].addActionListener(this);
                    if (gameView.UnitOfBoardButton[i][j].getModel().isArmed()) {
                        int row = i;
                        int column = j;
                        row++;
                        column++;
                        gameView.messageDisplayTextArea.append((BLANK_SPACE + "Button" + "[" + (row) + "," + (column) + "]" + " is Pressed\n"));

                    }
                }

            }

        }




    //=============================




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
package Piccross;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.util.Timer;


public class GameController implements ActionListener {
    private GameModel gameModel;
    private GameView gameView;


    int markCheckBoxCount = 0;
    final String RESET_BUTTON_REACTION = "Reset Button pressed!!\n";
    final String UNCHECK_MARK_CHECKBOX_REACTION = "Check Box \"Mark\" UnChecked!!\n";
    final String CHECK_MARK_CHECKBOX_REACTION = "Check Box \"Mark\" Checked!!\n";
    final String BLANK_SPACE = "           ";
    final String BLANK_SPACE_CHK_BOX = "        ";
    final String BLANK_SPACE_UNCHK_BOX = "      ";


    public GameController(GameModel gameModel,GameView  gameView) {

        this.gameModel = gameModel;
        this.gameView = gameView;
//====================
        //add ActionListener
        this.gameView.resetButton.addActionListener(this);
        this.gameView.markCheckbox.addActionListener(this);

        for (int i = 0; i < this.gameView.UnitOfBoardButton.length; i++) {
            for (int j = 0; j <this.gameView.UnitOfBoardButton[i].length; j++) {
                    this.gameView.UnitOfBoardButton[i][j].addActionListener(this);
            }

        }
    }
//============================





        //new Game================



        @Override
        public void actionPerformed (ActionEvent e){

            if (e.getSource() == gameView.resetButton) {
                // reset button is clicked
                gameView.messageDisplayTextArea.append(BLANK_SPACE+RESET_BUTTON_REACTION);

            } else if (e.getSource() == gameView.markCheckbox) {
                //markCheckbox is clicked
                //determine markCheckBoxCount is odd number or even number
                if (markCheckBoxCount % 2 != 0) {
                    //odd number behavior -> mark box unchecked
                    gameView.messageDisplayTextArea.append(BLANK_SPACE_UNCHK_BOX+UNCHECK_MARK_CHECKBOX_REACTION);
                } else {
                    //odd number behavior -> mark box checked
                    gameView.messageDisplayTextArea.append(BLANK_SPACE_CHK_BOX+CHECK_MARK_CHECKBOX_REACTION);

                }
                markCheckBoxCount++;
                //new Game
            } else if (e.getSource() == gameView.newGame) {
                //Default setting of the game
                JFrame gameLayoutSetterFrame;
                gameLayoutSetterFrame = new JFrame();
                gameLayoutSetterFrame.setTitle("Please set your game Layout");
                gameLayoutSetterFrame.setLayout(new BorderLayout());


                this.gameModel.numberOfRow      = 5;
                this.gameModel.numberOfColumn   = 5;
                this.gameModel.initGame();

            } else if (e.getSource() == this.gameView.solutionMenuItem){



            }

            // Game Board is clicked behaviors
            for (int i = 0; i < this.gameModel.numberOfRow; i++) {
                for (int j = 0; j < this.gameModel.numberOfColumn; j++) {
                    // Responds after Board unit get clicked.
                    if (gameView.UnitOfBoardButton[i][j].getModel().isArmed()) {
                        int row = i;
                        int column = j;
                        ++row;
                        ++column;
                        gameView.messageDisplayTextArea.append((BLANK_SPACE + "Button" + "[" + (row) + "," + (column) + "]" + " is Pressed!!!\n"));

                    }
                    if(e.getSource()  == this.gameView.newGame){
                        if(this.gameModel.config[i][j] == 1){
                            //set color
                            //this.gameView.UnitOfBoardButton[i][j].set
                        }else if(this.gameModel.config[i][j] == 0){
                            //set color

                        }
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

    private int seconds;    Timer timer;    TimerTask timerTask;
    public void startTimer() {// Timer task
        timerTask = new TimerTask() {
            @Override
            public void run() {
                seconds++;// Update your interface
                gameView.timeTextField.setText(String.valueOf(seconds));
            }
        };
        try {
            timer.scheduleAtFixedRate(timerTask, 0, 1000);
        } catch(Exception e) {// Eventual treatment}}
        }
    }


}
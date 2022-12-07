package Piccross;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class GameController implements ActionListener {
    private GameModel gameModel;
    private GameView gameView;

    private boolean [][]isButtonClicked;

    int pointsCount;
    int markCheckBoxCount;
    boolean isNewGameClicked;

    public GameController(GameModel gameModel,GameView  gameView) {
        //When game starts, initial the followings properties to its defaults.
        this.pointsCount = 0;
        this.markCheckBoxCount = 0;
        this.isNewGameClicked = false;
        this.gameModel = gameModel;
        this.gameView = gameView;

        this.initIsButtonClicked();
        this.addActionListener();
        this.startTimer();
    }


    private void addActionListener(){


        //add ActionListener
        this.gameView.getResetButton().addActionListener(this);
        this.gameView.getMarkCheckbox().addActionListener(this);
        this.gameView.getColorsMenuItem().addActionListener(this);
        this.gameView.getAboutMenuItem().addActionListener(this);
        this.gameView.getExitMenuItem().addActionListener(this);
        this.gameView.getNewGame().addActionListener(this);
        this.gameView.getSolutionMenuItem().addActionListener(this);

        //Add action listener for add each unit of the game board, in order to let player be able to play the game.
        //=================================================================================

        for (int i = 0; i < this.gameView.getUnitOfBoardButton().length; i++) {
            for (int j = 0; j <this.gameView.getUnitOfBoardButton()[i].length; j++) {
                this.gameView.getUnitOfBoardButton()[i][j].addActionListener(this);
            }

        }
        //=================================================================================
    }

        @Override
        public void actionPerformed (ActionEvent e){


        if (e.getSource() == gameView.getNewGame()) {
                isNewGameClicked = true;
                //Default setting of the game
                this.gameModel.initGame();

                resetGame();

            }
            if(!isNewGameClicked){
                if(e.getSource() != null){
                    this.gameView.msgDisplayTextArea.append(PgmConfigs.NEW_GAME_MSG);
                }
            }else {
                if (e.getSource() == this.gameView.getResetButton()) {
                    // reset button is clicked
                    gameView.msgDisplayTextArea.append(PgmConfigs.BLANK_SPACE + PgmConfigs.RESET_BUTTON_REACTION);

                    resetGame();

                } else if (e.getSource() == this.gameView.getMarkCheckbox()) {
                    //markCheckbox is clicked
                    //determine markCheckBoxCount is odd number or even number
                    if (markCheckBoxCount % 2 != 0) {
                        //odd number behavior -> mark box unchecked
                        gameView.msgDisplayTextArea.append(PgmConfigs.BLANK_SPACE_UNCHK_BOX + PgmConfigs.UNCHECK_MARK_CHECKBOX_REACTION);
                    } else {
                        //odd number behavior -> mark box checked
                        gameView.msgDisplayTextArea.append(PgmConfigs.BLANK_SPACE_CHK_BOX + PgmConfigs.CHECK_MARK_CHECKBOX_REACTION);

                    }
                    markCheckBoxCount++;
                    //new Game
                } else if (e.getSource() == this.gameView.solutionMenuItem) {

                    this.gameView.msgDisplayTextArea.append(this.gameModel.solutionTokenizer());

                } else if (e.getSource() == this.gameView.colorsMenuItem) {

                    this.gameView.initColorChooser();
                    this.gameView.correctColorButton.addActionListener(this);
                    this.gameView.markedColorButton.addActionListener(this);
                    this.gameView.errorColorButton.addActionListener(this);
                } else if (e.getSource() == this.gameView.exitMenuItem) {

                    this.gameView.mainFrame.setVisible(false);


                } else if (e.getSource() == this.gameView.aboutMenuItem) {
                    try {
                        JOptionPane.showMessageDialog(this.gameView.mainFrame, new ImageIcon(PgmConfigs.RESOURCE_PATH + PgmConfigs.DIALOG_IMAGE), PgmConfigs.ABOUT_AUTHOR_SIGNATURE, JOptionPane.PLAIN_MESSAGE);
                    } catch (Exception exception) {
                        System.err.println("Dialog at aboutMenuItem exception!!!");
                    }
                } else if (e.getSource() == this.gameView.correctColorButton) {
                    this.gameView.initCorrectColorChooser();
                } else if (e.getSource() == this.gameView.markedColorButton) {
                    this.gameView.initMarkedColorChooser();
                } else if (e.getSource() == this.gameView.errorColorButton) {
                    this.gameView.initErrorColorChooser();
                }


                // Game Board is clicked behaviors
                for (int i = 0; i < this.gameModel.getNumberOfRow(); i++) {
                    for (int j = 0; j < this.gameModel.getNumberOfColumn(); j++) {
                        // Responds after Board unit get clicked.
                        if (this.gameView.unitOfBoardButton[i][j].getModel().isArmed()) {
                            int row = i;
                            int column = j;

                            isButtonClicked[i][j] = true;
                            gameView.msgDisplayTextArea.append((PgmConfigs.BLANK_SPACE + "Button" + "[" + (++row) + "," + (++column) + "]" + " is Pressed!!!\n"));
                            if (this.gameView.markCheckbox.isSelected()) {
                                //if configuration match the belonging pattern,
                                if (this.gameModel.config[i][j] == 0) {
                                    // point +1
                                    ++pointsCount;
                                    this.gameView.unitOfBoardButton[i][j].setBackground(this.gameView.markedColor);
                                } else if (this.gameModel.config[i][j] == 1) {
                                    --pointsCount;
                                    this.gameView.unitOfBoardButton[i][j].setBackground(this.gameView.errorColor);

                                }


                            } else if (!(this.gameView.markCheckbox.isSelected())) {
                                if (this.gameModel.config[i][j] == 1) {
                                    ++pointsCount;
                                    this.gameView.unitOfBoardButton[i][j].setBackground(this.gameView.correctColor);


                                }
                                if (this.gameModel.config[i][j] == 0) {
                                    --pointsCount;
                                    this.gameView.unitOfBoardButton[i][j].setBackground(this.gameView.errorColor);

                                }

                            }

                            this.gameView.pointsTextField.setText(String.valueOf(pointsCount));
                        }
                    }
                }

                if (isAllButtonClicked()) {
                    //accumulated points reached  C*R
                    if (pointsCount == (this.gameModel.getNumberOfColumn() * this.gameModel.getNumberOfRow())) {
                        //displaySplashScreen(1000);
                        this.gameView.msgDisplayTextArea.append("                    Perfect Game!\n");
                        this.gameView.msgDisplayTextArea.append("                      Points: " + pointsCount + "\n");
                        this.gameView.msgDisplayTextArea.append("                       Time: " + seconds + "\n");

                        //Display PerfectGame
                        try {
                            JOptionPane.showMessageDialog(this.gameView.mainFrame, new ImageIcon(PgmConfigs.RESOURCE_PATH + PgmConfigs.PERFECT_GAME_DIALOG), PgmConfigs.PERFECT_GAME_MSG, JOptionPane.PLAIN_MESSAGE);
                        } catch (Exception ex) {
                            System.err.println("exception");
                        }

                        //Restart game
                        this.gameModel.initGame();
                        resetGame();
                    } else if (pointsCount < (this.gameModel.getNumberOfColumn() * this.gameModel.getNumberOfRow())) {

                        try {
                            JOptionPane.showMessageDialog(this.gameView.mainFrame, new ImageIcon(PgmConfigs.RESOURCE_PATH + PgmConfigs.SORRY_GAME_DIALOG), PgmConfigs.SORRY_GAME_MSG, JOptionPane.PLAIN_MESSAGE);
                        } catch (Exception ex) {
                            System.err.println("exception");
                        }

                        //Restart game
                        this.gameModel.initGame();
                        resetGame();

                    }
                }
            }
        }

        private void initIsButtonClicked(){
            isButtonClicked = new boolean[this.gameModel.getNumberOfRow()][this.gameModel.getNumberOfColumn()];
            for (int i = 0; i < this.gameModel.getNumberOfRow(); i++) {
                for (int j = 0; j < this.gameModel.getNumberOfColumn(); j++) {
                        isButtonClicked[i][j] = false;
                    }
                }

        }

        private boolean isAllButtonClicked(){

            for (int i = 0; i < this.gameModel.getNumberOfRow(); i++) {
                for (int j = 0; j < this.gameModel.getNumberOfColumn(); j++) {
                    if(!isButtonClicked[i][j]){
                        return false;
                    }
                }
            }
        return true;
        }


     void resetGame() {
        this.gameView.msgDisplayTextArea.setText("");
        this.gameView.pointsTextField.setText("0");
        pointsCount = 0;
        seconds = 0;


        for (int i = 0;i<this.gameModel.getNumberOfColumn();i++){
            for (int j = 0;j<this.gameModel.getNumberOfColumn();j++){
                this.gameView.unitOfBoardButton[i][j].setBackground(Color.lightGray);
            }
        }

        this.gameView.columnLabelString = this.gameModel.columnNumLabelArray;
        this.gameView.rowLabelString = this.gameModel.getLeftNumLabel();
        this.gameView.updateLabelView();
        this.initIsButtonClicked();
    }




    private int seconds;    ScheduledExecutorService timer;    Runnable task;
    public void startTimer() {// Timer task
        timer = Executors.newSingleThreadScheduledExecutor();
        task = new Runnable() {
            @Override
            public void run() {
                ++seconds;// Update your interface
                gameView.timeTextField.setText((seconds)+"s");
            }
        };
        try {
            timer.scheduleAtFixedRate(task,0,1, TimeUnit.SECONDS);
        } catch(Exception e) {// Eventual treatment}}
        }
    }


}
package Piccross;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class GameController implements ActionListener {
    private GameModel gameModel;
    private GameView gameView;

    final String RESET_BUTTON_REACTION = "Reset Button pressed!!\n";
    final String UNCHECK_MARK_CHECKBOX_REACTION = "Check Box \"Mark\" UnChecked!!\n";
    final String CHECK_MARK_CHECKBOX_REACTION = "Check Box \"Mark\" Checked!!\n";
    final String BLANK_SPACE = "           ";
    final String BLANK_SPACE_CHK_BOX = "        ";
    final String BLANK_SPACE_UNCHK_BOX = "      ";
    private final String RESOURCE_PATH = "A11\\src\\Piccross\\Resource\\";
    private final String DIALOG_IMAGE = "piccross.png";
    private final String ABOUT_AUTHOR_SIGNATURE = "Lin,Jiayu/Luo,Chang's Piccross Game ";
    private final String PERFECT_GAME_DIALOG = "gamepicwinner.png";
    private final String SORRY_GAME_DIALOG =    "gamepicend.png";
    private final String PERFECT_GAME_MSG = "Congrat!! You got perfect score";
    private final String SORRY_GAME_MSG = "Unfortunately,You didn't get perfect score";
    private boolean [][]isButtonClicked;
    private boolean isAllButtonClicked;

    int pointsCount = 0;
    int markCheckBoxCount = 0;
    private boolean isNewGameClicked = false;

    public GameController(GameModel gameModel,GameView  gameView) {

        this.gameModel = gameModel;
        this.gameView = gameView;

        initIsButtonClicked();
        addActionListener();

        this.startTimer();
    }


    private void addActionListener(){


        //add ActionListener
        this.gameView.resetButton.addActionListener(this);
        this.gameView.markCheckbox.addActionListener(this);
        this.gameView.colorsMenuItem.addActionListener(this);
        this.gameView.aboutMenuItem.addActionListener(this);
        this.gameView.exitMenuItem.addActionListener(this);
        this.gameView.newGame.addActionListener(this);
        this.gameView.solutionMenuItem.addActionListener(this);

//Array for add each unit of the game board's action listener
//=================================================================================

        for (int i = 0; i < this.gameView.unitOfBoardButton.length; i++) {
            for (int j = 0; j <this.gameView.unitOfBoardButton[i].length; j++) {
                this.gameView.unitOfBoardButton[i][j].addActionListener(this);
            }

        }
//=================================================================================



    }

        @Override
        public void actionPerformed (ActionEvent e){


        if (e.getSource() == gameView.newGame) {
                isNewGameClicked = true;
                //Default setting of the game
                this.gameModel.initGame();

                resetGame();

            }
            if(!isNewGameClicked){
                if(e.getSource() != null){
                    this.gameView.msgDisplayTextArea.append("Please go to\nGame > New Game\nto Start a new game\n");
                }

            }else {
                if (e.getSource() == gameView.resetButton) {
                    // reset button is clicked
                    gameView.msgDisplayTextArea.append(BLANK_SPACE + RESET_BUTTON_REACTION);

                    resetGame();

                } else if (e.getSource() == gameView.markCheckbox) {
                    //markCheckbox is clicked
                    //determine markCheckBoxCount is odd number or even number
                    if (markCheckBoxCount % 2 != 0) {
                        //odd number behavior -> mark box unchecked
                        gameView.msgDisplayTextArea.append(BLANK_SPACE_UNCHK_BOX + UNCHECK_MARK_CHECKBOX_REACTION);
                    } else {
                        //odd number behavior -> mark box checked
                        gameView.msgDisplayTextArea.append(BLANK_SPACE_CHK_BOX + CHECK_MARK_CHECKBOX_REACTION);

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

                    System.exit(0);

                } else if (e.getSource() == this.gameView.aboutMenuItem) {
                    try {
                        JOptionPane.showMessageDialog(this.gameView.mainFrame, new ImageIcon(RESOURCE_PATH + DIALOG_IMAGE), ABOUT_AUTHOR_SIGNATURE, JOptionPane.PLAIN_MESSAGE);
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
                for (int i = 0; i < this.gameModel.numberOfRow; i++) {
                    for (int j = 0; j < this.gameModel.numberOfColumn; j++) {
                        // Responds after Board unit get clicked.
                        if (this.gameView.unitOfBoardButton[i][j].getModel().isArmed()) {
                            int row = i;
                            int column = j;

                            isButtonClicked[i][j] = true;
                            gameView.msgDisplayTextArea.append((BLANK_SPACE + "Button" + "[" + (++row) + "," + (++column) + "]" + " is Pressed!!!\n"));
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
                    if (pointsCount == (this.gameModel.numberOfColumn * this.gameModel.numberOfRow)) {
                        //displaySplashScreen(1000);
                        this.gameView.msgDisplayTextArea.append("                    Perfect Game!\n");
                        this.gameView.msgDisplayTextArea.append("                      Points: " + pointsCount + "\n");
                        this.gameView.msgDisplayTextArea.append("                       Time: " + seconds + "\n");

                        //Display PerfectGame
                        try {
                            JOptionPane.showMessageDialog(this.gameView.mainFrame, new ImageIcon(RESOURCE_PATH + PERFECT_GAME_DIALOG), PERFECT_GAME_MSG, JOptionPane.PLAIN_MESSAGE);
                        } catch (Exception ex) {
                            System.err.println("exception");
                        }

                        //Restart game
                        this.gameModel.initGame();
                        resetGame();
                    } else if (pointsCount < (this.gameModel.numberOfColumn * this.gameModel.numberOfRow)) {

                        try {
                            JOptionPane.showMessageDialog(this.gameView.mainFrame, new ImageIcon(RESOURCE_PATH + SORRY_GAME_DIALOG), SORRY_GAME_MSG, JOptionPane.PLAIN_MESSAGE);
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
            isButtonClicked = new boolean[this.gameModel.numberOfRow][this.gameModel.numberOfColumn];
            for (int i = 0; i < this.gameModel.numberOfRow; i++) {
                for (int j = 0; j < this.gameModel.numberOfColumn; j++) {
                        isButtonClicked[i][j] = false;
                    }
                }

        }

        private boolean isAllButtonClicked(){
            isAllButtonClicked =true;
            for (int i = 0; i < this.gameModel.numberOfRow; i++) {
                for (int j = 0; j < this.gameModel.numberOfColumn; j++) {
                    if(!isButtonClicked[i][j]){isAllButtonClicked = false;
                        return isAllButtonClicked;
                    }
                }

            }
        return isAllButtonClicked;
        }


    private void resetGame() {
        this.gameView.msgDisplayTextArea.setText("");
        this.gameView.pointsTextField.setText("0");
        pointsCount = 0;
        seconds = 0;


        for (int i = 0;i<this.gameModel.numberOfColumn;i++){
            for (int j = 0;j<this.gameModel.numberOfColumn;j++){
                this.gameView.unitOfBoardButton[i][j].setBackground(Color.lightGray);
            }
        }

        this.gameView.columnLabelString = this.gameModel.columnNumLabelArray;
        this.gameView.rowLabelString = this.gameModel.rowNumLabelArray;
        this.gameView.updateLabelView();
        this.initIsButtonClicked();
    }




    private int seconds;    ScheduledExecutorService timer;    Runnable task;
    public void startTimer() {// Timer task
        timer = Executors.newSingleThreadScheduledExecutor();
        task = new Runnable() {
            @Override
            public void run() {
                seconds++;// Update your interface
                gameView.timeTextField.setText((seconds)+"s");
            }
        };
        try {
            timer.scheduleAtFixedRate(task,0,1, TimeUnit.SECONDS);
        } catch(Exception e) {// Eventual treatment}}
        }
    }


}
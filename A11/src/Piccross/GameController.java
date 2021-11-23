package Piccross;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.util.Timer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


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
    private final String RESOURCE_PATH = "A11\\src\\Piccross\\Resource\\";
    String DIALOG_IMAGE = "piccross.png";
    final String AUTHOR_SIGNATURE = "Lin,Jiayu/Luo,Chang's Piccross Game ";
    int pointsCount = 0;

    public GameController(GameModel gameModel,GameView  gameView) {

        this.gameModel = gameModel;
        this.gameView = gameView;
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


//=================================================================================

        for (int i = 0; i < this.gameView.UnitOfBoardButton.length; i++) {
            for (int j = 0; j <this.gameView.UnitOfBoardButton[i].length; j++) {
                this.gameView.UnitOfBoardButton[i][j].addActionListener(this);
            }

        }
//=================================================================================



    }



        //new Game================



        @Override
        public void actionPerformed (ActionEvent e){

            if (e.getSource() == gameView.resetButton) {
                // reset button is clicked
                gameView.msgDisplayTextArea.append(BLANK_SPACE+RESET_BUTTON_REACTION);

                resetGame();

            } else if (e.getSource() == gameView.markCheckbox) {
                //markCheckbox is clicked
                //determine markCheckBoxCount is odd number or even number
                if (markCheckBoxCount % 2 != 0) {
                    //odd number behavior -> mark box unchecked
                    gameView.msgDisplayTextArea.append(BLANK_SPACE_UNCHK_BOX+UNCHECK_MARK_CHECKBOX_REACTION);
                } else {
                    //odd number behavior -> mark box checked
                    gameView.msgDisplayTextArea.append(BLANK_SPACE_CHK_BOX+CHECK_MARK_CHECKBOX_REACTION);

                }
                markCheckBoxCount++;
                //new Game
            } else if (e.getSource() == gameView.newGame) {
                //Default setting of the game
                this.gameModel.initGame();

                resetGame();

            } else if (e.getSource() == this.gameView.solutionMenuItem){

                this.gameView.msgDisplayTextArea.append(this.gameModel.solutionTokenizer());

            }else if(e.getSource() == this.gameView.colorsMenuItem){

                this.gameView.initColorChooser();
                this.gameView.correctColorButton.addActionListener(this);
                this.gameView.markedColorButton.addActionListener(this);
                this.gameView.errorColorButton.addActionListener(this);
            }else if(e.getSource() == this.gameView.exitMenuItem){

                System.exit(0);

            }else if(e.getSource() == this.gameView.aboutMenuItem){
                JOptionPane.showMessageDialog(this.gameView.mainFrame, new ImageIcon(RESOURCE_PATH+DIALOG_IMAGE),AUTHOR_SIGNATURE,JOptionPane.PLAIN_MESSAGE);

            }else if(e.getSource() == this.gameView.correctColorButton){
                this.gameView.initCorrectColorChooser();
            }else if(e.getSource() == this.gameView.markedColorButton){
                this.gameView.initMarkedColorChooser();
            }else if(e.getSource() == this.gameView.errorColorButton){
                this.gameView.initErrorColorChooser();
            }

            // Game Board is clicked behaviors
            for (int i = 0; i < this.gameModel.numberOfRow; i++) {
                for (int j = 0; j < this.gameModel.numberOfColumn; j++) {
                    // Responds after Board unit get clicked.
                    if (this.gameView.UnitOfBoardButton[i][j].getModel().isArmed()) {
                        int row = i;
                        int column = j;

                        gameView.msgDisplayTextArea.append((BLANK_SPACE + "Button" + "[" + (++row) + "," + (++column) + "]" + " is Pressed!!!\n"));
                        if(this.gameView.markCheckbox.isSelected()){
                            //if configuration match the belonging pattern,
                            if(this.gameModel.config[i][j] == 1){
                                // point +1
                                ++pointsCount;
                                this.gameView.UnitOfBoardButton[i][j].setBackground(this.gameView.markedColor);


                            if(this.gameModel.config[i][j] == 0){
                                    --pointsCount;
                                    this.gameView.UnitOfBoardButton[i][j].setBackground(this.gameView.errorColor);
                                }
                            }


                        }else{
                                if(this.gameModel.config[i][j] == 1){
                                    ++pointsCount;
                                    this.gameView.UnitOfBoardButton[i][j].setBackground(this.gameView.correctColor);


                                }
                                if(this.gameModel.config[i][j] == 0){
                                    --pointsCount;
                                    this.gameView.UnitOfBoardButton[i][j].setBackground(this.gameView.errorColor);
                                }

                        }
                        if (pointsCount == 25){
                            //displaySplashScreen(1000);
                            this.gameView.msgDisplayTextArea.append("Perfect Game!\npoints: " +pointsCount+"\nTime: "+seconds);
                        }
                        this.gameView.pointsTextField.setText(String.valueOf(pointsCount));
                    }


                }
            }

        }

    private void resetGame() {
        this.gameView.msgDisplayTextArea.setText("");
        this.gameView.pointsTextField.setText("0");
        pointsCount = 0;
        seconds = 0;

        for (int i = 0;i<this.gameModel.numberOfColumn;i++){
            for (int j = 0;j<this.gameModel.numberOfColumn;j++){
                this.gameView.UnitOfBoardButton[i][j].setBackground(Color.lightGray);
            }
        }

        this.gameView.columnLabelString = this.gameModel.columnNumLabelArray;
        this.gameView.rowLabelString = this.gameModel.rowNumLabelArray;
        this.gameModel.printResult();
       this.gameView.updatetopView();
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
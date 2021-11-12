package Piccross;

import java.util.Random;

public class GameModel {

    public int numberOfRow;
    public int numberOfColumn;

    int[][] config = new int[numberOfRow][numberOfColumn];

    public String[] rowNumLabelArray = new String[numberOfRow];
    public String[] columnNumLabelArray = new String[numberOfColumn];

    public GameModel(){
    initGame();

    calculateColumnLabelArray();
    calculateRowLabelArray();
    printResult();

    }

    public  void initGame() {

        for (int i = 0; i < numberOfRow; i++) {
            for (int j = 0; j < numberOfColumn; j++) {
                config[i][j] = new Random().nextInt(2);
            }
        }



    }

    public void calculateRowLabelArray(){
        int accumulatedRowValue = 0;
        for (int i = 0; i < numberOfRow; i++) {
            rowNumLabelArray[i] = "";
            for (int j = 0; j < numberOfColumn; j++) {
                if(config[i][j] == 1){
                    accumulatedRowValue++;

                    if(j == (numberOfColumn -1)){

                        rowNumLabelArray[i] += accumulatedRowValue;
                    }
                }if(config[i][j]== 0){
                    if(accumulatedRowValue != 0){

                        rowNumLabelArray[i] += accumulatedRowValue + ",";

                    }
                    //reset to 0
                    accumulatedRowValue = 0;

                }
            }
            //reset to 0
            accumulatedRowValue = 0;
        }

    }

    public void calculateColumnLabelArray(){
        int accumulatedColumnValue = 0;
        for (int j = 0; j < numberOfColumn; j++)  {
            columnNumLabelArray[j] = "";
            for (int i = 0; i < numberOfRow; i++) {
                if(config[i][j] == 1){
                    accumulatedColumnValue++;
                    if(i == (numberOfColumn - 1)){
                        columnNumLabelArray[j] += accumulatedColumnValue;
                    }
                }if(config[i][j]== 0){
                    if(accumulatedColumnValue != 0){
                        columnNumLabelArray[j] += accumulatedColumnValue + ",";
                    }
                    //reset to 0
                    accumulatedColumnValue = 0;

                }
            }
            //reset to 0
            accumulatedColumnValue = 0;
        }
  

    }




    public void printResult() {
        for (int i = 0; i < numberOfRow; i++) {
            for (int j = 0; j < numberOfColumn; j++) {
                System.out.print(config[i][j] );
            }
            System.out.println();
        }
        for (int i = 0; i < numberOfColumn; i++) {
            System.out.println(columnNumLabelArray[i]);

        }
        System.out.println("==================");
        for (int i = 0; i < numberOfColumn; i++) {
            System.out.println(rowNumLabelArray[i]);

        }
    }

    public static void main(String[] args) {
        GameModel gm = new GameModel();
    }
}

package Piccross;

import java.util.Random;

public class GameModel {
    private int numberOfRow ;
    private int numberOfColumn;

    int[][] config;

    public String[] columnNumLabelArray;

    public String[] getLeftNumLabel() {
        return leftNumLabel;
    }

    public void setLeftNumLabel(String[] leftNumLabel) {
        this.leftNumLabel = leftNumLabel;
    }

    private String[] leftNumLabel;

    public GameModel(){
    initGame();

    }

    public  void initGame() {
        numberOfColumn =5;
        numberOfRow =5;
        config = new int[numberOfRow][numberOfColumn];
        for (int i = 0; i < numberOfRow; i++) {
            for (int j = 0; j < numberOfColumn; j++) {
                config[i][j] = new Random().nextInt(2);
            }
        }
        calculateColumnLabelArray();
        setLeftNumLabel(calcLeftPanel(5,5));
        //calculateRowLabelArray();


    }




    //TODO: Redesign the algorithm
//    public void calculateRowLabelArray(){
//        rowNumLabelArray = new String[numberOfRow];
//        int accumulatedRowValue = 0;
//        for (int i = 0; i < numberOfRow; i++) {
//            rowNumLabelArray[i] = "";
//            for (int j = 0; j < numberOfColumn; j++) {
//                if(config[i][j] == 1){
//                    accumulatedRowValue++;
//
//                    if(j == (numberOfColumn -1)){
//
//                        rowNumLabelArray[i] += accumulatedRowValue;
//                    }
//                }if(config[i][j]== 0){
//                    if(accumulatedRowValue != 0){
//                        rowNumLabelArray[i] += accumulatedRowValue + ",";
//                    }
//
//
//                    //reset to 0
//                    accumulatedRowValue = 0;
//
//                }
//            }
//            //reset to 0
//            accumulatedRowValue = 0;
//        }
//
//    }

    public String[] calcLeftPanel(int numOfRow,int numOfColumn){
        String[] lPanel = new String[numOfRow];
        int numOfOne = 0;

        for (int i = 0; i < this.getNumberOfRow(); i++) {
            //Each row own its label, so initialize it every time 'i' increase.
            lPanel[i] = "";
            for (int j = 0; j < this.getNumberOfColumn(); j++) {
                //From left to right, the pointer moves after j increase by 1.
                // 1 means the square are going to score
                if(this.config[i][j] == 1){
                    //When the pointer encountered 1, numOfOne increase by 1
                    ++numOfOne;

                    //When the pointer reached the last column which is most right element in a row,
                    // make it to the label array
                    if(j == (getNumberOfColumn() - 1)) lPanel[i] += numOfOne;

                }
                // 0 means the square need to score in the 'mark' mode
                if(this.config[i][j] == 0){
                    // To avoid the String start at ,3,1 in 0011101, comma is at the beginning
                    // Add comma when this condition applies, numOfOne not equals to zero.
                    if(numOfOne != 0){
                        lPanel[i] += numOfOne + ",";
                    }

                    //Reset the numOfOne to zero, recalculate the numOfOne when the pointer encountered 0
                    numOfOne = 0;
                }

                if(this.config[i][getNumberOfColumn()-1] == 0 && j == getNumberOfColumn()-1){
                    if(lPanel[i].length() >1){
                        //delete the comma in the end.
                        lPanel[i] = lPanel[i].substring(0,lPanel[i].length()-1);
                    }
                }


            }
            //reset to 0, when change row.
            numOfOne = 0;
        }
        return lPanel;
    }

    public void calculateColumnLabelArray(){
        columnNumLabelArray = new String[numberOfColumn];
        int accumulatedColumnValue = 0;
        for (int j = 0; j < numberOfColumn; j++)  {
            columnNumLabelArray[j] = "";
            for (int i = 0; i < numberOfRow; i++) {
                if(config[i][j] == 1){
                    accumulatedColumnValue++;
                    if(i == (numberOfColumn - 1)){
                        columnNumLabelArray[j] += accumulatedColumnValue;
                    }
                } else if(config[i][j]== 0){
                    if(accumulatedColumnValue != 0){
                        columnNumLabelArray[j] += accumulatedColumnValue + ",";
                    }

                    //reset to 0
                    accumulatedColumnValue = 0;

                }if(config[numberOfRow -1][j] == 0){
                    if(columnNumLabelArray[j].length() >1&& i == numberOfRow -1)
                        columnNumLabelArray[j] = columnNumLabelArray[j].substring(0,columnNumLabelArray[j].length() -1);
                }
            }
            //reset to 0
            accumulatedColumnValue = 0;
        }

    }




    public String solutionTokenizer() {
        String solutionString = "";
        solutionString += "                        Solution:\n                          ";
        for (int i = 0; i < numberOfRow; i++) {
            for (int j = 0; j < numberOfColumn; j++) {
                solutionString += config[i][j];
            }
            solutionString += "\n                          ";
        }
        solutionString +="\n";
        return solutionString;
    }

    public String solutionTokenizerForClient() {
        String solutionString = "";

        for (int i = 0; i < numberOfRow; i++) {
            for (int j = 0; j < numberOfColumn; j++) {
                solutionString += config[i][j];
            }
            // No comma at the end
            if(i == numberOfRow -1){

            }else {
                solutionString += ",";
            }
        }
        solutionString +="\n";
        return solutionString;
    }
    public void printResult() {
        for (int i = 0; i < numberOfRow; i++) {
            for (int j = 0; j < numberOfColumn; j++) {
                System.out.print(config[i][j] );
            }
            System.out.println();
        }
        System.out.println("=>columnNumLabelArray");
        for (int i = 0; i < numberOfColumn; i++) {
            System.out.println(columnNumLabelArray[i]);

        }
        System.out.println("=>rowNumLabelArray");
        for (int i = 0; i < numberOfColumn; i++) {
          //  System.out.println(rowNumLabelArray[i]);
            System.out.println(leftNumLabel[i]);

        }
    }
//=========
    //Getters and Setters
    public int getNumberOfRow() {
        return numberOfRow;
    }

    public void setNumberOfRow(int numberOfRow) {
        this.numberOfRow = numberOfRow;
    }

    public int getNumberOfColumn() {
        return numberOfColumn;
    }

    public void setNumberOfColumn(int numberOfColumn) {
        this.numberOfColumn = numberOfColumn;
    }
//===========

    public static void main(String[] args) {
        GameModel f = new GameModel();
        f.printResult();
    }
}

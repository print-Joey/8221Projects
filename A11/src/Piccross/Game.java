package Piccross;

import java.awt.*;
public class Game {
    public static void main(String[] args){

       // SplashScreen splashScreenObj = new SplashScreen(1000);


        GameModel gameModel = new GameModel();
        GameView gameView = new GameView();
        GameController gameController = new GameController(gameModel,gameView);


    }

}

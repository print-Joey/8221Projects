package Piccross;

import java.awt.*;
public class Game {
    public static void main(String[] args) {


        if(args == null|| args.length == 0){
            System.err.println("Error !!!Please Provide argument to execute ");
            System.err.println("eg. java Piccross.Game C");
        }else
        if ("MVC".equals(args[0])) {
            GameModel gameModel = new GameModel();
            GameView gameView = new GameView();
            GameController gameController = new GameController(gameModel, gameView);
        } else if ("S".equals(args[0])) {
            GameServer gameServer = new GameServer();
        } else if ("C".equals(args[0])) {
            GameClient gameClient = new GameClient();

        }

    }
}
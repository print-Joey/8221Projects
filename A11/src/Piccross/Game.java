package Piccross;

import java.awt.*;
public class Game {
    public static void main(String[] args) {

        // SplashScreen splashScreenObj = new SplashScreen(1000);

        if ("MVC".equals(args[0])) {
            GameModel gameModel = new GameModel();
            GameView gameView = new GameView();
            GameController gameController = new GameController(gameModel, gameView);
        } else if ("S".equals(args[1])) {
            GameServer gameServer = new GameServer();
        } else if ("C".equals(args[2])) {
            GameClient gameClient = new GameClient();

        }

    }
}
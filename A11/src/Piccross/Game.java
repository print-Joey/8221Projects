package Piccross;

import java.awt.*;
public class Game {
    public static void main(String[] args) throws InterruptedException {

        SplashScreen splashScreenObj = new SplashScreen(1000);

        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                GameController gameController = new GameController();
            }
        });
    }

}

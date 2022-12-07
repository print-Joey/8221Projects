package Piccross;


/**
 * Game.java
 * @author Jiayu
 * */
public class Game {
    public static void main(String[] args) {

        if(args == null || args.length != 1){
            System.err.println("Error!!!  Please Provide correct argument to execute!!!");
            System.err.println("============================================");
            System.err.println("Usage ==> java Piccross.Game [C/S/MVC]");




        }else  if (PgmConfigs.MVC.equals(args[0])) {
            new GameController(new GameModel(), new GameView());
        } else if (PgmConfigs.SERVER.equals(args[0])) {
            new GameServer();
        } else if (PgmConfigs.CLIENT.equals(args[0])) {
            new GameClient();
        }

    }
}
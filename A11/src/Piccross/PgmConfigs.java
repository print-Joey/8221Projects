package Piccross;

import java.io.File;

public class PgmConfigs {

//Server
    static public final String SERVER_PIC = "piccorssLogoServer.png";
    static public final String SERVER_LOGO = "icon.jpg";
    static public final String SERVER_TITLE = "Piccross Server";
    static public final int NUM_OF_COLUMN_OF_TEXT_FIELD = 8;
    static public final int THICKNESS_OF_BORDER = 5;
    // Constants =>client
    // In order to fit all to operate system, in other word cross-platform, change \\ to File.separator
    // Original: static public final String RESOURCE_PATH = "8221Projects\\A11\\src\\Piccross\\Resource\\";
    // Change To:======>
    static public final String RESOURCE_PATH = "A11"+ File.separator+"src"+File.separator+"Piccross"+File.separator+"Resource"+File.separator;

    // Change To:======^
    static public final String CLIENT_PIC = "piccorssLogoClient.png";
    static public final String CLIENT_TITLE = "Piccross Client";
    static public final String CLIENT_LOGO = "icon.jpg";
    static public final String END_CONNECTION_SYMBOOL = "0";

    public static final String SEPARATOR = "#";
    public static final String PROTOCOL = "P";


//controller
    public static final String RESET_BUTTON_REACTION = "Reset Button pressed!!\n";
    public static final String UNCHECK_MARK_CHECKBOX_REACTION = "Check Box \"Mark\" UnChecked!!\n";
    public static final String CHECK_MARK_CHECKBOX_REACTION = "Check Box \"Mark\" Checked!!\n";
    public static final String BLANK_SPACE = "           ";
    public static final String BLANK_SPACE_CHK_BOX = "        ";
    public static final String BLANK_SPACE_UNCHK_BOX = "      ";
    public static final String DIALOG_IMAGE = "piccross.png";
    public static final String ABOUT_AUTHOR_SIGNATURE = "Lin,Jiayu/Luo,Chang's Piccross Game ";
    public static final String PERFECT_GAME_DIALOG = "gamepicwinner.png";
    public static final String SORRY_GAME_DIALOG =    "gamepicend.png";
    public static final String PERFECT_GAME_MSG = "Congrat!! You got perfect score";
    public static final String SORRY_GAME_MSG = "Unfortunately,You didn't get perfect score";

    //================================================================
    //GameView
    //Constants
    public   static final String AUTHOR_SIGNATURE                                     = "Lin,Jiayu/Luo,Chang's Piccross Game Staring....";
    public static final String INTERRUPTED_EXCEPTION                                = "InterruptedException!!";
    public static final String POINTS                                               = "Points:  ";
    public static final String TIME                                                 = "Time:  ";
    public static final String RESET                                                = "Reset";
    public static final String FILE_NOT_FOUND                                       = "File not found!!";
    public static final String TITLE                                                = "Piccross";

    public static final String LOGO_PATH                                            = "piccrossNameMin.jpg";
    public static final String NEW_GAME_LOGO_PATH                                   = "piciconnew.gif";
    public static final String SOLUTION_LOGO_PATH                                   = "piciconsol.gif";
    public static final String ABOUT_LOGO_PATH                                      = "piciconabt.gif";
    public static final String EXIT_LOGO_PATH                                       = "piciconext.gif";
    public static final String COLOR_LOGO_PATH                                      = "piciconcol.gif";
    public static final String SPLASH_IMAGE_PATH                                    = "piccrossLogo.jpg";
    public static final String MAIN_FRAME_ICON = "icon.jpg";

    public static final String  NEW_GAME_MSG = "\"Please go to\\nGame > New Game\\nto Start a new game\\n\"";
    //================================================================
    //Game.java
    public static final String MVC = "MVC";
    public static final String SERVER = "S";
    public static final String CLIENT = "C";
}

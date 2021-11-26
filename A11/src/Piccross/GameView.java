package Piccross;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.Timer;
import java.util.TimerTask;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
//Con      Constructor flag Using CTRL + f to locate it







public class GameView extends JFrame {
    //Constants
    private final String AUTHOR_SIGNATURE                                     = "Lin,Jiayu/Luo,Chang's Piccross Game Staring....";
    private final String INTERRUPTED_EXCEPTION                                = "InterruptedException!!";
    private final String POINTS                                               = "Points:  ";
    private final String TIME                                                 = "Time:  ";
    private final String RESET                                                = "Reset";
    private final String FILE_NOT_FOUND                                       = "File not found!!";
    private final String TITLE                                                = "Piccross";

    private final String LOGO_PATH                                            = "piccrossNameMin.jpg";
    private final String NEW_GAME_LOGO_PATH                                   = "piciconnew.gif";
    private final String SOLUTION_LOGO_PATH                                   = "piciconsol.gif";
    private final String ABOUT_LOGO_PATH                                      = "piciconabt.gif";
    private final String EXIT_LOGO_PATH                                       = "piciconext.gif";
    private final String COLOR_LOGO_PATH                                      = "piciconcol.gif";
    private final String SPLASH_IMAGE_PATH                                    = "piccrossLogo.jpg";


    private final String RESOURCE_PATH = "A11\\src\\Piccross\\Resource\\";
private final String MAIN_FRAME_ICON = "icon.jpg";
    //================================================================


    //================================================================
    /* members of initMarkPanel()*/
    JPanel markPanel;
    JCheckBox markCheckbox;

    /**
     * The initMarkPanel simply returns a JPanel with the marks
     *
     * @author Jiayu Lin, Chang Luo
     * @version 1.1
     * @since 2021-09-24
     */
    public JPanel initMarkPanel() {
        markPanel = new JPanel();
        markCheckbox = new JCheckBox("Mark");

        //attributes

        // markCheckbox.       addActionListener(innerClassControllerObj);
        markCheckbox.setBackground(new Color(22, 186, 163));
        markCheckbox.setFont(new Font("TimesRoman", Font.BOLD, 12));

        markPanel.setBorder(new EmptyBorder(15, 10, 10, 10));

        //add
        markPanel.add(markCheckbox);


        return markPanel;
    }


    /* members of initTopPanel()*/
    JPanel topMainPanel;
    JLabel[] labelsOfTopPanel;
    JPanel[] columnOfLabelPanel;
    String[] columnLabelString = new String[]{"", "", "", "", ""};
    int numOfColumn = 5;
    //Set the panel which appears on the top

    /**
     * The initTopPanel returns the JPanel which is on the top
     *
     * @author Jiayu Lin, Chang Luo
     * @version 1.0
     * @since 2021-09-24
     */
    public JPanel initTopPanel() {



        topMainPanel = new JPanel(new GridLayout(1, numOfColumn));
        labelsOfTopPanel = new JLabel[numOfColumn];
        columnOfLabelPanel = new JPanel[numOfColumn];


        for (int i = 0; i < columnLabelString.length; i++) {
            labelsOfTopPanel[i] = new JLabel("(" + columnLabelString[i] + ")");
            columnOfLabelPanel[i] = new JPanel();

            //columnOfLabelPanel[i].setSize(new Dimension(10,10));
            //Set panel attributes
            columnOfLabelPanel[i].setBackground(new Color(179, 97, 16));
            //Set label attributes
            //labelsOfTopPanel[i].setHorizontalAlignment(SwingConstants.CENTER);
            labelsOfTopPanel[i].setFont(new Font("TimesRoman", Font.BOLD, 18));
            labelsOfTopPanel[i].setBorder(new EmptyBorder(10, 10, 10, 10));
            //add up
            columnOfLabelPanel[i].add(labelsOfTopPanel[i]);
            topMainPanel.add(columnOfLabelPanel[i]);
        }
        return topMainPanel;
    }


    /* members of initLeftPanel()*/
    JPanel leftMainPanel;

    JLabel[] labelsOfLeftPanel;
    JPanel[] rowOfLabelPanel;
    int numOfRow = 5;
    String[] rowLabelString = new String[]{"", "", "", "", ""};
    //Initiate the panel on the left

    /**
     * The initLeft Panel returns the JPanel on the Left
     *
     * @author Jiayu Lin, Chang Luo
     * @version 1.0
     * @since 2021-09-24
     */
    public JPanel initLeftPanel() {



        leftMainPanel = new JPanel(new GridLayout(numOfRow, 1));
        labelsOfLeftPanel = new JLabel[numOfRow];
        rowOfLabelPanel = new JPanel[numOfRow];



        for (int i = 0; i < rowLabelString.length; i++) {
            labelsOfLeftPanel[i] = new JLabel("(" + rowLabelString[i] + ")");
            rowOfLabelPanel[i] = new JPanel();

            //Set panel attributes
            rowOfLabelPanel[i].setBackground(new Color(179, 97, 16));
            rowOfLabelPanel[i].setBorder(new EmptyBorder(25, 0, 0, 0));

            //Set label attributes
            labelsOfLeftPanel[i].setFont(new Font("TimesRoman", Font.BOLD, 18));

            //add up
            rowOfLabelPanel[i].add(labelsOfLeftPanel[i]);
            leftMainPanel.add(rowOfLabelPanel[i]);
        }
        return leftMainPanel;
    }

    /* members of initBoardPanel()*/
    JPanel boardMainPanel;
    JButton[][] unitOfBoardButton;



    //Set the panel which is used to contain the board
    /**
     * Returns the JPanel which is used to contain the board of the game
     *
     * @author Jiayu Lin, Chang Luo
     * @version 1.0
     * @since 2021-09-24
     */

    public JPanel initBoardPanel() {
        boardMainPanel = new JPanel(new GridLayout(numOfRow, numOfColumn, 1, 1));

        unitOfBoardButton = new JButton[numOfRow][numOfColumn];

        for (int i = 0; i < unitOfBoardButton.length; i++) {
            for (int j = 0; j < unitOfBoardButton[i].length; j++) {
                unitOfBoardButton[i][j] = new JButton();
                unitOfBoardButton[i][j].setPreferredSize(new Dimension(100, 100));
                unitOfBoardButton[i][j].setBackground(Color.lightGray);
                //add all the buttons to the main panel
                boardMainPanel.add(unitOfBoardButton[i][j]);
            }
        }
        //Two border combine together makes nice looking
        Border b1 = BorderFactory.createEmptyBorder(5, 5, 0, 0);
        Border b2 = BorderFactory.createMatteBorder(2, 5, 5, 2, Color.GRAY);
        boardMainPanel.setBorder(new CompoundBorder(b1, b2));
        return boardMainPanel;
    }

    /* members of initControlPanel()*/
    JPanel controlMainPanel;
    JPanel northPanel, centerPanel, southPanel;
    JPanel logoPanel;
    JLabel logoLabel;
    JPanel pointsDisplayPanel;
    JLabel pointsLabel;
    JTextField pointsTextField;
    JScrollPane msgDisplayScrollPane;
    JTextArea msgDisplayTextArea;
    JPanel timePanel;
    JLabel timeLabel;
    JTextField timeTextField;
    JButton resetButton;
    JPanel resetPanel;
    //set the panel which is used to control the game

    /**
     * Return the JPanel which is used to control the game
     *
     * @author Jiayu Lin, Chang Luo
     * @version 1.0
     * @since 2021-09-24
     */

    public JPanel initControlPanel() {
        // init all panel in the control panel
        controlMainPanel = new JPanel(new BorderLayout());
        northPanel = new JPanel(new BorderLayout());
        centerPanel = new JPanel(new BorderLayout());
        southPanel = new JPanel(new BorderLayout());

        logoPanel = new JPanel();
        pointsLabel = new JLabel(POINTS);
        pointsDisplayPanel = new JPanel();

        msgDisplayTextArea = new JTextArea();
        msgDisplayScrollPane = new JScrollPane();


        timePanel = new JPanel();
        timeLabel = new JLabel(TIME);
        resetButton = new JButton();
        resetPanel = new JPanel();



        //logo panel member and behavior
        try {
            //Image Path may different in different IDEs
            logoLabel = new JLabel(new ImageIcon(RESOURCE_PATH + LOGO_PATH));
        } catch (Exception e) {
            System.err.println(FILE_NOT_FOUND);

        }
        logoPanel.add(logoLabel);

        //Points panel member and behavior

        pointsTextField = new JTextField("0", 5);

        pointsDisplayPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        //messageDisplayPanel member and behavior
        msgDisplayTextArea.setBackground(Color.WHITE);
        msgDisplayScrollPane.setViewportView(msgDisplayTextArea);
        msgDisplayScrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        msgDisplayScrollPane.setBorder(new CompoundBorder(new EmptyBorder(0,5,0,5), LineBorder.createBlackLineBorder())) ;


        //timePanel member and behavior
        //initiate the timer
        timeTextField = new JTextField("0s", 5);


        //set reset button property

        resetButton.setBackground(Color.ORANGE);

        // add up
        timePanel.add(timeLabel);
        timePanel.add(timeTextField);
        resetButton.add(new JLabel(RESET));
        resetPanel.add(resetButton);
        pointsDisplayPanel.add(pointsLabel);
        pointsDisplayPanel.add(pointsTextField);
        //layouts
        northPanel.add(logoPanel, BorderLayout.NORTH);
        northPanel.add(pointsDisplayPanel, CENTER);
        southPanel.add(timePanel, BorderLayout.NORTH);
        southPanel.add(resetPanel, CENTER);

        controlMainPanel.add(northPanel, BorderLayout.NORTH);
        controlMainPanel.add(msgDisplayScrollPane);
        controlMainPanel.add(southPanel, SOUTH);


        controlMainPanel.setBorder(new EmptyBorder(25, 0, 0, 0));

        return controlMainPanel;
    }

    /*member of addLeftAndMarkPanel*/
    JPanel combinedMarkLeftPanel;

    /**
     * Add Left Panel and Mark Panel to the same JPanel and return it
     *
     * @author Jiayu Lin, Chang Luo
     * @version 1.0
     * @since 2021-09-24
     */
    public JPanel addLeftAndMarkPanel() {
        combinedMarkLeftPanel = new JPanel(new BorderLayout());

        combinedMarkLeftPanel.add(initMarkPanel(), BorderLayout.NORTH);
        combinedMarkLeftPanel.add(initLeftPanel(), BorderLayout.CENTER);
        return combinedMarkLeftPanel;
    }

    /*member of combinedTopBoardPanel*/
    JPanel combinedTopBoardPanel;
    /**
     * Add Top and Board Panel to the same JPanel and return it
     * combine the top panel and the board panel
     * @author Jiayu Lin, Chang Luo
     * @version 1.0
     * @since 2021-09-24
     */
    public JPanel addTopAndBoardPanel() {
        combinedTopBoardPanel = new JPanel(new BorderLayout());

        combinedTopBoardPanel.add(initTopPanel(), BorderLayout.NORTH);
        combinedTopBoardPanel.add(initBoardPanel(), BorderLayout.CENTER);
        return combinedTopBoardPanel;
    }



    JWindow splashScreenWindow = new JWindow();

    JPanel windowPanel = new JPanel(new BorderLayout());
    ImageIcon windowImage = new ImageIcon(RESOURCE_PATH + SPLASH_IMAGE_PATH);
    JLabel authorSignatureLabel = new JLabel(AUTHOR_SIGNATURE, JLabel.CENTER);

    public void displaySplashScreen() {


        try {
            JLabel windowImageLabel = new JLabel(windowImage);
            //add image to windowPanel
            windowPanel.add(windowImageLabel, CENTER);
        } catch (Exception e) {
            System.err.println(FILE_NOT_FOUND);
        }


        //Customize signature
        authorSignatureLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        authorSignatureLabel.setForeground(Color.ORANGE);
        authorSignatureLabel.setBackground(new Color(18, 84, 8));
        authorSignatureLabel.setOpaque(true);


        //Centralize Splash
        int width = windowImage.getIconWidth();
        int height = windowImage.getIconHeight();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;


        //set the location and the size of the window
        splashScreenWindow.pack();
        splashScreenWindow.setBounds(x, y, width, height);
        windowPanel.add(authorSignatureLabel, SOUTH);
        splashScreenWindow.setContentPane(windowPanel);
        splashScreenWindow.setVisible(true);
        try {
            //Stay at SplashScreen time
            Thread.sleep(1000);
            //Dispose the SplashScreenWindow
            splashScreenWindow.dispose();
        } catch (InterruptedException e) {
            System.err.print(INTERRUPTED_EXCEPTION);

        }

    }

    JMenuItem newGame;
    JMenuBar menuBar;
    JMenuItem solutionMenuItem;
    JMenuItem exitMenuItem;
    JMenu gameMenu;
    JMenu helpMenu;
    JMenuItem colorsMenuItem;
    JMenuItem aboutMenuItem;

    /**
     * Add the menu bar of the main frame
     *
     * @return Menu Bar for the main frame
     * @author Jiayu Lin, Chang Luo
     * @version 1.0
     * @since 2021-11-11
     */
    public JMenuBar initMenuBar() {
        menuBar = new JMenuBar();
        gameMenu = new JMenu("Game");
        ImageIcon newIcon = new ImageIcon(RESOURCE_PATH + NEW_GAME_LOGO_PATH);
        newGame = new JMenuItem("New", newIcon);

        ImageIcon solutionIcon = new ImageIcon(RESOURCE_PATH + SOLUTION_LOGO_PATH);
        solutionMenuItem = new JMenuItem("Solution", solutionIcon);


        ImageIcon exitIcon = new ImageIcon(RESOURCE_PATH + EXIT_LOGO_PATH);
        exitMenuItem = new JMenuItem("Exit", exitIcon);



        gameMenu.add(newGame);
        gameMenu.add(solutionMenuItem);
        gameMenu.add(exitMenuItem);

        helpMenu = new JMenu("Help");
        ImageIcon colorsIcon = new ImageIcon(RESOURCE_PATH + COLOR_LOGO_PATH);
        colorsMenuItem = new JMenuItem("Colors", colorsIcon);

        ImageIcon aboutIcon = new ImageIcon(RESOURCE_PATH + ABOUT_LOGO_PATH);
        aboutMenuItem = new JMenuItem("About", aboutIcon);

        helpMenu.add(colorsMenuItem);
        helpMenu.add(aboutMenuItem);
        gameMenu.setMnemonic(KeyEvent.VK_G);
        helpMenu.setMnemonic(KeyEvent.VK_H);
        newGame.setMnemonic(KeyEvent.VK_N);
        solutionMenuItem.setMnemonic(KeyEvent.VK_S);
        exitMenuItem.setMnemonic(KeyEvent.VK_E);
        colorsMenuItem.setMnemonic(KeyEvent.VK_C);
        aboutMenuItem.setMnemonic(KeyEvent.VK_A);
        menuBar.add(gameMenu);
        menuBar.add(helpMenu);

        return menuBar;

    }
    JButton correctColorButton;
    JButton markedColorButton;
    JButton errorColorButton;



    Color correctColor = Color.green;
    Color markedColor  = Color.yellow;
    Color errorColor   = Color.red;
    JPanel correctColorPanel;
    JPanel markedColorPanel;
    JPanel errorColorPanel;
    JFrame colorFrame;
    public void initColorChooser(){
        //JDialog colorDialog = new JDialog();
        colorFrame = new JFrame("Color Model");
        colorFrame.setSize(500,200);
        //colorFrame.setAlwaysOnTop(true);
        colorFrame.setVisible(true);

        colorFrame.setLayout(new GridLayout(2,3));
        correctColorPanel = new JPanel();
        markedColorPanel = new JPanel();
        errorColorPanel = new JPanel();


        correctColorButton  =   new JButton("Color1:Correct");

        markedColorButton =new JButton("Color2:Marked");

        errorColorButton =new JButton("Color3:Error");

        colorFrame.add(correctColorPanel);
        colorFrame.add(markedColorPanel);
        colorFrame.add(errorColorPanel);
        colorFrame.add(correctColorButton);
        colorFrame.add(markedColorButton);
        colorFrame.add(errorColorButton);

        correctColorPanel.setBackground(correctColor);
        markedColorPanel.setBackground(markedColor);
        errorColorPanel.setBackground(errorColor);
        //colorFrame.set(false);

        //Making the Game window centralized
        int width = colorFrame.getWidth();
        int height = colorFrame.getHeight();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        colorFrame.setBounds(x, y, width, height);
    }
    JColorChooser markedColorChooser = new JColorChooser();

    void initMarkedColorChooser(){
        markedColor = JColorChooser.showDialog(colorFrame,"Marked Color Chooser",markedColor);
        markedColorPanel.setBackground(markedColor);
    }
    JColorChooser errorColorChooser = new JColorChooser();
    void initErrorColorChooser(){
        errorColor = JColorChooser.showDialog(colorFrame,"Error Color Chooser",errorColor);
        errorColorPanel.setBackground(errorColor);
    }
    JColorChooser correctColorChooser = new JColorChooser();
    void initCorrectColorChooser(){
        correctColor = JColorChooser.showDialog(colorFrame,"Correct Color Chooser",correctColor);
        correctColorPanel.setBackground(correctColor);
    }
    public void updateLabelView(){

        for (int i = 0; i < columnLabelString.length; i++) {
            labelsOfTopPanel[i].setText("(" + columnLabelString[i] + ")");
        }
        for (int i = 0; i < rowLabelString.length; i++) {
            labelsOfLeftPanel[i].setText("("+rowLabelString[i] + ")");
        }

    }




    //     Constructor flag Using CTRL + f to locate it

    /**
     * Initiate the game GUI
     *
     * @author  Jiayu Lin, Chang Luo
     * @version 1.0
     * @since   2021-09-24
     */
    JFrame mainFrame;
    JPanel gamePanel;
    ImageIcon mainFrameImageIcon;
    public GameView() {

        displaySplashScreen();


        mainFrame = new JFrame();
        gamePanel = new JPanel(new BorderLayout());

        //combine all the panels together
        gamePanel.add(addTopAndBoardPanel(), BorderLayout.CENTER);
        gamePanel.add(initControlPanel(), BorderLayout.EAST);
        gamePanel.add(addLeftAndMarkPanel(), BorderLayout.WEST);


        gamePanel.setVisible(true);

        //add all the panels to the main frame
        mainFrame.setJMenuBar(initMenuBar());
        mainFrame.add(gamePanel);
        mainFrame.pack();

        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        mainFrame.setTitle(TITLE);
        mainFrame.setBackground(Color.BLACK);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set main Frame image icon
        mainFrameImageIcon = new ImageIcon(RESOURCE_PATH + MAIN_FRAME_ICON);
        mainFrame.setIconImage(mainFrameImageIcon.getImage());

        //Making the Game window centralized
        int width = mainFrame.getWidth();
        int height = mainFrame.getHeight();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        mainFrame.setBounds(x, y, width, height);



    }

}
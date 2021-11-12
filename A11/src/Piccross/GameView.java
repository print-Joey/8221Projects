package Piccross;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
//Con      Constructor flag Using CTRL + f to locate it







public class GameView extends JFrame{
    private final String LOGO_PATH          =       "piccrossNameMin.jpg";
    private final String NEW_GAME_LOGO_PATH =       "piciconnew.gif";
    private final String SOLUTION_LOGO_PATH =       "piciconsol.gif";
    private final String ABOUT_LOGO_PATH    =       "piciconabt.gif";
    private final String EXIT_LOGO_PATH     =       "piciconext.gif";
    private final String COLOR_LOGO_PATH    =       "piciconcol.gif";
    private final String POINTS             =       "Points:  ";
    private final String TIME               =       "Time:  ";
    private final String RESET              =       "Reset";
    private final String FILE_NOT_FOUND     =       "File not found!!";
    private final String TITLE              =       "Piccross";
    private final int    NUMS_LABEL_OF_TOP_PANEL
                                            =       5;
    private final int    NUMS_LABEL_OF_LEFT_PANEL
                                            =       5;
    private final int    NUM_ROWS           =       5;
    private final int    NUM_COlUMNS        =       5;
    private final String RESOURCE_PATH      =       "A11\\src\\Piccross\\Resource\\";

    //================================================================


    //================================================================
    /* members of initMarkPanel()*/
    JPanel              markPanel;
    JCheckBox           markCheckbox;
    /**
    * The initMarkPanel simply returns a JPanel with the marks
    *
    * @author  Jiayu Lin, Chang Luo
    * @version 1.0
    * @since   2021-09-24
    */
    public JPanel initMarkPanel(){
        markPanel           =   new JPanel();
        markCheckbox        =   new JCheckBox("Mark");

        //attributes

       // markCheckbox.       addActionListener(innerClassControllerObj);
        markCheckbox.       setBackground(new Color(22, 186, 163));
        markCheckbox.       setFont(new Font("TimesRoman", Font.BOLD, 12));

        markPanel.          setBorder(new EmptyBorder(15,10,10,10));

        //add
        markPanel.          add(markCheckbox);


        return  markPanel;
    }


    /* members of initTopPanel()*/
    JPanel                  topMainPanel;
    JLabel[]                labelsOfTopPanel;
    JPanel[]                columnOfLabelPanel;
    //Set the panel which appears on the top
    /**
    * The initTopPanel returns the JPanel which is on the top
    *
    * @author  Jiayu Lin, Chang Luo
    * @version 1.0
    * @since   2021-09-24
    */
    public JPanel initTopPanel(){
        topMainPanel            = new JPanel(new GridLayout(1,5));
        labelsOfTopPanel        = new JLabel[NUMS_LABEL_OF_TOP_PANEL];
        columnOfLabelPanel      = new JPanel[NUMS_LABEL_OF_TOP_PANEL];



        // Make it constant for UI now, Will change to variable after implementing logic.
        final String[]  NUMS_DISPLAY_ON_TOP_LABELS = new String[]{"1", "3", "4", "3", "1"};
        for (int i = 0; i < NUMS_DISPLAY_ON_TOP_LABELS.length; i++) {
            labelsOfTopPanel[i]         = new JLabel("("+NUMS_DISPLAY_ON_TOP_LABELS[i]+")");
            columnOfLabelPanel[i]       = new JPanel();

            //columnOfLabelPanel[i].setSize(new Dimension(10,10));
            //Set panel attributes
            columnOfLabelPanel[i].          setBackground(new Color(179, 97, 16));
            //Set label attributes
            //labelsOfTopPanel[i].setHorizontalAlignment(SwingConstants.CENTER);
            labelsOfTopPanel[i].            setFont(new Font("TimesRoman", Font.BOLD, 18));
            labelsOfTopPanel[i].            setBorder(new EmptyBorder(10,10,10,10));
            //add up
            columnOfLabelPanel[i].          add(labelsOfTopPanel[i]);
            topMainPanel.                   add(columnOfLabelPanel[i]);
        }
        return topMainPanel;
    }


    /* members of initLeftPanel()*/
    JPanel          leftMainPanel;

    JLabel[]        labelsOfLeftPanel;
    JPanel[]        rowOfLabelPanel;
    //Initiate the panel on the left
    /**
    * The initLeft Panel returns the JPanel on the Left
    *
    * @author  Jiayu Lin, Chang Luo
    * @version 1.0
    * @since   2021-09-24
    */
    public JPanel initLeftPanel(){
        leftMainPanel       = new JPanel(new GridLayout(5,1));
        labelsOfLeftPanel   = new JLabel[NUMS_LABEL_OF_LEFT_PANEL];
        rowOfLabelPanel     = new JPanel[NUMS_LABEL_OF_LEFT_PANEL];
        // Make it constant for UI now, Will change to variable after implementing logic.
        final String[] NUMS_DISPLAY_ON_LEFT_LABELS = new String[]{"1","1", "5", "3", "1,1"};

        for (int i = 0; i < NUMS_DISPLAY_ON_LEFT_LABELS.length; i++) {
            labelsOfLeftPanel[i]        = new JLabel("("+NUMS_DISPLAY_ON_LEFT_LABELS[i]+")");
            rowOfLabelPanel[i]          = new JPanel();

            //Set panel attributes
            rowOfLabelPanel[i].         setBackground(new Color(179, 97, 16));
            rowOfLabelPanel[i].         setBorder(new EmptyBorder(25,0,0,0));

            //Set label attributes
            labelsOfLeftPanel[i].       setFont(new Font("TimesRoman", Font.BOLD, 18));

            //add up
            rowOfLabelPanel[i].         add(labelsOfLeftPanel[i]);
            leftMainPanel.              add(rowOfLabelPanel[i]);
        }
        return leftMainPanel;
    }

    /* members of initBoardPanel()*/
    JPanel                      boardMainPanel;
    JButton[][]                 UnitOfBoardButton;

    //Set the panel which is used to contain the board
    /**
    * Returns the JPanel which is used to contain the board of the game
    *
    * @author  Jiayu Lin, Chang Luo
    * @version 1.0
    * @since   2021-09-24
    */
    public JPanel initBoardPanel(){
        boardMainPanel      =   new JPanel(new GridLayout(5,5,1,1));

        UnitOfBoardButton   =   new JButton[NUM_ROWS][NUM_COlUMNS];

        for (int i = 0; i < UnitOfBoardButton.length; i++) {
            for (int j = 0; j < UnitOfBoardButton[i].length; j++) {
                UnitOfBoardButton[i][j] = new JButton();
                UnitOfBoardButton[i][j].setPreferredSize(new Dimension(100,100));
                UnitOfBoardButton[i][j].setBackground(Color.lightGray);
                //UnitOfBoardButton[i][j].addActionListener(new GameController());
                //add all the buttons to the main panel
                boardMainPanel.         add(UnitOfBoardButton[i][j]);
            }
        }
        Border b1 = BorderFactory.createEmptyBorder(5,5,0,0);
        Border b2 = BorderFactory.createMatteBorder(2,5,5,2,Color.GRAY);
        boardMainPanel.setBorder(new CompoundBorder(b1,b2));
        return boardMainPanel;
    }

    /* members of initControlPanel()*/
    JPanel      controlMainPanel;    JPanel northPanel,centerPanel,southPanel;
    JPanel      logoPanel;          JLabel logoLabel;
    JPanel      pointsDisplayPanel; JLabel pointsLabel;     JTextField pointsTextField;
    JScrollPane msgDisplayScrollPane; JTextArea messageDisplayTextArea;
    JPanel      timePanel;      JLabel timeLabel;           JTextField timeTextField;
    JButton     resetButton;    JPanel resetPanel;
    //set the panel which is used to control the game
    /**
    * Return the JPanel which is used to control the game
    *
    * @author  Jiayu Lin, Chang Luo
    * @version 1.0
    * @since   2021-09-24
    */
    public JPanel initControlPanel() {
        // init all panel in the control panel
        controlMainPanel        =       new JPanel(new BorderLayout());
        northPanel              =       new JPanel(new BorderLayout());
        centerPanel= new JPanel(new BorderLayout());
        southPanel=new JPanel(new BorderLayout());

        logoPanel               =       new JPanel();
        pointsLabel             =       new JLabel(POINTS);
        pointsDisplayPanel = new JPanel();

        messageDisplayTextArea  =       new JTextArea();
        msgDisplayScrollPane    =       new JScrollPane();


        timePanel               =       new JPanel();
        timeLabel               =       new JLabel(TIME);
        resetButton             =       new JButton(RESET);
        resetPanel              =       new JPanel();




        //logo panel member and behavior
        try {


            //Image Path may different in different IDEs

            logoLabel           =       new JLabel(new ImageIcon(RESOURCE_PATH + LOGO_PATH));

        }catch(Exception e){
            System.err.println(FILE_NOT_FOUND);

        }
        logoPanel.add(logoLabel);

        //Points panel member and behavior

        pointsTextField         =       new JTextField("0",5);
        pointsDisplayPanel.     add(pointsLabel);
        pointsDisplayPanel.     add(pointsTextField);
        pointsDisplayPanel.     setBorder(new EmptyBorder(10,0,0,0));

        //messageDisplayPanel member and behavior
        messageDisplayTextArea.setBackground(new Color(255, 255, 255));

        msgDisplayScrollPane.setViewportView(messageDisplayTextArea);

        msgDisplayScrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);

        



        //timePanel member and behavior
        timeTextField          =       new JTextField("0s",5);
        timePanel.             add(timeLabel);
        timePanel.             add(timeTextField);


        //button
       // resetButton.            addActionListener(innerClassControllerObj);
        resetPanel.             add(resetButton);

//layouts
        northPanel.add(logoPanel,BorderLayout.NORTH);
        northPanel.add(pointsDisplayPanel,BorderLayout.CENTER);
        southPanel.add(timePanel,BorderLayout.NORTH);
        southPanel.add(resetPanel,BorderLayout.CENTER);

        controlMainPanel.       add(northPanel,BorderLayout.NORTH);
        controlMainPanel.       add(msgDisplayScrollPane);
        controlMainPanel.       add(southPanel,BorderLayout.SOUTH);



        controlMainPanel.       setBorder(new EmptyBorder(25,0,0,0));
        return controlMainPanel;
    }
    JPanel          combinedMarkLeftPanel;
    /**
    * Add Left Panel and Mark Panel to the same JPanel and return it
    *
    * @author  Jiayu Lin, Chang Luo
    * @version 1.0
    * @since   2021-09-24
    */
    public JPanel addLeftAndMarkPanel(){
        combinedMarkLeftPanel       =       new JPanel(new BorderLayout());

        combinedMarkLeftPanel.      add(initMarkPanel(),BorderLayout.NORTH);
        combinedMarkLeftPanel.      add(initLeftPanel(),BorderLayout.CENTER);
        return combinedMarkLeftPanel;
    }

    JPanel combinedTopBoardPanel;
    //combine the top panel and the board panel
    /**
    * Add Top and Board Panel to the same JPanel and return it
    *
    * @author  Jiayu Lin, Chang Luo
    * @version 1.0
    * @since   2021-09-24
    */
    public JPanel addTopAndBoardPanel(){
        combinedTopBoardPanel       =       new JPanel(new BorderLayout());
        
        combinedTopBoardPanel.      add(initTopPanel(),BorderLayout.NORTH);
        combinedTopBoardPanel.      add(initBoardPanel(),BorderLayout.CENTER);
        return combinedTopBoardPanel;
    }

    //final String RESOURCE_PATH                                  = "A11\\";
    final String SPLASH_IMAGE_PATH                              = "piccrossLogo.jpg";
    //final String FILE_NOT_FOUND                                 = "File not found!!";
    final String AUTHOR_SIGNATURE                               = "Lin,Jiayu/Luo,Chang's Piccross Game Staring....";
    final String INTERRUPTED_EXCEPTION                          = "InterruptedException!!";
    JWindow splashScreenWindow = new JWindow();

    JPanel windowPanel = new JPanel(new BorderLayout());
    ImageIcon windowImage = new ImageIcon(RESOURCE_PATH + SPLASH_IMAGE_PATH);
    JLabel authorSignatureLabel = new JLabel(AUTHOR_SIGNATURE, JLabel.CENTER);
    public void displaySplashScreen(int time) {


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
        authorSignatureLabel.setBackground(new Color(18,84,8));
        authorSignatureLabel.setOpaque(true);


        //set Attributes width/height for Splash
        int width = windowImage.getIconWidth() ;
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
            Thread.sleep(time);

            splashScreenWindow.dispose();
        }catch (InterruptedException e) {
            System.err.print(INTERRUPTED_EXCEPTION);

        }

    }
    JMenuItem newGame ;JMenuBar menuBar;
    JMenuItem solutionMenuItem; JMenuItem exitMenuItem; JMenu gameMenu;
    JMenu helpMenu; JMenuItem colorsMenuItem; JMenuItem aboutMenuItem;

    public JMenuBar initMenuBar(){
        menuBar = new JMenuBar();
        gameMenu = new JMenu("Game");
        ImageIcon newIcon = new ImageIcon(RESOURCE_PATH + NEW_GAME_LOGO_PATH);
        newGame = new JMenuItem("New", newIcon);
        ImageIcon solutionIcon = new ImageIcon(RESOURCE_PATH + SOLUTION_LOGO_PATH);
        solutionMenuItem = new JMenuItem("Solution", solutionIcon);
        ImageIcon exitIcon = new ImageIcon(RESOURCE_PATH + EXIT_LOGO_PATH);
        exitMenuItem = new JMenuItem("Exit", exitIcon);
        exitMenuItem.addActionListener(new GameController.ExitListener());

        gameMenu.add(newGame);
        gameMenu.add(solutionMenuItem);
        gameMenu.add(exitMenuItem);

        helpMenu = new JMenu("Help");
        ImageIcon colorsIcon = new ImageIcon(RESOURCE_PATH + COLOR_LOGO_PATH);
        colorsMenuItem = new JMenuItem("Colors", colorsIcon);
        colorsMenuItem.addActionListener(new GameController.ColorListener());
        ImageIcon aboutIcon = new ImageIcon(RESOURCE_PATH + ABOUT_LOGO_PATH);
        aboutMenuItem = new JMenuItem("About", aboutIcon);
        aboutMenuItem.addActionListener(new GameController.AboutListener());
        helpMenu.add(colorsMenuItem);
        helpMenu.add(aboutMenuItem);

        menuBar.add(gameMenu);
        menuBar.add(helpMenu);

        return menuBar;
        //mainFrame.setJMenuBar(menuBar);
    }
    //Con      Constructor flag Using CTRL + f to locate it

    /**
     * Initiate the game GUI
     *
     * @author  Jiayu Lin, Chang Luo
     * @version 1.0
     * @since   2021-09-24
     */

    public GameView() {

        displaySplashScreen(1000);

                JFrame mainFrame = new JFrame();
                JPanel gamePanel = new JPanel(new BorderLayout());

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

                //Making the Game window centralized
                int width = mainFrame.getWidth();
                int height = mainFrame.getHeight();
                Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (screen.width - width) / 2;
                int y = (screen.height - height) / 2;
                mainFrame.setBounds(x, y, width, height);




           }

}

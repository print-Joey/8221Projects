package Piccross;


import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


import java.awt.*;

public class GameClient {

    private final String RESOURCE_PATH = "A11\\src\\Piccross\\Resource\\";
    private final String CLIENT_PIC = "piccorssLogoClient.png";
    private final String CLIENT_TITLE = "Piccross Client";
    private final String CLIENT_LOGO = "icon.jpg";
    private final int NUM_OF_COLUMN_OF_TEXT_FIELD = 8;
    private final int THICKNESS_OF_BORDER = 5;
    public GameClient(){
    initClientFrame();

    }
//Lv.1 Components
    JFrame clientFrame;
//Lv.2 Components
    JScrollPane msgPane;
    JPanel textFieldsPanel;
    JPanel buttonsPanel;
    JPanel controlPanel;
//Lv.3 Components

    JLabel picLabel;
    JTextArea clientTextArea;

    JTextField userTextField;
    JTextField serverTextField;
    JTextField portTextField;

    JLabel userLabel;
    JLabel serverLabel;
    JLabel portLabel;

    JButton connectButton;
    JButton endButton;
    JButton newGameButton;
    JButton sendButton;
    JButton playButton;

    public void initClientFrame(){
      //initialize each objects
        clientFrame = new JFrame();

        textFieldsPanel = new JPanel();
        buttonsPanel = new JPanel();
        controlPanel = new JPanel();
        picLabel = new JLabel();

        userLabel = new JLabel();
        serverLabel = new JLabel();
        portLabel = new JLabel();

        userTextField = new JTextField();
        serverTextField = new JTextField();
        portTextField = new JTextField();

        connectButton = new JButton();
        endButton = new JButton();
        newGameButton = new JButton();
        sendButton = new JButton();
        playButton = new JButton();

        clientTextArea = new JTextArea();

        msgPane = new JScrollPane();

        //set objects' properties

        try{
            ImageIcon clientPic = new ImageIcon(RESOURCE_PATH+CLIENT_PIC);
            picLabel.setIcon(clientPic);
        }catch(Exception e){

            System.err.println("e");

        }
        picLabel.setHorizontalAlignment(SwingConstants.CENTER);
        picLabel.setBorder(new EmptyBorder(THICKNESS_OF_BORDER,THICKNESS_OF_BORDER,THICKNESS_OF_BORDER,THICKNESS_OF_BORDER));

        userLabel.setText("User:");
        userTextField.setColumns(NUM_OF_COLUMN_OF_TEXT_FIELD);

        serverLabel.setText("Server:");
        serverTextField.setColumns(NUM_OF_COLUMN_OF_TEXT_FIELD);

        portLabel.setText("Port:");
        portTextField.setColumns(NUM_OF_COLUMN_OF_TEXT_FIELD);
        //set button color
        connectButton.setBackground(Color.ORANGE);
        endButton.setBackground(Color.ORANGE);
        newGameButton.setBackground(Color.ORANGE);
        sendButton.setBackground(Color.ORANGE);
        playButton.setBackground(Color.ORANGE);
        //set text in buttons
        connectButton.add(new JLabel("Connect"));
        endButton.add(new JLabel("End"));
        newGameButton.add(new JLabel("New Game"));
        sendButton.add(new JLabel("Send"));
        playButton.add(new JLabel("Play"));

        clientTextArea.setBackground(Color.WHITE);
        msgPane.setViewportView(clientTextArea);
        msgPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        msgPane.setBorder(new CompoundBorder(new EmptyBorder(THICKNESS_OF_BORDER,THICKNESS_OF_BORDER,THICKNESS_OF_BORDER,THICKNESS_OF_BORDER), LineBorder.createBlackLineBorder()) );

        //set size of MsgPane
        msgPane.setPreferredSize(new Dimension(580,100));



        //add up
        textFieldsPanel.add(userLabel);
        textFieldsPanel.add(userTextField);
        textFieldsPanel.add(serverLabel);
        textFieldsPanel.add(serverTextField);
        textFieldsPanel.add(portLabel);
        textFieldsPanel.add(portTextField);
        textFieldsPanel.add(connectButton);
        textFieldsPanel.add(endButton);

        //add up
        buttonsPanel.add(newGameButton);
        buttonsPanel.add(sendButton);
        buttonsPanel.add(playButton);


        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(textFieldsPanel,BorderLayout.NORTH);
        controlPanel.add(buttonsPanel,BorderLayout.CENTER);

        clientFrame.add(picLabel,BorderLayout.NORTH);
        clientFrame.add(controlPanel,BorderLayout.CENTER);
        clientFrame.add(msgPane,BorderLayout.SOUTH);

        //set frame properties
        clientFrame.pack();
        clientFrame.setVisible(true);
        clientFrame.setResizable(false);
        try{
            ImageIcon iconServerFrame = new ImageIcon(RESOURCE_PATH +CLIENT_LOGO);
            clientFrame.setIconImage(iconServerFrame.getImage());
        }catch (Exception e){
            System.err.println("e");
        }
        clientFrame.setTitle(CLIENT_TITLE);

        clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Making Frame centralized
        int width = clientFrame.getWidth();
        int height = clientFrame.getHeight();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        clientFrame.setBounds(x, y, width, height);
    }


}

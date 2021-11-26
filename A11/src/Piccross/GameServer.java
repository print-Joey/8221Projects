package Piccross;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GameServer {


    private final String RESOURCE_PATH = "A11\\src\\Piccross\\Resource\\";
    private final String SERVER_PIC = "piccorssLogoServer.png";
    private final String SERVER_LOGO = "icon.jpg";
    private final String SERVER_TITLE = "Piccross Server";
    private final int NUM_OF_COLUMN_OF_TEXT_FIELD = 8;
    private final int THICKNESS_OF_BORDER = 5;

    public GameServer() {
        initServerFrame();

    }

    //Lv.1 Components
    JFrame serverFrame;
    //Lv.2 Components
    JScrollPane msgPane;
    JPanel textFieldsPanel;
    JLabel serverPicLabel;
    /* Lv.3 Components */

    JTextArea serverTextArea;

    JTextField portTextField;


    JLabel portLabel;

    JButton executeButton;
    JCheckBox finalizeCheckBox;
    JButton endButton;



    public void initServerFrame() {
        //initialize each objects
        serverFrame = new JFrame();
        textFieldsPanel = new JPanel();
        serverPicLabel = new JLabel();

        portLabel = new JLabel();

        portTextField = new JTextField();

        executeButton = new JButton();
        finalizeCheckBox = new JCheckBox();
        endButton = new JButton();

        serverTextArea = new JTextArea();
        msgPane = new JScrollPane();

        //set objects' properties

        try {
            ImageIcon clientPic = new ImageIcon(RESOURCE_PATH + SERVER_PIC);
            serverPicLabel.setIcon(clientPic);
        } catch (Exception e) {

            System.err.println("e");

        }
        serverPicLabel.setHorizontalAlignment(SwingConstants.CENTER);
        serverPicLabel.setBorder(new EmptyBorder(THICKNESS_OF_BORDER, THICKNESS_OF_BORDER, THICKNESS_OF_BORDER, THICKNESS_OF_BORDER));

        portLabel.setText("Port:");
        portTextField.setColumns(NUM_OF_COLUMN_OF_TEXT_FIELD);

        //set button color
        executeButton.setBackground(Color.ORANGE);
        endButton.setBackground(Color.ORANGE);
        //Set text
        executeButton.add(new JLabel("Connect"));
        finalizeCheckBox.setText("Finalize");
        endButton.add(new JLabel("End"));


        serverTextArea.setBackground(Color.WHITE);
        msgPane.setViewportView(serverTextArea);
        msgPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        msgPane.setBorder(new CompoundBorder(new EmptyBorder(THICKNESS_OF_BORDER, THICKNESS_OF_BORDER, THICKNESS_OF_BORDER, THICKNESS_OF_BORDER), LineBorder.createBlackLineBorder()));

        //set size of MsgPane
        msgPane.setPreferredSize(new Dimension(580, 100));




        //add up
        textFieldsPanel.add(portLabel);
        textFieldsPanel.add(portTextField);
        textFieldsPanel.add(executeButton);
        textFieldsPanel.add(finalizeCheckBox);
        textFieldsPanel.add(endButton);



        serverFrame.add(serverPicLabel, BorderLayout.NORTH);
        serverFrame.add(textFieldsPanel, BorderLayout.CENTER);
        serverFrame.add(msgPane, BorderLayout.SOUTH);

        //set frame properties
        serverFrame.pack();
        serverFrame.setVisible(true);
        serverFrame.setResizable(false);
        try{
            ImageIcon iconServerFrame = new ImageIcon(RESOURCE_PATH +SERVER_LOGO);
            serverFrame.setIconImage(iconServerFrame.getImage());
        }catch (Exception e){
            System.err.println("e");
        }
        serverFrame.setTitle(SERVER_TITLE);

        serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Making Frame centralized
        int width = serverFrame.getWidth();
        int height = serverFrame.getHeight();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        serverFrame.setBounds(x, y, width, height);
        
    }
}
package Piccross;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer {


    private final String RESOURCE_PATH = "A11\\src\\Piccross\\Resource\\";
    private final String SERVER_PIC = "piccorssLogoServer.png";
    private final String SERVER_LOGO = "icon.jpg";
    private final String SERVER_TITLE = "Piccross Server";
    private final int NUM_OF_COLUMN_OF_TEXT_FIELD = 8;
    private final int THICKNESS_OF_BORDER = 5;

    public GameServer() {
        initServerFrame();
        addListener();
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
    JButton resultButton;
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
        resultButton = new JButton();
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
        portTextField.setText("1000");

        //set button color
        executeButton.setBackground(Color.ORANGE);
        endButton.setBackground(Color.ORANGE);
        //Set text
        executeButton.add(new JLabel("Execute"));
        resultButton.add(new JLabel("Results"));
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
    ServerSocket serverSocket = null;
    Socket socket = null;
    ArrayList<String> clientsData;
    int numOfClient;
    public void addListener(){
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int portNum = Integer.parseInt(portTextField.getText());
                    serverTextArea.append("Execute Button Pressed\n");
                    try {
                        serverSocket = new ServerSocket(portNum);

                        //socket = new Socket(serverSocket.getInetAddress(),portNum);
                        serverTextArea.append("Port Number: " + portNum + "\n");


                        serverDataManagement();


                        //Disable ExecuteButton
                        executeButton.setEnabled(false);
                        executeButton.setBackground(Color.GRAY);
                    } catch (Exception serverSocketException) {
                        serverTextArea.append("serverSocketException\n");
                    }
                } catch (Exception portNumberException) {
                    serverTextArea.append("Invalid port number\n");


                }

            }
        });

        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    serverSocket.close();
                    serverTextArea.append("Server end connection... Disconnected\n");

                    //Reset Exec button
                    executeButton.setEnabled(true);
                    executeButton.setBackground(Color.ORANGE);
                } catch (Exception Exception) {
                    serverTextArea.append("Fail to end connection\n");
                }
            }
        });
/*===============================================================
        resultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
***
*/
    }
    Thread serverMsgThread;
    public void serverDataManagement(){
        try{
            serverMsgThread = new Thread(new ServerMsgTask());
            serverMsgThread.start();

        }catch(Exception e){

        }

    }

    class ServerMsgTask implements Runnable{

        public final String SEPARATOR = "#";
        public final String PROTOCOL = "P";
        int secondSeparatorIndex = 0;
        int protocolIndex = 0;
        String clientID = "";
        String clientGameConfig = "";
        String fromClientFormattedData;
        PrintStream toClientSteam = null;
        BufferedReader fromClientStream = null;
        String toClientFormattedData = null;


        @Override
        public void run() {
            //initialize all variables

            numOfClient = 0;

            waitClient();


            for(;;) {


                try {

                    inputDataFromClient();



                    if (fromClientFormattedData.contains("P1")) {
                        clientID = fromClientFormattedData.substring(0,1);
                        clientGameConfig = fromClientFormattedData.substring(5,fromClientFormattedData.length());
                        System.out.println(("Client No. " + clientID + " Game Config: " + clientGameConfig));
                    }else if(fromClientFormattedData.contains("P2")){
                        toClientFormattedData = fromClientFormattedData +SEPARATOR+ clientGameConfig;
                        toClientSteam.write(toClientFormattedData.getBytes());
                    }
                    if(fromClientFormattedData == null){
                        break;
                    }

                } catch (Exception Excep) {
                    Excep.printStackTrace();
                }
            }

        }



       public void waitClient(){
                try {
                    serverTextArea.append("Waiting for clients to connect ...\n");
                    socket = serverSocket.accept();
                    ++numOfClient;
                    System.out.println("Connecting " + socket.getInetAddress() + " in port " + socket.getLocalPort() + ".\n");
                    System.out.println(socket.isConnected());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            public void inputDataFromClient() throws IOException {
                toClientSteam = new PrintStream(socket.getOutputStream());
                 fromClientStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                toClientSteam.println(numOfClient);
                fromClientFormattedData = fromClientStream.readLine();
        }


    }
    //For easy to run, delete before submission
    public static void main(String[] args) {
GameServer gs = new GameServer();
    }
    //=============================================
}
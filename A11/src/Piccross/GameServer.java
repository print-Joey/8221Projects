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

        //Set serverTextArea color
        serverTextArea.setBackground(Color.WHITE);
        //set msgPane
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


        //add all the components to the serverFrame
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



        //Making Frame centralized
        int width = serverFrame.getWidth();
        int height = serverFrame.getHeight();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        serverFrame.setBounds(x, y, width, height);
        
    }
    ServerSocket serverSocket = null;
    Socket socket;

    int numOfClient;
    //set the actionLisener for each button
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
                    System.exit(0);

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
    public final String SEPARATOR = "#";
    public final String PROTOCOL = "P";
    int secondSeparatorIndex = 0;
    int protocolIndex = 0;
    String clientID = "";
    String clientGameConfig = "";
    String fromClientFormattedData;
    //PrintStream toClientSteam = null;
    //PrintStream fromClientStream = null;
    String toClientFormattedData = null;
    class ServerMsgTask implements Runnable{




        @Override
        public void run() {
            //initialize all variables
            numOfClient = 0;
            for(;;) {
                waitClient();
                Worked w = new Worked(socket,numOfClient);
                w.start();
            }


        }



       public void waitClient(){
                try {
                    serverTextArea.append("Waiting for clients to connect ...\n");
                    socket = serverSocket.accept();

                    if(socket.isConnected()){
                        ++numOfClient;

                        serverTextArea.append(("Connected " + socket.getInetAddress() + " in port " + socket.getLocalPort() + ".\n"));
                        serverTextArea.append(("Numbers of Client: " + numOfClient+".\n"));
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            public void inputDataFromClient() throws IOException {
               // toClientSteam = new PrintStream(socket.getOutputStream());
              //   fromClientStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
               // toClientSteam.println(numOfClient);
              //  fromClientFormattedData = fromClientStream.readLine();
        }


    }
    String clientidString;
    int clientId;
    class Worked extends Thread{
        Socket socket;

        public Worked(Socket s, int nclient) {
            socket = s;
            clientidString = String.valueOf(nclient);
            clientId = nclient;
        }
        @Override
        public void run() {
            try {
                PrintStream toClientSteam = new PrintStream(socket.getOutputStream());
                BufferedReader fromClientStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println(clientId);
                toClientSteam.println(clientId);

                fromClientFormattedData = fromClientStream.readLine();


                String clientInfo[];
                    while(!fromClientFormattedData.contains("P0")) {

                    if (fromClientFormattedData.contains("P1")) {
//                        clientidString = fromClientFormattedData.substring(0, 1);
//                        clientGameConfig = fromClientFormattedData.substring(5);

                        clientInfo = fromClientFormattedData.split("#");
                        clientidString = clientInfo[0];
                        clientGameConfig = clientInfo[2];
                        serverTextArea.append(("Client No. " + clientidString + " Game Config: " + clientGameConfig+"\n"));
                    } else if (fromClientFormattedData.contains("P2")) {
                        toClientFormattedData = clientidString + SEPARATOR + clientGameConfig;
                        toClientSteam.println(toClientFormattedData);
                    }
                        fromClientFormattedData = "";

                    fromClientFormattedData = fromClientStream.readLine();
                    }

                    numOfClient--;
                    serverTextArea.append("Disconnecting " + clientidString +" at "+socket.getInetAddress() + "!");
                if (numOfClient == 0 && finalizeCheckBox.isSelected()) {
                    System.out.println("Ending server...");

                    System.exit(0);
                }
                    socket.close();



            }catch (Exception e5){

            }


        }
    }
    //For easy to run, delete before submission
    public static void main(String[] args) {
GameServer gs = new GameServer();
    }
    //=============================================
}
//======================================
/*                try {
                    if(fromClientFormattedData == null){
                        break;
                    }
                    inputDataFromClient();



                    if (fromClientFormattedData.contains("P1")) {
                        clientID = fromClientFormattedData.substring(0,1);
                        clientGameConfig = fromClientFormattedData.substring(5,fromClientFormattedData.length());
                        System.out.println(("Client No. " + clientID + " Game Config: " + clientGameConfig));
                    }else if(fromClientFormattedData.contains("P2")){
                        toClientFormattedData = fromClientFormattedData +SEPARATOR+ clientGameConfig;
                        toClientSteam.write(toClientFormattedData.getBytes());
                    }


                } catch (Exception Excep) {
                    Excep.printStackTrace();
                }*/
//===============================================

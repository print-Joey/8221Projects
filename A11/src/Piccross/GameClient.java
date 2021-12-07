package Piccross;




import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class GameClient  {

    private final String RESOURCE_PATH = "A11\\src\\Piccross\\Resource\\";
    private final String CLIENT_PIC = "piccorssLogoClient.png";
    private final String CLIENT_TITLE = "Piccross Client";
    private final String CLIENT_LOGO = "icon.jpg";
    private final int NUM_OF_COLUMN_OF_TEXT_FIELD = 8;
    private final int THICKNESS_OF_BORDER = 5;

    public GameClient(){
    initClientFrame();
    addListener();

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
    JButton receiveButton;
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
        receiveButton = new JButton();

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
        //set default server localhost for convenience
        serverTextField.setText("localhost");

        portLabel.setText("Port:");
        portTextField.setColumns(NUM_OF_COLUMN_OF_TEXT_FIELD);
        portTextField.setText("1000");
        //set button color
        connectButton.setBackground(Color.ORANGE);
        endButton.setBackground(Color.GRAY);
        newGameButton.setBackground(Color.ORANGE);
        sendButton.setBackground(Color.ORANGE);
        playButton.setBackground(Color.ORANGE);
        receiveButton.setBackground(Color.orange);
        //set text in buttons
        connectButton.add(new JLabel("Connect"));
        endButton.add(new JLabel("End"));
        newGameButton.add(new JLabel("New Game"));
        sendButton.add(new JLabel("Send"));
        receiveButton.add(new JLabel("Receive game"));
        playButton.add(new JLabel("Play"));

        clientTextArea.setBackground(Color.WHITE);
        msgPane.setViewportView(clientTextArea);
        msgPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        msgPane.setBorder(new CompoundBorder(new EmptyBorder(THICKNESS_OF_BORDER,THICKNESS_OF_BORDER,THICKNESS_OF_BORDER,THICKNESS_OF_BORDER), LineBorder.createBlackLineBorder()) );

        //set size of MsgPane
        msgPane.setPreferredSize(new Dimension(580,100));

        endButton.setEnabled(false);


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
        buttonsPanel.add(receiveButton);
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
    Socket socket;
    GameModel gameModel = new GameModel();
    String newGameConfig = " ";

    //==========
    boolean isisDoBackgroundRuned = false; // isDoBackground method in sentAction runs
    public void addListener(){

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                SentButtonAction sba = new SentButtonAction();
                sba.execute();






            }
        });

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientTextArea.append("Connection button\n");
                try {
                    int port = Integer.parseInt(portTextField.getText());

                    InetAddress severIPAddress = null;

                    try {
                        severIPAddress = InetAddress.getByName(String.valueOf(serverTextField.getText()));
                        socket = new Socket(severIPAddress,port);

                        //initMsgThread();
                        clientTextArea.append("Connection with "+ severIPAddress +" on port "+ port+" successfully\n");
                        //Connection success,set it false.
                        connectButton.setEnabled(false);
                        connectButton.setBackground(Color.gray);

                        //End button released
                        endButton.setEnabled(true);
                        endButton.setBackground(Color.ORANGE);
                    } catch (Exception Exception) {
                        JOptionPane.showMessageDialog(clientFrame,"connection refused:connect","Unable to connect",JOptionPane.ERROR_MESSAGE);
                        clientTextArea.append("Connection with "+ severIPAddress +" on port "+ port+" Failed\n");
                        clientTextArea.append("Connection refused:connect\n");
                    }

                }catch (Exception invalidPortNumberE){
                    clientTextArea.append("Invalid port number\n");
                }

            }
        });





        receiveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

                ReceiveGameConfig rgc = new ReceiveGameConfig();
                rgc.execute();


            }
        });


        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    socket.close();
                    //reset connection Button

                    connectButton.setEnabled(true);
                    connectButton.setBackground(Color.ORANGE);
                } catch (Exception Exception) {
                    clientTextArea.append("Client end connection Disconnected\n");

                }
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGameConfig = gameModel.solutionTokenizerForClient();
                clientTextArea.append("Creating New MVC game \n");
                clientTextArea.append("New Game:"+newGameConfig);

            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameView gameView = new GameView();
                GameController gameController = new GameController(gameModel,gameView);
                //ActionEvent ae = new ActionEvent(gameView.newGame,1,"null");
                gameController.isNewGameClicked = true;
                gameController.resetGame();


            }
        });

    }

    public final String SEPARATOR = "#";
    public final String PROTOCOL = "P";

    String toServerGameConfigFormattedData;
    BufferedReader bufferFromServer;
    PrintStream toServerStream;
    String clientId;

    class SentButtonAction extends SwingWorker<Void, String> {






        @Override
        public Void doInBackground () {
            try{
            isisDoBackgroundRuned =true;
            bufferFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            toServerStream = new PrintStream(socket.getOutputStream());


            clientId = bufferFromServer.readLine();


            toServerGameConfigFormattedData = clientId + SEPARATOR + PROTOCOL + "1" + SEPARATOR + newGameConfig;
            toServerStream.write(toServerGameConfigFormattedData.getBytes());
                clientTextArea.append("You are Player No." + clientId + "\n");
            }catch (Exception e3){

            }

            return null;
        }


    }
    class ReceiveGameConfig extends SwingWorker <Void,String>{
        String dataFromServer;

        @Override
        protected Void doInBackground()  {
            try {
                bufferFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                toServerStream = new PrintStream(socket.getOutputStream());

                toServerGameConfigFormattedData = clientId + SEPARATOR + PROTOCOL + 2;
                toServerStream.write(toServerGameConfigFormattedData.getBytes());

                dataFromServer = bufferFromServer.readLine();
                clientId = dataFromServer.substring(0,1);
                newGameConfig = dataFromServer.substring(3);



            }catch (Exception e5){}

            clientTextArea.append("Game config received: "+ newGameConfig);
            return null;
        }

        @Override
        protected void done() {


        }
    }
//For easy to run, delete before submission
    public static void main(String[] args) {
        GameClient gameClient = new GameClient();
    }
    }
//========================================


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

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * GameClient
 * @author Jiayu Lin
 * @version 1.0
 * @since Dec-09-2021
 */

public class GameClient {



    public GameClient() {
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

    /**
     * initClientFrame
     *
     * @author Jiayu Lin
     * @since Dec-09-2021
     */
    public void initClientFrame() {
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
        // import images from specific path, Path may vary between different IDE.
        try {

            ImageIcon clientPic = new ImageIcon(PgmConfigs.RESOURCE_PATH + PgmConfigs.CLIENT_PIC);
            System.out.println(PgmConfigs.RESOURCE_PATH + PgmConfigs.CLIENT_PIC);
            picLabel.setIcon(clientPic);
        } catch (Exception e) {

            System.err.println("e");

        }
        //Set each component's attributes
        picLabel.setHorizontalAlignment(SwingConstants.CENTER);
        picLabel.setBorder(new EmptyBorder(PgmConfigs.THICKNESS_OF_BORDER, PgmConfigs. THICKNESS_OF_BORDER, PgmConfigs. THICKNESS_OF_BORDER, PgmConfigs. THICKNESS_OF_BORDER));

        userLabel.setText("User:");
        userTextField.setColumns(PgmConfigs.NUM_OF_COLUMN_OF_TEXT_FIELD);
        userTextField.setText("Your name");

        serverLabel.setText("Server:");
        serverTextField.setColumns(PgmConfigs.NUM_OF_COLUMN_OF_TEXT_FIELD);
        //set default server localhost for convenience
        serverTextField.setText("localhost");

        portLabel.setText("Port:");
        portTextField.setColumns(PgmConfigs.NUM_OF_COLUMN_OF_TEXT_FIELD);
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
        msgPane.setBorder(new CompoundBorder(new EmptyBorder(PgmConfigs.THICKNESS_OF_BORDER, PgmConfigs. THICKNESS_OF_BORDER, PgmConfigs. THICKNESS_OF_BORDER, PgmConfigs. THICKNESS_OF_BORDER), LineBorder.createBlackLineBorder()));

        //set size of MsgPane
        msgPane.setPreferredSize(new Dimension(580, 100));

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

//set layouts
        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(textFieldsPanel, BorderLayout.NORTH);
        controlPanel.add(buttonsPanel, BorderLayout.CENTER);

        clientFrame.add(picLabel, BorderLayout.NORTH);
        clientFrame.add(controlPanel, BorderLayout.CENTER);
        clientFrame.add(msgPane, BorderLayout.SOUTH);



        // import images from specific path, Path may vary between different IDE.
        try {
            ImageIcon iconServerFrame = new ImageIcon(PgmConfigs.RESOURCE_PATH + PgmConfigs. CLIENT_LOGO);
            clientFrame.setIconImage(iconServerFrame.getImage());
        } catch (Exception e) {
            System.err.println("e");
        }
        clientFrame.setTitle(PgmConfigs.CLIENT_TITLE);
        //set frame properties
        //Set new Close Operation by exit Client
        clientFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        clientFrame.pack();
        clientFrame.setVisible(true);
        clientFrame.setResizable(false);

        //Making Frame centralized
        int width = clientFrame.getWidth();
        int height = clientFrame.getHeight();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        //To stay with Sever at single screen, So reduce x's value.
        clientFrame.setBounds(x - 400, y, width, height);
        gameModel = new GameModel();

    }

    //Instance variable used in addListener()
    Socket socket;
    GameModel gameModel;
    String newGameConfig = " ";

    //==========

    InputStream inputStreamFromServer;

    /**
     * addListener
     * <p>
     * Add listeners of gameClient components
     *
     * @author Jiayu Lin
     * @since Dec-09-2021
     */
    public void addListener() {

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    toServerStream = socket.getOutputStream();

                    toServerGameConfigFormattedData = clientId + PgmConfigs.SEPARATOR + PgmConfigs.PROTOCOL + "1" + PgmConfigs.SEPARATOR + newGameConfig;
                    toServerStream.write(toServerGameConfigFormattedData.getBytes());

                } catch (Exception ex) {
                    System.err.println("Send Button Exception!!!");
                }
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
                        socket = new Socket(severIPAddress, port);

                        //initMsgThread();
                        clientTextArea.append("Connection with " + severIPAddress + " on port " + port + " successfully\n");
                        //Connection success,set it false.
                        connectButton.setEnabled(false);
                        connectButton.setBackground(Color.gray);

                        //

                        Thread receiveClientId = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    inputStreamFromServer = socket.getInputStream();
                                    bufferFromServer = new BufferedReader(new InputStreamReader(inputStreamFromServer));

                                    clientId = bufferFromServer.readLine();
                                    clientTextArea.append("You are Client No." + clientId + "\n");
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }

                            }
                        }
                        );
                        receiveClientId.start();
                        //End button released
                        endButton.setEnabled(true);
                        endButton.setBackground(Color.ORANGE);
                    } catch (Exception Exception) {
                        JOptionPane.showMessageDialog(clientFrame, "connection refused:connect", "Unable to connect", JOptionPane.ERROR_MESSAGE);
                        clientTextArea.append("Connection with " + severIPAddress + " on port " + port + " Failed\n");
                        clientTextArea.append("Connection refused:connect\n");
                    }

                } catch (Exception invalidPortNumberE) {
                    clientTextArea.append("Invalid port number\n");
                }

            }
        });


        receiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread receiveConfig = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String[] infos;
                            PrintStream toServerPrintStream = new PrintStream(socket.getOutputStream());
                            //toServerStream = socket.getOutputStream();

                            toServerGameConfigFormattedData = new String(clientId + PgmConfigs.SEPARATOR + PgmConfigs.PROTOCOL + "2");

                            toServerPrintStream.println(toServerGameConfigFormattedData);
                            toServerPrintStream.flush();
                            BufferedReader bufferFromServer1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                            dataFromServer = bufferFromServer1.readLine();
                            infos = dataFromServer.split("#");
                            String clientIdConfig = infos[0];
                            newGameConfig = infos[1];
                            clientTextArea.append("Received Game Config " + newGameConfig + " From client No. " + clientIdConfig + "\n");
                        } catch (Exception exception) {
                            System.err.println("Receive Button Exception!!!");
                        }


                    }


                });
                receiveConfig.start();

            }
        });
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (socket == (null)) {
                        System.exit(0);
                    }

                    PrintStream printStream = new PrintStream(socket.getOutputStream());
                    printStream.println(clientId + PgmConfigs.PROTOCOL + PgmConfigs.END_CONNECTION_SYMBOOL);
                    socket.close();
                    //reset connection Button
                    System.exit(0);
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
                gameModel.initGame();
                newGameConfig = gameModel.solutionTokenizerForClient();
                clientTextArea.append("Creating New MVC game \n");
                clientTextArea.append("New Game:" + newGameConfig);

            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameView gameView = new GameView();
                GameController gameController = new GameController(gameModel, gameView);
                //ActionEvent ae = new ActionEvent(gameView.newGame,1,"null");
                gameController.isNewGameClicked = true;
                gameController.resetGame();
            }
        });

    }



    String toServerGameConfigFormattedData;
    BufferedReader bufferFromServer;
    OutputStream toServerStream;
    String clientId;

    String dataFromServer;
}
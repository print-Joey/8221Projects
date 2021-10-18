package Piccross;

import java.awt.*;
import javax.swing.*;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;


/**
 * SplashScreen
 *
 *
 *
 */

public class SplashScreen extends JWindow {
    private final String RESOURCE_PATH                                  = "A11\\";
    private final String SPLASH_IMAGE_PATH                              = "piccrossLogo.jpg";
    private final String FILE_NOT_FOUND                                 = "File not found!!";
    private final String AUTHOR_SIGNATURE                               = "Lin,Jiayu/Luo,Chang's Piccross Game Staring....";
    private final String INTERRUPTED_EXCEPTION                          = "InterruptedException!!";

    Integer time;
    public SplashScreen(Integer time)  {
        this.time = time;
        displaySplashWindow();


    }

    public void displaySplashWindow() {

        JPanel windowPanel = new JPanel(new BorderLayout());
        ImageIcon windowImage = new ImageIcon(RESOURCE_PATH + SPLASH_IMAGE_PATH);
        try {
            JLabel windowImageLabel = new JLabel(windowImage);
            //add image to windowPanel
            windowPanel.add(windowImageLabel, CENTER);
        } catch (Exception e) {
            System.err.println(FILE_NOT_FOUND);
        }
        JLabel authorSignatureLabel = new JLabel(AUTHOR_SIGNATURE, JLabel.CENTER);


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

        setBounds(x, y, width, height);
        windowPanel.add(authorSignatureLabel, SOUTH);
        setContentPane(windowPanel);
        setVisible(true);
        try {
            Thread.sleep(this.time);
            dispose();
        }catch (InterruptedException e) {
            System.err.print(INTERRUPTED_EXCEPTION);

        }

    }

}
    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TrayIcon;

import Security.AuthenticationServerInterface;
import Security.LogIn;
import Security.MirageSecurityManager;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JFrame;

/**
 *
 * @author manuel
 */
public class MirageTrayIcon {
    MirageSecurityManager msm;
//    AuthenticationServerInterface asi;
    LogIn li;
    
    /**
     * MirageTrayIcon makes the icon.
     * @param msm
     * @param a 
     */
    public MirageTrayIcon(MirageSecurityManager msm){
        this.msm=msm;
        //this.asi=a;
    }
    
    private static TrayIcon trayIcon = null;
    private static SystemTray tray=null;

    /**
     * @param args the command line arguments
     */
    public void init() {
        //Initilize LogIn gui
        
        
        if (SystemTray.isSupported()) {

            ActionListener LoginListener = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    //LogIn.main();
                    msm.createLoginDialog();
                }
            };

            ActionListener exitListener = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
            };


            tray = SystemTray.getSystemTray();
                    
            URL url = MirageTrayIcon.class.getResource("/Images/G.jpg");
            Image image = Toolkit.getDefaultToolkit().getImage(url);
            
            PopupMenu popup = new PopupMenu();

            MenuItem LogInItem = new MenuItem("Login");
            LogInItem.addActionListener(LoginListener);
            popup.add(LogInItem);

            MenuItem defaultItem = new MenuItem("Exit");
            defaultItem.addActionListener(exitListener);
            popup.add(defaultItem);


            trayIcon = new TrayIcon(image, "Gauto House Client", popup);



            trayIcon.setImageAutoSize(true);

            System.out.println("TrayIcon Added!");
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println("TrayIcon could not be added.");
            }

        }
    }

    public TrayIcon getTrayIcon(){
        return trayIcon;
    }
    public SystemTray getSystemTray(){
        return tray;
    }

}

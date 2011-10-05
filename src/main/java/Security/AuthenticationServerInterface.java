/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;


import Startup.Main;
import com.manuwebdev.mirageobjectlibrary.Authentication.LoginAttempt;
import com.manuwebdev.mirageobjectlibrary.Authentication.User;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel Gauto
 */
public class AuthenticationServerInterface {
    final static String ACK="ACK";
    final static String AUTHORIZATION="CHECK AUTHORIZATION";
    final static String FBKEY="GET FACEBOOK KEY";
    static final int PORT=7263;
    
    public static boolean checkLogin(String user, String password) {
        try {
            Socket clientSocket = new Socket(Main.getServer(), PORT);
            
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            ObjectOutputStream oos=new ObjectOutputStream(outToServer);
            ObjectInputStream ois=new ObjectInputStream(clientSocket.getInputStream());
            
            LoginAttempt la=new LoginAttempt(user, password);
            oos.writeObject(ois);
        } catch (UnknownHostException ex) {
            Logger.getLogger(AuthenticationServerInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AuthenticationServerInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
}

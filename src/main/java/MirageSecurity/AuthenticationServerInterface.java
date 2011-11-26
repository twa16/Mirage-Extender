/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MirageSecurity;


import Security.*;
import Startup.Main;
import com.manuwebdev.mirageobjectlibrary.Authentication.LoginAttempt;
import com.manuwebdev.mirageobjectlibrary.Configuration.Client;
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
    static final int PORT=5700;
    Socket clientSocket;
    Client c;
    //MirageSecurityManager sec;
    
    public AuthenticationServerInterface(String server){
        //sec=sc;
        try {
            System.out.println("Attempting Conenction to:"+server);
            clientSocket = new Socket(server, PORT);
        } catch (UnknownHostException ex) {
            Logger.getLogger(AuthenticationServerInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AuthenticationServerInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean checkLogin(String user, String password) {
        try {
            
            System.out.println("Checking login for: "+user);

            ObjectOutputStream oos=new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream ois=new ObjectInputStream(clientSocket.getInputStream());
            
            LoginAttempt la=new LoginAttempt(user, password);
            System.out.println(la.getHash()+"\n"+la.getUsername()+password+la.getTimeStamp());
            oos.writeObject(la);
            
            try {
                Object o = ois.readObject();
                //System.err.println(o.getClass());
                System.out.println("Response Recieved.");
                if(o ==null){
                    return false;
                }
                else{
                    System.out.println("Client Object is here.");
                    c=(Client) o;
                    return true;
                    
                }
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AuthenticationServerInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(AuthenticationServerInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AuthenticationServerInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Client getClientObject(String user, String password) {
        try {
            
            System.out.println("Checking login for: "+user);

            ObjectOutputStream oos=new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream ois=new ObjectInputStream(clientSocket.getInputStream());
            
            LoginAttempt la=new LoginAttempt(user, password);
            System.out.println(la.getHash()+"\n"+la.getUsername()+password+la.getTimeStamp());
            oos.writeObject(la);
            
            try {
                Object o = ois.readObject();
                //System.err.println(o.getClass());
                System.out.println("Response Recieved.");
                if(o ==null){
                    return null;
                }
                else{
                    System.out.println("Client Object is here.");
                    c=(Client) o;
                    return c;
                    
                }
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AuthenticationServerInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(AuthenticationServerInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AuthenticationServerInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
}

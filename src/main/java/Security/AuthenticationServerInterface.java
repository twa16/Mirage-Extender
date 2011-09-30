/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

import StartUp.Main;
import com.manuwebdev.Encryption.SharedKey.SharedDecrypter;
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
            Socket clientSocket = new Socket(Main.HOUSE_SERVER, PORT);
            
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

    public static String getFacebookKey(String user){
        try {
            SharedDecrypter d = new SharedDecrypter();
            final String KEY = "113_171_50_8_144_184_188_195_117_41_156_104_30_54_169_4_61_190_149_62_73_35_42_6_1";

            Socket clientSocket = new Socket(Main.HOUSE_SERVER, PORT);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            
            outToServer.writeBytes(FBKEY+'\n');
            System.out.println("Request Sent");
            inFromServer.readLine();
            System.out.println("ACK Received");           
            outToServer.writeBytes(user+'\n');
            System.out.println("Sent Details");
            System.out.println("Waiting for response");
            String encryptedKey=inFromServer.readLine();

            System.out.println("Decrypting");
            //return d.decrypt(encryptedKey, KEY);
            //return encryptedKey.split(":")[1];
            return encryptedKey;

        } catch (UnknownHostException ex) {
            Logger.getLogger(AuthenticationServerInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AuthenticationServerInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "User Does Not Exist";
    }
}

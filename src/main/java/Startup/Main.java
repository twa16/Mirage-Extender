/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Startup;

import Security.AuthenticationServerInterface;
import Security.LogIn;
import Security.MirageSecurityManager;

/**
 *
 * @author manuel
 */
public class Main {
    private static String Server;
    public static void main(String[] args){
        Server=args[0];
        MirageSecurityManager sc=new MirageSecurityManager();
        AuthenticationServerInterface AuthenticationInterface=new AuthenticationServerInterface(sc,Server);
        LogIn l=new LogIn(AuthenticationInterface,sc);
        l.setVisible(true);
        
    }
    public static String getServer(){
        return Server;
    }
}

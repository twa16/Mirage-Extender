/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Startup;

import Facebook.FacebookInterface;
import Security.AuthenticationServerInterface;
import Security.MirageSecurityManager;
import TrayIcon.MirageTrayIcon;

/**
 *
 * @author manuel
 */
public class Main {
    private static String Server;
    private static MirageTrayIcon mti;
    public static void main(String[] args){
        if(args.length>0)Server=args[0];
        else{
            Server="localhost";
        }
        FacebookInterface fb=new FacebookInterface();
        AuthenticationServerInterface AuthenticationInterface=new AuthenticationServerInterface(Server);
        MirageSecurityManager msm=new MirageSecurityManager(fb,AuthenticationInterface);
        mti=new MirageTrayIcon(msm);
        mti.init();
    }
    public static String getServer(){
        return Server;
    }
}

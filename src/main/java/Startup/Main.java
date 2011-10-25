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
    public static void main(String[] args){
        if(args.length>0)Server=args[0];
        else{
            Server="localhost";
        }
        FacebookInterface fb=new FacebookInterface();
        MirageSecurityManager msm=new MirageSecurityManager(fb);
        AuthenticationServerInterface AuthenticationInterface=new AuthenticationServerInterface(msm,Server);
        MirageTrayIcon mti=new MirageTrayIcon(msm,AuthenticationInterface);
        mti.init();
    }
    public static String getServer(){
        return Server;
    }
}

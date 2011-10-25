/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Facebook;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.User;

/**
 *
 * @author Manuel Gauto
 */
public class FacebookInterface {
    final public static String STATUS_UPDATE="message";
    String key;
    public static FacebookClient facebookClient;
    
    public FacebookInterface(){
        //Default
    }
    
    public void setToken(String token){
        key=token;
    }
    
    public FacebookInterface(String Token){
        key=Token;
        init(Token);
    }
    
    public String whoami() {
        User user = facebookClient.fetchObject("me", User.class);
        System.out.println("\n\nI am: " + user.getName());
        return user.getName();
    }

    public User getFacebookUser(){
        User user = facebookClient.fetchObject("me", User.class);
        return user;
    }
    private void init(String token){
        facebookClient = new DefaultFacebookClient(token);
    }

    public void PostStatus(String Status) {
        FacebookType publishMessageResponse = facebookClient.publish("me/feed", FacebookType.class, Parameter.with(FacebookInterface.STATUS_UPDATE, Status));

        System.out.println("\n\nPublished message ID: " + publishMessageResponse.getId());
    }
}

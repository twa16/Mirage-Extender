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
    
    /**
     * Default constructor
     */
    public FacebookInterface(){
        //Default
    }
    
    /**
     * Sets Graph API access token
     * @param token 
     */
    public void setToken(String token){
        key=token;
    }
    
    /**
     * Construct Facebook Interface with specified
     * facebook key
     * @param Token 
     */
    public FacebookInterface(String Token){
        key=Token;
        init(Token);
    }
    
    /**
     * Prints out name of Facebook user
     * Useful for testing facebook connection
     * @return Name of facebook user
     */
    public String whoami() {
        User user = facebookClient.fetchObject("me", User.class);
        System.out.println("\n\nI am: " + user.getName());
        return user.getName();
    }

    /**
     * Returns user object representing the 
     * user on the facebook account
     * @return User Object
     */
    public User getFacebookUser(){
        User user = facebookClient.fetchObject("me", User.class);
        return user;
    }
    
    /**
     * Internal method used for instantiating 
     * FacebookClient
     * @param token 
     */
    private void init(String token){
        facebookClient = new DefaultFacebookClient(token);
    }

    /**
     * Posts status to the Facebook user's profile
     * @param Status Status to post
     */
    public void PostStatus(String Status) {
        FacebookType publishMessageResponse = facebookClient.publish("me/feed", FacebookType.class, Parameter.with(FacebookInterface.STATUS_UPDATE, Status));

        System.out.println("\n\nPublished message ID: " + publishMessageResponse.getId());
    }
}

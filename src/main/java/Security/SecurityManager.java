/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

import com.manuwebdev.mirageobjectlibrary.Authentication.User;

/**
 *
 * @author manuel
 */
public class SecurityManager {
    private User user;
    private String token;
    private void setUser(User u){
        user=u;
    }
    
    private void setToken(String Token){
        this.token=Token;
    }
    public User getUserObject(){
        return user;
    }
    public String getUsername(){
        return user.getUserName();
    }
    public void logout(){
        user=null;
        token=null;
    }
    public void login(User u){
        setUser(u);
        setToken(u.getFacebookToken());
    }
        
}

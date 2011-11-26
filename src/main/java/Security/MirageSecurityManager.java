/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

import Facebook.FacebookInterface;
import com.manuwebdev.mirageobjectlibrary.Authentication.User;
import com.manuwebdev.mirageobjectlibrary.Configuration.Client;
import javax.swing.JFrame;

/**
 *
 * @author manuel
 */
public class MirageSecurityManager {

    private User user;
    private String token;
    private FacebookInterface fb;
    private AuthenticationServerInterface asi;

    public MirageSecurityManager(FacebookInterface fb, AuthenticationServerInterface asi) {
        this.fb = fb;
    }

    private void setUser(User u) {
        user = u;
    }

    private void setToken(String Token) {
        this.token = Token;
    }

    public User getUserObject() {
        return user;
    }

    public String getUsername() {
        return user.getUserName();
    }

    public void logout() {
        user = null;
        token = null;
    }

    public boolean login(String user, String password) {
        boolean OK=asi.checkLogin(user, password);
        if(OK){
            Client c=asi.getClientObject(user, password);
            loginUser(c.getUserObject());
            return true;
        }
        else{
            return false;
        }
    }

    private void loginUser(User u) {
        setUser(u);
        setToken(u.getFacebookToken());
        
    }

    public void createLoginDialog() {
        LogIn li = new LogIn(asi);
        li.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        li.setVisible(true);

    }
}

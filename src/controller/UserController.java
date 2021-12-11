package controller;

import java.util.List;
import model.User;


public interface UserController {

    boolean checkUser(String username, String password);
    
    void addNewUser(User user);
    
    List<String> getAllRuoli();

	User getUser(String username);
}

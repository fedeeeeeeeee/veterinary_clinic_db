package controller;

import java.util.List;
import java.util.Optional;

import model.User;
import model.UserImpl;

// la view chiama UserController che chiama UserTable 
public interface UserController {

    boolean checkUser(String username, String password);
    
    void addNewUser(User user);
    
    List<String> getAllRuoli();
}

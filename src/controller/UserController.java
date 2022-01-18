package controller;

import java.util.List;

import model.Specializzazione;
import model.User;


public interface UserController {

    boolean checkUser(String username, String password);
    
    void addNewUser(User user);
    
    List<String> getAllRuoli();

	User getUser(String username);

	List<User> getAllPersonaleByRuolo(String string);

	List<Specializzazione> getAllSpecializzazioni(String cf);

	void addNewSpecializzazione(String cf, Specializzazione specializzazione);
}

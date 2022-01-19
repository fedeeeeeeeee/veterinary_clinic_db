package database;

import java.util.List;

import model.Specializzazione;
import model.User;

public interface UserTable {
	
    boolean findUser(String username, String password);

	boolean addNewUser(User user);
	
	List<String> getAllRuoli();

	User getUser(String username);

	List<User> getAllPersonaleByRuolo(String ruolo);

	List<Specializzazione> getAllSpecializzazioni(String cf);

	void addNewSpecializzazione(String cf, Specializzazione specializzazione);

	boolean updateUser(User user);

}

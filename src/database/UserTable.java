package database;

import model.User;

public interface UserTable {
	
    boolean findUser(String username, String password);

	boolean addNewUser(User user);
}

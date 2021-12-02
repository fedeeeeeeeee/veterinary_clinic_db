package database;

public interface UserTable {
	
    boolean findUser(String username, char[] password);

    boolean newUser(String username, char[] password);
}

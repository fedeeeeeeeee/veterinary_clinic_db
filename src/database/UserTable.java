package database;

public interface UserTable {
	
    boolean findUser(String username, char[] password, String professione);

}

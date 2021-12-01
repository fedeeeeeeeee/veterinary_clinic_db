package controller;

import database.UserTable;
import database.UserTableImpl;

public class UserControllerImpl implements UserController {

	private UserTable userTable;
	
	public UserControllerImpl(){
		this.userTable = new UserTableImpl();
	}
	
	@Override
	public boolean checkUser(String username, char[] password, String professione) {
		return this.userTable.findUser(username, password, professione);
	}

}

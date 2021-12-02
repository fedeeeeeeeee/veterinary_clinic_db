package controller;

import java.util.Optional;

import database.UserTable;
import database.UserTableImpl;
import model.UserImpl;

public class UserControllerImpl implements UserController {

	private UserTable userTable;
	
	public UserControllerImpl(){
		this.userTable = new UserTableImpl();
	}
	
	@Override
	public boolean checkUser(String username, char[] password) {
		return this.userTable.findUser(username, password);
	}

}

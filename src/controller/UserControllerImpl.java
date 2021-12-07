package controller;

import java.util.List;

import database.UserTable;
import database.UserTableImpl;
import model.User;

public class UserControllerImpl implements UserController {

	private UserTable userTable;
	
	public UserControllerImpl(){
		this.userTable = new UserTableImpl();
	}
	
	@Override
	public boolean checkUser(String username, String password) {
		return this.userTable.findUser(username, password);
	}

	@Override
	public void addNewUser(User user) {
		if (this.userTable.addNewUser(user)) {
			System.out.println("utente inserito correttamente");
		} else {
			System.out.println("errore inserimento utente");
		}
	}

	@Override
	public List<String> getAllRuoli() {
		return userTable.getAllRuoli();
	}

}

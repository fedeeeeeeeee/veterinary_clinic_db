package controller;

import java.util.List;

import database.UserTable;
import database.UserTableImpl;
import model.Specializzazione;
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

	@Override
	public User getUser(String username) {
		return userTable.getUser(username);
	}

	@Override
	public List<User> getAllPersonaleByRuolo(String ruolo) {
		return userTable.getAllPersonaleByRuolo(ruolo);
	}
	@Override
	public List<Specializzazione> getAllSpecializzazioni(String cf) {
		return userTable.getAllSpecializzazioni(cf);
	}


	@Override
	public void addNewSpecializzazione(String cf, Specializzazione specializzazione) {
		userTable.addNewSpecializzazione(cf,  specializzazione);
		
	}

	@Override
	public boolean updateUser(User user) {
		return userTable.updateUser(user);
	}
}

package controller;

import java.sql.Date;
import java.util.List;

import database.PrestazioneTable;
import database.PrestazioneTableImpl;
import model.User;

public class PrestazioneControllerImpl implements PrestazioneController {

	private PrestazioneTable prestazioneTable;
	
	public PrestazioneControllerImpl() {
		this.prestazioneTable = new PrestazioneTableImpl();
	}
	
	
	@Override
	public List<String> getAllPrestazioni() { // ordinate per data
		return this.prestazioneTable.getAllPrestazioni();
	}

	@Override
	public List<String> getPrestazioniInData(Date data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getPrestazioneDiVeterinario(User veterinario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addNewPrestazione() {
		// TODO Auto-generated method stub
		return false;
	}

}

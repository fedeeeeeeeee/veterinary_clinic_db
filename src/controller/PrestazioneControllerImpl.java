package controller;

import java.sql.Date;
import java.util.List;

import database.PrestazioneTable;
import database.PrestazioneTableImpl;
import model.Appuntamento;
import model.User;

public class PrestazioneControllerImpl implements PrestazioneController {

	private PrestazioneTable prestazioneTable;
	
	public PrestazioneControllerImpl() {
		this.prestazioneTable = new PrestazioneTableImpl();
	}
	
	
	@Override
	public List<Appuntamento> getAllPrestazioni() { // ordinate per data
		return this.prestazioneTable.getAllPrestazioni();
	}

	@Override
	public List<Appuntamento> getPrestazioniInData(String data) {
		return this.prestazioneTable.getPrestazioniInData(data);
	}

	@Override
	public List<Appuntamento> getPrestazioneDiVeterinario(User veterinario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addNewPrestazione(Appuntamento appuntamento) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public List<Appuntamento> getPrestazioneSettimana(String data) {
		return this.prestazioneTable.getPrestazioneSettiamana(data);
	}


	@Override
	public List<Appuntamento> getPrestazioneMese(String data) {
		return this.prestazioneTable.getPrestazioneMese(data);
	}


	@Override
	public List<Appuntamento> getPrestazione(String data) {
		// TODO Auto-generated method stub
		return null;
	}

}

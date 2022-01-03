package controller;
import java.util.List;

import database.PrestazioneTable;
import database.PrestazioneTableImpl;
import database.TipoPrestazioneTable;
import database.TipoPrestazioneTableImpl;
import model.Appuntamento;
import model.Prestazione;
import model.User;

public class PrestazioneControllerImpl implements PrestazioneController {

	private PrestazioneTable prestazioneTable;
	private TipoPrestazioneTable tipoPrestazioneTable;
	
	public PrestazioneControllerImpl() {
		this.prestazioneTable = new PrestazioneTableImpl();
		this.tipoPrestazioneTable = new TipoPrestazioneTableImpl();

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
	public boolean addNewAppuntamento(Appuntamento appuntamento) {
		return this.prestazioneTable.addNewAppuntamento(appuntamento);
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


	@Override
	public List<Appuntamento> getFuturePrestazioni() {
		return this.prestazioneTable.getFuturePrestazioni();
	}


	@Override
	public List<Prestazione> getAllTipiDiPrestazione() {
		return this.tipoPrestazioneTable.getAllTipiDiPrestazione();
	}

}

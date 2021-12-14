package controller;

import java.util.List;

import database.ProprietarioTable;
import database.ProprietarioTableImpl;
import model.Person;
import model.Proprietario;

public class ProprietarioControllerImpl implements ProprietarioController {
	private ProprietarioTable proprietarioTable;

	public ProprietarioControllerImpl(){
		this.proprietarioTable = new ProprietarioTableImpl();
	}
	@Override
	public void addNewProprietario(Person proprietario) {
		if (this.proprietarioTable.addNewProprietario(proprietario)) {
			System.out.println("proprietario inserito correttamente");
		} else {
			System.out.println("errore inserimento proprietario");
		}
	}
	
	@Override
	public List<String> getAllPropietariCf() {
		return this.proprietarioTable.getAllPropietariCf();
	}
}

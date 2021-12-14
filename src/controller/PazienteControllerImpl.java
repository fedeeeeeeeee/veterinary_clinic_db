package controller;

import java.util.List;

import database.AnimaleTable;
import database.AnimaleTableImpl;
import database.ProprietarioTable;
import database.ProprietarioTableImpl;
import utility.Pair;


public class PazienteControllerImpl implements PazienteController {

	private AnimaleTable animaleTable;
	private ProprietarioTable proprietarioTable;

	
	public PazienteControllerImpl() {
		this.animaleTable = new AnimaleTableImpl();
		this.proprietarioTable = new ProprietarioTableImpl();

	}
	
	@Override
	public List<String> getAllPropietariCf() {
		return this.proprietarioTable.getAllPropietariCf();
	}

	@Override
	public List<Pair<String, String>> getNomeAndRazzaByProprietario(String cf) {
		return animaleTable.getNomeAndRazzaByProprietario(cf);
	}
	


}

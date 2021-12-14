package controller;

import java.util.List;

import database.AnimaleTable;
import database.AnimaleTableImpl;


public class PazienteControllerImpl implements PazienteController {

	private AnimaleTable animaleTable;
	
	public PazienteControllerImpl() {
		this.animaleTable = new AnimaleTableImpl();
	}
	@Override
	public List<String> getAllPropietariCf() {
		return this.animaleTable.getAllPropietariCf();
	}

}

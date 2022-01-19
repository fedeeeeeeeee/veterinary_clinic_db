package controller;

import java.util.List;

import database.AnimaleTable;
import database.AnimaleTableImpl;
import database.PazientiTable;
import database.PazientiTableImpl;
import database.ProprietarioTable;
import database.ProprietarioTableImpl;
import model.Animale;
import model.Patologia;
import model.Paziente;
import model.User;
import utility.Pair;


public class AnimaleControllerImpl implements AnimaleController {

	private AnimaleTable animaleTable;
	private ProprietarioTable proprietarioTable;
	private PazientiTable pazienteTable;

	
	public AnimaleControllerImpl() {
		this.animaleTable = new AnimaleTableImpl();
		this.proprietarioTable = new ProprietarioTableImpl();
		this.pazienteTable = new PazientiTableImpl();

	}
	
	@Override
	public List<String> getAllPropietariCf() {
		return this.proprietarioTable.getAllPropietariCf();
	}

	@Override
	public List<Pair<String, String>> getNomeAndRazzaByProprietario(String cf) {
		return animaleTable.getNomeAndRazzaByProprietario(cf);
	}

	@Override
	public void addNewAnimale(Animale animale) {
		this.animaleTable.addNewAnimale(animale);
	}

	@Override
	public List<Animale> getAllAnimali() {
		return this.animaleTable.getAllAnimali();
	}

	@Override
	public List<Paziente> getAllPazienti() {
		return this.pazienteTable.getAllPazienti();
	}

	@Override
	public List<Patologia> getAllPatologiePaziente(Paziente paziente) {
		return this.pazienteTable.getAllPatologiePaziente(paziente);
	}
}

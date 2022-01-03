package controller;

import java.util.List;

import database.FarmaciTable;
import database.FarmaciTableImpl;
import model.Farmaco;

public class FarmaciControllerImpl implements FarmaciController {

	private FarmaciTable farmaciTable;
	
	public FarmaciControllerImpl() {
		this.farmaciTable = new FarmaciTableImpl();
	}
	
	@Override
	public List<Farmaco> getAllFarmaci() {
		return this.farmaciTable.getAllFarmaci();
	}

	@Override
	public List<Farmaco> getFarmaciScaduti() {
		return this.farmaciTable.getFarmaciScaduti();
	}

	@Override
	public List<Farmaco> getFarmaciInScadenza() {
		return this.farmaciTable.getFarmaciInScadenza();
	}

	@Override
	public List<Farmaco> getFarmaciInEsaurimento() {
		return this.farmaciTable.getFarmaciInEsaurimento();
	}

	@Override
	public void updateQuantità(Farmaco farmaco, int q) {
		this.farmaciTable.updateQuantità(farmaco, q);
	}

	@Override
	public List<Farmaco> getAllVaccini() {
		return this.farmaciTable.getAllVaccini();
	}

	@Override
	public List<Farmaco> getOnlyFarmaci() {
		return this.farmaciTable.getOnlyFarmaci();
	}

	@Override
	public List<Farmaco> getFarmaciAcquistati() {
		return this.farmaciTable.getFarmaciAcquistati();
	}

	@Override
	public List<Farmaco> getFarmaciMaiAcquistati() {
		return this.farmaciTable.getFarmaciMaiAcquistati();
	}

	@Override
	public List<String> getAllTypeFarmaco(){
		return this.farmaciTable.getAllTypeFarmaco();
	}

	@Override
	public void addNewFarmaci(Farmaco farmaco) {
		this.farmaciTable.addNewFarmaco(farmaco);		
	}
}

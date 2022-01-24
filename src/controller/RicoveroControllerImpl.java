package controller;

import java.util.List;

import database.RicoveroTable;
import database.RicoveroTableImpl;
import model.Animale;
import model.Appuntamento;
import model.Posto;
import model.Ricovero;

public class RicoveroControllerImpl implements RicoveroController {

	private RicoveroTable ricoveroTable;
	
	public RicoveroControllerImpl() {
		ricoveroTable = new RicoveroTableImpl();
	}
	
	@Override
	public Animale getAnimaleInPosto(int posto) {
		return null;
	}

	@Override
	public List<Ricovero> getRicoveriAttivi() {
		return ricoveroTable.getRicoveriAttivi();
	}

	@Override
	public List<Posto> getPostiLiberi() {
		return ricoveroTable.getPostiLiberi();
	}

	@Override
	public void addRicovero(Ricovero ricovero, Appuntamento appuntamento) {
		 ricoveroTable.addRicovero(ricovero, appuntamento);
		
	}

	@Override
	public void updateDurataRicovero(Ricovero ricovero) {
		 ricoveroTable.updateDurataRicovero(ricovero);
		
	}

	
	
}

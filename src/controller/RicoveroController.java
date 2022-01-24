package controller;

import java.util.List;

import model.Animale;
import model.Appuntamento;
import model.Posto;
import model.Ricovero;

public interface RicoveroController {
	
	Animale getAnimaleInPosto(int posto);
	public List<Ricovero> getRicoveriAttivi();
	List<Posto> getPostiLiberi();
	void addRicovero(Ricovero ricovero, Appuntamento appuntamento);
	void updateDurataRicovero(Ricovero ricovero);
}

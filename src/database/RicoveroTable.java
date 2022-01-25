package database;

import java.util.List;

import model.Appuntamento;
import model.Posto;
import model.Ricovero;

public interface RicoveroTable {

	List<Ricovero> getRicoveriAttivi();

	List<Posto> getPostiLiberi();

	void addRicovero(Ricovero ricovero, Appuntamento appuntamento);

	void updateDurataRicovero(Ricovero ricovero);

	boolean isRicoveratoOggi(int codAnimale);
	
}

package database;

import java.util.List;

import model.Farmaco;

public interface FarmaciTable {

	List<Farmaco> getAllFarmaci();

	List<Farmaco> getFarmaciScaduti();

	List<Farmaco> getFarmaciInScadenza();

	List<Farmaco> getFarmaciInEsaurimento();

	void updateQuantita(Farmaco farmaco, int q);

	List<Farmaco> getAllVaccini();

	List<Farmaco> getOnlyFarmaci();

	List<Farmaco> getFarmaciAcquistati();

	List<Farmaco> getFarmaciMaiAcquistati();

	List<String> getAllTypeFarmaco();

	void addNewFarmaco(Farmaco farmaco);
	
	//void addNewLotto(Lotto lotto);
}

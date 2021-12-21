package controller;

import java.util.List;

import model.Farmaco;

public interface FarmaciController {

	List<Farmaco> getAllFarmaci();

	List<Farmaco> getFarmaciScaduti();

	List<Farmaco> getFarmaciInScadenza();

	List<Farmaco> getFarmaciInEsaurimento();

	void updateQuantità(Farmaco farmaco, int q);
	
	List<Farmaco> getAllVaccini();

	List<Farmaco> getOnlyFarmaci();
	
	List<Farmaco> getFarmaciAcquistati();
	//void addNewLotto(Lotto lotto);

	List<Farmaco> getFarmaciMaiAcquistati();
}

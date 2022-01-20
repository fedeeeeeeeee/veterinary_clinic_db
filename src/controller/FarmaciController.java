package controller;

import java.util.List;

import model.Farmaco;
import model.FarmacoInPrestazione;

public interface FarmaciController {

	List<Farmaco> getAllFarmaci();

	List<Farmaco> getFarmaciScaduti();

	List<Farmaco> getFarmaciInScadenza();

	List<Farmaco> getFarmaciInEsaurimento();

	void updateQuantita(Farmaco farmaco, int q);
	
	List<Farmaco> getAllVaccini();

	List<Farmaco> getOnlyFarmaci();
	
	List<Farmaco> getFarmaciAcquistati();
	//void addNewLotto(Lotto lotto);

	List<Farmaco> getFarmaciMaiAcquistati();
	
	List<String> getAllTypeFarmaco();

	void addNewFarmaci(Farmaco farmaco);

	List<FarmacoInPrestazione> getFarmaciDiAnimale(int codAnimale);

	List<String> getFarmaciAssuntiByAnimale(int codAnimale);
}

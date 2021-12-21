package database;

import java.util.List;

import model.Farmaco;

public interface FarmaciTable {

	List<Farmaco> getAllFarmaci();

	List<Farmaco> getFarmaciScaduti();

	List<Farmaco> getFarmaciInScadenza();

	List<Farmaco> getFarmaciInEsaurimento();

	void updateQuantità(Farmaco farmaco, int q);
	
	//void addNewLotto(Lotto lotto);
}

package database;

import java.util.List;

import model.Strumento;

public interface StrumentiTable {

	
	List<Strumento> getAllStrumenti();
	
	List<Strumento> getStrumentiScaduti();
	
	List<Strumento> getStrumentiInScadenza();
	
	List<Strumento> getStrumentiInEsaurimento();

	void updateQuantita(Strumento strumento, int q);

	void addNewStrumento(Strumento stru);
}

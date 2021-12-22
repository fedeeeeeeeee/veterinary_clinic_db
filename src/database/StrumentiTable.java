package database;

import java.util.List;

import model.Strumento;

public interface StrumentiTable {

	
	List<Strumento> getAllStrumenti();
	
	List<Strumento> getStrumentiScaduti();
	
	List<Strumento> getStrumentiInScadenza();
	
	List<Strumento> getStrumentiInEsaurimento();

	void updateQuantità(Strumento strumento, int q);
}

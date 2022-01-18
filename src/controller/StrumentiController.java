package controller;

import java.util.List;

import model.Strumento;

public interface StrumentiController {

	List<Strumento> getAllStrumenti();
	
	List<Strumento> getStrumentiScaduti();
	
	List<Strumento> getStrumentiInScadenza();
	
	List<Strumento> getStrumentiInEsaurimento();

	void updateQuantita(Strumento strumento, int q);
	
	
}

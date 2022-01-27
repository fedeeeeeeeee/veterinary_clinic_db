package controller;

import java.util.List;

import model.Strumento;
import model.Utilizzo;

public interface StrumentiController {

	List<Strumento> getAllStrumenti();
	
	List<Strumento> getStrumentiScaduti();
	
	List<Strumento> getStrumentiInScadenza();
	
	List<Strumento> getStrumentiInEsaurimento();

	void updateQuantita(Strumento strumento, int q);

	void addNewStrumento(Strumento stru);

	void addUtilizzo(Utilizzo utilizzo);

	boolean isStrumentiAvailable(int codPrestazione);

	void updateStrumentiDiPrestazione(int codPrestazione);
	
	
}

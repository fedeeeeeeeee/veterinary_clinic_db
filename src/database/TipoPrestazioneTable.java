package database;

import java.util.List;

import model.Prestazione;
import model.Utilizzo;

public interface TipoPrestazioneTable {

	List<Prestazione> getAllTipiDiPrestazione();
	
	List<Utilizzo> getAllUtilizziDiPrestazione(Prestazione prestazione);

	void addPrestazione(Prestazione prestazione);
	
}

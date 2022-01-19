package database;

import java.util.List;

import model.Patologia;
import model.Paziente;

public interface PazientiTable {

	List<Paziente> getAllPazienti();

	List<Patologia> getAllPatologiePaziente(Paziente paziente);

	 
}

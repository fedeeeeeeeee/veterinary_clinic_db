package database;

import java.util.List;

import model.ParametriPaziente;
import model.Patologia;
import model.Paziente;

public interface PazientiTable {

	List<Paziente> getAllPazienti();

	List<Patologia> getAllPatologiePaziente(Paziente paziente);

	List<ParametriPaziente> getAllParametriPaziente(Paziente paziente);

	void updateParametriAnimale(Float altezza, Float peso, int codAnimale);

	 
}

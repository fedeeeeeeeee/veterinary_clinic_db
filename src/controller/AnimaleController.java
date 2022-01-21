package controller;

import java.util.List;

import model.Animale;
import model.ParametriPaziente;
import model.Patologia;
import model.Paziente;
import utility.Pair;


public interface AnimaleController {
    
	List<String> getAllPropietariCf();

	List<Pair<String, String>> getNomeAndRazzaByProprietario(String string);

    void addNewAnimale(Animale animale);

	List<Animale> getAllAnimali();

	List<Paziente> getAllPazienti();

	List<Patologia> getAllPatologiePaziente(Paziente paziente);

	List<ParametriPaziente> getAllParametriPaziente(Paziente paziente);

	void updateParametriAnimale(Float altezza, Float peso, int codAnimale);

}

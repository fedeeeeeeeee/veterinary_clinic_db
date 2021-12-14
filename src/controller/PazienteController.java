package controller;

import java.util.List;

import utility.Pair;


public interface PazienteController {
    
	List<String> getAllPropietariCf();

	List<Pair<String, String>> getNomeAndRazzaByProprietario(String string);

}

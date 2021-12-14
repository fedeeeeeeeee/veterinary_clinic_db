package database;

import java.util.List;

import utility.Pair;

public interface AnimaleTable {

	// solo proprietari che hanno un cane --> List<String> getAllPropietariCf();

	List<Pair<String, String>> getNomeAndRazzaByProprietario(String cf);

}

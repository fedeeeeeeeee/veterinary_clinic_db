package database;

import java.util.List;

import utility.Pair;

public interface AnimaleTable {

	List<String> getAllPropietariCf();

	List<Pair<String, String>> getNomeAndRazzaByProprietario(String cf);

}

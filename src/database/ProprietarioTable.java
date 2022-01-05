package database;

import java.util.List;

import model.Person;

public interface ProprietarioTable {

	boolean addNewProprietario(Person proprietario);

	List<String> getAllPropietariCf();

	Person getProprietario(String cf);

}

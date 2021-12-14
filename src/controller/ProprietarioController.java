package controller;

import java.util.List;

import model.Person;

public interface ProprietarioController {
   
	void addNewProprietario(Person proprietario);

	List<String> getAllPropietariCf();

}

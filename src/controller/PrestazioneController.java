package controller;

import java.sql.Date;
import java.util.List;

import model.Appuntamento;
import model.User;

public interface PrestazioneController {

	//visualizzare appuntamenti/prestazioni 
	List<Appuntamento> getAllPrestazioni();
	List<Appuntamento>getPrestazioniInData(Date data);
	//List<String>getPrestazioneDiAnimale(Animale animale );
	List<Appuntamento> getPrestazioneDiVeterinario(User veterinario);
	
	
	// aggiungere appuntamenti
	boolean addNewPrestazione(Appuntamento appuntamento);
	
	
}

package controller;

import java.sql.Date;
import java.util.List;

import model.Appuntamento;
import model.User;

public interface PrestazioneController {

	//visualizzare appuntamenti/prestazioni 
	List<Appuntamento> getAllPrestazioni();
	List<Appuntamento>getPrestazioniInData(String dataformat);
	//List<String>getPrestazioneDiAnimale(Animale animale );
	List<Appuntamento> getPrestazioneDiVeterinario(User veterinario);
	
	List<Appuntamento> getPrestazioneSettimana(String data);
	List<Appuntamento> getPrestazioneMese(String data);
	List<Appuntamento> getPrestazione(String data);
	
	// aggiungere appuntamenti
	boolean addNewPrestazione(Appuntamento appuntamento);
	
	
}

package controller;

import java.util.List;
import model.Appuntamento;
import model.Prestazione;
import model.User;

public interface PrestazioneController {

	// visualizzare appuntamenti/prestazioni
	List<Appuntamento> getAllPrestazioni();

	List<Appuntamento> getPrestazioniInData(String dataformat);

	// List<String>getPrestazioneDiAnimale(Animale animale );
	List<Appuntamento> getPrestazioneDiVeterinario(User veterinario);

	List<Appuntamento> getPrestazioneSettimana(String data);

	List<Appuntamento> getPrestazioneMese(String data);

	List<Appuntamento> getPrestazione(String data);

	boolean addNewAppuntamento(Appuntamento appuntamento);

	List<Appuntamento> getFuturePrestazioni();
	
	List<Prestazione> getAllTipiDiPrestazione();

	void addEffettua(Appuntamento app);

}

package controller;

import java.util.List;
import model.Appuntamento;
import model.Prestazione;
import model.User;

public interface PrestazioneController {

	// visualizzare appuntamenti/prestazioni
	List<Appuntamento> getAllPrestazioni();

	List<Appuntamento> getPrestazioniInData(String dataformat);

	List<Appuntamento> getPrestazioneSettimana(String data);

	List<Appuntamento> getPrestazioneMese(String data);

	boolean addNewAppuntamento(Appuntamento appuntamento);

	List<Appuntamento> getFuturePrestazioni();
	
	List<Prestazione> getAllTipiDiPrestazione();

	boolean addEffettua(Appuntamento app);

	List<String> getVeterinariNonDisponibili(String date, String time, String durata);

	List<String> getInfermieriPerAppuntamento(Appuntamento app);

	boolean updatePrestazioneSuAnimale(Appuntamento app, Appuntamento datiDiAppModificati);

	boolean updateEffettua(Appuntamento app, Appuntamento datiDiAppModificati);

	boolean deleteAppointmnet(Appuntamento app);

	List<Appuntamento> getPrestazioniByAnimale(int codAnimale);

}

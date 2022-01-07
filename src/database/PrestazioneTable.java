package database;

import java.util.List;

import model.Appuntamento;
import model.User;

public interface PrestazioneTable {
	
		List<Appuntamento> getAllPrestazioni();

		List<Appuntamento>getPrestazioniInData(String data);
		
		//List<String>getPrestazioneDiAnimale(Animale animale );
		
		List<Appuntamento> getPrestazioneDiVeterinario(User veterinario);
		
		List<Appuntamento> getPrestazioneMese(String data);

		List<Appuntamento> getPrestazioneSettiamana(String data);

		List<Appuntamento> getFuturePrestazioni();

		boolean addNewAppuntamento(Appuntamento appuntamento);

		boolean addEffettua(Appuntamento app);
		
		List<String> getVeterinariNonDisponibili(String date, String time, String durata);

		List<String> getInfermieriPerAppuntamento(Appuntamento app);

		boolean updatePrestazioneSuAnimale(Appuntamento app, Appuntamento datiDiAppModificati);

		boolean updateEffettua(Appuntamento app, Appuntamento datiDiAppModificati);

		boolean deleteAppointmnet(Appuntamento app);

}

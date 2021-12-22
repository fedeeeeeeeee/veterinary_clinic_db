package database;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import model.Appuntamento;
import model.User;

public interface PrestazioneTable {
	
		List<Appuntamento> getAllPrestazioni();

		List<Appuntamento>getPrestazioniInData(String data);
		//List<String>getPrestazioneDiAnimale(Animale animale );
		List<Appuntamento> getPrestazioneDiVeterinario(User veterinario);
		
		
		boolean addNewPrestazione(Appuntamento appuntamento);
}

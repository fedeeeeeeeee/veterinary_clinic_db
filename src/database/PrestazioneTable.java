package database;

import java.sql.Date;
import java.util.List;

import model.User;

public interface PrestazioneTable {
	
		List<String> getAllPrestazioni();
		List<String>getPrestazioniInData(Date data);
		//List<String>getPrestazioneDiAnimale(Animale animale );
		List<String> getPrestazioneDiVeterinario(User veterinario);
		
		
		boolean addNewPrestazione();
}

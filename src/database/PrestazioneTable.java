package database;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import model.Appuntamento;
import model.User;

public interface PrestazioneTable {
	
		List<Appuntamento> getAllPrestazioni();
		
		/* fare queste operazioni quando passiamo la data
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(dateChooser.getDate());
		 * */
		List<Appuntamento>getPrestazioniInData(String data);
		//List<String>getPrestazioneDiAnimale(Animale animale );
		List<Appuntamento> getPrestazioneDiVeterinario(User veterinario);
		
		
		boolean addNewPrestazione(Appuntamento appuntamento);
}

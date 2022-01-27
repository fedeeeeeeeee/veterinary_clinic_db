package controller;

import java.util.List;
import model.Appuntamento;
import model.Farmaco;
import model.Patologia;
import model.Prestazione;
import model.Utilizzo;

public interface PrestazioneController {

	List<Appuntamento> getAllPrestazioni();

	List<Appuntamento> getAllPrestazioniByPersonale(String cf);

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

	List<Farmaco> getFarmaciInPrestazione(Appuntamento appuntamento);

	List<Patologia> getPatologieAttiveDiAnimale(Appuntamento appuntamento);

	void addDataFinePatologia(Patologia p);

	List<Patologia> getAllPatologie();

	void addPatologiaInPrestazione(Patologia np, Appuntamento app);

	void addFarmacoInPrestazione(Farmaco nf, Appuntamento appuntamento);

	void addReferto(Appuntamento appuntamento);

	List<Appuntamento> getAllPrestazioniPerGestioneRicovero(String cf);

	boolean checkIfHasReferto(Appuntamento appuntamento);

	List<Utilizzo> getAllUtilizziDiPrestazione(Prestazione prestazione);

	String getReferto(Appuntamento appuntamento);

	void addPrestazione(Prestazione prestazione);

}

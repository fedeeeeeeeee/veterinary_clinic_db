package controller;

import java.util.List;

import database.PrestazioneTable;
import database.PrestazioneTableImpl;
import database.TipoPrestazioneTable;
import database.TipoPrestazioneTableImpl;
import model.Appuntamento;
import model.Farmaco;
import model.Patologia;
import model.Prestazione;

public class PrestazioneControllerImpl implements PrestazioneController {

	private PrestazioneTable prestazioneTable;
	private TipoPrestazioneTable tipoPrestazioneTable;

	public PrestazioneControllerImpl() {
		this.prestazioneTable = new PrestazioneTableImpl();
		this.tipoPrestazioneTable = new TipoPrestazioneTableImpl();

	}

	@Override
	public List<Appuntamento> getAllPrestazioni() { // ordinate per data
		return this.prestazioneTable.getAllPrestazioni();
	}

	@Override
	public List<Appuntamento> getPrestazioniInData(String data) {
		return this.prestazioneTable.getPrestazioniInData(data);
	}

	@Override
	public boolean addNewAppuntamento(Appuntamento appuntamento) {
		return this.prestazioneTable.addNewAppuntamento(appuntamento);
	}

	@Override
	public List<Appuntamento> getPrestazioneSettimana(String data) {
		return this.prestazioneTable.getPrestazioneSettiamana(data);
	}

	@Override
	public List<Appuntamento> getPrestazioneMese(String data) {
		return this.prestazioneTable.getPrestazioneMese(data);
	}

	@Override
	public List<Appuntamento> getFuturePrestazioni() {
		return this.prestazioneTable.getFuturePrestazioni();
	}

	@Override
	public List<Prestazione> getAllTipiDiPrestazione() {
		return this.tipoPrestazioneTable.getAllTipiDiPrestazione();
	}

	@Override
	public boolean addEffettua(Appuntamento app) {
		return this.prestazioneTable.addEffettua(app);
	}

	@Override
	public List<String> getVeterinariNonDisponibili(String date, String time, String durata) {
		return this.prestazioneTable.getVeterinariNonDisponibili(date, time, durata);
	}

	@Override
	public List<String> getInfermieriPerAppuntamento(Appuntamento app) {
		return this.prestazioneTable.getInfermieriPerAppuntamento(app);
	}

	@Override
	public boolean updatePrestazioneSuAnimale(Appuntamento app, Appuntamento datiDiAppModificati) {
		return this.prestazioneTable.updatePrestazioneSuAnimale(app, datiDiAppModificati);
	}

	@Override
	public boolean updateEffettua(Appuntamento app, Appuntamento datiDiAppModificati) {
		return this.prestazioneTable.updateEffettua(app, datiDiAppModificati);
	}

	@Override
	public boolean deleteAppointmnet(Appuntamento app) {
		return this.prestazioneTable.deleteAppointmnet(app);
	}

	@Override
	public List<Appuntamento> getPrestazioniByAnimale(int codAnimale) {
		return this.prestazioneTable.getPrestazioniByAnimale(codAnimale);
	}

	@Override
	public List<Farmaco> getFarmaciInPrestazione(Appuntamento appuntamento) {
		return this.prestazioneTable.getFarmaciInPrestazione(appuntamento);

	}

	@Override
	public List<Patologia> getPatologieAttiveDiAnimale(Appuntamento appuntamento) {
		return this.prestazioneTable.getPatologieInPrestazione(appuntamento);
	}

	@Override
	public void addDataFinePatologia(Patologia p) {
		this.prestazioneTable.addDataFinePatologia(p);
	}

	@Override
	public List<Patologia> getAllPatologie() {
		return this.prestazioneTable.getAllPatologie();
	}

	@Override
	public void addPatologiaInPrestazione(Patologia np, Appuntamento app) {
		this.prestazioneTable.addPatologiaInPrestazione(np, app);
	}

	@Override
	public List<Appuntamento> getAllPrestazioniByPersonale(String cf) {
		return this.prestazioneTable.getAllPrestazioniByPersonale(cf);
	}

	@Override
	public void addFarmacoInPrestazione(Farmaco nf, Appuntamento appuntamento) {
		this.prestazioneTable.addFarmacoInPrestazione(nf, appuntamento);
	}

}

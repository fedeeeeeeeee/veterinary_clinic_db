package model;

import java.util.ArrayList;
import java.util.List;

public class AppuntamentoImpl implements Appuntamento {

	private Animale animale;
	private User veterinario;

	private int codPrestazione;
	private String tipoPrestazione;

	private String prestazione;
	private String data;
	private String ora;
	private String durata;

	private List<User> list = new ArrayList<>();
	private String descrizioneReferto;
	private List<String> listFarmaci;
	private List<String> listPatologie;

	@Override
	public String getDescrizioneReferto() {
		return descrizioneReferto;
	}

	@Override
	public void setDescrizioneReferto(String descrizioneReferto) {
		this.descrizioneReferto = descrizioneReferto;
	}

	@Override
	public List<String> getListFarmaci() {
		return listFarmaci;
	}

	@Override
	public void setListFarmaci(List<String> listFarmaci) {
		this.listFarmaci = listFarmaci;
	}

	@Override
	public List<String> getListPatologie() {
		return listPatologie;
	}

	@Override
	public void setListPatologie(List<String> listPatologie) {
		this.listPatologie = listPatologie;
	}

	@Override
	public String getTipoPrestazione() {
		return tipoPrestazione;
	}

	@Override
	public void setTipoPrestazione(String tipoPrestazione) {
		this.tipoPrestazione = tipoPrestazione;
	}

	@Override
	public int getCodPrestazione() {
		return codPrestazione;
	}

	@Override
	public void setCodPrestazione(int codPrestazione) {
		this.codPrestazione = codPrestazione;
	}

	@Override
	public String getPrestazione() {
		return prestazione;
	}

	@Override
	public void setPrestazione(String prestazione) {
		this.prestazione = prestazione;
	}

	@Override
	public String getData() {
		return data;
	}

	@Override
	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String getOra() {
		return ora;
	}

	@Override
	public void setOra(String ora) {
		this.ora = ora;
	}

	@Override
	public String getDurata() {
		return durata;
	}

	@Override
	public void setDurata(String durata) {
		this.durata = durata;
	}

	@Override
	public List<User> getAllMediciEInfermieri() {
		return this.list;
	}

	@Override
	public void setAllMediciEInfermieri(List<User> list) {
		this.list = list;
	}

	@Override
	public void setVeterinario(User veterinario) {
		this.veterinario = veterinario;

	}

	@Override
	public User getVeterinario() {
		return veterinario;
	}

	@Override
	public Animale getAnimale() {
		return this.animale;
	}

	@Override
	public void setAnimale(Animale animale) {
		this.animale = animale;
	}

}
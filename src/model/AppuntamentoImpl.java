package model;

import java.util.ArrayList;
import java.util.List;

public class AppuntamentoImpl implements Appuntamento {
	

	private Animale animale;
	private User veterinario; 
	
	//private int codAnimale;
	//private String nomeAnimale;

	private int codPrestazione;
	private String prestazione;
	private String data;
	private String ora;
	private String durata;

	private List<User> list = new ArrayList<>();
	
	@Override
	public int getCodPrestazione() {
		return codPrestazione;
	}

	
	@Override
	public void setCodPrestazione(int codPrestazione) {
		this.codPrestazione = codPrestazione;
	}

	/*
	@Override
	public int getCodAnimale() {
		return codAnimale;
	}

	@Override
	public String getNomeAnimale() {
		return nomeAnimale;
	}

	@Override
	public void setNomeAnimale(String nomeAnimale) {
		this.nomeAnimale = nomeAnimale;
	}
*/
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
/*
	@Override
	public String getNomeVeterinario() {
		return nomeVeterinario;
	}

	@Override
	public void setNomeVeterinario(String nomeVeterinario) {
		this.nomeVeterinario = nomeVeterinario;
	}

	@Override
	public String getCognomeVeterinario() {
		return cognomeVeterinario;
	}

	@Override
	public void setCognomeVeterinario(String cognomeVeterinario) {
		this.cognomeVeterinario = cognomeVeterinario;
	}
*/
	@Override
	public void addInfermiere(User infermiere) {
		// TODO Auto-generated method stub
		
	}

	/*
	@Override
	public void setCodAnimale(int codAnimale) { // questi non vengono visualizzati in Gestione Appuntamento ma ci servono per fare le select nelle altre pagine
		this.codAnimale= codAnimale;
	}
*/
	@Override
	public void setCodTipoPrestazione(int codPrestazione) {
		this.codPrestazione= codPrestazione;		
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
	public User getVeterinario( ) {
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
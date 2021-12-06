package model;

import java.util.List;
import java.util.Optional;

public class UserImpl implements User{

	private String nome;
	private String cognome;
	private String CF;
	private String dataNascita;
	private String indirizzoCitta;
	private String indirizzoVia;
	private int indirizzoCap;
	private int indirizzoNumero;
	private long telefono;
	private String email;
	private String sale;
	private String hashPassword;
	private int yearOnTheJob;
	private  Optional<List<String>>  specializzazioni;
	private String ruolo;
	
	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public String getCognome() {
		return cognome;
	}

	@Override
	public String getCF() {
		return this.CF;
	}

	@Override
	public String getDataNascita() {
		return this.dataNascita;
	}

	@Override
	public String getIndirizzoCitta() {
		return this.indirizzoCitta;
	}

	@Override
	public String getIndirizzoVia() {
		return this.indirizzoVia;
	}

	@Override
	public int getIndirizzoNumero() {
		return this.indirizzoNumero;
	}

	@Override
	public int getIndirizzoCap() {
		return this.indirizzoCap;
	}

	@Override
	public long getTelefono() {
		return this.telefono;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public void setNome(String nome) {
		this.nome= nome;
		
	}

	@Override
	public void setCognome(String cognome) {
		this.cognome = cognome;
		
	}

	@Override
	public void setCF(String CF) {
		this.CF=CF;
		
	}

	@Override
	public void setIndirizzoVia(String via) {
		this.indirizzoVia = via;
		
	}

	@Override
	public void setIndirizzoNumero(int numero) {
		this.indirizzoNumero = numero;
		
	}

	@Override
	public void setIndirizzoCap(int cap) {
this.indirizzoCap = cap;		
	}

	@Override
	public void setIndirizzoCitta(String citta) {
		this.indirizzoCitta = citta;
		
	}

	@Override
	public void setDataNascita(String dataNascita) {
this.dataNascita = dataNascita;
		
	}

	@Override
	public void setTelefono(long telefono) {
		this.telefono = telefono;		
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getSalt() {
		return sale; 
	}

	@Override
	public String getHashPassword() {
		return this.hashPassword;
	}

	@Override
	public int getAnniDiServizio() {
		return this.yearOnTheJob;
	}

	@Override
	public Optional<List<String>> getSpecializations() {
		return this.specializzazioni;
	}

	@Override
	public String getRuolo() {
		return this.ruolo;
	}

	@Override
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
		
	}

	@Override
	public void setSalt(String salt) {
		this.sale = salt;
	}

	@Override
	public void setHashPassword(String password) {
		this.hashPassword = password;
	}

	@Override
	public void setAnniDiServizio(int years) {
		this.yearOnTheJob = years;
	}

	@Override
	public void setSpecialization(String specialization) {
		//TODO 
	}

}

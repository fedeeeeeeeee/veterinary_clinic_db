package model;

public class Proprietario implements Person {

	private String nome;
	private String cognome;
	private String cf;
	private String dataNascita;
	private String indirizzoCitta;
	private String indirizzoVia;
	private int indirizzoNumero;
	private int indirizzoCap;
	private long telefono;
	private String email;

	@Override
	public String getNome() {
		return nome;
	}
	
	@Override	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String getCognome() {
		return cognome;
	}

	@Override
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	@Override
	public String getCF() {
		return cf;
	}

	@Override
	public void setCF(String cf) {
		this.cf = cf;
	}

	@Override
	public String getDataNascita() {
		return dataNascita;
	}

	@Override
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	@Override
	public String getIndirizzoCitta() {
		return indirizzoCitta;
	}

	@Override
	public void setIndirizzoCitta(String indirizzoCitta) {
		this.indirizzoCitta = indirizzoCitta;
	}
	
	@Override
	public String getIndirizzoVia() {
		return indirizzoVia;
	}

	@Override
	public void setIndirizzoVia(String indirizzoVia) {
		this.indirizzoVia = indirizzoVia;
	}

	@Override
	public int getIndirizzoNumero() {
		return indirizzoNumero;
	}

	@Override
	public void setIndirizzoNumero(int indirizzoNumero) {
		this.indirizzoNumero = indirizzoNumero;
	}

	@Override
	public int getIndirizzoCap() {
		return indirizzoCap;
	}

	@Override
	public void setIndirizzoCap(int indirizzoCap) {
		this.indirizzoCap = indirizzoCap;
	}

	@Override
	public long getTelefono() {
		return telefono;
	}

	
	@Override
	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	@Override
	public String getEmail() {
		return email;
	}
	
	@Override
	public void setEmail(String email) {
		this.email = email;
	}

}

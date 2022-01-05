package model;

public class AnimaleImpl implements Animale {
	
	private int codAnimale;
	private String nome;
	private String genere;
	private String dataNascita;
	private float altezza;
	private float peso;
	private String razza;
	private String specie;
	
	private String cf; // person
	//private String nomeProprietario;// person
	//private String cognomeProprietario;// person
	private Person proprietario;
	//private long telefono;// person
	
	@Override
	public void setProprietario(Person proprietario) {
		this.proprietario = proprietario;
	}
	@Override
	public Person getProprietario() {
		return this.proprietario;
	}
	@Override
	public String getNome() {
		return nome;
	}
	
	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String getGenere() {
		return genere;
	}
	
	@Override
	public void setGenere(String genere) {
		this.genere = genere;
	}
	
	@Override
	public String getDataDiNascita() {
		return dataNascita;
	}
	
	@Override
	public void setDataDiNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	@Override
	public float getAltezza() {
		return altezza;
	}
	
	@Override
	public void setAltezza(float altezza) {
		this.altezza = altezza;
	}
	
	@Override
	public float getPeso() {
		return peso;
	}
	
	@Override
	public void setPeso(float peso) {
		this.peso = peso;
	}
	
	@Override
	public String getRazza() {
		return razza;
	}
	
	@Override
	public void setRazza(String razza) {
		this.razza = razza;
	}
	
	@Override
	public String getSpecie() {
		return specie;
	}
	
	@Override
	public void setSpecie(String specie) {
		this.specie = specie;
	}
	
	@Override
	public String getCf() {
		return cf;
	}
	
	@Override
	public void setCf(String cf) {
		this.cf = cf;
	}
	/*
	@Override
	public String getNomeProprietario() {
		return nomeProprietario;
	}

	@Override
	public void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
	}

	@Override
	public String getCognomeProprietario() {
		return cognomeProprietario;
	}

	@Override
	public void setCognomeProprietario(String cognomeProprietario) {
		this.cognomeProprietario = cognomeProprietario;
	}

	@Override
	public long getTelefono() {
		return telefono;
	}

	@Override
	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}
*/
	@Override
	public int getCodAnimale() {
		return this.codAnimale;
	}

	@Override
	public void setCodAnimale(int cod) {
		this.codAnimale = cod;
	}
}

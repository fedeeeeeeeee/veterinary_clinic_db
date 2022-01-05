package model;

public interface Animale {
	
	int getCodAnimale();

	String getNome();

	String getGenere();

	String getDataDiNascita();

	float getAltezza();

	float getPeso();

	String getRazza();

	String getSpecie();

	String getCf();

//	String getNomeProprietario();

	//String getCognomeProprietario();

	void setNome(String nome);

	void setGenere(String genere);

	void setDataDiNascita(String dataNascita);

	void setAltezza(float altezza);

	void setPeso(float peso);

	void setRazza(String razza);

	void setSpecie(String specie);

	void setCf(String cf);

	//void setNomeProprietario(String nomeProprietario);

	//void setCognomeProprietario(String cognomeProprietario);

	//long getTelefono();

	//void setTelefono(long telefono);
	
	void setCodAnimale(int cod);


	Person getProprietario();

	void setProprietario(Person proprietario);
}

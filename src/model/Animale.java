package model;

public interface Animale {
	
	String getNome();

	String getGenere();

	String getDataDiNascita();

	float getAltezza();

	float getPeso();

	String getRazza();
	
	String getSpecie();
		
	String getCf();

	void setNome(String nome);

	void setGenere(String genere);

	void setDataDiNascita(String dataNascita);

	void setAltezza(float altezza);

	void setPeso(float peso);

	void setRazza(String razza);
	
	void setSpecie(String specie);
		
	void setCf(String cf);
}

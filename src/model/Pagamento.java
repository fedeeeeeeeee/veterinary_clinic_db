package model;

public interface Pagamento {
	
	Animale getAnimale();
	
	void setAnimale(Animale animale);
	
	String getDataPrestazione();
	
	void setDataPrestazione(String data);
	
	String getNomePrestazione();
	
	void setNomePrestazione(String nomePrestazione);

	float getCosto();
	
	void setCosto(float f);

	void setRicovero(boolean duranteRicovero);
	
	boolean isDuranteRicovero();

	String getOraPrestazione();
	
	void setOraPrestazione(String ora);

}

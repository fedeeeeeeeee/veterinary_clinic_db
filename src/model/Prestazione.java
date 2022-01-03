package model;

public interface Prestazione {
	
	int getCodPrestazione();

	String getTipoPrestazione();
	
	String getNomePrestazione();
	
	String getDurataMedia();
	
	float getPrezzo();

	String getDescrizione();

	void setCodPrestazione(int i);
	
	void setTipoPrestazione(String nomePrestazione);
	
	void setNomePrestazione(String nomePrestazione);

	void setDurataMedia(String durata);

	void setPrezzo(float prezzo);
	
	void setDescrizione(String descrizione);

}

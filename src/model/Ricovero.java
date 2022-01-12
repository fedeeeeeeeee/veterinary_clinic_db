package model;

public interface Ricovero {
	
	Animale getAnimale();

	void setAnimale(Animale animale);

	String getDataInizio();

	void setDataInizio(String dataInizio);

	String getOraInizio();

	public void setOraInizio(String oraInizio);

	int getDurataInGiorni();

	void setDurataInGiorni(int durataInGiorni);

	float getPrezzoGiornaliero();

	void setPrezzoGiornaliero(float prezzoGiornaliero);

	void setMotivoRicovero(String string);

	void setPosto(Posto posto);

	Posto getPosto();

	String getMotivoRicovero();
}

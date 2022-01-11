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

	int getNumeroPosto();

	void setNumeroPosto(int numeroPosto);
}

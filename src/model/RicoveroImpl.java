package model;

public class RicoveroImpl implements Ricovero {
	private Animale animale;
	private String dataInizio;
	private String oraInizio;
	private int durataInGiorni;
	private float prezzoGiornaliero;
	private int numeroPosto;

	@Override
	public Animale getAnimale() {
		return animale;
	}

	@Override
	public void setAnimale(Animale animale) {
		this.animale = animale;
	}

	@Override
	public String getDataInizio() {
		return dataInizio;
	}

	@Override
	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}

	@Override
	public String getOraInizio() {
		return oraInizio;
	}

	@Override
	public void setOraInizio(String oraInizio) {
		this.oraInizio = oraInizio;
	}

	@Override
	public int getDurataInGiorni() {
		return durataInGiorni;
	}

	@Override
	public void setDurataInGiorni(int durataInGiorni) {
		this.durataInGiorni = durataInGiorni;
	}

	@Override
	public float getPrezzoGiornaliero() {
		return prezzoGiornaliero;
	}

	@Override
	public void setPrezzoGiornaliero(float prezzoGiornaliero) {
		this.prezzoGiornaliero = prezzoGiornaliero;
	}

	@Override
	public int getNumeroPosto() {
		return numeroPosto;
	}

	@Override
	public void setNumeroPosto(int numeroPosto) {
		this.numeroPosto = numeroPosto;
	}
}

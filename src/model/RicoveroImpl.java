package model;

public class RicoveroImpl implements Ricovero {
	private Animale animale;
	private String dataInizio;
	private String oraInizio;
	private String motivoRicovero;
	private int durataInGiorni;
	private float prezzoGiornaliero;
	//private int numeroPosto;
	private Posto posto;
	

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
	public Posto getPosto() {
		return this.posto;
	}
	
	@Override
	public String getMotivoRicovero() {
		return this.motivoRicovero;
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
	public void setMotivoRicovero(String string) {
		this.motivoRicovero = string;
	}

	@Override
	public void setPosto(Posto posto) {
		this.posto = posto;
		
	}
}

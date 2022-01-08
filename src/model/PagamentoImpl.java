package model;

public class PagamentoImpl implements Pagamento {
	
	private Animale animale;
	private String data;
	private String nomePrestazione;
	private float costo;

	@Override
	public Animale getAnimale() {
		return this.animale;
	}

	@Override
	public void setAnimale(Animale animale) {
		this.animale = animale;
	}

	@Override
	public String getDataPrestazione() {
		return this.data;
	}

	@Override
	public void setDataPrestazione(String data) {
		this.data = data;
	}

	@Override
	public String getNomePrestazione() {
		return this.nomePrestazione;
	}

	@Override
	public void setNomePrestazione(String nomePrestazione) {
		this.nomePrestazione = nomePrestazione;
	}

	@Override
	public float getCosto() {
		return this.costo;
	}

	@Override
	public void setCosto(float costo) {
		this.costo = costo;
	}

}

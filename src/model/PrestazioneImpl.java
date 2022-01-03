package model;

public class PrestazioneImpl implements Prestazione {

	private int codPrestazione;
	private String nomePrestazione;
	private String tipoPrestazione;
	private String durataMedia;
	private float prezzo;
	private String descrizione;
	
	@Override
	public int getCodPrestazione() {
		return codPrestazione;
	}

	@Override
	public void setCodPrestazione(int codPrestazione) {
		this.codPrestazione = codPrestazione;
	}

	@Override
	public String getNomePrestazione() {
		return nomePrestazione;
	}
	
	@Override
	public void setNomePrestazione(String nomePrestazione) {
		this.nomePrestazione = nomePrestazione;
	}
	
	@Override
	public String getTipoPrestazione() {
		return tipoPrestazione;
	}

	@Override
	public void setTipoPrestazione(String tipoPrestazione) {
		this.tipoPrestazione = tipoPrestazione;
	}

	@Override
	public String getDurataMedia() {
		return durataMedia;
	}

	@Override
	public void setDurataMedia(String durataMedia) {
		this.durataMedia = durataMedia;
	}

	@Override
	public float getPrezzo() {
		return prezzo;
	}

	@Override
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	@Override
	public String getDescrizione() {
		return descrizione;
	}

	@Override
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}



}

package model;

public class PatologiaImpl implements Patologia {

	private int codPatologia;
	private String nomePatologia;
	private String descrizione;
	private String tipoPatologia;

	@Override
	public int getCodPatologia() {
		return codPatologia;
	}

	@Override
	public void setCodPatologia(int codPatologia) {
		this.codPatologia = codPatologia;
	}

	@Override
	public String getNomePatologia() {
		return nomePatologia;
	}

	@Override
	public void setNomePatologia(String nomePatologia) {
		this.nomePatologia = nomePatologia;
	}

	@Override
	public String getDescrizione() {
		return descrizione;
	}

	@Override
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String getTipoPatologia() {
		return tipoPatologia;
	}

	@Override
	public void setTipoPatologia(String tipoPatologia) {
		this.tipoPatologia = tipoPatologia;
	}

}

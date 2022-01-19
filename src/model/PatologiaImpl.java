package model;

public class PatologiaImpl implements Patologia {

	private int codPatologia;
	private String nomePatologia;
	private String descrizione;
	private String tipoPatologia;
private String dataInizio; 
private String dataFine;
private String codTipoPrestazione;
private String dataPrestazione;

@Override
	public String getDataInizio() {
	return dataInizio;
}
	@Override
public void setDataInizio(String dataInizio) {
	this.dataInizio = dataInizio;
}

public String getDataFine() {
	return dataFine;
}
@Override
public void setDataFine(String dataFine) {
	this.dataFine = dataFine;
}
@Override
public String getCodTipoPrestazione() {
	return codTipoPrestazione;
}
@Override
public void setCodTipoPrestazione(String codTipoPrestazione) {
	this.codTipoPrestazione = codTipoPrestazione;
}
@Override
public String getDataPrestazione() {
	return dataPrestazione;
}
@Override
public void setDataPrestazione(String dataPrestazione) {
	this.dataPrestazione = dataPrestazione;
}

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

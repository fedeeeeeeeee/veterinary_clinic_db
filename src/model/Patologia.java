package model;

public interface Patologia {

	public int getCodPatologia();

	public void setCodPatologia(int codPatologia);

	public String getNomePatologia();

	public void setNomePatologia(String nomePatologia);

	public String getDescrizione();

	public void setDescrizione(String descrizione);

	public String getTipoPatologia();

	public void setTipoPatologia(String tipoPatologia);

	public String getDataInizio();

	public void setDataInizio(String dataInizio);

	public String getDataFine();

	public void setDataFine(String dataFine);

	public String getCodTipoPrestazione();

	public void setCodTipoPrestazione(String codTipoPrestazione);

	public String getDataPrestazione();

	public void setDataPrestazione(String dataPrestazione);
}

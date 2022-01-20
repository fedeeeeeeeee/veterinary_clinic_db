package model;

public interface Patologia {

	int getCodPatologia();

	void setCodPatologia(int codPatologia);

	String getNomePatologia();

	void setNomePatologia(String nomePatologia);

	String getDescrizione();

	void setDescrizione(String descrizione);

	String getTipoPatologia();

	void setTipoPatologia(String tipoPatologia);

	String getDataInizio();

	void setDataInizio(String dataInizio);

	String getDataFine();

	void setDataFine(String dataFine);

	int getCodTipoPrestazione();

	void setCodTipoPrestazione(int codTipoPrestazione);

	String getDataPrestazione();

	void setDataPrestazione(String dataPrestazione);

	int getCodAnimale();
	
	void setCodAnimale(int codAnimale);

}

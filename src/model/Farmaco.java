package model;

public interface Farmaco {

	String getNomeFarmaco();

	void setNomeFarmaco(String nomeFarmaco);

	int getCodLotto();

	void setCodLotto(int codLotto);

	String getDataScadenza();

	void setDataScadenza(String dataScadenza);

	int getDisponibilita();

	void setDisponibilita(int disponibilita);

	String getTipoFarmaco();

	void setTipoFarmaco(String tipoFarmaco);

	String getDescrizione();

	void setDescrizione(String descrizione);

	int getCodFarmaco();

	void setCodFarmaco(int codFarmaco);

	String getCopertura();

	void setCopertura(String copertura);

	void setIndicazioni(String string);

	String getIndicazioni();
}

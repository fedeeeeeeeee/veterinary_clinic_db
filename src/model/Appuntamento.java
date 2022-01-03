package model;

public interface Appuntamento {
	int getCodPrestazione();

	int getCodAnimale();

	String getNomeAnimale();

	String getPrestazione();

	String getData();

	String getOra();

	String getNomeVeterinario();

	String getCognomeVeterinario();

	String getDurata();

	void setNomeAnimale(String nomeAnimale);

	void setPrestazione(String prestazione);

	void setData(String data);

	void setOra(String ora);

	void setNomeVeterinario(String nomeVeterinario);

	void setCognomeVeterinario(String cognomeVeterinario);

	void addInfermiere(User infermiere); // add table effettua

	void setCodAnimale(int codAnimale);

	void setCodTipoPrestazione(int codPrestazione);

	void setDurata(String durata);

	void setCodPrestazione(int codPrestazione);

}

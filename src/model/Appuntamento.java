package model;

public interface Appuntamento {

	String getNomeAnimale();

	String getPrestazione();

	String getData();

	String getOra();

	String getNomeVeterinario();

	String getCognomeVeterinario();

	void setNomeAnimale(String nomeAnimale);

	void setPrestazione(String prestazione);

	void setData(String data);

	void setOra(String ora);

	void setNomeVeterinario(String nomeVeterinario);

	void setCognomeVeterinario(String cognomeVeterinario);
}

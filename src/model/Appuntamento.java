package model;

import java.util.List;

public interface Appuntamento {
	Animale getAnimale();
	void setAnimale(Animale animale);
	
	int getCodPrestazione();

	//int getCodAnimale();

	//String getNomeAnimale();

	String getPrestazione();

	String getData();

	String getOra();
	User getVeterinario( );

	String getDurata();
	
	List<User> getAllMediciEInfermieri();

	//void setNomeAnimale(String nomeAnimale);

	void setPrestazione(String prestazione);

	void setData(String data);

	void setOra(String ora);

	void addInfermiere(User infermiere); // add table effettua

//	void setCodAnimale(int codAnimale);

//	void setCodTipoPrestazione(int codPrestazione);

	void setDurata(String durata);

	void setCodPrestazione(int codPrestazione);
	
	void setAllMediciEInfermieri(List<User> list);

	void setVeterinario(User veterinario);
	String getTipoPrestazione();
	void setTipoPrestazione(String tipoPrestazione); 

}

package model;

import java.util.List;

public interface Appuntamento {
	Animale getAnimale();

	void setAnimale(Animale animale);

	int getCodPrestazione();

	String getPrestazione();

	String getData();

	String getOra();

	User getVeterinario();

	String getDurata();

	List<User> getAllMediciEInfermieri();

	void setPrestazione(String prestazione);

	void setData(String data);

	void setOra(String ora);

//	void addInfermiere(User infermiere); // add table effettua

	void setDurata(String durata);

	void setCodPrestazione(int codPrestazione);

	void setAllMediciEInfermieri(List<User> list);

	void setVeterinario(User veterinario);

	String getTipoPrestazione();

	void setTipoPrestazione(String tipoPrestazione);

	void setListFarmaci(List<Farmaco> farmaci);

	void setDescrizioneReferto(String descrizioneReferto);

	void setListPatologie(List<Patologia> listPatologie);

	
	String getDescrizioneReferto();

	List<Patologia> getListPatologie();

	List<Farmaco> getListFarmaci();
	
	void setPeso(float peso);
	
	void setAltezza(float peso);
	
	float getPeso();
	
	float getAltezza();

}

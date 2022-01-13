package model;

import java.util.List;

public interface Paziente {

	void setPrestazioniPazienti(List<Prestazione> prestazioniPazienti);

	List<Prestazione> getPrestazioniPazienti();

	void setPatologiePazienti(List<Patologia> patologiePazienti);

	List<Patologia> getPatologiePazienti();

	void setFarmaciPazienti(List<Farmaco> farmaciPazienti);

	List<Farmaco> getFarmaciPazienti();

	void setAnimale(Animale animale);

	Animale getAnimale();

}

package model;

import java.util.List;

public class PazienteImpl implements Paziente {

	private Animale animale;
	private List<Farmaco> farmaciPazienti;
	private List<Patologia> patologiePazienti;
	private List<Prestazione> prestazioniPazienti;
	
	
	@Override
	public Animale getAnimale() {
		return animale;
	}

	@Override
	public void setAnimale(Animale animale) {
		this.animale = animale;
	}


	@Override
	public List<Farmaco> getFarmaciPazienti() {
		return farmaciPazienti;
	}

	@Override
	public void setFarmaciPazienti(List<Farmaco> farmaciPazienti) {
		this.farmaciPazienti = farmaciPazienti;
	}

	@Override
	public List<Patologia> getPatologiePazienti() {
		return patologiePazienti;
	}


	@Override
	public void setPatologiePazienti(List<Patologia> patologiePazienti) {
		this.patologiePazienti = patologiePazienti;
	}

	@Override
	public List<Prestazione> getPrestazioniPazienti() {
		return prestazioniPazienti;
	}

	@Override
	public void setPrestazioniPazienti(List<Prestazione> prestazioniPazienti) {
		this.prestazioniPazienti = prestazioniPazienti;
	}


	
	
}

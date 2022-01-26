package model;

public class UtilizzoImpl implements Utilizzo {

	private Strumento strumento;
	private Prestazione prestazione;
	private int qt;
	
	@Override
	public Strumento getStrumento() {
		return this.strumento;
	}

	@Override
	public void setStrumento(Strumento strumento) {
		this.strumento = strumento;
	}

	@Override
	public Prestazione getPrestazione() {
		return this.prestazione;
	}

	@Override
	public void setPrestazione(Prestazione prestazione) {
		this.prestazione = prestazione;
	}

	@Override
	public int getQuantita() {
		return this.qt;
	}

	@Override
	public void setQuantita(int qt) {
		this.qt = qt;
	}

}

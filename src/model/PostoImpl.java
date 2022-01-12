package model;

public class PostoImpl implements Posto {

	private int numero;
	private String descrizione;
	
	
	@Override
	public int getNumero() {
		return numero;
	}
	@Override
	public void setNumero(int numero) {
		this.numero = numero;
	}
	@Override
	public String getDescrizione() {
		return descrizione;
	}
	@Override
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
}

package model;

public class StrumentoImpl implements Strumento {

	private int codStrumento;
	private String nome;
	private String descrizione;
	private String dataScadenza;
	private int quantita;
	private float prezzo;

	@Override
	public int getCodStrumento() {
		return codStrumento;
	}

	@Override
	public void setCodStrumento(int codStrumento) {
		this.codStrumento = codStrumento;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String getDescrizione() {
		return descrizione;
	}

	@Override
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String getDataScadenza() {
		return dataScadenza;
	}

	@Override
	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	@Override
	public int getQuantita() {
		return quantita;
	}

	@Override
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	@Override
	public float getPrezzo() {
		return prezzo;
	}
	@Override
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

}

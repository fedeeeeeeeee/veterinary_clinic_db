package model;

public class LottoImpl implements Lotto {

	private int codLotto;
	private int numeroConfezioni;
	private int quantitaPerConfezione;
	private String dataScadenza;
	private int codFarmaco;
	
	@Override
	public int getCodLotto() {
		return codLotto;
	}

	@Override
	public void setCodLotto(int codLotto) {
		this.codLotto = codLotto;
	}

	@Override
	public int getNumeroConfezioni() {
		return numeroConfezioni;
	}

	@Override
	public void setNumeroConfezioni(int numeroConfezioni) {
		this.numeroConfezioni = numeroConfezioni;
	}

	@Override
	public int getQuantitaPerConfezione() {
		return quantitaPerConfezione;
	}

	@Override
	public void setQuantitaPerConfezione(int quantitaPerConfezione) {
		this.quantitaPerConfezione = quantitaPerConfezione;
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
	public int getCodFarmaco() {
		return codFarmaco;
	}

	@Override
	public void setCodFarmaco(int codFarmaco) {
		this.codFarmaco = codFarmaco;
	}

}

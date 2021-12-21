package model;

public class FarmacoImpl implements Farmaco {

	private int codFarmaco;
	private String nomeFarmaco;
	private int codLotto;
	private String dataScadenza;
	private int disponibilita;
	private String tipoFarmaco;
	private String descrizione;
	
	@Override
	public String getNomeFarmaco() {
		return nomeFarmaco;
	}
	@Override
	public void setNomeFarmaco(String nomeFarmaco) {
		this.nomeFarmaco = nomeFarmaco;
	}
	@Override
	public int getCodLotto() {
		return codLotto;
	}
	@Override
	public void setCodLotto(int codLotto) {
		this.codLotto = codLotto;
	}
	@Override
	public int getCodFarmaco() {
		return codFarmaco;
	}
	@Override
	public void setCodFarmaco(int codFarmaco) {
		this.codFarmaco = codFarmaco;
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
	public int getDisponibilita() {
		return disponibilita;
	}
	@Override
	public void setDisponibilita(int disponibilita) {
		this.disponibilita = disponibilita;
	}
	@Override
	public String getTipoFarmaco() {
		return tipoFarmaco;
	}
	@Override
	public void setTipoFarmaco(String tipoFarmaco) {
		this.tipoFarmaco = tipoFarmaco;
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

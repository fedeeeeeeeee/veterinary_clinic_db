package model;

public class FarmacoImpl implements Farmaco {

	private int codFarmaco;
	
	private int codLotto;
	private String nomeFarmaco;
	private String descrizione;
	private String dataScadenza;
	private int disponibilita;
	private String tipoFarmaco;
	private String copertura;
	private String indicazioni;
	private String dataInizioSomministrazione;
	private String dataFineSomministrazione;
	
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
	
	@Override
	public String getCopertura() {
		return copertura;
	}
	
	@Override
	public void setCopertura(String copertura) {
		this.copertura = copertura;
	}
	@Override
	public void setIndicazioni(String indicazioni) {
		this.indicazioni = indicazioni;
	}
	@Override
	public String getIndicazioni() {
		return this.indicazioni;
	}
	@Override
	public void setDataInizioSomministrazione(String data) {
		this.dataInizioSomministrazione = data;
	}
	@Override
	public void setDataFineSomministrazione(String data) {
		this.dataFineSomministrazione = data;
	}
	@Override
	public String getDataInizioSomministrazione() {
		return this.dataInizioSomministrazione;
	}
	@Override
	public String getDataFineSomministrazione() {
		return this.dataFineSomministrazione;
	}
}

package model;

public class FarmacoInPrestazioneImpl implements FarmacoInPrestazione {
	private Farmaco farmaco;
	private String indicazioni;
	private String dataInizioSomministrazione;
	private String dataFineSomministrazione;
	
	@Override
	public Farmaco getFarmaco() {
		return farmaco;
	}
	
	@Override
	public void setFarmaco(Farmaco farmaco) {
		this.farmaco = farmaco;
	}
	
	@Override
	public String getIndicazioni() {
		return indicazioni;
	}
	
	@Override
	public void setIndicazioni(String indicazioni) {
		this.indicazioni = indicazioni;
	}
	
	@Override
	public String getDataInizioSomministrazione() {
		return dataInizioSomministrazione;
	}
	
	@Override
	public void setDataInizioSomministrazione(String dataInizioSomministrazione) {
		this.dataInizioSomministrazione = dataInizioSomministrazione;
	}
	
	@Override
	public String getDataFineSomministrazione() {
		return dataFineSomministrazione;
	}
	
	@Override
	public void setDataFineSomministrazione(String dataFineSomministrazione) {
		this.dataFineSomministrazione = dataFineSomministrazione;
	}
	
}

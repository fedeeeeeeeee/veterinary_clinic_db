package model;

public interface FarmacoInPrestazione {

	void setDataFineSomministrazione(String dataFineSomministrazione);

	String getDataFineSomministrazione();

	void setDataInizioSomministrazione(String dataInizioSomministrazione);

	String getDataInizioSomministrazione();

	void setIndicazioni(String indicazioni);

	String getIndicazioni();

	void setFarmaco(Farmaco farmaco);

	Farmaco getFarmaco();

}

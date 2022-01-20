package model;

public class ParametriPazienteImpl implements ParametriPaziente {

	private String data;
	private float altezza;
	private float peso;
	
	@Override
	public String getData() {
		return data;
	}
	@Override
	public void setData(String data) {
		this.data = data;
	}
	@Override
	public float getAltezza() {
		return altezza;
	}
	@Override
	public void setAltezza(float altezza) {
		this.altezza = altezza;
	}
	@Override
	public float getPeso() {
		return peso;
	}
	@Override
	public void setPeso(float peso) {
		this.peso = peso;
	}
}

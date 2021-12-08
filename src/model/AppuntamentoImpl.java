package model;

public class AppuntamentoImpl implements Appuntamento {

	private String nomeAnimale;
	private String prestazione;
	private String data;
	private String ora;
	private String nomeVeterinario;
	private String cognomeVeterinario;

	@Override
	public String getNomeAnimale() {
		return nomeAnimale;
	}

	@Override
	public void setNomeAnimale(String nomeAnimale) {
		this.nomeAnimale = nomeAnimale;
	}

	@Override
	public String getPrestazione() {
		return prestazione;
	}

	@Override
	public void setPrestazione(String prestazione) {
		this.prestazione = prestazione;
	}

	@Override
	public String getData() {
		return data;
	}

	@Override
	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String getOra() {
		return ora;
	}

	@Override
	public void setOra(String ora) {
		this.ora = ora;
	}

	@Override
	public String getNomeVeterinario() {
		return nomeVeterinario;
	}

	@Override
	public void setNomeVeterinario(String nomeVeterinario) {
		this.nomeVeterinario = nomeVeterinario;
	}

	@Override
	public String getCognomeVeterinario() {
		return cognomeVeterinario;
	}

	@Override
	public void setCognomeVeterinario(String cognomeVeterinario) {
		this.cognomeVeterinario = cognomeVeterinario;
	}

}
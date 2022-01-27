package controller;

import java.util.List;

import database.StrumentiTable;
import database.StrumentiTableImpl;
import model.Strumento;
import model.Utilizzo;

public class StrumentiControllerImpl implements StrumentiController {

	private StrumentiTable strumentiTable;
	
	public StrumentiControllerImpl () {
		this.strumentiTable = new StrumentiTableImpl();
	}
	
	@Override
	public List<Strumento> getAllStrumenti() {
		return this.strumentiTable.getAllStrumenti();
	}

	@Override
	public List<Strumento> getStrumentiScaduti() {
		return this.strumentiTable.getStrumentiScaduti();
	}

	@Override
	public List<Strumento> getStrumentiInScadenza() {
		return this.strumentiTable.getStrumentiInScadenza();
	}

	@Override
	public List<Strumento> getStrumentiInEsaurimento() {
		return this.strumentiTable.getStrumentiInEsaurimento();
	}

	@Override
	public void updateQuantita(Strumento strumento, int q) {
		this.strumentiTable.updateQuantita( strumento, q);
	}

	@Override
	public void addNewStrumento(Strumento stru) {
		this.strumentiTable.addNewStrumento( stru);
		
	}

	@Override
	public void addUtilizzo(Utilizzo utilizzo) {
		this.strumentiTable.addUtilizzo(utilizzo);
	}
}

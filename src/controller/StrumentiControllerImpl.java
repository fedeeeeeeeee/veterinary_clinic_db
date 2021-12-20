package controller;

import java.util.List;

import database.StrumentiTable;
import database.StrumentiTableImpl;
import model.Strumento;

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
}

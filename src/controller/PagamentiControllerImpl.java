package controller;

import java.util.List;

import database.PagamentiTable;
import database.PagamentiTableImpl;
import model.Pagamento;

public class PagamentiControllerImpl implements PagamentiController {
	private PagamentiTable pagamentiTable;
	
	
	public PagamentiControllerImpl() {
		this.pagamentiTable = new PagamentiTableImpl();
		
	}
	@Override
	public List<Pagamento> getAllPagamenti() {
		return this.pagamentiTable.getAllPagamenti();
	}

	@Override
	public List<Pagamento> getPagamentiOggi() {
		return this.pagamentiTable.getPagamentiOggi();
	}

	@Override
	public List<Pagamento> getPagamentiAnnuali() {
		return this.pagamentiTable.getPagamentiAnnuali();
	}

	@Override
	public List<Pagamento> getPagamentiMensili() {
		return this.pagamentiTable.getPagamentiMensili();
	}

	@Override
	public List<Pagamento> getPagamentiInPeriodo(String dataInizio, String dataFine) {
		return this.pagamentiTable.getPagamentiInPeriodo(dataInizio, dataFine);
	}

}

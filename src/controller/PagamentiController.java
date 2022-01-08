package controller;

import java.util.List;

import model.Pagamento;

public interface PagamentiController {
	
	List<Pagamento> getAllPagamenti();
	
	List<Pagamento> getPagamentiOggi();
	
	List<Pagamento> getPagamentiAnnuali();
	
	List<Pagamento> getPagamentiMensili();
	
	List<Pagamento> getPagamentiInPeriodo(String dataInizio, String dataFine);

}

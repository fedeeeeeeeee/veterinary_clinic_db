package database;

import java.util.List;

import model.Pagamento;

public interface PagamentiTable {

	List<Pagamento> getAllPagamenti();

	List<Pagamento> getPagamentiOggi();

	List<Pagamento> getPagamentiAnnuali();

	List<Pagamento> getPagamentiMensili();

	List<Pagamento> getPagamentiInPeriodo(String dataInizio, String dataFine);

	List<Pagamento> getRicoveroDiPrestazione(Pagamento pagamento);

}

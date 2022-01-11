package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import model.Animale;
import model.AnimaleImpl;
import model.Pagamento;
import model.PagamentoImpl;
;

public class PagamentiTableImpl implements PagamentiTable {
	private DatabaseConnection dataSource;
	
	private String getMonthStartDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		java.util.Date monthFirstDay = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(monthFirstDay);
	}

	private String getMonthEndDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		java.util.Date monthLastDay = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(monthLastDay);
	}
	
	private String getYearEndDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 0);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
		java.util.Date monthLastDay = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(monthLastDay);
	}

	private String getYearStartDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 0);
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
		java.util.Date monthFirstDay = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(monthFirstDay);
	}

	private float getTotalePrestazioniDuranteRicovero(Pagamento pag) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> s = Optional.empty();

		 final String query = "select ora, sum(prezzo) tot, data " 
				 + "from prestazione, prestazione_su_animale " 
				 + "where prestazione_su_animale.codTipoPrestazione = prestazione.codPrestazione " 
				 + "and prestazione_su_animale.codAnimale = ? " 
				 + "and prestazione_su_animale.data in (select data " 
				 + "from ricovero,  prestazione_su_animale " 
				 + "where prestazione_su_animale.data between ? and date_add(?, INTERVAL ricovero.durataInGiorni DAY)) " 
				 + "group by ora, data "; 	 
		float costoTotale = 0;

		try {
			s = Optional.of(connection.prepareStatement(query));
			s.get().setInt(1, pag.getAnimale().getCodAnimale());
			s.get().setString(2, pag.getDataPrestazione());
			s.get().setString(3, pag.getDataPrestazione());
			final ResultSet result = s.get().executeQuery();
			
			while (result.next()) {
				if((result.getString("ora").compareTo(pag.getOraPrestazione()) > 0 || result.getString("ora").equals(pag.getOraPrestazione()))
						&& result.getString("data").equals(pag.getDataPrestazione())
						|| !result.getString("data").equals(pag.getDataPrestazione())) {
					costoTotale = costoTotale+result.getFloat("tot");
				}
			} 

			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (s.isPresent()) {
					s.get().close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
			}
		}
		return costoTotale;
	}
	

	private float getPrezzoRicovero(Pagamento pag) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> s = Optional.empty();

		final String query = "SELECT prezzoGiornaliero, durataInGiorni " 
					+ "from ricovero " 
				    + "where ricovero.dataInizio = ? "
				    + "and ricovero.oraInizio = ? " 
				    + "and ricovero.codAnimale = ? ";
		float costoTotale = 0;

		try {
			s = Optional.of(connection.prepareStatement(query));
			s.get().setString(1, pag.getDataPrestazione());
			s.get().setString(2, pag.getOraPrestazione());
			s.get().setInt(3, pag.getAnimale().getCodAnimale());

			final ResultSet result = s.get().executeQuery();
			
			if (result.next()) {
				costoTotale = result.getFloat("prezzoGiornaliero")*result.getFloat("durataInGiorni");
			} 
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (s.isPresent()) {
					s.get().close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
			}
		}
		return costoTotale;
	}
	
	private boolean setFlagRicovero(Pagamento pagamento) {
		final Connection connection = this.dataSource.getMySQLConnection();
		boolean b = false;

		Optional<PreparedStatement> s = Optional.empty();

		// tra le prestazioni di gestione pagamenti, quali comprendono anche l'inizio
		// del ricovero?
		final String query2 = "SELECT * " 
				+ "from ricovero " 
				+ "where ricovero.dataInizio = ? "
				+ "and ricovero.oraInizio = ? "
				+ "and ricovero.codAnimale = ? ";
		try {
			s = Optional.of(connection.prepareStatement(query2));
			s.get().setString(1, pagamento.getDataPrestazione());
			s.get().setString(2, pagamento.getOraPrestazione());
			s.get().setInt(3, pagamento.getAnimale().getCodAnimale());

			final ResultSet result = s.get().executeQuery();
			float totale = 0;
			
			if (result.next()) {
				b = true;
				totale = getPrezzoRicovero(pagamento) + getTotalePrestazioniDuranteRicovero(pagamento);
				pagamento.setCosto(totale);
			} 
			
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (s.isPresent()) {
					s.get().close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
			}
		}
		return b;
	}
	
	public PagamentiTableImpl() {
		this.dataSource = new DatabaseConnection();
	}

	@Override
	public List<Pagamento> getAllPagamenti() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		 final String query = "select animale.nome, animale.specie, animale.cf, prestazione_su_animale.data, prestazione.nome, prestazione.prezzo, prestazione_su_animale.ora, animale.codAnimale, prestazione_su_animale.codTipoPrestazione " 
		 + "from animale, prestazione, prestazione_su_animale " 
		 + "where prestazione_su_animale.codAnimale = animale.codAnimale " 
		 + "and prestazione_su_animale.codTipoPrestazione = prestazione.codPrestazione " 
		 + "and (prestazione_su_animale.data, prestazione_su_animale.codAnimale) not in (select data, prestazione_su_animale.codAnimale " 
		 + "from ricovero,  prestazione_su_animale " 
		 + "where ricovero.codAnimale = prestazione_su_animale.codAnimale "
		 + "and prestazione_su_animale.data > ricovero.dataInizio " 
		 + "and prestazione_su_animale.data <= date_add(ricovero.dataInizio, INTERVAL ricovero.durataInGiorni DAY)) "
		 + " order by  prestazione_su_animale.data" ;
		 
		 List<Pagamento> pagamenti = new ArrayList<>();
		try {
			statement = Optional.of(connection.prepareStatement(query));
			final ResultSet result = statement.get().executeQuery();
			
			while (result.next()) {
				Pagamento p = new PagamentoImpl();
			
				Animale animale = new AnimaleImpl();
				animale.setNome(result.getString("animale.nome"));
				animale.setCf(result.getString("animale.cf"));
				animale.setSpecie(result.getString("animale.specie"));
				animale.setCodAnimale( result.getInt("animale.codAnimale"));
				p.setAnimale(animale);
				p.setDataPrestazione(result.getString("data"));
				p.setNomePrestazione(result.getString("prestazione.nome"));
				p.setCosto(result.getFloat("prestazione.prezzo"));
				p.setOraPrestazione(result.getString("prestazione_su_animale.ora"));	
				p.setCodPrestazione(result.getInt("prestazione_su_animale.codTipoPrestazione"));
				pagamenti.add(p);
			}
			result.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {	
			try {
				if (statement.isPresent()) {
					statement.get().close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
			}
		}
		pagamenti.forEach(pag-> {
			pag.setRicovero(setFlagRicovero(pag));
		});
		return pagamenti;
	}
	
	@Override
	public List<Pagamento> getPagamentiOggi() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		
		 /* l'ultima prestazione durante il ricovero avviene durante l'ultimo giorno. 
		    quella prestazione va aggiunta alla tabella con nome = Rilascio Ricovero */
		 final String query = "select animale.nome, animale.specie, animale.cf, prestazione_su_animale.data, prestazione.nome, prestazione.prezzo, animale.codAnimale, prestazione_su_animale.ora, prestazione_su_animale.codTipoPrestazione " 
		 + "from animale, prestazione, prestazione_su_animale " 
		 + "where prestazione_su_animale.codAnimale = animale.codAnimale " 
		 + "and prestazione_su_animale.codTipoPrestazione = prestazione.codPrestazione " 
		 + "and prestazione_su_animale.data not in (select data " 
		 + "from ricovero,  prestazione_su_animale " 
		 + "where prestazione_su_animale.data > ricovero.dataInizio " 
		 + "and prestazione_su_animale.data < date_add(ricovero.dataInizio, INTERVAL ricovero.durataInGiorni DAY)) "
		 + "and prestazione_su_animale.data = ?"; 
		 
		 List<Pagamento> pagamenti = new ArrayList<>();
		try {
			statement = Optional.of(connection.prepareStatement(query));
			
			DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
			String data = formatter.format(LocalDateTime.now());
            statement.get().setString(1, data);
            
			final ResultSet result = statement.get().executeQuery();
			
			while (result.next()) {
				Pagamento p = new PagamentoImpl();
			
				Animale animale = new AnimaleImpl();
				animale.setNome(result.getString("animale.nome"));
				animale.setCf(result.getString("animale.cf"));
				animale.setSpecie(result.getString("animale.specie"));
				animale.setCodAnimale(result.getInt("codAnimale"));
				p.setAnimale(animale);
				p.setDataPrestazione(result.getString("data"));
				p.setNomePrestazione(result.getString("prestazione.nome"));
				p.setOraPrestazione(result.getString("ora"));
				p.setCosto(result.getFloat("prestazione.prezzo"));
				p.setCodPrestazione(result.getInt("prestazione_su_animale.codTipoPrestazione"));
				pagamenti.add(p);
			}

			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement.isPresent()) {
					statement.get().close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
			}
		}
		
		pagamenti.forEach(pag-> {
			pag.setRicovero(setFlagRicovero(pag));
		});
		return pagamenti;
	}

	@Override
	public List<Pagamento> getPagamentiAnnuali() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		 final String query = "select animale.nome, animale.specie, animale.cf, prestazione_su_animale.data, prestazione.nome, prestazione.prezzo, prestazione_su_animale.ora, animale.codAnimale, prestazione_su_animale.codTipoPrestazione " 
		 + "from animale, prestazione, prestazione_su_animale " 
		 + "where prestazione_su_animale.codAnimale = animale.codAnimale " 
		 + "and prestazione_su_animale.codTipoPrestazione = prestazione.codPrestazione " 
		 + "and (prestazione_su_animale.data, prestazione_su_animale.codAnimale) not in ( select data, prestazione_su_animale.codAnimale " 
		 + "from ricovero,  prestazione_su_animale " 
		 + "where ricovero.codAnimale = prestazione_su_animale.codAnimale "
		 + "and prestazione_su_animale.data > ricovero.dataInizio " 
		 + "and prestazione_su_animale.data < date_add(ricovero.dataInizio, INTERVAL ricovero.durataInGiorni DAY)) "
		 + "and prestazione_su_animale.data >= ? and prestazione_su_animale.data <= ? "
		 + " order by  prestazione_su_animale.data" ;
		 
		 List<Pagamento> pagamenti = new ArrayList<>();
		try {
			statement = Optional.of(connection.prepareStatement(query));
			
			
			statement.get().setString(1, getYearStartDate());
			statement.get().setString(2, getYearEndDate());

			final ResultSet result = statement.get().executeQuery();
			
			while (result.next()) {
				Pagamento p = new PagamentoImpl();
			
				Animale animale = new AnimaleImpl();
				animale.setNome(result.getString("animale.nome"));
				animale.setCf(result.getString("animale.cf"));
				animale.setSpecie(result.getString("animale.specie"));
				animale.setCodAnimale(result.getInt("codAnimale"));
				p.setAnimale(animale);
				p.setDataPrestazione(result.getString("data"));
				p.setNomePrestazione(result.getString("prestazione.nome"));
				p.setCosto(result.getFloat("prestazione.prezzo"));
				p.setOraPrestazione(result.getString("ora"));
				p.setCodPrestazione(result.getInt("prestazione_su_animale.codTipoPrestazione"));
				pagamenti.add(p);
			}

			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement.isPresent()) {
					statement.get().close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
			}
		}
		pagamenti.forEach(pag-> {
			pag.setRicovero(setFlagRicovero(pag));
		});

		return pagamenti;
	}
	


	@Override
	public List<Pagamento> getPagamentiMensili() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		 final String query = "select animale.nome, animale.specie, animale.cf, prestazione_su_animale.data, prestazione.nome, prestazione.prezzo, prestazione_su_animale.ora, animale.codAnimale,  prestazione_su_animale.codTipoPrestazione " 
		 + "from animale, prestazione, prestazione_su_animale " 
		 + "where prestazione_su_animale.codAnimale = animale.codAnimale " 
		 + "and prestazione_su_animale.codTipoPrestazione = prestazione.codPrestazione " 
		 + "and (prestazione_su_animale.data, prestazione_su_animale.codAnimale) not in ( select data, prestazione_su_animale.codAnimale " 
		 + "from ricovero,  prestazione_su_animale " 
		 + "where ricovero.codAnimale = prestazione_su_animale.codAnimale "
		 + "and prestazione_su_animale.data > ricovero.dataInizio " 
		 + "and prestazione_su_animale.data < date_add(ricovero.dataInizio, INTERVAL ricovero.durataInGiorni DAY)) "
		 + "and prestazione_su_animale.data >= ? and prestazione_su_animale.data <= ? "
		 + " order by  prestazione_su_animale.data" ;
		 
		 List<Pagamento> pagamenti = new ArrayList<>();
		try {
			statement = Optional.of(connection.prepareStatement(query));
			
			
			statement.get().setString(1, getMonthStartDate());
			statement.get().setString(2, getMonthEndDate());

			final ResultSet result = statement.get().executeQuery();
			
			while (result.next()) {
				Pagamento p = new PagamentoImpl();
			
				Animale animale = new AnimaleImpl();
				animale.setNome(result.getString("animale.nome"));
				animale.setCf(result.getString("animale.cf"));
				animale.setSpecie(result.getString("animale.specie"));
				animale.setCodAnimale(result.getInt("codAnimale"));
				p.setAnimale(animale);
				p.setDataPrestazione(result.getString("data"));
				p.setNomePrestazione(result.getString("prestazione.nome"));
				p.setCosto(result.getFloat("prestazione.prezzo"));
				p.setOraPrestazione(result.getString("ora"));
				p.setCodPrestazione(result.getInt("prestazione_su_animale.codTipoPrestazione"));
				pagamenti.add(p);
			}

			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement.isPresent()) {
					statement.get().close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
			}
		}
		pagamenti.forEach(pag-> {
			pag.setRicovero(setFlagRicovero(pag));
		});
		return pagamenti;
	}

	@Override
	public List<Pagamento> getPagamentiInPeriodo(String dataInizio, String dataFine) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pagamento> getPrestazioniDuranteRicovero(Pagamento pagamento) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		 final String query = "select animale.nome, animale.specie, animale.cf, animale.codAnimale, prestazione_su_animale.data, prestazione.nome, prestazione.prezzo, prestazione_su_animale.ora, animale.codAnimale " 
		 + "from prestazione_su_animale, animale, prestazione " 
		 + "where prestazione_su_animale.codAnimale = animale.codAnimale " 
		 + "and prestazione_su_animale.codTipoPrestazione = prestazione.codPrestazione " 
		 + " and prestazione_su_animale.codAnimale = ? "
		+ " and prestazione_su_animale.data in  "
			 + " (select prestazione_su_animale.data "
			 + " from prestazione_su_animale, ricovero "
			 + " where prestazione_su_animale.data >= ? "
			 + " and prestazione_su_animale.data <= date_add( ? , INTERVAL ricovero.durataInGiorni DAY))"
		 + " order by  prestazione_su_animale.data" ;
		 
		 List<Pagamento> pagamenti = new ArrayList<>();
		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setInt(1, pagamento.getAnimale().getCodAnimale());
			statement.get().setString(2, pagamento.getDataPrestazione());
			statement.get().setString(3, pagamento.getDataPrestazione());
			
			final ResultSet result = statement.get().executeQuery();
			
			while (result.next()) {
				if ((result.getString("ora").compareTo(pagamento.getOraPrestazione()) > 0 ||  result.getString("ora").equals(pagamento.getOraPrestazione()))
					&& result.getString("data").equals(pagamento.getDataPrestazione())
					|| !result.getString("data").equals(pagamento.getDataPrestazione())) {
					Pagamento p = new PagamentoImpl();
					
					Animale animale = new AnimaleImpl();
					animale.setNome(result.getString("animale.nome"));
					animale.setCf(result.getString("animale.cf"));
					animale.setSpecie(result.getString("animale.specie"));
					animale.setCodAnimale( result.getInt("animale.codAnimale"));
					p.setAnimale(animale);
					p.setDataPrestazione(result.getString("data"));
					p.setNomePrestazione(result.getString("prestazione.nome"));
					p.setCosto(result.getFloat("prestazione.prezzo"));
					p.setOraPrestazione(result.getString("prestazione_su_animale.ora"));	
					pagamenti.add(p);
				}
			}
			result.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {	
			try {
				if (statement.isPresent()) {
					statement.get().close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
			}
		}
		return pagamenti;
	}
	
}

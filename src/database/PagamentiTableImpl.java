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
import java.util.Date;
import java.util.List;
import java.util.Optional;

import model.Animale;
import model.AnimaleImpl;
import model.Appuntamento;
import model.AppuntamentoImpl;
import model.Pagamento;
import model.PagamentoImpl;
import model.User;
import model.UserImpl;

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

	public PagamentiTableImpl() {
		this.dataSource = new DatabaseConnection();
	}

	@Override
	public List<Pagamento> getAllPagamenti() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		 final String query = "select animale.nome, animale.specie, animale.cf, prestazione_su_animale.data, prestazione.nome, prestazione.prezzo " 
		 + "from animale, prestazione, prestazione_su_animale " 
		 + "where prestazione_su_animale.codAnimale = animale.codAnimale " 
		 + "and prestazione_su_animale.codTipoPrestazione = prestazione.codPrestazione " 
		 + "and prestazione_su_animale.data not in (select dataInizio " 
		 + "from ricovero,  prestazione_su_animale " 
		 + "where prestazione_su_animale.data >= ricovero.dataInizio " 
		 + "and prestazione_su_animale.data <= date_add(ricovero.dataInizio, INTERVAL ricovero.durataInGiorni DAY)) "; 
		 
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
				p.setAnimale(animale);
				p.setDataPrestazione(result.getString("data"));
				p.setNomePrestazione(result.getString("prestazione.nome"));
				p.setCosto(result.getFloat("prestazione.prezzo"));
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
		return pagamenti;
	}

	@Override
	public List<Pagamento> getPagamentiOggi() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		
		 /* l'ultima prestazione durante il ricovero avviene durante l'ultimo giorno. 
		    quella prestazione va aggiunta alla tabella con nome = Rilascio Ricovero */
		 final String query = "select animale.nome, animale.specie, animale.cf, prestazione_su_animale.data, prestazione.nome, prestazione.prezzo " 
		 + "from animale, prestazione, prestazione_su_animale " 
		 + "where prestazione_su_animale.codAnimale = animale.codAnimale " 
		 + "and prestazione_su_animale.codTipoPrestazione = prestazione.codPrestazione " 
		 + "and prestazione_su_animale.data not in (select dataInizio " 
		 + "from ricovero,  prestazione_su_animale " 
		 + "where prestazione_su_animale.data >= ricovero.dataInizio " 
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
				p.setAnimale(animale);
				p.setDataPrestazione(result.getString("data"));
				p.setNomePrestazione(result.getString("prestazione.nome"));
				p.setCosto(result.getFloat("prestazione.prezzo"));
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
		return pagamenti;
	}

	@Override
	public List<Pagamento> getPagamentiAnnuali() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		 final String query = "select animale.nome, animale.specie, animale.cf, prestazione_su_animale.data, prestazione.nome, prestazione.prezzo " 
		 + "from animale, prestazione, prestazione_su_animale " 
		 + "where prestazione_su_animale.codAnimale = animale.codAnimale " 
		 + "and prestazione_su_animale.codTipoPrestazione = prestazione.codPrestazione " 
		 + "and prestazione_su_animale.data not in (select dataInizio " 
		 + "from ricovero,  prestazione_su_animale " 
		 + "where prestazione_su_animale.data >= ricovero.dataInizio " 
		 + "and prestazione_su_animale.data < date_add(ricovero.dataInizio, INTERVAL ricovero.durataInGiorni DAY)) "
		 + "and prestazione_su_animale.data >= ? and prestazione_su_animale.data <= ? "; 
		 
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
				p.setAnimale(animale);
				p.setDataPrestazione(result.getString("data"));
				p.setNomePrestazione(result.getString("prestazione.nome"));
				p.setCosto(result.getFloat("prestazione.prezzo"));
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
		return pagamenti;
	}
	


	@Override
	public List<Pagamento> getPagamentiMensili() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		 final String query = "select animale.nome, animale.specie, animale.cf, prestazione_su_animale.data, prestazione.nome, prestazione.prezzo " 
		 + "from animale, prestazione, prestazione_su_animale " 
		 + "where prestazione_su_animale.codAnimale = animale.codAnimale " 
		 + "and prestazione_su_animale.codTipoPrestazione = prestazione.codPrestazione " 
		 + "and prestazione_su_animale.data not in (select dataInizio " 
		 + "from ricovero,  prestazione_su_animale " 
		 + "where prestazione_su_animale.data >= ricovero.dataInizio " 
		 + "and prestazione_su_animale.data < date_add(ricovero.dataInizio, INTERVAL ricovero.durataInGiorni DAY)) "
		 + "and prestazione_su_animale.data >= ? and prestazione_su_animale.data <= ? "; 
		 
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
				p.setAnimale(animale);
				p.setDataPrestazione(result.getString("data"));
				p.setNomePrestazione(result.getString("prestazione.nome"));
				p.setCosto(result.getFloat("prestazione.prezzo"));
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
		return pagamenti;
	}

	@Override
	public List<Pagamento> getPagamentiInPeriodo(String dataInizio, String dataFine) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

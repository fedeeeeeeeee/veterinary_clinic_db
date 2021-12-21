package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Strumento;
import model.StrumentoImpl;

public class StrumentiTableImpl implements StrumentiTable{

	private DatabaseConnection dataSource;
	
	public StrumentiTableImpl() {
		this.dataSource = new DatabaseConnection();
	}
	
	@Override
	public List<Strumento> getAllStrumenti() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		 final String query = "select * from strumento";
		 		 
		 List<Strumento> strumenti = new ArrayList<>();
			try {
				statement = Optional.of(connection.prepareStatement(query));
				final ResultSet result = statement.get().executeQuery();
				while (result.next()) {
					Strumento stru = new StrumentoImpl();
					stru.setCodStrumento(result.getInt(1));
					stru.setNome(result.getString(2));
					stru.setDescrizione(result.getString(3));
					stru.setDataScadenza(result.getString(4));
					stru.setQuantità(result.getInt(5));

					 
					strumenti.add(stru);
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
					System.out.println("errore sql");
				}
			}
			return strumenti;
		}

	
	
	
	
	@Override
	 public List<Strumento> getStrumentiScaduti() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		 final String query = "select * from strumento where dataScadenza <= ? ";
		 
		 List<Strumento> strumenti = new ArrayList<>();
			try {
				DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE; //("yyyy-MM-dd")
				String dataformat = formatter.format(LocalDateTime.now());

				
				statement = Optional.of(connection.prepareStatement(query));
	           statement.get().setString(1,dataformat);
				final ResultSet result = statement.get().executeQuery();
				while (result.next()) {
					Strumento stru = new StrumentoImpl();
					stru.setCodStrumento(result.getInt(1));
					stru.setNome(result.getString(2));
					stru.setDescrizione(result.getString(3));
					stru.setDataScadenza(result.getString(4));
					stru.setQuantità(result.getInt(5));
					
					strumenti.add(stru);
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
			return strumenti;
	}

	@Override
	public List<Strumento> getStrumentiInScadenza() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		 final String query = "select * from strumento where dataScadenza <= ? AND dataScadenza > ? ";
		 
		 List<Strumento> strumenti = new ArrayList<>();
			try {
				LocalDateTime nowDate = LocalDateTime.now(); 
				LocalDateTime newDate =  nowDate.plusDays(15); // considerato che tra ordine e consegnano servono questi giorni? 
				DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE; //("yyyy-MM-dd")
				String nowDateFormat = formatter.format(nowDate);
				String newDateFormat = formatter.format(newDate);
				
				statement = Optional.of(connection.prepareStatement(query));
	           statement.get().setString(1,newDateFormat);
	           statement.get().setString(2,nowDateFormat);
	           
				final ResultSet result = statement.get().executeQuery();
				while (result.next()) {
					Strumento stru = new StrumentoImpl();
					stru.setCodStrumento(result.getInt(1));
					stru.setNome(result.getString(2));
					stru.setDescrizione(result.getString(3));
					stru.setDataScadenza(result.getString(4));
					stru.setQuantità(result.getInt(5));
					
					strumenti.add(stru);
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
			return strumenti;
	}

	@Override
	public List<Strumento> getStrumentiInEsaurimento() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		 final String query = "select * from strumento where quantitaInMagazzino <= 10 ";
		 
		 List<Strumento> strumenti = new ArrayList<>();
			try {

				statement = Optional.of(connection.prepareStatement(query));
	           //sm w2tatement.get().setString(1,10);
				final ResultSet result = statement.get().executeQuery();
				while (result.next()) {
					Strumento stru = new StrumentoImpl();
					stru.setCodStrumento(result.getInt(1));
					stru.setNome(result.getString(2));
					stru.setDescrizione(result.getString(3));
					stru.setDataScadenza(result.getString(4));
					stru.setQuantità(result.getInt(5));
					System.out.println(result.getInt(1));
					strumenti.add(stru);
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
			return strumenti;
	}
}

package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Prestazione;
import model.PrestazioneImpl;
import model.Strumento;
import model.StrumentoImpl;
import model.Utilizzo;

public class StrumentiTableImpl implements StrumentiTable{

	private DatabaseConnection dataSource;
	private TipoPrestazioneTable tipoPrestTable;
	
	public StrumentiTableImpl() {
		this.dataSource = new DatabaseConnection();
		this.tipoPrestTable = new TipoPrestazioneTableImpl();
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
					stru.setQuantita(result.getInt(5));

					 
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
					stru.setQuantita(result.getInt(5));
					
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
					stru.setQuantita(result.getInt(5));
					
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
					stru.setQuantita(result.getInt(5));

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
	public void updateQuantita(Strumento strumento, int q) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		 final String query = "update strumento set quantitaInMagazzino = ? "
		 		+ "where  (codStrumento = ?) ";
		 
			try {
				statement = Optional.of(connection.prepareStatement(query));
				statement.get().setInt(1, (strumento.getQuantita() + q));
		           statement.get().setInt(2, strumento.getCodStrumento());
		           statement.get().executeUpdate();   
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
	}

	@Override
	public void addNewStrumento(Strumento stru) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		final String query = " insert into strumento (nome, descrizione, dataScadenza, quantitaInMagazzino, prezzo) values (?,?,?,?,?) ";

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setString(1, stru.getNome());
			statement.get().setString(2, stru.getDescrizione());
			statement.get().setString(3, stru.getDataScadenza());
			statement.get().setInt(4, stru.getQuantita());
			statement.get().setFloat(5, stru.getPrezzo());
			
			statement.get().executeUpdate();
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
				System.out.println("Errore inserimento nuovo strumento");
			}
		}
	}

	@Override
	public void addUtilizzo(Utilizzo utilizzo) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		final String query = " insert into utilizzo (codStrumento, codPrestazione, quantita) values (?,?,?) ";

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setInt(1, utilizzo.getStrumento().getCodStrumento());
			statement.get().setInt(2, utilizzo.getPrestazione().getCodPrestazione());
			statement.get().setInt(3, utilizzo.getQuantita());

			
			statement.get().executeUpdate();
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
				System.out.println("Errore inserimento nuovo utilizzo");
			}
		}
	}

	@Override
	public boolean isStrumentiAvailable(int codPrestazione) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		 final String query = "SELECT count(utilizzo.codStrumento) as numeroStrumenti " 
				 	+ "FROM utilizzo, strumento " 
			 		+ "where utilizzo.codPrestazione = ? " 
				 	+ "and utilizzo.codStrumento = strumento.codStrumento " 
			 		+ "and strumento.quantitaInMagazzino >= utilizzo.quantita ";
		 
		 boolean b  = false;
			try {

				statement = Optional.of(connection.prepareStatement(query));
				statement.get().setInt(1, codPrestazione);
				
				final ResultSet result = statement.get().executeQuery();
				if (result.next()) {
					int strumentiDisponibili = result.getInt("numeroStrumenti");
					b = strumentiDisponibili == this.getStrumentiInPrestazioneCount(codPrestazione);
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
			return b;
	}

	private int getStrumentiInPrestazioneCount(int codPrestazione) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		 final String query = "SELECT count(utilizzo.codStrumento) as numeroStrumentiInPrestazione " 
				 	+ "FROM utilizzo, strumento " 
			 		+ "where utilizzo.codPrestazione = ? " 
				 	+ "and utilizzo.codStrumento = strumento.codStrumento ";
		 
		 int count = 0; 
			try {

				statement = Optional.of(connection.prepareStatement(query));
				statement.get().setInt(1, codPrestazione);
				
				final ResultSet result = statement.get().executeQuery();
				if (result.next()) {
					count = result.getInt("numeroStrumentiInPrestazione");
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
			return count;
	}

	@Override
	public void updateStrumentiDiPrestazione(int codPrestazione) {
		Prestazione p = new PrestazioneImpl();
		p.setCodPrestazione(codPrestazione);
		List<Utilizzo> utilizzi = this.tipoPrestTable.getAllUtilizziDiPrestazione(p);
		
		utilizzi.forEach(u-> {
			this.updateQuantita(u.getStrumento(), -u.getQuantita());
		});
	}

}

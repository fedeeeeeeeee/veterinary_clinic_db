package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import model.Animale;
import model.AnimaleImpl;
import model.Appuntamento;
import model.AppuntamentoImpl;
import model.Person;
import model.Proprietario;
import model.User;
import model.UserImpl;

public class PrestazioneTableImpl implements PrestazioneTable {
	
	private DatabaseConnection dataSource;
	private boolean isOk;
	//private String prestazioneTableName;
	
	public PrestazioneTableImpl() {
		this.dataSource = new DatabaseConnection();
		//this.prestazioneTableName = "prestazione_su_animale";
	}
	
	@Override
	public List<Appuntamento> getAllPrestazioni() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		
		// rivedi query, perchè c'è il ?
		 final String query = "select animale.nome, prestazione.nome, prestazione_su_animale.data, ora, personale.nome, personale.cognome, prestazione_su_animale.codAnimale, prestazione_su_animale.codTipoPrestazione, personale.cf , animale.cf   "
	        		+ "from prestazione_su_animale, effettua , prestazione , animale , personale "
	        		+ "where prestazione_su_animale.data=effettua.data "
	        		+ "and prestazione_su_animale.codAnimale=effettua.codAnimale "
	        		+ "and prestazione_su_animale.codTipoPrestazione = effettua.codTipoPrestazione "
	        		+ "and animale.codAnimale=effettua.codAnimale  "
	        		+ "and prestazione.codPrestazione = effettua.codTipoPrestazione "
	        		+ "and personale.CF=effettua.CF "
	        		+ "and personale.ruolo=?"; 
		 
		 List<Appuntamento> prestazioni = new ArrayList<>();
		try {
			statement = Optional.of(connection.prepareStatement(query));
            statement.get().setString(1, "Veterinario");
			final ResultSet result = statement.get().executeQuery();
			
			while (result.next()) {
				Appuntamento app = new AppuntamentoImpl();
				Animale animale = new AnimaleImpl();
				/*
				Person proprietario = new Proprietario();
				proprietario.setCF(result.getString(10));
				proprietario.setNome(result.getString(11));
				proprietario.setCognome(result.getString(12));
				proprietario.setTelefono(result.getLong(13));
				animale.setProprietario(proprietario);
				*/
				animale.setNome(result.getString(1));
				animale.setCodAnimale(result.getInt(7));
				animale.setCf(result.getString(10));
				app.setAnimale(animale);
				
				app.setData(result.getString(3));
				app.setOra(result.getString(4));
				app.setPrestazione(result.getString(2));
				
				User veterinario = new UserImpl();
				veterinario.setCF(result.getString(9));
				veterinario.setNome(result.getString(5));
				veterinario.setCognome(result.getString(6));
				app.setVeterinario(veterinario);
				
				app.setCodTipoPrestazione(result.getInt(8));
				prestazioni.add(app);
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
		return prestazioni;
	}

	@Override
	public List<Appuntamento> getPrestazioniInData(String data) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		 final String query = "select animale.nome, prestazione.nome, prestazione_su_animale.data, ora, personale.nome, personale.cognome, prestazione.durataMedia,  personale.cf, animale.codAnimale, animale.cf  "
	        		+ "from prestazione_su_animale, effettua , prestazione , animale , personale "
	        		+ "where prestazione_su_animale.data= ? "
	        		+ "and prestazione_su_animale.codAnimale=effettua.codAnimale "
	        		+ "and prestazione_su_animale.codTipoPrestazione = effettua.codTipoPrestazione "
	        		+ "and animale.codAnimale=effettua.codAnimale  "
	        		+ "and prestazione.codPrestazione = effettua.codTipoPrestazione "
	        		+ "and personale.CF=effettua.CF "
	        		+ "and personale.ruolo=?"; 
		 List<Appuntamento> prestazioni = new ArrayList<>();
			try {
				statement = Optional.of(connection.prepareStatement(query));
				statement.get().setString(1, data);
				statement.get().setString(2, "Veterinario");
				final ResultSet result = statement.get().executeQuery();
				
				while (result.next()) {
					Appuntamento app = new AppuntamentoImpl();
					Animale animale = new AnimaleImpl();
					animale.setNome(result.getString(1));
					animale.setCodAnimale(result.getInt(9));
					animale.setCf(result.getString(10));
					app.setAnimale(animale);
					
					app.setPrestazione(result.getString(2));
					app.setData(result.getString(3));
					app.setOra(result.getString(4));
					
					User veterinario = new UserImpl();
					veterinario.setCF(result.getString(8));
					veterinario.setNome(result.getString(5));
					veterinario.setCognome(result.getString(6));
					app.setVeterinario(veterinario);

					app.setDurata(result.getString(7));
					
					prestazioni.add(app);
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
			return prestazioni;
		}
	
	@Override
	public List<Appuntamento> getFuturePrestazioni() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		 final String query = "select animale.nome, prestazione.nome, prestazione_su_animale.data,  ora, personale.nome, personale.cognome, prestazione.durataMedia, personale.cf, animale.codAnimale, animale.cf "
	        		+ "from prestazione_su_animale, effettua , prestazione , animale , personale "
	        		+ "where prestazione_su_animale.data>= ? "
	        		+ "and prestazione_su_animale.codAnimale=effettua.codAnimale "
	        		+ "and prestazione_su_animale.codTipoPrestazione = effettua.codTipoPrestazione "
	        		+ "and animale.codAnimale=effettua.codAnimale  "
	        		+ "and prestazione.codPrestazione = effettua.codTipoPrestazione "
	        		+ "and personale.CF=effettua.CF "
	        		+ "and personale.ruolo=?"; 
		 List<Appuntamento> prestazioni = new ArrayList<>();
			DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
			String data = formatter.format( LocalDateTime.now());
			try {
				statement = Optional.of(connection.prepareStatement(query));
				
				statement.get().setString(1, data);
				statement.get().setString(2, "Veterinario");
				final ResultSet result = statement.get().executeQuery();
				
				while (result.next()) {
					Appuntamento app = new AppuntamentoImpl();
					Animale animale = new AnimaleImpl();
					animale.setNome(result.getString(1));
					animale.setCodAnimale(result.getInt(9));
					animale.setCf(result.getString(10));
					app.setAnimale(animale);
					
					app.setPrestazione(result.getString(2));
					app.setData(result.getString(3));
					app.setOra(result.getString(4));
					
					User veterinario = new UserImpl();
					veterinario.setCF(result.getString(8));
					veterinario.setNome(result.getString(5));
					veterinario.setCognome(result.getString(6));
					app.setVeterinario(veterinario);
				
					app.setDurata(result.getString(7));
					
					prestazioni.add(app);
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
			return prestazioni;
		}
	
	@Override
	public List<Appuntamento> getPrestazioneMese(String data) {

		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
	
		 final String query = "select animale.nome, prestazione.nome, prestazione_su_animale.data, ora, personale.nome, personale.cognome,  personale.cf, animale.codAnimale, animale.cf  "
	        		+ "from prestazione_su_animale, effettua , prestazione , animale , personale "
	        		+ "where prestazione_su_animale.data >= ? and prestazione_su_animale.data <= ? "
	        		+ "and prestazione_su_animale.codAnimale=effettua.codAnimale "
	        		+ "and prestazione_su_animale.codTipoPrestazione = effettua.codTipoPrestazione "
	        		+ "and animale.codAnimale=effettua.codAnimale  "
	        		+ "and prestazione.codPrestazione = effettua.codTipoPrestazione "
	        		+ "and personale.CF=effettua.CF "
	        		+ "and personale.ruolo=?"; 
		 List<Appuntamento> prestazioni = new ArrayList<>();
			try {
				statement = Optional.of(connection.prepareStatement(query));
				statement.get().setString(1, getMonthStartDate());
				statement.get().setString(2, getMonthEndDate());
				statement.get().setString(3, "Veterinario");
				final ResultSet result = statement.get().executeQuery();
				
				while (result.next()) {
					Appuntamento app = new AppuntamentoImpl();
					Animale animale = new AnimaleImpl();
					animale.setNome(result.getString(1));
					animale.setCodAnimale(result.getInt(8));
					animale.setCf(result.getString(9));
					app.setAnimale(animale);
					
					app.setPrestazione(result.getString(2));
					app.setData(result.getString(3));
					app.setOra(result.getString(4));
					User veterinario = new UserImpl();
					veterinario.setCF(result.getString(7));
					veterinario.setNome(result.getString(5));
					veterinario.setCognome(result.getString(6));
					app.setVeterinario(veterinario);
					
					prestazioni.add(app);
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
			return prestazioni;
		}

		public static String getMonthStartDate() {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, 0);
			calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			java.util.Date monthFirstDay = calendar.getTime();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			return df.format(monthFirstDay);
		}

		public static String getMonthEndDate() {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, 0);
			calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			java.util.Date monthLastDay = calendar.getTime();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			return df.format(monthLastDay);
		}
	
	public static java.util.Date getWeekStartDate() {
	    Calendar calendar = Calendar.getInstance();
	    while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
	        calendar.add(Calendar.DATE, -1);
	    }
	    return calendar.getTime();
	}

	public static java.util.Date getWeekEndDate() {
	    Calendar calendar = Calendar.getInstance();
	    while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
	        calendar.add(Calendar.DATE, 1);
	    }
	    calendar.add(Calendar.DATE, -1);
	    return calendar.getTime();
	}
	
	
	
	
	@Override
	public List<Appuntamento> getPrestazioneSettiamana(String data) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateInizio = format.format( getWeekStartDate() );
		String dateFine = format.format( getWeekEndDate() );
		
		 final String query = "select animale.nome, prestazione.nome, prestazione_su_animale.data, ora, personale.nome, personale.cognome,  personale.cf,  animale.codAnimale, animale.cf  "
	        		+ "from prestazione_su_animale, effettua , prestazione , animale , personale "
	        		+ "where prestazione_su_animale.data >= ? and prestazione_su_animale.data <= ? "
	        		+ "and prestazione_su_animale.codAnimale=effettua.codAnimale "
	        		+ "and prestazione_su_animale.codTipoPrestazione = effettua.codTipoPrestazione "
	        		+ "and animale.codAnimale=effettua.codAnimale  "
	        		+ "and prestazione.codPrestazione = effettua.codTipoPrestazione "
	        		+ "and personale.CF=effettua.CF "
	        		+ "and personale.ruolo=?"; 
		 List<Appuntamento> prestazioni = new ArrayList<>();
			try {
				statement = Optional.of(connection.prepareStatement(query));
				statement.get().setString(1, dateInizio);
				statement.get().setString(2, dateFine);
				statement.get().setString(3, "Veterinario");
				final ResultSet result = statement.get().executeQuery();
				
				while (result.next()) {
					Appuntamento app = new AppuntamentoImpl();
					Animale animale = new AnimaleImpl();
					animale.setNome(result.getString(1));
					animale.setCodAnimale(result.getInt(8));
					animale.setCf(result.getString(9));
					app.setAnimale(animale);
					
					app.setPrestazione(result.getString(2));
					app.setData(result.getString(3));
					app.setOra(result.getString(4));
					
					User veterinario = new UserImpl();
					veterinario.setCF(result.getString(7));
					veterinario.setNome(result.getString(5));
					veterinario.setCognome(result.getString(6));
					app.setVeterinario(veterinario);
					
					prestazioni.add(app);
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
			return prestazioni;
		}

	@Override
	public List<Appuntamento> getPrestazioneDiVeterinario(User veterinario) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		 final String query = "select distinct animale.nome, prestazione.nome, prestazione_su_animale.data, ora, animale.codAnimale, animale.cf "
	        		+ "from prestazione_su_animale, effettua , prestazione , animale , personale "
	        		+ "where prestazione_su_animale.data=effettua.data"
	        		+ "and prestazione_su_animale.codAnimale=effettua.codAnimale "
	        		+ "and prestazione_su_animale.codTipoPrestazione = effettua.codTipoPrestazione "
	        		+ "and animale.codAnimale=effettua.codAnimale  "
	        		+ "and prestazione.codPrestazione = effettua.codTipoPrestazione "
	        		+ "and personale.CF=?";
		 List<Appuntamento> prestazioni = new ArrayList<>();
			try {
				statement = Optional.of(connection.prepareStatement(query));
				statement.get().setString(1, veterinario.getCF());
				final ResultSet result = statement.get().executeQuery();
				
				while (result.next()) {
					Appuntamento app = new AppuntamentoImpl();
					Animale animale = new AnimaleImpl();
					animale.setNome(result.getString(1));
					animale.setCodAnimale(result.getInt(5));
					animale.setCf(result.getString(6));
					app.setAnimale(animale);
					
					app.setData(result.getString(3));
					app.setOra(result.getString(4));
					app.setPrestazione(result.getString(2));
// TODO controlla  tutti i cambi di veterinario potrebbero non essere stati rimepiti nella chiamata 
					app.setVeterinario(veterinario); 
					prestazioni.add(app);
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
			return prestazioni;
	}

	@Override
	public boolean addNewAppuntamento(Appuntamento appuntamento) {
		final Connection connection = this.dataSource.getMySQLConnection();

		// controllo data+ora+vet
		
		Optional<PreparedStatement> statement = Optional.empty();
		String query = "insert into prestazione_su_animale"
				+ " (codAnimale, codTipoPrestazione, data, ora, rilevazioneAltezza, rilevazionePeso) values (?,?,?,?,?,?)";

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setInt(1, appuntamento.getAnimale().getCodAnimale()); //TODO Controlal
			statement.get().setInt(2, appuntamento.getCodPrestazione());
			statement.get().setString(3, appuntamento.getData());
			statement.get().setString(4, appuntamento.getOra());
			
			// va bene string se tipo=float???
			statement.get().setString(5, null);
			statement.get().setString(6, null);
	
			statement.get().executeUpdate();
		} catch (SQLException e) {
			System.out.println("eccezione INSERIMENTO APPUNTAMENTO " + e);
			return false;
		} finally {
			try {
				if (statement.isPresent()) {
					statement.get().close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("eccezione INSERIMENTO APPUNTAMENTO statement" + e);
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addEffettua(Appuntamento appuntamento) {
		this.isOk = true;
		
		appuntamento.getAllMediciEInfermieri().forEach(x-> {
			final Connection connection = this.dataSource.getMySQLConnection();

			// controllo data+ora+vet
			
			Optional<PreparedStatement> statement = Optional.empty();
			String query = "insert into effettua"
					+ " (codAnimale, codTipoPrestazione, data, CF) values (?,?,?,?)";

			try {
				
				statement = Optional.of(connection.prepareStatement(query));
				statement.get().setInt(1, appuntamento.getAnimale().getCodAnimale());
				statement.get().setInt(2, appuntamento.getCodPrestazione());
				statement.get().setString(3, appuntamento.getData());
				statement.get().setString(4, x.getCF());
		
				statement.get().executeUpdate();
			}  catch (SQLException e) {
				System.out.println("eccezione INSERIMENTO effettua " + e);
				isOk = false;
			} finally {
				try {
					if (statement.isPresent()) {
						statement.get().close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					System.out.println("eccezione INSERIMENTO effettua statement" + e);
					isOk = false;
				}
			}
		});
		return isOk;
	}

	@Override
	public List<String> getVeterinariNonDisponibili(String date, String time, String durata) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();


		 final String query = "select personale.nome, personale.cognome, effettua.cf "
		 		+ "from effettua, personale "
		 		+ "where (codAnimale, codTipoPrestazione, data) in "
		 		+ "(select codAnimale, codTipoPrestazione, data from prestazione_su_animale, prestazione "
	        	+ "where data = ? "
	        	+ "and prestazione_su_animale.codTipoPrestazione = prestazione.codPrestazione "
	        	+ "and ( ? between prestazione_su_animale.ora and addtime(prestazione_su_animale.ora, prestazione.durataMedia) "
	        	+ "or ora between ? and addtime(?, ?))) "
	        	+ "and effettua.cf = personale.cf " 
	        	+ "and personale.ruolo = ?";
		 
		 List<String> veterianariNonDisponibili = new ArrayList<>();
			try {
				statement = Optional.of(connection.prepareStatement(query));
				statement.get().setString(1, date);
				statement.get().setString(2, time);
				statement.get().setString(3, time);
				statement.get().setString(4, time);
				statement.get().setString(5, durata);
				statement.get().setString(6, "Veterinario");

				final ResultSet result = statement.get().executeQuery();
				
				while (result.next()) {
					String veterianario = result.getString("nome") + " " + result.getString("cognome");
					veterianariNonDisponibili.add(veterianario);
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
			return veterianariNonDisponibili;
	}


}

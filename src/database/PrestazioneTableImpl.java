package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
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
import model.Farmaco;
import model.FarmacoImpl;
import model.Patologia;
import model.PatologiaImpl;
import model.User;
import model.UserImpl;

public class PrestazioneTableImpl implements PrestazioneTable {
	
	private DatabaseConnection dataSource;
	private boolean isOk;
	
	public PrestazioneTableImpl() {
		this.dataSource = new DatabaseConnection();
	}
	
	@Override
	public List<Appuntamento> getAllPrestazioni() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		 final String query = "select animale.nome, prestazione.nome, prestazione_su_animale.data, ora, personale.nome, personale.cognome, prestazione_su_animale.codAnimale, prestazione_su_animale.codTipoPrestazione, personale.cf , animale.cf , prestazione.tipo "
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
				animale.setNome(result.getString(1));
				animale.setCodAnimale(result.getInt(7));
				animale.setCf(result.getString(10));
				
				User veterinario = new UserImpl();
				veterinario.setCF(result.getString(9));
				veterinario.setNome(result.getString(5));
				veterinario.setCognome(result.getString(6));
				
				app.setAnimale(animale);
				app.setData(result.getString(3));
				app.setOra(result.getString(4));
				app.setPrestazione(result.getString(2));
				app.setVeterinario(veterinario);
				app.setCodPrestazione(result.getInt(8)); //TODO controlla
				app.setTipoPrestazione(result.getString(11));
				
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
		 final String query = "select animale.nome, prestazione.nome, prestazione_su_animale.data, ora, personale.nome, personale.cognome, prestazione.durataMedia,  personale.cf, animale.codAnimale, animale.cf, prestazione.tipo  "
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

					User veterinario = new UserImpl();
					veterinario.setCF(result.getString(8));
					veterinario.setNome(result.getString(5));
					veterinario.setCognome(result.getString(6));			

					app.setPrestazione(result.getString(2));
					app.setData(result.getString(3));
					app.setOra(result.getString(4));
					app.setDurata(result.getString(7));
					app.setTipoPrestazione(result.getString(11));
					
					app.setVeterinario(veterinario);
					app.setAnimale(animale);
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
		 final String query = "select animale.nome, prestazione.nome, prestazione_su_animale.data,  ora, personale.nome, personale.cognome, prestazione.durataMedia, personale.cf, animale.codAnimale, animale.cf, prestazione.tipo, prestazione_su_animale.codTipoPrestazione "
	        		+ "from prestazione_su_animale, effettua , prestazione , animale , personale "
	        		+ "where prestazione_su_animale.data >= ? "
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

					User veterinario = new UserImpl();
					veterinario.setCF(result.getString(8));
					veterinario.setNome(result.getString(5));
					veterinario.setCognome(result.getString(6));
				
					app.setTipoPrestazione(result.getString(11));
					app.setCodPrestazione(result.getInt(12));
					app.setDurata(result.getString(7));	
					app.setPrestazione(result.getString(2));
					app.setData(result.getString(3));
					app.setOra(result.getString(4));
					
					app.setVeterinario(veterinario);
					app.setAnimale(animale);
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
	
		 final String query = "select animale.nome, prestazione.nome, prestazione_su_animale.data, ora, personale.nome, personale.cognome,  personale.cf, animale.codAnimale, animale.cf, prestazione.tipo  "
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
				
					User veterinario = new UserImpl();
					veterinario.setCF(result.getString(7));
					veterinario.setNome(result.getString(5));
					veterinario.setCognome(result.getString(6));
					
					app.setTipoPrestazione(result.getString(10));
					app.setPrestazione(result.getString(2));
					app.setData(result.getString(3));
					app.setOra(result.getString(4));
					
					app.setVeterinario(veterinario);
					app.setAnimale(animale);
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

	private java.util.Date getWeekStartDate() {
		Calendar calendar = Calendar.getInstance();
		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			calendar.add(Calendar.DATE, -1);
		}
		return calendar.getTime();
	}

	private java.util.Date getWeekEndDate() {
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
		
		 final String query = "select animale.nome, prestazione.nome, prestazione_su_animale.data, ora, personale.nome, personale.cognome,  personale.cf,  animale.codAnimale, animale.cf, prestazione.tipo  "
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

					User veterinario = new UserImpl();
					veterinario.setCF(result.getString(7));
					veterinario.setNome(result.getString(5));
					veterinario.setCognome(result.getString(6));

					app.setPrestazione(result.getString(2));
					app.setData(result.getString(3));
					app.setOra(result.getString(4));
					app.setTipoPrestazione(result.getString(10));
					
					app.setAnimale(animale);
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

		 final String query = "select distinct animale.nome, prestazione.nome, prestazione_su_animale.data, ora, animale.codAnimale, animale.cf, prestazione.tipo "
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
					app.setTipoPrestazione(result.getString(7));
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
		Optional<PreparedStatement> statement = Optional.empty();
		String query = "";
		if(appuntamento.getCodPrestazione()==12) {
			query = "insert into prestazione_su_animale"
					+ " (codAnimale, codTipoPrestazione, data, ora, rilevazioneAltezza, rilevazionePeso) values (?,?,?,?,?,?)";
		} else {
			 query = "insert into prestazione_su_animale"
					+ " (codAnimale, codTipoPrestazione, data, ora) values (?,?,?,?)";
		}

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setInt(1, appuntamento.getAnimale().getCodAnimale()); //TODO Controlal
			statement.get().setInt(2, appuntamento.getCodPrestazione());
			statement.get().setString(3, appuntamento.getData());
			statement.get().setString(4, appuntamento.getOra());
			
			if(appuntamento.getCodPrestazione()==12) {
				if( appuntamento.getPeso() != 0.0f && appuntamento.getAltezza() != 0.0f  ) {
					statement.get().setFloat(6,  appuntamento.getPeso());
					statement.get().setFloat(5, appuntamento.getAltezza());
				}else {
					statement.get().setNull(6, Types.NULL);
					statement.get().setFloat(5, Types.NULL);
				}
				
			}
	
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
	        	+ "and (prestazione_su_animale.codTipoPrestazione != ? and prestazione_su_animale.data != ? and prestazione_su_animale.codAnimale != ? ) "
	        	+ "and prestazione_su_animale.codTipoPrestazione = prestazione.codPrestazione "
	        	+ "and ( ? between prestazione_su_animale.ora and addtime(prestazione_su_animale.ora, prestazione.durataMedia) "
	        	+ "or ora between ? and addtime(?, ?))) "
	        	+ "and effettua.cf = personale.cf " 
	        	+ "and personale.ruolo = ? ";
		 
		 List<String> veterianariNonDisponibili = new ArrayList<>();
			try {
				statement = Optional.of(connection.prepareStatement(query));
				statement.get().setString(1, date);
				statement.get().setInt(2, 5);
				statement.get().setString(3, "2022-01-29");
				statement.get().setInt(4, 3);

				statement.get().setString(5, time);
				statement.get().setString(6, time);
				statement.get().setString(7, time);
				statement.get().setString(8, durata);
				statement.get().setString(9, "Veterinario");

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

	@Override
	public List<String> getInfermieriPerAppuntamento(Appuntamento app) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();


		 final String query = "select * "
		 		+ "from personale "
		 		+ "inner join effettua  "
		 		+ "on personale.cf = effettua.cf "
	        	+ "where data = ? "
	        	+ "and codTipoPrestazione = ? "
	        	+ "and codAnimale = ? "
	        	+ "and personale.ruolo = ? ";
		 
		 List<String> infermieri = new ArrayList<>();
			try {
				statement = Optional.of(connection.prepareStatement(query));
				statement.get().setString(1, app.getData());
				statement.get().setInt(2, app.getCodPrestazione());
				statement.get().setInt(3, app.getAnimale().getCodAnimale());
				statement.get().setString(4, "Infermiere");

				final ResultSet result = statement.get().executeQuery();
				
				while (result.next()) {
					String infermiere = result.getString("nome") + " " + result.getString("cognome");
					infermieri.add(infermiere);
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
			return infermieri;
	}

	@Override
	public boolean updatePrestazioneSuAnimale(Appuntamento app, Appuntamento datiDiAppModificati) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		
		final String query = "update prestazione_su_animale set data=? , ora=?, rilevazionePeso = ?, rilevazioneAltezza = ? where data=? and codTipoPrestazione= ? and codAnimale=? ";

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setString(1,datiDiAppModificati.getData());
			statement.get().setString(2, datiDiAppModificati.getOra());
		
			if (datiDiAppModificati.getPeso() != 0.0f && datiDiAppModificati.getAltezza() != 0.0f) {
				statement.get().setFloat(3, datiDiAppModificati.getPeso());
				statement.get().setFloat(4, datiDiAppModificati.getAltezza());
			} else {
				statement.get().setNull(3, Types.NULL);
				statement.get().setFloat(4, Types.NULL);
			}
				
			statement.get().setString(5, app.getData());
			statement.get().setInt(6, app.getCodPrestazione());
			statement.get().setInt(7, app.getAnimale().getCodAnimale());
			
			
			statement.get().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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
				System.out.println("errore sql");
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean updateEffettua(Appuntamento app, Appuntamento datiDiAppModificati) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		Optional<PreparedStatement> statementCF = Optional.empty();

		final String queryUpdateGenerica = " update effettua set data=? where data=? and codTipoPrestazione=? and codAnimale=? ";
		try {
			statement = Optional.of(connection.prepareStatement(queryUpdateGenerica));			
			statement.get().setString(1, datiDiAppModificati.getData());
			statement.get().setString(2, app.getData());
			statement.get().setInt(3, app.getCodPrestazione());
			statement.get().setInt(4, app.getAnimale().getCodAnimale());
			statement.get().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		final String queryUpdateVeterinario = "update effettua set CF= ? where data= ? and codTipoPrestazione= ? and codAnimale= ? and CF= ?  ";
		try {
			statementCF = Optional.of(connection.prepareStatement(queryUpdateVeterinario));
			statementCF.get().setString(1, datiDiAppModificati.getVeterinario().getCF());
			statementCF.get().setString(2, datiDiAppModificati.getData());
			statementCF.get().setInt(3, app.getCodPrestazione());
			statementCF.get().setInt(4, app.getAnimale().getCodAnimale());
			statementCF.get().setString(5, app.getVeterinario().getCF());
			statementCF.get().executeUpdate();
			
		} catch (SQLException e1) {
			System.out.println("errore modifica veterinario");
			e1.printStackTrace();
		} 
		finally {
			try {
				if (statement.isPresent()) {
					statement.get().close();
					statementCF.get().close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("errore sql update in effettua");
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean deleteAppointmnet(Appuntamento app) { 
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statementEffettua = Optional.empty();
		Optional<PreparedStatement> statementPrestazione = Optional.empty();
		
		final String queryEffettua = "DELETE FROM effettua WHERE codAnimale=? and codTipoPrestazione=? and data=?; ";
		final String queryPrestazione = "DELETE FROM prestazione_su_animale WHERE codAnimale=? and codTipoPrestazione=? and data=?; ";
	
		try {
			statementEffettua = Optional.of(connection.prepareStatement(queryEffettua));
			statementEffettua.get().setString(3, app.getData());
			statementEffettua.get().setInt(2, app.getCodPrestazione());
			statementEffettua.get().setInt(1, app.getAnimale().getCodAnimale());
			statementEffettua.get().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} 
		
		try {
			statementPrestazione = Optional.of(connection.prepareStatement(queryPrestazione));
			statementPrestazione.get().setString(3, app.getData());
			statementPrestazione.get().setInt(2, app.getCodPrestazione());
			statementPrestazione.get().setInt(1, app.getAnimale().getCodAnimale());
			statementPrestazione.get().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} 
		
		
		finally {
			try {
				if (statementEffettua.isPresent()) {
					statementEffettua.get().close();
					statementPrestazione.get().close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("errore sql");
				return false;
			}
		}
		return true;
	}

	@Override
	public List<Appuntamento> getPrestazioniByAnimale(int codAnimale) {
		
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		 final String query = "SELECT * " 
				 + "FROM referto "
				 + "inner JOIN prestazione_su_animale "
				 + "ON referto.data = prestazione_su_animale.data "
				 + "and referto.codAnimale = prestazione_su_animale.codAnimale "
				 + "and referto.codTipoPrestazione = prestazione_su_animale.codTipoPrestazione "
				 + "and referto.codAnimale = ? "
				 + "INNER JOIN prestazione ON prestazione.codPrestazione = prestazione_su_animale.codTipoPrestazione";
		 
		 List<Appuntamento> prestazioni = new ArrayList<>();
		try {
			statement = Optional.of(connection.prepareStatement(query));
            statement.get().setInt(1, codAnimale);
			final ResultSet result = statement.get().executeQuery();
			
			while (result.next()) {
				Appuntamento app = new AppuntamentoImpl();
				Animale animale = new AnimaleImpl();
				animale.setCodAnimale(result.getInt("referto.codAnimale"));
				app.setAnimale(animale);
				app.setData(result.getString("referto.data"));
				app.setDescrizioneReferto(result.getString("referto.descrizione"));
				app.setPrestazione(result.getString("prestazione.nome"));
				app.setCodPrestazione(result.getInt("prestazione.codPrestazione"));
				app.setTipoPrestazione(result.getString("prestazione.tipo"));
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
		
		prestazioni.forEach(p-> {
			this.setFarmaciInPrestazione(p);
		});
		
		
		prestazioni.forEach(p-> {
			this.setPatologieInPrestazione(p);
		});
		
		return prestazioni;
	}

	private void setPatologieInPrestazione(Appuntamento appuntamento) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		List<Patologia> patologie = new ArrayList<>();

		final String query = "SELECT * "  
				+ "FROM patologia_in_prestazione, patologia "  
				+ "where codAnimale = ? " 
				+ "and codTipoPrestazione = ? "  
				+ "and data = ? " 
				+ "and patologia_in_prestazione.codPatologia = patologia.codPatologia";
	
		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setInt(1, appuntamento.getAnimale().getCodAnimale());
			statement.get().setInt(2, appuntamento.getCodPrestazione());
			statement.get().setString(3, appuntamento.getData());

			final ResultSet result = statement.get().executeQuery();

			while (result.next()) {
				Patologia pat = new PatologiaImpl();
				pat.setNomePatologia(result.getString("patologia.nome"));
				patologie.add(pat);
			}
			
			appuntamento.setListPatologie(patologie);
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
	}

	private void setFarmaciInPrestazione(Appuntamento appuntamento) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		final String query = "SELECT * "  
				+ "FROM farmaco_in_prestazione, farmaco "  
				+ "where codAnimale = ? " 
				+ "and codTipoPrestazione = ? "  
				+ "and data = ? " 
				+ "and farmaco_in_prestazione.codFarmaco = farmaco.codFarmaco";

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setInt(1, appuntamento.getAnimale().getCodAnimale());
			statement.get().setInt(2, appuntamento.getCodPrestazione());
			statement.get().setString(3, appuntamento.getData());

			final ResultSet result = statement.get().executeQuery();

			List<Farmaco> farmaci = new ArrayList<>();
			while (result.next()) {
				Farmaco f = new FarmacoImpl();
				f.setNomeFarmaco(result.getString("farmaco.nome"));
				f.setDescrizione(result.getString("farmaco.descrizione"));
				f.setIndicazioni(result.getString("farmaco_in_prestazione.indicazioni"));
				farmaci.add(f);
			}
			
			appuntamento.setListFarmaci(farmaci);
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
	}
	
	@Override
	public List<Farmaco> getFarmaciInPrestazione(Appuntamento appuntamento) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		final String query = "SELECT * "  
				+ "FROM farmaco_in_prestazione, farmaco "  
				+ "where codAnimale = ? " 
				+ "and codTipoPrestazione = ? "  
				+ "and data = ? " 
				+ "and farmaco_in_prestazione.codFarmaco = farmaco.codFarmaco";
		List<Farmaco> farmaci = new ArrayList<>();

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setInt(1, appuntamento.getAnimale().getCodAnimale());
			statement.get().setInt(2, appuntamento.getCodPrestazione());
			statement.get().setString(3, appuntamento.getData());

			final ResultSet result = statement.get().executeQuery();

			while (result.next()) {
				Farmaco f = new FarmacoImpl();
				f.setNomeFarmaco(result.getString("farmaco.nome"));
				f.setDescrizione(result.getString("farmaco.descrizione"));
				f.setDataInizioSomministrazione(result.getString("farmaco_in_prestazione.dataInizioSomministrazione"));
				f.setDataFineSomministrazione(result.getString("farmaco_in_prestazione.datafineSomministrazione"));
				f.setIndicazioni(result.getString("farmaco_in_prestazione.indicazioni"));
				farmaci.add(f);
			}
			
			appuntamento.setListFarmaci(farmaci);
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
		return farmaci;
	}
	
	
	@Override
	public List<Patologia> getPatologieInPrestazione(Appuntamento appuntamento) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		final String query = "SELECT * "  
				+ "FROM patologia_in_prestazione, patologia "  
				+ "where codAnimale = ? " 
				+ "and patologia_in_prestazione.codPatologia = patologia.codPatologia "
				+ "and dataFine is null ";
		List<Patologia> patologie = new ArrayList<>();

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setInt(1, appuntamento.getAnimale().getCodAnimale());

			final ResultSet result = statement.get().executeQuery();

			while (result.next()) {
				Patologia pat = new PatologiaImpl();
				pat.setCodPatologia(result.getInt("patologia.codPatologia"));
				pat.setDataPrestazione(result.getString("patologia_in_prestazione.data"));
				pat.setDataInizio(result.getString("patologia_in_prestazione.dataInizio"));
				pat.setDataFine(result.getString("patologia_in_prestazione.dataFine"));
				pat.setDescrizione(result.getString("patologia.descrizione"));
				pat.setNomePatologia(result.getString("patologia.nome"));
				pat.setTipoPatologia(result.getString("patologia.tipoPatologia"));
				pat.setCodAnimale(result.getInt("patologia_in_prestazione.codAnimale"));
				pat.setCodTipoPrestazione(result.getInt("patologia_in_prestazione.codTipoPrestazione"));
				patologie.add(pat);
			}
			
			//appuntamento.setListPatologie(patologie);
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
		return patologie;
	}

	@Override
	public void addDataFinePatologia(Patologia p) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		final String query = "update patologia_in_prestazione "
					+ "set dataFine = ? "
					+ "where codAnimale = ? "
					+ "and codTipoPrestazione = ? "
					+ "and data = ? " 
					+ "and codPatologia = ? ";
		
		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setString(1, p.getDataFine());
			statement.get().setInt(2, p.getCodAnimale());
			statement.get().setInt(3, p.getCodTipoPrestazione());
			statement.get().setString(4, p.getDataInizio());
			statement.get().setInt(5, p.getCodPatologia());
			statement.get().executeUpdate();

			//final ResultSet result = statement.get().executeQuery();	
			//result.close();
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
	public List<Patologia> getAllPatologie() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		 final String query = "select * from patologia "; 
		 
		 List<Patologia> patologie = new ArrayList<>();
		try {
			statement = Optional.of(connection.prepareStatement(query));
			final ResultSet result = statement.get().executeQuery();
			
			while (result.next()) {
				Patologia p = new PatologiaImpl();
				p.setCodPatologia(result.getInt("codPatologia"));
				p.setNomePatologia(result.getString("nome"));
				p.setTipoPatologia(result.getString("tipoPatologia"));
				p.setDescrizione(result.getString("descrizione"));
				patologie.add(p);
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
		return patologie;
	}

	@Override
	public void addPatologiaInPrestazione(Patologia np,  Appuntamento app) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		String query = "insert into patologia_in_prestazione"
				+ " (codAnimale, codTipoPrestazione, data, codPatologia, dataInizio) values (?,?,?,?,?)";

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setInt(1, app.getAnimale().getCodAnimale()); 
			statement.get().setInt(2, app.getCodPrestazione()); 
			statement.get().setString(3, app.getData()); 
			statement.get().setInt(4, np.getCodPatologia()); 
			statement.get().setString(5, np.getDataInizio()); 
			statement.get().executeUpdate();
		} catch (SQLException e) {
			System.out.println("eccezione INSERIMENTO patologia_in_prestazione " + e);
		} finally {
			try {
				if (statement.isPresent()) {
					statement.get().close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("eccezione INSERIMENTO patologia_in_prestazione statement" + e);
			}
		}
	}

	@Override
	public List<Appuntamento> getAllPrestazioniByPersonale(String cf) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		 final String query = "select animale.nome, prestazione.nome, prestazione_su_animale.data, ora, personale.nome, "
		 		+ "personale.cognome, prestazione_su_animale.codAnimale, prestazione_su_animale.codTipoPrestazione, "
		 		+ "personale.cf , animale.cf , prestazione.tipo, animale.specie, animale.altezza, prestazione.durataMedia , prestazione.descrizione "
	        		+ "from prestazione_su_animale, effettua , prestazione , animale , personale "
	        		+ "where prestazione_su_animale.data=effettua.data "
	        		+ "and prestazione_su_animale.codAnimale=effettua.codAnimale "
	        		+ "and prestazione_su_animale.codTipoPrestazione = effettua.codTipoPrestazione "
	        		+ "and animale.codAnimale=effettua.codAnimale  "
	        		+ "and prestazione.codPrestazione = effettua.codTipoPrestazione "
	        		+ "and personale.CF=effettua.CF "
	        		+ "and personale.CF=? "
	        		+ "and prestazione_su_animale.data = ? "; 
		 
		 List<Appuntamento> prestazioni = new ArrayList<>();
		try {
			statement = Optional.of(connection.prepareStatement(query));
            statement.get().setString(1, cf);
            statement.get().setString(2,  new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            
			final ResultSet result = statement.get().executeQuery();
			
			while (result.next()) {
				Appuntamento app = new AppuntamentoImpl();
				Animale animale = new AnimaleImpl();
				animale.setNome(result.getString(1));
				animale.setCodAnimale(result.getInt(7));
				animale.setCf(result.getString(10));
				animale.setSpecie(result.getString(12));
				animale.setAltezza(result.getFloat(13));
				User veterinario = new UserImpl();
				veterinario.setCF(cf);
				veterinario.setNome(result.getString(5));
				veterinario.setCognome(result.getString(6));
				
				app.setAnimale(animale);
				app.setData(result.getString(3));
				app.setOra(result.getString(4));
				app.setPrestazione(result.getString(2));
				app.setVeterinario(veterinario);
				app.setCodPrestazione(result.getInt(8)); //TODO controlla
				app.setTipoPrestazione(result.getString(11));
				app.setDurata(result.getString(14));
				app.setDescrizionePrestazione(result.getString(15));
				
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
	public void addFarmacoInPrestazione(Farmaco nf, Appuntamento appuntamento) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		String query = "insert into farmaco_in_prestazione (codAnimale, codTipoPrestazione, data, codFarmaco, dataInizioSomministrazione, dataFineSomministrazione, indicazioni) values (?,?,?,?,?,?,?)";

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setInt(1, appuntamento.getAnimale().getCodAnimale()); 
			statement.get().setInt(2, appuntamento.getCodPrestazione()); 
			statement.get().setString(3, appuntamento.getData()); 
			statement.get().setInt(4, nf.getCodFarmaco()); 
			statement.get().setString(5, nf.getDataInizioSomministrazione());
			statement.get().setString(6, nf.getDataFineSomministrazione());
			statement.get().setString(7, nf.getIndicazioni());

			statement.get().executeUpdate();
		} catch (SQLException e) {
			System.out.println("eccezione INSERIMENTO farmaco_in_prestazione " + e);
		} finally {
			try {
				if (statement.isPresent()) {
					statement.get().close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("eccezione INSERIMENTO farmaco_in_prestazione statement" + e);
			}
		}
	}

	@Override
	public void addReferto(Appuntamento appuntamento) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		String query = "insert into referto (codAnimale, codTipoPrestazione, data, descrizione) values (?,?,?,?)";

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setInt(1, appuntamento.getAnimale().getCodAnimale()); 
			statement.get().setInt(2, appuntamento.getCodPrestazione());
			statement.get().setString(3, appuntamento.getData());
			statement.get().setString(4, appuntamento.getDescrizioneReferto());

			statement.get().executeUpdate();
		} catch (SQLException e) {
			System.out.println("eccezione INSERIMENTO referto " + e);
		} finally {
			try {
				if (statement.isPresent()) {
					statement.get().close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("eccezione INSERIMENTO referto statement" + e);
			}
		}
		
	}
	
	@Override
	public List<Appuntamento> getAllPrestazioniPerGestioneRicovero(String cf) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		 final String query = "select * " + 
		 		"from prestazione_su_animale, effettua , prestazione , animale , personale " + 
		 		"where prestazione_su_animale.data=effettua.data " + 
		 		"and prestazione_su_animale.codAnimale=effettua.codAnimale " + 
		 		"and prestazione_su_animale.codTipoPrestazione = effettua.codTipoPrestazione " + 
		 		"and animale.codAnimale=effettua.codAnimale " + 
		 		"and prestazione.codPrestazione = effettua.codTipoPrestazione " + 
		 		"and personale.CF=effettua.CF " + 
		 		"and personale.CF= ? " + 
		 		"and prestazione_su_animale.data = ? " + 
		 		"and prestazione_su_animale.codTipoPrestazione <> ? " + 
		 		"and prestazione_su_animale.ora <= ? " + 
		 		"and prestazione_su_animale.codAnimale not in (select ricovero.codAnimale " + 
		 		"	from ricovero, prestazione_su_animale " + 
		 		"	where prestazione_su_animale.codAnimale = ricovero.codAnimale " + 
		 		"	and prestazione_su_animale.data = ricovero.dataInizio " + 
		 		"	and prestazione_su_animale.ora = ricovero.oraInizio " + 
		 		"	and date_add(dataInizio, INTERVAL ricovero.durataInGiorni DAY) >= ? " + 
		 		"	and ricovero.codAnimale not in (select prestazione_su_animale.codAnimale " + 
		 		"	from prestazione_su_animale " + 
		 		"   where prestazione_su_animale.codTipoPrestazione = ? " + 
		 		"   and prestazione_su_animale.data = ? " + 
		 		"))"; 
		 
		 List<Appuntamento> prestazioni = new ArrayList<>();
		try {
			statement = Optional.of(connection.prepareStatement(query));
            statement.get().setString(1, cf);
            statement.get().setString(2,  new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            statement.get().setInt(3,  12);
            
            DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("HH:mm");
            System.out.println("HH:mm-> "+dtf4.format(LocalDateTime.now())+":00");

            statement.get().setString(4, dtf4.format(LocalDateTime.now())+":00");
            
            statement.get().setString(5,  new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            statement.get().setInt(6, 12);
            statement.get().setString(7,  new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

			final ResultSet result = statement.get().executeQuery();
			
			while (result.next()) {
				Appuntamento app = new AppuntamentoImpl();
				Animale animale = new AnimaleImpl();
				animale.setNome(result.getString("animale.nome"));
				animale.setCodAnimale(result.getInt("animale.codAnimale"));
				animale.setCf(result.getString("animale.cf"));
				animale.setSpecie(result.getString("animale.specie"));
				animale.setAltezza(result.getFloat("animale.altezza"));
				User veterinario = new UserImpl();
				veterinario.setCF(cf);
				veterinario.setNome(result.getString("personale.nome"));
				veterinario.setCognome(result.getString("personale.cognome"));
				
				app.setAnimale(animale);
				app.setData(result.getString("prestazione_su_animale.data"));
				app.setOra(result.getString("prestazione_su_animale.ora"));
				app.setPrestazione(result.getString("prestazione.nome"));
				app.setVeterinario(veterinario);
				app.setCodPrestazione(result.getInt("prestazione.codPrestazione")); //TODO controlla
				app.setTipoPrestazione(result.getString("prestazione.tipo"));
				
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
	public boolean checkIfHasReferto(Appuntamento appuntamento) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		boolean b = false;
		final String query = "SELECT * FROM referto " 
		+ "where referto.codTipoPrestazione = ? " 
		+ "and referto.codAnimale = ? " 
		+ "and referto.data = ? ";

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setInt(1, appuntamento.getCodPrestazione());
			statement.get().setInt(2, appuntamento.getAnimale().getCodAnimale());
			statement.get().setString(3, appuntamento.getData());
			
			final ResultSet result = statement.get().executeQuery();
			if (result.next()) {
				b = true;
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
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
				System.out.println("errore sql");
				return false;
			}
		}
		return b;
	}

	@Override
	public String getReferto(Appuntamento appuntamento) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		String refertoCommento = "";
		boolean b = false;
		final String query = "SELECT * FROM referto " 
		+ "where referto.codTipoPrestazione = ? " 
		+ "and referto.codAnimale = ? " 
		+ "and referto.data = ? ";

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setInt(1, appuntamento.getCodPrestazione());
			statement.get().setInt(2, appuntamento.getAnimale().getCodAnimale());
			statement.get().setString(3, appuntamento.getData());
			
			final ResultSet result = statement.get().executeQuery();
			if (result.next()) {
				refertoCommento = result.getString("descrizione");
			}			
			
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
		return refertoCommento;
	}
}
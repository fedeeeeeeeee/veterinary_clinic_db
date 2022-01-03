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

import model.Farmaco;
import model.FarmacoImpl;
import model.Strumento;
import model.StrumentoImpl;

public class FarmaciTableImpl implements FarmaciTable {

	private DatabaseConnection dataSource;
	private String tableNameFarmaco;
	
	public FarmaciTableImpl() {
		this.dataSource = new DatabaseConnection();
		this.tableNameFarmaco = "farmaco";
	}
	
	@Override
	public List<Farmaco> getFarmaciAcquistati() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		
		 final String query = "select lotto_acquistato.codLotto, nome, descrizione, lotto_acquistato.dataDiScadenza, disponibilita,  tipoFarmaco "
		 		+ "from lotto_acquistato "
		 		+ "INNER JOIN farmaco "
		 		+ "on lotto_acquistato.codFarmaco=farmaco.codFarmaco";
		 List<Farmaco> farmaci = new ArrayList<>();
		 
			try {
				statement = Optional.of(connection.prepareStatement(query));
				final ResultSet result = statement.get().executeQuery();
				
				while (result.next()) {
					Farmaco far = new FarmacoImpl();
					far.setCodLotto(result.getInt(1));
					far.setNomeFarmaco(result.getString(2));
					far.setDescrizione(result.getString(3));
					far.setDataScadenza(result.getString(4));
					far.setDisponibilita(result.getInt(5));
					far.setTipoFarmaco(result.getString(6));

					farmaci.add(far);
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
			return farmaci;
		}
	@Override
	public List<Farmaco> getAllFarmaci() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
	
		 final String query = "select lotto_acquistato.codLotto, farmaco.nome, descrizione, lotto_acquistato.dataDiScadenza, disponibilita,  tipoFarmaco "
		 		+ "from farmaco "
		 		+ "left join lotto_acquistato "
		 		+ "on  farmaco.codFarmaco=lotto_acquistato.codFarmaco";
		 List<Farmaco> farmaci = new ArrayList<>();
		 
			try {
				statement = Optional.of(connection.prepareStatement(query));
				final ResultSet result = statement.get().executeQuery();
				
				while (result.next()) {
					Farmaco far = new FarmacoImpl();
					far.setCodLotto(result.getInt(1));
					far.setNomeFarmaco(result.getString(2));
					far.setDescrizione(result.getString(3));
					far.setDataScadenza(result.getString(4));
					far.setDisponibilita(result.getInt(5));
					far.setTipoFarmaco(result.getString(6));

					farmaci.add(far);
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
			return farmaci;
		}

	@Override
	public List<Farmaco> getFarmaciScaduti() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		 final String query = " select lotto_acquistato.codLotto, nome, descrizione, lotto_acquistato.dataDiScadenza, disponibilita,  tipoFarmaco "
			 		+ "from lotto_acquistato "
			 		+ "INNER JOIN farmaco "
			 		+ "on lotto_acquistato.codFarmaco=farmaco.codFarmaco "
			 		+ "where dataDiScadenza <= ?";
		 List<Farmaco> farmaci = new ArrayList<>();
			try {
			
				DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE; //("yyyy-MM-dd")
				String dataformat = formatter.format(LocalDateTime.now());
				System.out.println(dataformat);
				statement = Optional.of(connection.prepareStatement(query));
			    statement.get().setString(1,dataformat);
				final ResultSet result = statement.get().executeQuery();
				
				while (result.next()) {
					Farmaco far = new FarmacoImpl();
					far.setCodLotto(result.getInt(1));
					far.setNomeFarmaco(result.getString(2));
					far.setDescrizione(result.getString(3));
					far.setDataScadenza(result.getString(4));
					far.setDisponibilita(result.getInt(5));
					far.setTipoFarmaco(result.getString(6));

					farmaci.add(far);
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
			return farmaci;
		}

	@Override
	public List<Farmaco> getFarmaciInScadenza() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		 final String query = " select lotto_acquistato.codLotto, nome, descrizione, lotto_acquistato.dataDiScadenza, disponibilita,  tipoFarmaco "
			 		+ "from lotto_acquistato "
			 		+ "INNER JOIN farmaco "
			 		+ "on lotto_acquistato.codFarmaco=farmaco.codFarmaco "
			 		+ "where dataDiScadenza <= ? AND dataDiScadenza > ? ";
		 List<Farmaco> farmaci = new ArrayList<>();
		 
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
					Farmaco far = new FarmacoImpl();
					far.setCodLotto(result.getInt(1));
					far.setNomeFarmaco(result.getString(2));
					far.setDescrizione(result.getString(3));
					far.setDataScadenza(result.getString(4));
					far.setDisponibilita(result.getInt(5));
					far.setTipoFarmaco(result.getString(6));

					farmaci.add(far);
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
			return farmaci;
		}

	@Override
	public List<Farmaco> getFarmaciInEsaurimento() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		 final String query =  " select lotto_acquistato.codLotto, nome, descrizione, lotto_acquistato.dataDiScadenza, disponibilita,  tipoFarmaco "
			 		+ "from lotto_acquistato "
			 		+ "INNER JOIN farmaco "
			 		+ "on lotto_acquistato.codFarmaco=farmaco.codFarmaco "
			 		+ "where disponibilita <= 10 ";
		 List<Farmaco> farmaci = new ArrayList<>();
		 
			try {
				statement = Optional.of(connection.prepareStatement(query));
				final ResultSet result = statement.get().executeQuery();
				
				while (result.next()) {
					Farmaco far = new FarmacoImpl();
					far.setCodLotto(result.getInt(1));
					far.setNomeFarmaco(result.getString(2));
					far.setDescrizione(result.getString(3));
					far.setDataScadenza(result.getString(4));
					far.setDisponibilita(result.getInt(5));
					far.setTipoFarmaco(result.getString(6));

					farmaci.add(far);
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
			return farmaci;
		}
	@Override
	public List<Farmaco> getAllVaccini() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		 final String query = "where tipoFarmaco=? ";
		 List<Farmaco> farmaci = new ArrayList<>();
		 
			try {
				statement = Optional.of(connection.prepareStatement(query));
				  statement.get().setString(1,"Vaccino");
				final ResultSet result = statement.get().executeQuery();
				
				while (result.next()) {
					Farmaco far = new FarmacoImpl();
					far.setCodLotto(result.getInt(1));
					far.setNomeFarmaco(result.getString(2));
					far.setDescrizione(result.getString(3));
					far.setDataScadenza(result.getString(4));
					far.setDisponibilita(result.getInt(5));
					far.setTipoFarmaco(result.getString(6));

					farmaci.add(far);
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
			return farmaci;
		}
	@Override
	public List<Farmaco> getOnlyFarmaci() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		 final String query = "where tipoFarmaco=? ";
		 List<Farmaco> farmaci = new ArrayList<>();
		 
			try {
				statement = Optional.of(connection.prepareStatement(query));
				  statement.get().setString(1,"Farmaco");
				final ResultSet result = statement.get().executeQuery();
				
				while (result.next()) {
					Farmaco far = new FarmacoImpl();
					far.setCodLotto(result.getInt(1));
					far.setNomeFarmaco(result.getString(2));
					far.setDescrizione(result.getString(3));
					far.setDataScadenza(result.getString(4));
					far.setDisponibilita(result.getInt(5));
					far.setTipoFarmaco(result.getString(6));

					farmaci.add(far);
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
			return farmaci;
		}
	@Override
	public void updateQuantità(Farmaco farmaco, int q) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Farmaco> getFarmaciMaiAcquistati() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		 final String query =  " select lotto_acquistato.codLotto, nome, descrizione, lotto_acquistato.dataDiScadenza, disponibilita,  tipoFarmaco "
			 		+ "from farmaco "
			 		+ "left JOIN lotto_acquistato "
			 		+ "on farmaco.codFarmaco=lotto_acquistato.codFarmaco "
			 		+ "where lotto_acquistato.codFarmaco IS NULL  ";
		 List<Farmaco> farmaci = new ArrayList<>();
		 
			try {
				statement = Optional.of(connection.prepareStatement(query));
				final ResultSet result = statement.get().executeQuery();
				
				while (result.next()) {
					Farmaco far = new FarmacoImpl();
					far.setCodLotto(result.getInt(1));
					far.setNomeFarmaco(result.getString(2));
					far.setDescrizione(result.getString(3));
					far.setDataScadenza(result.getString(4));
					far.setDisponibilita(result.getInt(5));
					far.setTipoFarmaco(result.getString(6));

					farmaci.add(far);
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
			return farmaci;
		}

	@Override
	public List<String> getAllTypeFarmaco() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		 final String query =  " select distinct tipoFarmaco "
			 		+ "from farmaco ";
		 List<String> farmaci = new ArrayList<>();
		 
			try {
				statement = Optional.of(connection.prepareStatement(query));
				final ResultSet result = statement.get().executeQuery();
				
				while (result.next()) {
					farmaci.add(result.getString(1));
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
			return farmaci;
		}

	@Override
	public void addNewFarmaco(Farmaco farmaco) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		final String query = " insert into " +  tableNameFarmaco +" (codFarmaco, nome, descrizione, disponibilita, tipoFarmaco, copertura) values (?,?,?,?,?,?) ";

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setInt(1,0);
			statement.get().setString(2, farmaco.getNomeFarmaco());
			statement.get().setString(3, farmaco.getDescrizione());
			statement.get().setInt(4, farmaco.getDisponibilita());
			statement.get().setString(5, farmaco.getTipoFarmaco());
			if(farmaco.getTipoFarmaco().isEmpty()) {
				statement.get().setString(6, null);
			} else { 
				statement.get().setString(6, farmaco.getCopertura());
			}
			
			
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
				System.out.println("errore sql");
			}
		}
	}
}
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Person;
import model.Proprietario;

public class ProprietarioTableImpl implements ProprietarioTable {
	
	private DatabaseConnection dataSource;
	private String tableName;

	public ProprietarioTableImpl(){
		this.dataSource = new DatabaseConnection();
		this.tableName = "proprietario";
	}
	
	private boolean checkIfProprietarioExists(String cf) {
		final Connection connection = this.dataSource.getMySQLConnection();
		boolean isPresent = false;
		Optional<PreparedStatement> statement = Optional.empty();

		final String query = "select * from " +tableName + " where CF=?";

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setString(1, cf);
			final ResultSet result = statement.get().executeQuery();

			if (result.next()) {
				System.out.println("l'utente esiste");
			} else {
				System.out.println("l'utente non esiste");
				isPresent = false;
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
				return false;
			}
		}
		return isPresent;
	}

	
	@Override
	public boolean addNewProprietario(Person proprietario) {
		final Connection connection = this.dataSource.getMySQLConnection();

		if (this.checkIfProprietarioExists(proprietario.getCF())) {
			System.out.println("Utente già presente");
			return false;
		}

		Optional<PreparedStatement> statement = Optional.empty();
		String query = "insert into " + tableName
				+ " (CF, nome, cognome, ind_citta, ind_via, ind_numero, ind_cap, telefono, e_mail, dataDiNascita) values (?,?,?,?,?,?,?,?,?,?)";

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setString(1, proprietario.getCF());
			statement.get().setString(2, proprietario.getNome());
			statement.get().setString(3, proprietario.getCognome());
			statement.get().setString(4, proprietario.getIndirizzoCitta());
			statement.get().setString(5, proprietario.getIndirizzoVia());
			statement.get().setInt(6, proprietario.getIndirizzoNumero());
			statement.get().setInt(7, proprietario.getIndirizzoCap());
			statement.get().setLong(8, proprietario.getTelefono());
			statement.get().setString(9, proprietario.getEmail());
			statement.get().setString(10, proprietario.getDataNascita());

			statement.get().executeUpdate();
		} catch (SQLException e) {
			System.out.println("eccezione INSERIMENTO UTENTE " + e);
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
				System.out.println("eccezione INSERIMENTO UTENTE statement" + e);
				return false;
			}
		}
		return true;
	}

	@Override
	public List<String> getAllPropietariCf() {
		List<String> cf = new ArrayList<>();

		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		final String query = "select cf from " + tableName;

		try {
			statement = Optional.of(connection.prepareStatement(query));
			final ResultSet result = statement.get().executeQuery();

			while (result.next()) {
				cf.add(result.getString("cf"));
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
		return cf;
	}

	@Override
	public Person getProprietario(String cf) {
		
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		final String query = "select * from " +tableName + " where CF=? ";
		Person proprietario = new Proprietario();
		
		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setString(1, cf);
			final ResultSet result = statement.get().executeQuery();
			
			if (result.next()) {
			proprietario.setCF(cf);
			proprietario.setNome(result.getString("nome"));
			proprietario.setCognome(result.getString("cognome"));
			proprietario.setTelefono(result.getLong("telefono"));
			proprietario.setDataNascita(result.getString("dataDiNascita"));
			proprietario.setEmail(result.getString("e_mail"));
			proprietario.setIndirizzoVia(result.getString("ind_via"));
			proprietario.setIndirizzoNumero(result.getInt("ind_numero"));
			proprietario.setIndirizzoCap(result.getInt("ind_cap"));
			proprietario.setIndirizzoCitta(result.getString("ind_citta"));
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
		return proprietario;
	}
}

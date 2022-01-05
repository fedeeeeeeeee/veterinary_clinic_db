package database;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import database.DatabaseConnection;
import model.User;
import model.UserImpl;

public class UserTableImpl implements UserTable {

	private DatabaseConnection dataSource;
	private String personaleTableName;

	public UserTableImpl() {
		this.dataSource = new DatabaseConnection();
		this.personaleTableName = "personale";
	}

	private String generateSalt() {
		int lenght = 4;
		String abcCapitals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String abcLowerCase = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "01234567890123456789";

		String total = abcCapitals + abcLowerCase + numbers;
		String response = "";

		char letters[] = new char[lenght];
		for (int i = 0; i < lenght; i++) {
			Random r = new Random();
			char letter = total.charAt(r.nextInt(total.length()));
			letters[i] = letter;
		}

		response = Arrays.toString(letters).replaceAll("\\s+", "");
		response = response.replaceAll(",", "");
		return response;
	}

	private String generateHash(String password, String salt) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes(StandardCharsets.UTF_8));
			byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
		}
		return generatedPassword;
	}

	@Override
	public boolean findUser(String email, String password) {
		final Connection connection = this.dataSource.getMySQLConnection();
		boolean isPresent = false;
		Optional<PreparedStatement> statement = Optional.empty();
		final String query = "select sale , password from " + personaleTableName + " where e_mail=?";

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setString(1, email);
			final ResultSet result = statement.get().executeQuery();

			if (result.next()) {
				//System.out.println("l'utente esiste");
				String passwordHash = generateHash(password, result.getString("sale"));

				if (result.getString("password").equals(passwordHash)) {
					//System.out.println("password corretta");
					isPresent = true;
				} else {
					//System.out.println("password non corretta");
					isPresent = false;
				}
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
	public boolean addNewUser(User user) {
		final Connection connection = this.dataSource.getMySQLConnection();

		// controllo se l'utente esiste
		if (this.checkIfUserExists(user.getCF())) {
			System.out.println("Utente già presente");
			return false;
		}

		Optional<PreparedStatement> statement = Optional.empty();
		String query = "insert into " + personaleTableName
				+ " (CF, nome, cognome, ind_citta, ind_via, ind_numero, ind_cap, telefono, e_mail, anniDiServizio, password, sale, ruolo, dataDiNascita) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setString(1, user.getCF());
			statement.get().setString(2, user.getNome());
			statement.get().setString(3, user.getCognome());
			statement.get().setString(4, user.getIndirizzoCitta());
			statement.get().setString(5, user.getIndirizzoVia());
			statement.get().setInt(6, user.getIndirizzoNumero());
			statement.get().setInt(7, user.getIndirizzoCap());
			statement.get().setLong(8, user.getTelefono());
			statement.get().setString(9, user.getEmail());
			statement.get().setInt(10, user.getAnniDiServizio());

			String salt = this.generateSalt();
			String passwordHash = this.generateHash(user.getHashPassword(), salt.substring(1, salt.length() - 1));
			statement.get().setString(11, passwordHash);
			statement.get().setString(12, salt.substring(1, salt.length() - 1));
			statement.get().setString(13, user.getRuolo());
			statement.get().setString(14, user.getDataNascita());
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

	private boolean checkIfUserExists(String cf) {
		final Connection connection = this.dataSource.getMySQLConnection();
		boolean isPresent = false;
		Optional<PreparedStatement> statement = Optional.empty();

		final String query = "select * from " + personaleTableName + " where CF=?";

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
	public List<String> getAllRuoli() {
		List<String> ruoli = new ArrayList<>();

		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		final String query = "select distinct ruolo from " + personaleTableName;

		try {
			statement = Optional.of(connection.prepareStatement(query));
			final ResultSet result = statement.get().executeQuery();

			while (result.next()) {
				ruoli.add(result.getString("ruolo"));
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
				// return false;
			}
		}
		return ruoli;
	}

	@Override
	public User getUser(String username) {

		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		final String query = "select * from " + personaleTableName;
		User user = new UserImpl();

		try {
			statement = Optional.of(connection.prepareStatement(query));
			final ResultSet result = statement.get().executeQuery();
			if (result.next()) {
				user.setCF(result.getString("CF"));
				user.setAnniDiServizio(result.getInt("anniDiServizio"));
				user.setNome(result.getString("nome"));
				user.setCognome(result.getString("cognome"));
				user.setDataNascita(result.getString("dataDiNascita"));
				user.setIndirizzoCitta(result.getString("ind_citta"));
				user.setIndirizzoCap(result.getInt("ind_cap"));
				user.setIndirizzoNumero(result.getInt("ind_numero"));
				user.setIndirizzoVia(result.getString("ind_via"));
				user.setTelefono(result.getLong("telefono"));
				user.setEmail(result.getString("e_mail"));
				user.setRuolo(result.getString("ruolo"));
				user.setHashPassword(result.getString("password"));
				user.setSalt(result.getString("sale"));
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
		return user;
	}

	@Override
	public List<User> getAllPersonaleByRuolo(String ruolo) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		final String query = "select nome, cognome, CF from personale where ruolo = ? ";
		
		List<User> list = new ArrayList<>();

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setString(1, ruolo);

			final ResultSet result = statement.get().executeQuery();
			while (result.next()) {
				User user = new UserImpl();
				user.setNome(result.getString("nome"));
				user.setCognome(result.getString("cognome"));
				user.setCF(result.getString("CF"));
				list.add(user);
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
		return list;
	}
}

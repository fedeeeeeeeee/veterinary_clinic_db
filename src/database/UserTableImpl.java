package database;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import database.DatabaseConnection;

public class UserTableImpl implements UserTable {

	private DatabaseConnection dataSource;
	private String personaleTableName;

	public UserTableImpl() {
		this.dataSource = new DatabaseConnection();
		this.personaleTableName = "personale";
	}

	// per registrazione
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

	private String generateHash(char[] password, String salt) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes(StandardCharsets.UTF_8));
			byte[] bytes = md.digest(new String(password).getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		return generatedPassword;
	}

	@Override
	public boolean findUser(String email, char[] password) {
		final Connection connection = this.dataSource.getMySQLConnection();
		boolean isPresent = false;
		Optional<PreparedStatement> statement = Optional.empty();

		final String query = "select sale , password from " + personaleTableName + " where e_mail=?";

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setString(1, email);
			final ResultSet result = statement.get().executeQuery();

			if (result.next()) {
				System.out.println("l'utente esiste");
				String passwordHash = generateHash(password, result.getString("sale"));

				if (result.getString("password").equals(passwordHash)) {
					System.out.println("password corretta");
					isPresent = true;
				} else {
					System.out.println("password non corretta");
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
	public boolean newUser(String username, char[] password) {
		// TODO Auto-generated method stub
		return false;
	}

}

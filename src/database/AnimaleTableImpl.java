package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Animale;
import model.AnimaleImpl;
import model.Person;
import model.Proprietario;
import utility.Pair;

public class AnimaleTableImpl implements AnimaleTable {
	
	private DatabaseConnection dataSource;
	private String animaleTableName;
	
	public AnimaleTableImpl() {
		this.dataSource = new DatabaseConnection();
		this.animaleTableName = "animale";
	}
	
	/*@Override
	public List<String> getAllPropietariCf() {
		List<String> cf = new ArrayList<>();

		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		final String query = "select distinct cf from " + animaleTableName;

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
	}*/

	@Override
	public List<Pair<String, String>> getNomeAndRazzaByProprietario(final String CF) {
		
		List<Pair<String, String>> list = new ArrayList<>();

		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		final String query = "select nome, razza from animale where CF=?";

		try {			
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setString(1, CF);
			final ResultSet result = statement.get().executeQuery();

			while (result.next()) {
				list.add(new Pair<>(result.getString("nome"), result.getString("razza")));
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
				 e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public void addNewAnimale(Animale animale) {
		final Connection connection = this.dataSource.getMySQLConnection();

		Optional<PreparedStatement> statement = Optional.empty();
		String query = "insert into " + animaleTableName
				+ " (nome, genere, dataDiNascita, peso, altezza, razza, specie, cf) values (?,?,?,?,?,?,?,?)";

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setString(1, animale.getNome());
			statement.get().setString(2, animale.getGenere());
			statement.get().setString(3, animale.getDataDiNascita());
			statement.get().setFloat(4, animale.getPeso());
			statement.get().setFloat(5, animale.getAltezza());
			statement.get().setString(6, animale.getRazza());
			statement.get().setString(7, animale.getSpecie());
			statement.get().setString(8, animale.getCf());
			statement.get().executeUpdate();
		} catch (SQLException e) {
			System.out.println("eccezione INSERIMENTO ANIMALE " + e);
		} finally {
			try {
				if (statement.isPresent()) {
					statement.get().close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("eccezione INSERIMENTO ANIMALE statement" + e);
			}
		}
	}

	@Override
	public List<Animale> getAllAnimali() {

		List<Animale> list = new ArrayList<>();

		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		final String query = "SELECT * FROM animale INNER JOIN proprietario on animale.cf = proprietario.cf";

		try {			
			statement = Optional.of(connection.prepareStatement(query));

			final ResultSet result = statement.get().executeQuery();

			while (result.next()) {
				Animale animale = new AnimaleImpl();
				animale.setNome(result.getString("animale.nome"));
				Person proprietario = new Proprietario();
				proprietario.setCF(result.getString("cf"));
				proprietario.setNome(result.getString("proprietario.nome"));
				proprietario.setCognome(result.getString("cognome"));
				proprietario.setTelefono(result.getLong("telefono"));
				
				animale.setProprietario(proprietario);

				animale.setCodAnimale(result.getInt("codAnimale"));
				
				list.add(animale);
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
				 e.printStackTrace();
			}
		}
		return list;
	}
}

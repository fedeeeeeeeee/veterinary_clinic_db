package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import controller.PrestazioneController;
import model.User;

public class PrestazioneTableImpl implements PrestazioneTable, PrestazioneController {
	
	private DatabaseConnection dataSource;
	private String prestazioneTableName;
	
	public PrestazioneTableImpl() {
		this.dataSource = new DatabaseConnection();
		this.prestazioneTableName = "prestazione_su_animale";
	}
	
	@Override
	public List<String> getAllPrestazioni() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		final String query = "select data, ora from " + prestazioneTableName + " order by data DESC";
		List<String> prestazioni = new ArrayList<>();
		
		try {
			statement = Optional.of(connection.prepareStatement(query));
			final ResultSet result = statement.get().executeQuery();

			while (result.next()) {
				prestazioni.add(result.getString("data") + " | " + result.getString("ora"));
				// data ora tipo animale dottore --> nome, durata, prezzo, descrizione 
				//icona i icona cestino 
				// icona i --> vedere e modificare
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
	public List<String> getPrestazioniInData(Date data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getPrestazioneDiVeterinario(User veterinario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addNewPrestazione() {
		// TODO Auto-generated method stub
		return false;
	}

}

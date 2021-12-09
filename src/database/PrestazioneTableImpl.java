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
import model.Appuntamento;
import model.AppuntamentoImpl;
import model.User;

public class PrestazioneTableImpl implements PrestazioneTable {
	
	private DatabaseConnection dataSource;
	//private String prestazioneTableName;
	
	public PrestazioneTableImpl() {
		this.dataSource = new DatabaseConnection();
		//this.prestazioneTableName = "prestazione_su_animale";
	}
	
	@Override
	public List<Appuntamento> getAllPrestazioni() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		 final String query = "select animale.nome, prestazione.nome, prestazione_su_animale.data, ora, personale.nome, personale.cognome, prestazione_su_animale.codAnimale, prestazione_su_animale.codTipoPrestazione "
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
				app.setNomeAnimale(result.getString(1));
				app.setData(result.getString(3));
				app.setOra(result.getString(4));
				app.setPrestazione(result.getString(2));
				app.setNomeVeterinario(result.getString(5));
				app.setCognomeVeterinario(result.getString(6));
				app.setCodAnimale(result.getInt(7));
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

		 final String query = "select animale.nome, prestazione.nome, prestazione_su_animale.data, ora, personale.nome, personale.cognome "
	        		+ "from prestazione_su_animale, effettua , prestazione , animale , personale "
	        		+ "where prestazione_su_animale.data=?"
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
					app.setNomeAnimale(result.getString(1));
					app.setData(result.getString(3));
					app.setOra(result.getString(4));
					app.setPrestazione(result.getString(2));
					app.setNomeVeterinario(result.getString(5));
					app.setCognomeVeterinario(result.getString(6));
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
	public List<Appuntamento> getPrestazioneDiVeterinario(User veterinario) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		 final String query = "select distinct animale.nome, prestazione.nome, prestazione_su_animale.data, ora, personale.nome, personale.cognome "
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
					app.setNomeAnimale(result.getString(1));
					app.setData(result.getString(3));
					app.setOra(result.getString(4));
					app.setPrestazione(result.getString(2));
					app.setNomeVeterinario(result.getString(5));
					app.setCognomeVeterinario(result.getString(6));
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
	public boolean addNewPrestazione(Appuntamento appuntamento) {
		// TODO Auto-generated method stub
		return false;
	}

}

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
	private String prestazioneTableName;
	
	public PrestazioneTableImpl() {
		this.dataSource = new DatabaseConnection();
		this.prestazioneTableName = "prestazione_su_animale";
	}
	
	@Override
	public List<Appuntamento> getAllPrestazioni() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		String veterinario = new String("veterinario");
		 final String query = "select animale.nome, prestazione.nome, prestazione_su_animale.data, ora, personale.nome, personale.cognome "
	        		+ "from prestazione_su_animale pa, effettua e , prestazione pr , animale a , personale pe "
	        		+ "where pa.data=e.data "
	        		+ "and pa.codTipoPrestazione = e.codTipoPrestazione "
	        		+ "and a.codAnimale=e.codAnimale  "
	        		+ "and pr.codPrestazione = e.codTipoPrestazione"
	        		+ "and pe.CF=e.CF "
	        		+ "pe.ruolo=" + veterinario; // NO
		 
		 List<Appuntamento> prestazioni = new ArrayList<>();
		try {
			statement = Optional.of(connection.prepareStatement(query));
			final ResultSet result = statement.get().executeQuery();
			
			while (result.next()) {
				Appuntamento app = new AppuntamentoImpl();
				app.setNomeAnimale(result.getString(0));
				app.setData(result.getString(2));
				app.setOra(result.getString(3));
				app.setPrestazione(result.getString(1));
				app.setNomeVeterinario(result.getString(4));
				app.setCognomeVeterinario(result.getString(5));
				prestazioni.add(app);
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
	public List<Appuntamento> getPrestazioniInData(Date data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appuntamento> getPrestazioneDiVeterinario(User veterinario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addNewPrestazione(Appuntamento appuntamento ) {
		// TODO Auto-generated method stub
		return false;
	}

}

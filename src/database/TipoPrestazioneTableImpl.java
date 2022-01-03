package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Prestazione;
import model.PrestazioneImpl;

public class TipoPrestazioneTableImpl implements TipoPrestazioneTable {
	
	private DatabaseConnection dataSource;
	
	public TipoPrestazioneTableImpl() {
		this.dataSource = new DatabaseConnection();
	}
	
	@Override
	public List<Prestazione> getAllTipiDiPrestazione() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		final String query = "select codPrestazione, nome, tipo, durataMedia from prestazione"; 

		List<Prestazione> prestazioni = new ArrayList<>();
		try {
			statement = Optional.of(connection.prepareStatement(query));
			final ResultSet result = statement.get().executeQuery();
			
			while (result.next()) {
				Prestazione p = new PrestazioneImpl();
				p.setCodPrestazione(result.getInt(1));
				p.setNomePrestazione(result.getString(2));
				p.setTipoPrestazione(result.getString(3));
				p.setDurataMedia(result.getString(4));
				prestazioni.add(p);
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

}

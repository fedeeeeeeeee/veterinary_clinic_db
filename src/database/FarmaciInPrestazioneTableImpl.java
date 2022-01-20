package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import model.Animale;
import model.AnimaleImpl;
import model.Farmaco;
import model.FarmacoImpl;
import model.FarmacoInPrestazione;
import model.FarmacoInPrestazioneImpl;
import model.Pagamento;
import model.PagamentoImpl;

public class FarmaciInPrestazioneTableImpl implements FarmaciInPrestazioneTable {
	private DatabaseConnection dataSource;

	public FarmaciInPrestazioneTableImpl() {
		this.dataSource = new DatabaseConnection();
	}
	
	@Override
	public List<FarmacoInPrestazione> getFarmaciDiAnimale(int codAnimale) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		 final String query = "SELECT * " 
				 		+ "FROM farmaco, farmaco_in_prestazione " 
				 		+ "where farmaco_in_prestazione.codAnimale= ? " 
				 		+ "and farmaco.codFarmaco = farmaco_in_prestazione.codFarmaco ";
		 
		 List<FarmacoInPrestazione> farmaci = new ArrayList<>();
		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setInt(1, codAnimale);
			final ResultSet result = statement.get().executeQuery();
			
			while (result.next()) {
				FarmacoInPrestazione f = new FarmacoInPrestazioneImpl();
			
				f.setDataInizioSomministrazione(result.getString("dataInizioSomministrazione"));
				f.setDataFineSomministrazione(result.getString("dataFineSomministrazione"));
				f.setIndicazioni(result.getString("indicazioni"));
				Farmaco farmaco = new FarmacoImpl();
				farmaco.setCodFarmaco(result.getInt("codFarmaco"));
				farmaco.setNomeFarmaco(result.getString("nome"));
				farmaco.setDescrizione(result.getString("descrizione"));
				farmaco.setTipoFarmaco(result.getString("tipoFarmaco"));
				f.setFarmaco(farmaco);
				farmaci.add(f);
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
		
		return farmaci;
	}

	@Override
	public List<String> getFarmaciAssuntiByAnimale(int codAnimale) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		 final String query = "SELECT * " 
				 		+ "FROM farmaco, farmaco_in_prestazione " 
				 		+ "where farmaco_in_prestazione.codAnimale= ? " 
				 		+ "and farmaco.codFarmaco = farmaco_in_prestazione.codFarmaco "
				 		+ "and farmaco_in_prestazione.dataFineSomministrazione >= ? "
				 		+ "and farmaco_in_prestazione.dataInizioSomministrazione <> ? ";
		 
		 List<String> farmaci = new ArrayList<>();
		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setInt(1, codAnimale);
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String date = df.format(new Date());
			statement.get().setString(2, date);
			statement.get().setString(3, date);

			final ResultSet result = statement.get().executeQuery();
			
			while (result.next()) {
				farmaci.add(result.getString("farmaco.nome"));
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
		
		return farmaci;
	}
}

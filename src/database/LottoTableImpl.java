package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Farmaco;
import model.FarmacoImpl;
import model.Lotto;

public class LottoTableImpl implements LottoTable {
	private DatabaseConnection dataSource;

	public LottoTableImpl() {
		this.dataSource = new DatabaseConnection();
	}

	@Override
	public List<Farmaco> getAllDistinctFarmaci() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		final String query = "select codFarmaco, nome, disponibilita, descrizione from farmaco ";
		
		List<Farmaco> farmaci = new ArrayList<>();

		try {
			statement = Optional.of(connection.prepareStatement(query));
			final ResultSet result = statement.get().executeQuery();

			while (result.next()) {
				Farmaco far = new FarmacoImpl();
				far.setCodFarmaco(result.getInt(1));
				far.setNomeFarmaco(result.getString(2));
				far.setDisponibilita(result.getInt(3));
				far.setDescrizione(result.getString(4));
				
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
	public void addNewLotto(Lotto lotto) {
		// TODO Auto-generated method stub

	}

}

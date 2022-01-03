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
	private String tableName;
	private String tableNameFarmaco;
	
	public LottoTableImpl() {
		this.dataSource = new DatabaseConnection();
		this.tableName = "lotto_acquistato";
		this.tableNameFarmaco = "farmaco";
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
	public boolean addNewLotto(Lotto lotto) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		final String query = " insert into " +  tableName +" (codLotto, numeroConfezioni, quantitaPerConfezione, dataDiScadenza, codFarmaco) values (?,?,?,?,?) ";

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setInt(1,0);
			statement.get().setInt(2, lotto.getNumeroConfezioni());
			statement.get().setInt(3, lotto.getQuantitaPerConfezione());
			statement.get().setString(4,lotto.getDataScadenza());
			statement.get().setInt(5, lotto.getCodFarmaco());
			
			statement.get().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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
				System.out.println("errore sql");
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean updateQuantitaAFarmaci(Lotto lotto) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		int quantita = lotto.getNumeroConfezioni() * lotto.getQuantitaPerConfezione();
		System.out.println(quantita);
		final String query = " update " +  tableNameFarmaco +" set disponibilita=? where codFarmaco=? ";

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setInt(1,quantita);
			statement.get().setInt(2, lotto.getCodFarmaco());
			
			statement.get().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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
				System.out.println("errore sql");
				return false;
			}
		}
		return true;
	}
}

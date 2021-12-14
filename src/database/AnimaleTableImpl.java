package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnimaleTableImpl implements AnimaleTable {
	
	private DatabaseConnection dataSource;
	private String animaleTableName;
	
	public AnimaleTableImpl() {
		this.dataSource = new DatabaseConnection();
		this.animaleTableName = "animale";
	}
	
	@Override
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
				// return false;
			}
		}
		return cf;
	}

}

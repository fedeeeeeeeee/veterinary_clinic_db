package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import database.DatabaseConnection;

public class UserTableImpl implements UserTable {

	private DatabaseConnection dataSource;
	private String personaleTableName;
	
	public UserTableImpl() {
		this.dataSource = new DatabaseConnection();
		this.personaleTableName = "personale";
	}

	@Override
	public boolean findUser(String username, char[] password, String professione) {
		final Connection connection = this.dataSource.getMySQLConnection();
		boolean isPresent = false;
        
        Optional<PreparedStatement> statement = Optional.empty();
                
        final String query = "select * from " + personaleTableName + " where e_mail=?";
        
        try {
            statement = Optional.of(connection.prepareStatement(query));
            statement.get().setString(1, username);
            final ResultSet result = statement.get().executeQuery();
            
            if (result.next()) {
            	isPresent = true;
            } else {
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

}

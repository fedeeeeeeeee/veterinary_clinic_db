package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
    public Connection getMySQLConnection() {
        final String dbName  = "clinicaveterinaria";

        final String driver = "com.mysql.cj.jdbc.Driver";
        final String dbUri = "jdbc:mysql://localhost:3306/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final String userName = "root";
        final String password = "password";

        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(dbUri, userName, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

package freres.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ColocManagerDB implements IColocManager {
	private Connection connection;
	
	public ColocManagerDB() {
		try {
			this.connection = this.getConnection();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	  Connection connection =  null;
	  String url = "jdbc:mysql://localhost:3306/coloceasy"; 
	  Class.forName("com.mysql.jdbc.Driver").newInstance();
	  connection = DriverManager.getConnection(url,"root","root");   
	  return connection;
	}

	@Override
	public boolean createColoc(Integer id, Integer district, Integer surface, Integer capacity, Integer rooms, String titre, String description, Float rent, Boolean isEnabled) {
		PreparedStatement stmt = null;
		int result = 0;
		try {
			
			String userSQL = "INSERT INTO coloc(district, surface, capacity, rooms, titre, description, rent, is_enabled) VALUES(?, ?, ?, ?, ?, ? );";
			stmt = this.connection.prepareStatement(userSQL);
			
			stmt.setInt(1, district);
			stmt.setInt(2, surface);
			stmt.setInt(3, capacity);
			stmt.setInt(4, rooms);
			stmt.setString(5, titre);
			stmt.setString(6, description);
			stmt.setString(7, description);
			stmt.setBoolean(8, isEnabled);
			
			result = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return result == 1;
	}
}

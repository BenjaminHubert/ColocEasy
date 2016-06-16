package freres.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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
	public boolean createColoc(Integer district, Integer surface, Integer capacity, Integer rooms, String titre, String description, Float rent) {
		PreparedStatement stmt = null;
		int result = 0;
		try {
			
			String userSQL = "INSERT INTO coloc(district, surface, capacity, rooms, titre, description, rent, is_enabled) VALUES(?, ?, ?, ?, ?, ?, ?, true );";
			stmt = this.connection.prepareStatement(userSQL);
			
			stmt.setInt(1, district);
			stmt.setInt(2, surface);
			stmt.setInt(3, capacity);
			stmt.setInt(4, rooms);
			stmt.setString(5, titre);
			stmt.setString(6, description);
			stmt.setFloat(7, rent);
			
			result = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return result == 1;
	}

	@Override
	public Coloc getColoc(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coloc> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}

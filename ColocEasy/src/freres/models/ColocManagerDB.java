package freres.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ColocManagerDB implements IColocManager {
	private Connection connection;
	
	private static final String DISTRICT = "district";
	private static final String SURFACE = "surface";
	private static final String CAPACITY = "capacity";
	private static final String ROOMS = "rooms";
	private static final String TITLE = "title";
	private static final String DESCRIPTION = "description";
	private static final String RENT = "rent";
	private static final String ENABLED = "isEnabled";
	
	public ColocManagerDB() {
		try {
			this.connection = this.getConnection();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	  String url = "jdbc:mysql://localhost:3306/coloceasy"; 
	  Class.forName("com.mysql.jdbc.Driver").newInstance();
	  return DriverManager.getConnection(url,"root","root");
	}

	@Override
	public boolean createColoc(Integer district, Integer surface, Integer capacity, Integer rooms, String titre, String description, Float rent) {
		PreparedStatement stmt = null;
		int result = 0;
		try {
			
			String userSQL = "INSERT INTO coloc(district, surface, capacity, rooms, title, description, rent, is_enabled) VALUES(?, ?, ?, ?, ?, ?, ?, true );";
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
		Coloc coloc = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			String colocSql = "SELECT * FROM coloc WHERE id = ?";
			stmt = this.connection.prepareStatement(colocSql);
			
			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				Integer capacity = rs.getInt(ColocManagerDB.CAPACITY);
				Integer district = rs.getInt(ColocManagerDB.DISTRICT);
				Integer surface = rs.getInt(ColocManagerDB.SURFACE);
				Integer rooms = rs.getInt(ColocManagerDB.ROOMS);
				Integer rent = rs.getInt(ColocManagerDB.RENT);
				String title = rs.getString(ColocManagerDB.TITLE);
				String description = rs.getString(ColocManagerDB.DESCRIPTION);
				Integer isEnabled = rs.getInt(ColocManagerDB.ENABLED);
				coloc = new Coloc(id, district, surface, capacity, rooms, title, description, rent, isEnabled);
			}
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return coloc;
	}

//	@Override
//	public List<Coloc> getAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}

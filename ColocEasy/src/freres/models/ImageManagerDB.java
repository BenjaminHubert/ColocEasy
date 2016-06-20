package freres.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImageManagerDB implements IImageManager{
	private Connection connection;
	
	public ImageManagerDB() {
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
	public boolean createImage(String path, Integer idColoc) {
		PreparedStatement stmt = null;
		int result = 0;
		try {
			
			String userSQL = "INSERT INTO picture (path, id_coloc) VALUES(?, ?);";
			stmt = this.connection.prepareStatement(userSQL);
			
			stmt.setString(1, path);
			stmt.setInt(2, idColoc);
			
			result = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return result == 1;
	}
}

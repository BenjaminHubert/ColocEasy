package freres.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	  String url = "jdbc:mysql://localhost:3306/coloceasy?useUnicode=true&characterEncoding=utf-8"; 
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

	public boolean deleteImage(Integer idImage) {
		PreparedStatement stmt = null;
		int result = 0;
		try {
			String userSQL = "DELETE FROM picture WHERE id = ?";
			stmt = this.connection.prepareStatement(userSQL);
			
			stmt.setInt(1, idImage);
			
			result = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return result == 1;
	}
	
	@Override
	public List<Image> getColocImages(Integer idColoc) {
		List<Image> imageList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String listSQL = "SELECT * FROM picture WHERE id_coloc = ?";
			stmt = this.connection.prepareStatement(listSQL);
			
			stmt.setInt(1, idColoc);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				Integer id = rs.getInt("id");
				String path = rs.getString("path");
				Integer idC = rs.getInt("id_coloc");
				Image image = new Image(id, path, idC);
				imageList.add(image);
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return imageList;
	}

	public Image getImage(Integer idColoc) {
		Image img = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM picture WHERE id_coloc = ? AND id = (SELECT MIN(id) FROM picture WHERE id_coloc = ?)";
			stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, idColoc);
			stmt.setInt(2, idColoc);
			rs = stmt.executeQuery();
			
			Integer id = rs.getInt("id");
			String path = rs.getString("path");
			Integer idC = rs.getInt("id_coloc");
			img = new Image(id, path, idC);
			
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return img;
	}

	@Override
	public Image getPreview(Integer idColoc) {
		Image image = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			String sql = "SELECT * FROM picture WHERE id_coloc = ? LIMIT 1";
			stmt = this.connection.prepareStatement(sql);
			
			stmt.setInt(1, idColoc);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				Integer id = rs.getInt("id");
				String path = rs.getString("path");
				image = new Image(id, path, idColoc);
			}
			
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return image;
	}
}

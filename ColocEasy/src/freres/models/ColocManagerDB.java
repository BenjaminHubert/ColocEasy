package freres.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ColocManagerDB implements IColocManager {
	private Connection connection;

	private static final String ID = "id";
	private static final String DISTRICT = "district";
	private static final String SURFACE = "surface";
	private static final String CAPACITY = "capacity";
	private static final String ROOMS = "rooms";
	private static final String TITLE = "title";
	private static final String DESCRIPTION = "description";
	private static final String RENT = "rent";
	private static final String ENABLED = "isEnabled";
	private static final String OWNER = "idOwner";
	
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
	public Integer createColoc(Integer district, Integer surface, Integer capacity, Integer rooms, String titre, String description, Float rent, Integer idOwner) {
		PreparedStatement stmt = null;
		int result = 0;
		try {
			
			String userSQL = "INSERT INTO coloc(district, surface, capacity, rooms, title, description, rent, isEnabled, idOwner) VALUES(?, ?, ?, ?, ?, ?, ?, true, ? );";
			stmt = this.connection.prepareStatement(userSQL, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setInt(1, district);
			stmt.setInt(2, surface);
			stmt.setInt(3, capacity);
			stmt.setInt(4, rooms);
			stmt.setString(5, titre);
			stmt.setString(6, description);
			stmt.setFloat(7, rent);
			stmt.setInt(8, idOwner);
			
			result = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return result;
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
				Integer idOwner = rs.getInt(ColocManagerDB.OWNER);
				coloc = new Coloc(id, district, surface, capacity, rooms, title, description, rent, isEnabled, idOwner);
			}
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return coloc;
	}

	@Override
	public List<Coloc> getLast() {
		List<Coloc> colocList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = this.connection.createStatement();
			String listSQL = "SELECT * FROM coloc WHERE isEnabled = 1 ORDER BY id DESC LIMIT 3";
			rs = stmt.executeQuery(listSQL);
			while(rs.next()){
				Integer id = rs.getInt(ColocManagerDB.ID);
				Integer capacity = rs.getInt(ColocManagerDB.CAPACITY);
				Integer district = rs.getInt(ColocManagerDB.DISTRICT);
				Integer surface = rs.getInt(ColocManagerDB.SURFACE);
				Integer rooms = rs.getInt(ColocManagerDB.ROOMS);
				Integer rent = rs.getInt(ColocManagerDB.RENT);
				String title = rs.getString(ColocManagerDB.TITLE);
				String description = rs.getString(ColocManagerDB.DESCRIPTION);
				Integer idOwner = rs.getInt(ColocManagerDB.OWNER);
				Coloc coloc = new Coloc(id, district, surface, capacity, rooms, title, description, rent, 1, idOwner);
				colocList.add(coloc);
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return colocList;
	}

	@Override
	public List<Coloc> getMine(Integer owner){
		List<Coloc> colocList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			String listSQL = "SELECT * FROM coloc WHERE idOwner = ?";
			stmt = this.connection.prepareStatement(listSQL);
			
			stmt.setInt(1, owner);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				Integer id = rs.getInt(ColocManagerDB.ID);
				Integer capacity = rs.getInt(ColocManagerDB.CAPACITY);
				Integer district = rs.getInt(ColocManagerDB.DISTRICT);
				Integer surface = rs.getInt(ColocManagerDB.SURFACE);
				Integer rooms = rs.getInt(ColocManagerDB.ROOMS);
				Integer rent = rs.getInt(ColocManagerDB.RENT);
				String title = rs.getString(ColocManagerDB.TITLE);
				String description = rs.getString(ColocManagerDB.DESCRIPTION);
				Integer idOwner = rs.getInt(ColocManagerDB.OWNER);
				Coloc coloc = new Coloc(id, district, surface, capacity, rooms, title, description, rent, 1, idOwner);
				colocList.add(coloc);
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return colocList;
	}
	
	@Override
	public List<Coloc> filterColocs(String[] districts, Integer minRent, Integer maxRent, Integer minSurface, Integer maxSurface){
		List<Coloc> colocList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM coloc WHERE isEnabled = 1";
			
			int index = 0;
			if(districts != null){
				for(String district : districts){
					if(index == 0){
						sql += " AND ";
					} else {
						sql += " OR ";
					}
					//sql += "district = "+district;
					sql += "district = ?";
					index++;
				}
			}
			if(minRent != null) {
				//sql += " AND rent >= "+minRent;
				sql += " AND rent >= ?";
				index++;
			}
			if(maxRent != null) {
				//sql += " AND rent <= "+maxRent;
				sql += " AND rent <= ?";
				index++;
			}
			if(minSurface != null) {
				//sql += " AND surface >= "+minSurface;
				sql += " AND surface >= ?";
				index++;
			}
			if(maxSurface != null) {
				//sql += " AND surface <= "+maxSurface;
				sql += " AND surface <= ?";
				index++;
			}
			
			stmt = this.connection.prepareStatement(sql);
			int i = 1;
			if(districts != null){
				for(String district : districts){
					stmt.setInt(i++, Integer.parseInt(district));
				}
			}
			if(minRent != null) {
				stmt.setInt(i++, minRent);
			}
			if(maxRent != null) {
				stmt.setInt(i++, maxRent);
			}
			if(minSurface != null) {
				stmt.setInt(i++, minSurface);
			}
			if(maxSurface != null) {
				stmt.setInt(i++, maxSurface);
			}
			
			rs = stmt.executeQuery();
			while(rs.next()){
				Integer id = rs.getInt(ColocManagerDB.ID);
				Integer capacity = rs.getInt(ColocManagerDB.CAPACITY);
				Integer district = rs.getInt(ColocManagerDB.DISTRICT);
				Integer surface = rs.getInt(ColocManagerDB.SURFACE);
				Integer rooms = rs.getInt(ColocManagerDB.ROOMS);
				Integer rent = rs.getInt(ColocManagerDB.RENT);
				String title = rs.getString(ColocManagerDB.TITLE);
				String description = rs.getString(ColocManagerDB.DESCRIPTION);
				Integer idOwner = rs.getInt(ColocManagerDB.OWNER);
				Coloc coloc = new Coloc(id, district, surface, capacity, rooms, title, description, rent, 1, idOwner);
				colocList.add(coloc);
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return colocList;
	}
	
	@Override
	public boolean editColoc(Integer id, Integer district, Integer surface, Integer capacity, Integer rooms, String titre, String description, Integer rent, Integer enabled) {
		PreparedStatement stmt = null;
		int result = 0;
		
		try {
			String sql = "UPDATE coloc SET surface=?, capacity=?, rooms=?, title=?, description=?, rent=?, district=?, isEnabled=? WHERE id = ?";
			stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, surface);
			stmt.setInt(2, capacity);
			stmt.setInt(3, rooms);
			stmt.setString(4, titre);
			stmt.setString(5, description);
			stmt.setInt(6, rent);
			stmt.setInt(7, district);
			stmt.setInt(8, enabled);
			stmt.setInt(9, id);
			
			result = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result == 1;
	}
}

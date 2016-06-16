package freres.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserManagerDB implements IUserManager {
	private Connection connection;

	private static final String LOGIN = "login"; 
	private static final String PWD = "password"; 
	private static final String LASTNAME = "last_name"; 
	private static final String FIRSTNAME = "first_name"; 
	private static final String BDAY = "birth_date";
	
	public UserManagerDB() {
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
	public boolean checkLogin(String login) {
		User user = this.getUser(login);
		return user != null;
	}

	@Override
	public boolean checkLoginWithPassword(String login, String password) {
		User user = this.getUser(login);
		
		if (user != null) {
			return user.getPassword().equals(password);
		}
		return false;
	}

	@Override
	public boolean createUser(String login, String password, String lastName, String firstName, String birthDate, String sexe) {
		PreparedStatement stmt = null;
		int result = 0;
		try {
			
			String userSQL = "INSERT INTO utilisateur(login, password, last_name, first_name, birth_date, sexe) VALUES(?, ?, ?, ?, ?, ? );";
			stmt = this.connection.prepareStatement(userSQL);
			
			stmt.setString(1, login);
			stmt.setString(2, password);
			stmt.setString(3, lastName);
			stmt.setString(4, firstName);
			stmt.setString(5, birthDate);
			stmt.setString(6, sexe);
			
			result = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return result == 1;
	}

	@Override
	public User getUser(String login) {
		User user = null; 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			
			String userSQL = "SELECT * FROM utilisateur WHERE login = ?";
			stmt = this.connection.prepareStatement(userSQL);
			stmt.setString(1, login);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				Integer id = rs.getInt("id");
				String loginU = rs.getString(UserManagerDB.LOGIN);
				String password = rs.getString(UserManagerDB.PWD);
				String lastName = rs.getString(UserManagerDB.LASTNAME);
				String firstName = rs.getString(UserManagerDB.FIRSTNAME);
				String birthDate = rs.getString(UserManagerDB.BDAY);
				Integer sexe = rs.getInt("sexe");
				user = new User(id, loginU, password, lastName, firstName, birthDate, sexe);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return user;
	}

	@Override
	public List<User> allUsers() {
		List<User> userList = new ArrayList<>(); 
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = this.connection.createStatement();
			String userSQL = "SELECT * FROM utilisateur";
			rs = stmt.executeQuery(userSQL);
			while(rs.next()){
				Integer id = rs.getInt("id");
				String loginU = rs.getString(UserManagerDB.LOGIN);
				String password = rs.getString(UserManagerDB.PWD);
				String lastName = rs.getString(UserManagerDB.LASTNAME);
				String firstName = rs.getString(UserManagerDB.FIRSTNAME);
				String birthDate = rs.getString(UserManagerDB.BDAY);
				Integer sexe = rs.getInt("sexe");
				User newUser = new User(id, loginU, password, lastName, firstName, birthDate, sexe);
				userList.add(newUser);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return userList;
	}
	
	public Boolean deleteUser(String login) {
		PreparedStatement stmt = null;
		int resultat = 0;
		try {
			String deleteUserSQL = "DELETE FROM utilisateur WHERE login = ?";
			stmt = this.connection.prepareStatement(deleteUserSQL);
			stmt.setString(1, login);
			
			resultat = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return resultat == 1;
	}
	
	@Override
	protected void finalize() throws Throwable {
		this.connection.close();
		super.finalize();
	}

	@Override
	public boolean editUser(String id, String login, String password, String lastName, String firstName, String birthDate, String sexe) {
		PreparedStatement stmt = null;
		int result = 0;
		try {
			
			String userSQL = "UPDATE utilisateur SET login=?, password=?, last_name=?, first_name=?, birth_date=?, sexe=? WHERE id = ?;";
			stmt = this.connection.prepareStatement(userSQL);
			
			stmt.setString(1, login);
			stmt.setString(2, password);
			stmt.setString(3, lastName);
			stmt.setString(4, firstName);
			stmt.setString(5, birthDate);
			stmt.setString(6, sexe);
			stmt.setString(7, id);
			
			result = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return result == 1;
	}

	@Override
	public User getUser(Integer id) {
		User user = null; 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			
			String userSQL = "SELECT * FROM utilisateur WHERE id = ?";
			stmt = this.connection.prepareStatement(userSQL);
			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				Integer idU = rs.getInt("id");
				String loginU = rs.getString(UserManagerDB.LOGIN);
				String password = rs.getString(UserManagerDB.PWD);
				String lastName = rs.getString(UserManagerDB.LASTNAME);
				String firstName = rs.getString(UserManagerDB.FIRSTNAME);
				String birthDate = rs.getString(UserManagerDB.BDAY);
				Integer sexe = rs.getInt("sexe");
				user = new User(idU, loginU, password, lastName, firstName, birthDate, sexe);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return user;
	}
}

package freres.models;

import java.util.List;

public interface IUserManager {
	public boolean checkLogin(String login);
	public boolean checkLoginWithPassword(String login, String password);
	public boolean createUser(String login, String password, String lastName, String firstName, String birthDate, String sexe);
	public boolean editUser(String id, String login, String password, String lastName, String firstName, String birthDate, String sexe);
	public User getUser(String login);
	public User getUser(Integer id);
	public List<User> allUsers();
}

package freres.models;

import java.util.List;

public interface IUserManager {
	public boolean checkLogin(String login);
	public boolean checkLoginWithPassword(String login, String password);
	public boolean createUser(String login, String password, String last_name, String first_name, String birth_date, String sexe);
	public User getUser(String login);
	public List<User> allUsers();
}

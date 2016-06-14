package freres.models;

public class User {
	private Integer id;
	private String login;
	private String password;
	private String lastName;
	private String firstName;
	private String birth_date;
	private Integer sexe;
	
	public User(Integer id, String login, String password, String last_name, String first_name, String birth_date, Integer sexe) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.lastName = last_name;
		this.firstName = first_name;
		this.birth_date = birth_date;
		this.sexe = sexe;
	}

	public User(){
		this.id = null;
		this.login = null;
		this.password = null;
		this.lastName = null;
		this.firstName = null;
		this.birth_date = null;
		this.sexe = null;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

	public Integer getSexe(){
		return sexe;
	}
	
	public void setSexe(Integer sexe) {
		this.sexe = sexe;
	}

	@Override
	public String toString() {
		return getLogin();
	}
}

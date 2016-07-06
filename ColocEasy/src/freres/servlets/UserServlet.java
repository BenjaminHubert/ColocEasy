package freres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freres.models.IUserManager;
import freres.models.User;
import freres.models.UserManagerDB;
import freres.others.MD5;

@WebServlet(
		name = "user-servlet", 
		description = "Servlet handling user login", 
		urlPatterns = { "/login", "/logout" , "/signup", "/profile", "/confirmUser" /*, "/userList"*/ })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_SESSION = "userSession";

	private static final String LOGIN = "login"; 
	private static final String PWD = "password"; 
	private static final String NEWPWD = "password_new"; 
	private static final String NOMATCH = "Les mots de passe ne correspondent pas."; 
	private static final String ERRORMESSAGE = "errorMessage"; 
	private static final String ACTION = "action"; 
	private static final String SUCCESS = "success"; 
	
	private IUserManager userManager = new UserManagerDB();

	public UserServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String uri = request.getRequestURI();
		if (uri.contains("/login")) {
			this.login(request, response);
		} else if (uri.contains("/logout")) {
			this.logout(request, response);
		} else if (uri.contains("/signup")) {
			this.create(request, response);
		} else if(uri.contains("/profile")) {
			this.profile(request, response);
		} else if (uri.contains("/confirmUser")) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/html/confirmUser.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final String login = request.getParameter(UserServlet.LOGIN);
		final String password = request.getParameter(UserServlet.PWD) != null ? MD5.getMD5(request.getParameter(UserServlet.PWD)) : null;

		if (login == null || password == null) {
		} else if (this.userManager.checkLoginWithPassword(login, password)) {
				request.getSession().setAttribute(UserServlet.USER_SESSION, this.userManager.getUser(login));
				response.sendRedirect("index");
				return;
			}  else {
			request.setAttribute(UserServlet.ERRORMESSAGE, "Login ou mot de passe incorrect");
		}
		request.setAttribute(UserServlet.ACTION, UserServlet.LOGIN);
		request.getRequestDispatcher("/WEB-INF/html/login.jsp").forward(request, response);
	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		 final String login = request.getParameter(UserServlet.LOGIN);
		 final String password = request.getParameter(UserServlet.PWD) != null ? MD5.getMD5(request.getParameter(UserServlet.PWD)) : null;
		 final String confirm = request.getParameter("confirm") != null ? MD5.getMD5(request.getParameter("confirm")) : null;
		 final String lastName = request.getParameter("last_name");
		 final String firstName = request.getParameter("first_name");
		 final String birthDate = request.getParameter("birth_date_submit");
		 final String sexe = request.getParameter("sexe");
		
		 if (login != null && password != null) {
			 if (this.userManager.checkLogin(login)) {
				 request.setAttribute(UserServlet.ERRORMESSAGE, "Cet adresse mail est déjà utilisée.");
				 request.getRequestDispatcher("/WEB-INF/html/signup.jsp").forward(request, response);
				 return;
			 } else {
				 if(password.equals(confirm)){
					 this.userManager.createUser(login, password, lastName, firstName, birthDate, sexe);
					 request.setAttribute(UserServlet.SUCCESS, "User succesfully created");
					 response.sendRedirect("confirmUser");
					 return;
				 } else { 
					 request.setAttribute(UserServlet.ERRORMESSAGE, UserServlet.NOMATCH);
					 request.getRequestDispatcher("/WEB-INF/html/signup.jsp").forward(request, response);
					 return;
				 }
			 }
		 }
		 request.setAttribute(UserServlet.ACTION, "create");
		 request.getRequestDispatcher("/WEB-INF/html/signup.jsp").forward(request, response);
	 }
	 
	private void profile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { 
		if(request.getSession().getAttribute(UserServlet.USER_SESSION) != null) {
			User u = this.userManager.getUser(request.getSession().getAttribute(UserServlet.USER_SESSION).toString());
			request.setAttribute("user", u);

			final String birthDate = request.getParameter("birthday_submit");
			final String login = request.getParameter("email");
			final String firstName = request.getParameter("first_name");
			final String lastName = request.getParameter("last_name");
			final String id = request.getParameter("id");
			final String sexe = request.getParameter("sexe");
			final String password = request.getParameter(UserServlet.PWD) != null ? MD5.getMD5(request.getParameter(UserServlet.PWD)) : request.getParameter(UserServlet.PWD);
			final String confirm = request.getParameter("password_confirmation") != null ? MD5.getMD5(request.getParameter(UserServlet.NEWPWD)) : null;
			final String newPass = request.getParameter(UserServlet.NEWPWD) != null ? MD5.getMD5(request.getParameter(UserServlet.NEWPWD)) : null;
			
			if(id != null) {
				if(newPass != null && newPass.equals(confirm) && password.equals(((User)request.getSession().getAttribute(UserServlet.USER_SESSION)).getPassword())){
					if(this.userManager.editUser(id, login, newPass, lastName, firstName, birthDate, sexe)){
						request.getSession().setAttribute(UserServlet.USER_SESSION, this.userManager.getUser(Integer.parseInt(id)));
						request.setAttribute(UserServlet.SUCCESS, "The user "+login+" has been updated.");
					}
				} else {
					 request.setAttribute(UserServlet.ERRORMESSAGE, UserServlet.NOMATCH);
				}
			}
		}
		request.setAttribute(UserServlet.ACTION, "profile");
		request.getRequestDispatcher("/WEB-INF/html/profile.jsp").forward(request, response);
	}
	 
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getSession().removeAttribute(UserServlet.USER_SESSION);
		response.sendRedirect("index");
	}
}

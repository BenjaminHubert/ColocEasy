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

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(
		name = "user-servlet", 
		description = "Servlet handling user login", 
		urlPatterns = { "/login", "/logout" , "/signup"/*, "/list", "/home" */ })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_SESSION = "userSession";

	private IUserManager userManager = new UserManagerDB();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String uri = request.getRequestURI();
		if (uri.contains("/login")) {
			this.login(request, response);
		} else if (uri.contains("/logout")) {
			this.logout(request, response);
		} else if (uri.contains("/signup")) {
			this.create(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final String login = request.getParameter("login");
		final String password = request.getParameter("password");

		if (login == null || password == null) {
			System.out.println("Navigating to login.");
		} else if (this.userManager.checkLogin(login)) {
			if (this.userManager.checkLoginWithPassword(login, password)) {
				request.getSession().setAttribute(this.USER_SESSION, this.userManager.getUser(login));
				System.out.println(request.getSession().getAttribute(this.USER_SESSION) + " logging in, redirecting to index.");
				response.sendRedirect("index");
				return;
			} else {
				request.setAttribute("errorMessage", "Bad password");
				System.out.println("Bad pwd");
			}
		} else {
			request.setAttribute("errorMessage", "User not found");
			System.out.println("User not found");
		}
		request.setAttribute("action", "login");
		request.getRequestDispatcher("/WEB-INF/html/login.jsp").forward(request, response);
	}

	 private void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		 final String login = request.getParameter("login");
		 final String password = request.getParameter("password");
		 final String confirm = request.getParameter("confirm");
		 final String last_name = request.getParameter("last_name");
		 final String first_name = request.getParameter("first_name");
		 final String birth_date = request.getParameter("birth_date_submit");
		 final String sexe = request.getParameter("sexe");
		
		 if (login != null && password != null) {
			 if(password.equals(confirm)){
				 if (this.userManager.checkLogin(login)) {
					 request.setAttribute("errorMessage", "User already exists. Please chose another");
					 System.out.println(request.getAttribute("errorMessage"));
				 } else {
					 this.userManager.createUser(login, password, last_name, first_name, birth_date, sexe);
					 request.setAttribute("success", "User succesfully created");
					 System.out.println(request.getAttribute("success"));
					 response.sendRedirect("confirm");
					 return;
				 }
			 }System.out.println("The passwords do not match.");
		 }
		 request.setAttribute("action", "create");
		 request.getRequestDispatcher("/WEB-INF/html/signup.jsp").forward(request, response);
	 }
	//
	// private void list(HttpServletRequest request, HttpServletResponse
	// response) throws IOException, ServletException {
	// String pageType = request.getParameter("output");
	//
	// if (pageType == null || !pageType.equals("json")) {
	// pageType = "html";
	// }
	// request.setAttribute("title", "List all users");
	// request.setAttribute("userList", this.userManager.allUsers());
	// request.getRequestDispatcher("/WEB-INF/html/userList.jsp").forward(request,
	// response);
	// }
	//
	// private void home(HttpServletRequest request, HttpServletResponse
	// response) throws IOException, ServletException {
	// User user = (User)
	// request.getSession().getAttribute(UserServlet.USER_SESSION);
	// if (user == null) {
	// response.sendRedirect("login");
	// return;
	// }
	// request.setAttribute("login", user.getLogin());
	// request.getRequestDispatcher("/WEB-INF/html/home.jsp").forward(request,
	// response);
	// }
	//
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println(request.getSession().getAttribute(USER_SESSION)+" logging out, redirecting to index");
		request.getSession().removeAttribute(this.USER_SESSION);
		response.sendRedirect("index");
	}

}

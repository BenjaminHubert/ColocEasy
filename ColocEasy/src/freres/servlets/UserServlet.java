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
		urlPatterns={"/login"/*, "/create", "/list", "/home", "/logout"*/}
)
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String uri = request.getRequestURI();
		if(uri.contains("/login")) {
			this.login(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final String login = request.getParameter("login");
		final String password = request.getParameter("password");
		
		if (login == null || password == null) {
			System.out.println("Navigating to login.");
		} else if(this.userManager.checkLogin(login)) {
			if (this.userManager.checkLoginWithPassword(login, password)) {				
				request.getSession().setAttribute("userSession", this.userManager.getUser(login));
				System.out.println(request.getSession().getAttribute("userSession")+" logging in, redirecting to index.");
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
	
//	private void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		final String login = request.getParameter("user");
//		final String password = request.getParameter("password");
//		
//		if (login != null && password != null) {
//			if (this.userManager.checkLogin(login)) {
//				request.setAttribute("errorMessage", "User already exists. Please chose another");
//			} else {
//				this.userManager.createUser(login, password);
//				request.setAttribute("success", "User succesfully created");
//			}	
//		}
//		request.setAttribute("action", "create");
//		request.getRequestDispatcher("/WEB-INF/html/userForm.jsp").forward(request, response);
//	}
//	
//	private void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		String pageType = request.getParameter("output");
//		
//		if (pageType == null || !pageType.equals("json")) {
//			pageType = "html";
//		} 
//		request.setAttribute("title", "List all users");
//		request.setAttribute("userList", this.userManager.allUsers());
//		request.getRequestDispatcher("/WEB-INF/html/userList.jsp").forward(request, response);
//	}
//	
//	private void home(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		User user = (User) request.getSession().getAttribute(UserServlet.USER_SESSION);
//		if (user == null) {
//			response.sendRedirect("login");
//			return;
//		}
//		request.setAttribute("login", user.getLogin());
//		request.getRequestDispatcher("/WEB-INF/html/home.jsp").forward(request, response);
//	}
//	
//	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		request.getSession().removeAttribute(UserServlet.USER_SESSION);
//		response.sendRedirect("login");
//	}

}

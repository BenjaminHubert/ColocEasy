package freres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freres.models.ColocManagerDB;
import freres.models.IColocManager;

/**
 * Servlet implementation class Coloc
 */
@WebServlet(
		name = "coloc-servlet", 
		description = "Servlet handling colocs", 
		urlPatterns = { "/coloc", "/addColoc" /*, "/signup", "/profile" /*, "/userList"*/ })
public class ColocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private IColocManager colocManager = new ColocManagerDB();
    
    public ColocServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String uri = request.getRequestURI();
		if (uri.contains("/coloc")) {
			this.coloc(request, response);
		} else if (uri.contains("/addColoc")) {
			this.add(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void coloc(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("/WEB-INF/html/coloc.jsp").forward(request, response);
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("/WEB-INF/html/addColoc.jsp").forward(request, response);
	}
}

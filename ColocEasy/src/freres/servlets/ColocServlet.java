package freres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freres.models.Coloc;
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
		final Integer id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) :null;
		if(id!=null){
			Coloc c = this.colocManager.getColoc(id);
			request.setAttribute("coloc", c);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		request.setAttribute("action", "coloc");
		request.getRequestDispatcher("/WEB-INF/html/coloc.jsp").forward(request, response);
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final Integer capacity = request.getParameter("capacity") != null ? Integer.parseInt(request.getParameter("capacity")) : null;
		final String description = request.getParameter("description");
		final Integer district = request.getParameter("district") != null ? Integer.parseInt(request.getParameter("district")) : null;
		final Float rent = request.getParameter("rent") != null ? Float.parseFloat(request.getParameter("rent")) : null;
		final Integer rooms = request.getParameter("rooms") != null ? Integer.parseInt(request.getParameter("rooms")) : null;
		final Integer surface = request.getParameter("surface") != null ? Integer.parseInt(request.getParameter("surface")) : null;
		final String title = request.getParameter("title");
		
		if(capacity != null && description != null && district != null && rent != null && rooms!= null && surface!= null && title!= null){
			this.colocManager.createColoc(district, surface, capacity, rooms, title, description, rent);
		}
		request.setAttribute("action", "add");
		request.getRequestDispatcher("/WEB-INF/html/addColoc.jsp").forward(request, response);
	}
}

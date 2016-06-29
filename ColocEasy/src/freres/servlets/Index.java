package freres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freres.models.ColocManagerDB;
import freres.models.IColocManager;

@WebServlet(
		name = "index-servlet",
		urlPatterns = {"", "/index", "/Index"}
		)
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IColocManager colocManager = new ColocManagerDB();
	
    public Index() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("lastColocs", this.colocManager.getLast());
		this.getServletContext().getRequestDispatcher("/WEB-INF/html/index.jsp").forward(request, response);
	}

}

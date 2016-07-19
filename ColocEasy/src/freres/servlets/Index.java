package freres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freres.models.Coloc;
import freres.models.ColocManagerDB;
import freres.models.IColocManager;
import freres.models.IImageManager;
import freres.models.Image;
import freres.models.ImageManagerDB;

@WebServlet(
		name = "index-servlet",
		urlPatterns = {"", "/index", "/Index"}
		)
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IColocManager colocManager = new ColocManagerDB();

	private IImageManager imageManager = new ImageManagerDB();
	
    public Index() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Image> iList = new ArrayList<>();
		List<Coloc> cList = this.colocManager.getLast();
		for (Coloc coloc : cList) {
			Image i = this.imageManager.getPreview(coloc.getId());
			iList.add(i);
		}
		request.setAttribute("lastColocs", cList);
		request.setAttribute("colocImages", iList);
		this.getServletContext().getRequestDispatcher("/WEB-INF/html/index.jsp").forward(request, response);
	}

}

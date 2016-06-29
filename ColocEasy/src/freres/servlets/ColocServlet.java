package freres.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import freres.models.Coloc;
import freres.models.ColocManagerDB;
import freres.models.IColocManager;
import freres.models.IImageManager;
import freres.models.ImageManagerDB;
import freres.models.User;

@WebServlet(
		name = "coloc-servlet", 
		description = "Servlet handling colocs", 
		urlPatterns = { "/coloc", "/addColoc", "/editColoc", "/myColocs", "/confirmColoc"})
//UPLOAD
@MultipartConfig(location="D:/ColocEasy/tmp",
				 fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB
//UPLOAD
public class ColocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String SAVE_DIR = "D:/ColocEasy";
    
	private IColocManager colocManager = new ColocManagerDB();
    private IImageManager imageManager = new ImageManagerDB();
	
    public ColocServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String uri = request.getRequestURI();
		if (uri.contains("/coloc")) {
			this.coloc(request, response);
		} else if (uri.contains("/addColoc")) {
			this.add(request, response);
		} else if  (uri.contains("/editColoc")) {
			this.edit(request, response);
		} else if(uri.contains("/myColocs")) {
			this.mine(request, response);
		} else if(uri.contains("/confirmColoc")) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/html/confirmColoc.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void coloc(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final Integer id = request.getParameter("id") != "" ? Integer.parseInt(request.getParameter("id")) : null;
		if(id!=null){
			Coloc c = this.colocManager.getColoc(id);
			if(c != null){
				request.setAttribute("coloc", c);
				request.setAttribute("imageList", this.imageManager.getColocImages(c.getId()));
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
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
		final Integer idOwner = request.getSession().getAttribute("userSession") != null  ? ((User)request.getSession().getAttribute("userSession")).getId() : null;
		//DÉBUT GESTION UPLOAD
		ArrayList<String> imageList = new ArrayList<String>();
		
		if(request.getMethod().equals("POST")){
	        
	        File uploads = new File(ColocServlet.SAVE_DIR); 
	        for (Part part : request.getParts()) { 
	            String fileName = createFileName(part, idOwner);
	            if(!fileName.isEmpty()){
	            	imageList.add(fileName);
	            }
	            File f = new File(fileName);
	            File file = new File(uploads, f.getName());
	            try(InputStream input = part.getInputStream()){
	            	Files.copy(input, file.toPath());
	            } catch (Exception e) {

	            }
	        }
		}
		//FIN GESTION UPLOAD
		if(capacity != null && description != null && district != null && rent != null && rooms!= null && surface!= null && title!= null){
			int idC = this.colocManager.createColoc(district, surface, capacity, rooms, title, description, rent, idOwner);
			//INSERT IMAGE
			for(String path : imageList){
				this.imageManager.createImage(path, idC);
			}
			response.sendRedirect("confirmColoc");
			return;
		}
		request.setAttribute("action", "add");
		request.getRequestDispatcher("/WEB-INF/html/addColoc.jsp").forward(request, response);
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Integer capacity = request.getParameter("capacity") != null ? Integer.parseInt(request.getParameter("capacity")) : null;
		final String description = request.getParameter("description");
		final Integer district = request.getParameter("district") != null ? Integer.parseInt(request.getParameter("district")) : null;
		final Float rent = request.getParameter("rent") != null ? Float.parseFloat(request.getParameter("rent")) : null;
		final Integer rooms = request.getParameter("rooms") != null ? Integer.parseInt(request.getParameter("rooms")) : null;
		final Integer surface = request.getParameter("surface") != null ? Integer.parseInt(request.getParameter("surface")) : null;
		final String title = request.getParameter("title");
		final Integer idOwner = request.getSession().getAttribute("userSession") != null  ? ((User)request.getSession().getAttribute("userSession")).getId() : null;
		
		//Créer une coloc à partir de l'id récupéré en GET pour remplir les champs de la vue
		if(request.getMethod().equals("POST")){
			//Faire un input caché dans la vue qui contient l'id coloc
			//Vérifier que la coloc appartient à l'utilisateur
			//Faire l'édition
		}
		request.setAttribute("action", "edit");
		request.getRequestDispatcher("/WEB-INF/html/editColoc.jsp").forward(request, response);
		
	}
	
	private void mine(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setAttribute("action", "mine");
		request.getRequestDispatcher("/WEB-INF/html/myColocs.jsp").forward(request, response);
	}
	
	//UPLOAD
	private String createFileName(Part part, Integer idOwner) {
	    String contentDisp = part.getHeader("content-disposition");
	    String[] items = contentDisp.split(";");
	    for (String s : items) {
	        if (s.trim().startsWith("filename")) {
	        	int i = s.lastIndexOf('.');
	        	if(i>0){
	        		String extension = s.substring(i+1, s.length()-1);
		        	if(extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("gif")){
		        		return java.util.UUID.randomUUID().toString()+idOwner+"."+extension;
		        	} 
	        	}
	        }
	    }
	    return "";
	}
}

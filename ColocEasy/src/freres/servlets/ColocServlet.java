package freres.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

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
		urlPatterns = { "/coloc", "/list", "/addColoc", "/editColoc", "/myColocs", "/confirmColoc", "/confirmEditColoc", "/deleteImage"})
//UPLOAD
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB
//UPLOAD
public class ColocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String SAVE_DIR = "upload_img";
	private static final String ERRORMESSAGE = "errorMessage";
    
	private IColocManager colocManager = new ColocManagerDB();
    private IImageManager imageManager = new ImageManagerDB();
	
    public ColocServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String uri = request.getRequestURI();
		if (uri.contains("/coloc")) {
			this.coloc(request, response);
		} else if(uri.contains("/list")) {
			this.list(request, response);
		} else if (uri.contains("/addColoc")) {
			this.add(request, response);
		} else if  (uri.contains("/editColoc")) {
			this.edit(request, response);
		} else if(uri.contains("/myColocs")) {
			this.mine(request, response);
		} else if(uri.contains("/deleteImage")) {
			this.deleteImage(request, response);
		} else if(uri.contains("/confirmColoc")) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/html/confirmColoc.jsp").forward(request, response);
		} else if(uri.contains("/confirmEditColoc")) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/html/confirmEditColoc.jsp").forward(request, response);
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
		if(request.getSession().getAttribute("userSession") != null){
			final Integer capacity = request.getParameter("capacity") != null ? Integer.parseInt(request.getParameter("capacity")) : null;
			final String description = request.getParameter("description");
			final Integer district = request.getParameter("district") != null ? Integer.parseInt(request.getParameter("district")) : null;
			final Float rent = request.getParameter("rent") != null ? Float.parseFloat(request.getParameter("rent")) : null;
			final Integer rooms = request.getParameter("rooms") != null ? Integer.parseInt(request.getParameter("rooms")) : null;
			final Integer surface = request.getParameter("surface") != null ? Integer.parseInt(request.getParameter("surface")) : null;
			final String title = request.getParameter("title");
			final Integer idOwner = request.getSession().getAttribute("userSession") != null  ? ((User)request.getSession().getAttribute("userSession")).getId() : null;
			
			ArrayList<String> imageList = new ArrayList<String>();

			if(request.getMethod().equals("POST")){
				//TODO: Vérifier l'intégrité des champs
				String[] supportedContentTypes = { "image/jpeg", "image/png"};
		        String appPath = request.getServletContext().getRealPath("");
		        String savePath = appPath + File.separator + SAVE_DIR;
		        
		        File fileSaveDir = new File(savePath);
		        if(!fileSaveDir.exists()){
		        	fileSaveDir.mkdir();
		        }
		        for (Part part : request.getParts()) { 
		        	String fileName = createFileName(part, idOwner);
		            String contentType = part.getContentType();
		            
		            if(fileName == null || fileName.isEmpty()) continue;
		            if(contentType == null || contentType.isEmpty()) continue;
		            if(!Arrays.asList(supportedContentTypes).contains(contentType)) continue;
		            
		            part.write(savePath + File.separator + fileName);
		            imageList.add(fileName);
		        }
			}
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
		}
		
		request.getRequestDispatcher("/WEB-INF/html/addColoc.jsp").forward(request, response);
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getSession().getAttribute("userSession") != null){
			final Integer id = Integer.parseInt(request.getParameter("id"));
			Coloc c = this.colocManager.getColoc(id);
			request.setAttribute("coloc", c);
			
			final Integer capacity = request.getParameter("capacity") != null ? Integer.parseInt(request.getParameter("capacity")) : null;
			final String description = request.getParameter("description");
			final Integer district = request.getParameter("district") != null ? Integer.parseInt(request.getParameter("district")) : null;
			final Integer enabled = request.getParameter("enabled") != null && request.getParameter("enabled").equals("on") ? 1 : 0;
			final Integer rent = request.getParameter("rent") != null ? Integer.parseInt(request.getParameter("rent")) : null;
			final Integer rooms = request.getParameter("rooms") != null ? Integer.parseInt(request.getParameter("rooms")) : null;
			final Integer surface = request.getParameter("surface") != null ? Integer.parseInt(request.getParameter("surface")) : null;
			final String title = request.getParameter("title");
			final Integer idOwner = request.getSession().getAttribute("userSession") != null  ? ((User)request.getSession().getAttribute("userSession")).getId() : null;

			if(idOwner == c.getIdOwner()){
				request.setAttribute("imageList", this.imageManager.getColocImages(c.getId()));
			} else {
				request.setAttribute("forbidden", 1);
			}
			
			if(request.getMethod().equals("POST")){
				//TODO: Vérifier l'intégrité des champs

				ArrayList<String> imageList = new ArrayList<String>();
				String[] supportedContentTypes = { "image/jpeg", "image/png"};
		        String appPath = request.getServletContext().getRealPath("");
		        String savePath = appPath + File.separator + SAVE_DIR;
		        
		        File fileSaveDir = new File(savePath);
		        if(!fileSaveDir.exists()){
		        	fileSaveDir.mkdir();
		        }
		        for (Part part : request.getParts()) { 
		        	String fileName = createFileName(part, idOwner);
		            String contentType = part.getContentType();
		            
		            if(fileName == null || fileName.isEmpty()) continue;
		            if(contentType == null || contentType.isEmpty()) continue;
		            if(!Arrays.asList(supportedContentTypes).contains(contentType)) continue;
		            
		            part.write(savePath + File.separator + fileName);
		            imageList.add(fileName);
		        }
				
				if(capacity != null && description != null && district != null && rent != null && rooms!= null && surface!= null && title!= null && enabled !=null){
					if(this.colocManager.editColoc(id, district, surface, capacity, rooms, title, description, rent, enabled)){
						//INSERT IMAGE
						for(String path : imageList){
							this.imageManager.createImage(path, id);
						}
						response.sendRedirect("confirmEditColoc");
						return;
					} else {
						 request.setAttribute(ColocServlet.ERRORMESSAGE, "Une erreur s'est produite lors de l'édition.");
					}
				}
			}
			request.setAttribute("action", "edit");
		}
		request.getRequestDispatcher("/WEB-INF/html/editColoc.jsp").forward(request, response);
	}
	
	private void deleteImage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		Map<String, String[]> parameters = request.getParameterMap();
		for(String parameter : parameters.keySet()) {
			if(parameter.startsWith("img")) {
				Integer idImage = Integer.parseInt(parameter.substring(3));
				this.imageManager.deleteImage(idImage);
			}
		}
		response.sendRedirect("editColoc");
		return;
	}
	
	private void mine(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final Integer idOwner = request.getSession().getAttribute("userSession") != null  ? ((User)request.getSession().getAttribute("userSession")).getId() : null;
		if(idOwner != null){
			request.setAttribute("colocList", this.colocManager.getMine(idOwner));
		}
		request.getRequestDispatcher("/WEB-INF/html/myColocs.jsp").forward(request, response);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if(request.getSession().getAttribute("userSession") != null){
			String sql = "";

			String [] districts = request.getParameterValues("district");
			int index = 0;
			for(String district : districts){
				if(index == 0){
					sql += " AND ";
				} else {
					sql += " OR ";
				}
				sql += "district = "+district;
				index++;
			}
			
			Integer minRent = request.getParameter("sMinRent") != "" ? Integer.parseInt(request.getParameter("sMinRent")) : null;
			Integer maxRent = request.getParameter("sMaxRent") != "" ? Integer.parseInt(request.getParameter("sMaxRent")) : null;
			Integer minSurface = request.getParameter("sMinSurface") != "" ? Integer.parseInt(request.getParameter("sMinSurface")) : null;
			Integer maxSurface = request.getParameter("sMaxSurface") != "" ? Integer.parseInt(request.getParameter("sMaxSurface")) : null;
			if(minRent != null) {
				sql += " AND rent >= "+minRent;
			}
			if(maxRent != null) {
				sql += " AND rent <= "+maxRent;
			}
			if(minSurface != null) {
				sql += " AND surface >= "+minSurface;
			}
			if(maxSurface != null) {
				sql += " AND surface <= "+maxSurface;
			}
			
			request.setAttribute("colocList", this.colocManager.filterColocs(sql));
		}
		request.getRequestDispatcher("/WEB-INF/html/list.jsp").forward(request, response);		
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

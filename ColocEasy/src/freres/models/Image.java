package freres.models;

public class Image {
	private Integer id;
	private String path;
	private Integer idColoc;
	
	public Image(Integer id, String path, Integer idColoc){
		this.id = id;
		this.path = path;
		this.idColoc = idColoc;
	}
	
	public Image(){
		this.id = null;
		this.path = null;
		this.idColoc = null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getIdColoc() {
		return idColoc;
	}

	public void setIdColoc(Integer idColoc) {
		this.idColoc = idColoc;
	}
}

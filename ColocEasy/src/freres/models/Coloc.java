package freres.models;

public class Coloc {
	private Integer id;
	private Integer district;
	private Integer surface;
	private Integer capacity;
	private Integer rooms;
	private String 	description;
	private Float 	rent;
	private Boolean isEnabled;
	
	public Coloc(Integer id, Integer district, Integer surface, Integer capacity, Integer rooms, String description, Float rent, Boolean isEnabled){
		this.id = id;
		this.district = district;
		this.surface = surface;
		this.capacity = capacity;
		this.rooms = rooms;
		this.description = description;
		this.rent = rent;
		this.isEnabled = isEnabled;
	}

	public Coloc(){
		this.id = null;
		this.district = null;
		this.surface = null;
		this.capacity = null;
		this.rooms = null;
		this.description = null;
		this.rent = null;
		this.isEnabled = null;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDistrict() {
		return district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	public Integer getSurface() {
		return surface;
	}

	public void setSurface(Integer surface) {
		this.surface = surface;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getRooms() {
		return rooms;
	}

	public void setRooms(Integer rooms) {
		this.rooms = rooms;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getRent() {
		return rent;
	}

	public void setRent(Float rent) {
		this.rent = rent;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
}

package freres.models;

public interface IColocManager {
	public boolean createColoc(Integer id, Integer district, Integer surface, Integer capacity, Integer rooms, String titre, String description, Float rent, Boolean isEnabled);
}

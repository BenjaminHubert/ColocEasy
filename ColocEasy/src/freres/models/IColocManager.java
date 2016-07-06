package freres.models;

import java.util.List;

public interface IColocManager {
	public Integer createColoc(Integer district, Integer surface, Integer capacity, Integer rooms, String titre, String description, Float rent, Integer idOwner);
	public boolean editColoc(Integer id, Integer district, Integer surface, Integer capacity, Integer rooms, String titre, String description, Integer rent, Integer enabled);
	public Coloc getColoc(Integer id);
	public List<Coloc> getLast();
	public List<Coloc> filterColocs(String[] districts, Integer minRent, Integer maxRent, Integer minSurface, Integer maxSurface);
	public List<Coloc> getMine(Integer owner);

}

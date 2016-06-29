package freres.models;

import java.util.List;

public interface IColocManager {
	public Integer createColoc(Integer district, Integer surface, Integer capacity, Integer rooms, String titre, String description, Float rent, Integer idOwner);
	public boolean editColoc(Integer id, Integer surface, Integer capacity, Integer rooms, String titre, String description, Integer rent, Integer idOwner);
	public Coloc getColoc(Integer id);
	public List<Coloc> getLast();

}

package freres.models;

import java.util.List;

public interface IColocManager {
	public boolean createColoc(Integer district, Integer surface, Integer capacity, Integer rooms, String titre, String description, Float rent);
//	public Coloc getColoc(Integer id);
//	public List<Coloc> getAll();
}

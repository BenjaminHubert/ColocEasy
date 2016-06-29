package freres.models;

import java.util.List;

public interface IImageManager {
	public boolean createImage(String path, Integer idColoc);
	public List<Image> getColocImages(Integer idColoc);
}

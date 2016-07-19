package freres.models;

import java.util.List;

public interface IImageManager {
	public boolean createImage(String path, Integer idColoc);
	public boolean deleteImage(Integer idImage);
	public List<Image> getColocImages(Integer idColoc);
	public Image getImage(Integer idColoc);
	public Image getPreview(Integer id);
}

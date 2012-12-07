package com.kurui.kums.library.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.library.ImageDependent;
import com.kurui.kums.library.ImageDependentListForm;
import com.kurui.kums.library.ImageLibrary;
import com.kurui.kums.library.ImageLibraryListForm;
import com.kurui.kums.library.dao.ImageDependentDAO;

public class ImageDependentBizImp implements ImageDependentBiz {
	private ImageDependentDAO imageDependentDAO;

	public List list(ImageDependentListForm imageDependentListForm) throws AppException {
		return imageDependentDAO.list(imageDependentListForm);
	}
	
	public List listViewImageLibrary(ImageLibraryListForm plf) throws AppException{
		return imageDependentDAO.listViewImageLibrary(plf);
	}

	public void delete(long id) throws AppException {
		try {
			imageDependentDAO.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteImageDependent(Long imageDependentId) throws AppException {
		ImageDependent imageDependent = getImageDependentById(imageDependentId);
		imageDependent.setStatus(ImageDependent.STATES_0);// 将状态变为无效
		update(imageDependent);
	}

	public long save(ImageDependent imageDependent) throws AppException {
		return imageDependentDAO.save(imageDependent);
	}

	public long update(ImageDependent imageDependent) throws AppException {
		return imageDependentDAO.update(imageDependent);
	}

	public ImageDependent getImageDependentById(long imageDependentId) throws AppException {
		return imageDependentDAO.getImageDependentById(imageDependentId);
	}
	
	public ImageLibrary getCoverImageLibraryByRowId(String tableName,long rowId) throws AppException{
		return imageDependentDAO.getCoverImageLibraryByRowId(tableName, rowId);
	}

	public List<ImageDependent> getImageDependentList() throws AppException {
		return imageDependentDAO.getImageDependentList();
	}

	public List<ImageDependent> getValidImageDependentList() throws AppException {
		return imageDependentDAO.getValidImageDependentList();
	}

	public void setImageDependentDAO(ImageDependentDAO imageDependentDAO) {
		this.imageDependentDAO = imageDependentDAO;
	}

	public List<ImageDependent> getImageDependentList(String tableName,
			long rowId) throws AppException {
		return imageDependentDAO.getImageDependentList(tableName, rowId);
	}

	public List<ImageLibrary> getImageLibraryList(String tableName, long rowId)
			throws AppException {
		return imageDependentDAO.getImageLibraryList(tableName, rowId);
	}
}

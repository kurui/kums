package com.kurui.kums.library.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.library.ImageLibrary;
import com.kurui.kums.library.ImageLibraryListForm;
import com.kurui.kums.library.dao.ImageLibraryDAO;

public class ImageLibraryBizImp implements ImageLibraryBiz {
	private ImageLibraryDAO imageLibraryDAO;

	public List list(ImageLibraryListForm imageLibraryListForm) throws AppException {
		return imageLibraryDAO.list(imageLibraryListForm);
	}

	public void delete(long id) throws AppException {
		try {
			imageLibraryDAO.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteImageLibrary(Long imageLibraryId) throws AppException {
		ImageLibrary imageLibrary = getImageLibraryById(imageLibraryId);
		imageLibrary.setStatus(ImageLibrary.STATES_0);// 将状态变为无效
		update(imageLibrary);
	}

	public long save(ImageLibrary imageLibrary) throws AppException {
		return imageLibraryDAO.save(imageLibrary);
	}

	public long update(ImageLibrary imageLibrary) throws AppException {
		return imageLibraryDAO.update(imageLibrary);
	}

	public ImageLibrary getImageLibraryById(long imageLibraryId) throws AppException {
		return imageLibraryDAO.getImageLibraryById(imageLibraryId);
	}

	public List<ImageLibrary> getImageLibraryList() throws AppException {
		return imageLibraryDAO.getImageLibraryList();
	}

	public List<ImageLibrary> getValidImageLibraryList() throws AppException {
		return imageLibraryDAO.getValidImageLibraryList();
	}

	public void setImageLibraryDAO(ImageLibraryDAO imageLibraryDAO) {
		this.imageLibraryDAO = imageLibraryDAO;
	}
}

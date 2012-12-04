package com.kurui.kums.library.dao;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.library.ImageLibrary;
import com.kurui.kums.library.ImageLibraryListForm;

public interface ImageLibraryDAO {

	public List list(ImageLibraryListForm imageLibraryForm) throws AppException;

	public void delete(long id) throws AppException;

	public long save(ImageLibrary ImageLibrary) throws AppException;

	public long update(ImageLibrary ImageLibrary) throws AppException;

	public ImageLibrary getImageLibraryById(long imageLibraryId) throws AppException;

	public List<ImageLibrary> getImageLibraryList() throws AppException;

	public ImageLibrary getImageLibrary(long dishId, String dateString)
			throws AppException;

	public List<ImageLibrary> getValidImageLibraryList() throws AppException;

	public List<ImageLibrary> getImageLibraryByDishId(long dishId)
			throws AppException;
}

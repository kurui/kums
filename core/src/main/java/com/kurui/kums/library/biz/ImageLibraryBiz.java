package com.kurui.kums.library.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.library.ImageLibrary;
import com.kurui.kums.library.ImageLibraryListForm;

public interface ImageLibraryBiz {

	public List list(ImageLibraryListForm form) throws AppException;

	public void delete(long id) throws AppException;

	public void deleteImageLibrary(Long imageLibraryId) throws AppException;

	public long save(ImageLibrary imageLibrary) throws AppException;

	public long update(ImageLibrary imageLibrary) throws AppException;

	public ImageLibrary getImageLibraryById(long imageLibraryId) throws AppException;

	public List<ImageLibrary> getImageLibraryList() throws AppException;

	public List<ImageLibrary> getValidImageLibraryList() throws AppException;

}

package com.kurui.kums.library.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.library.ImageDependent;
import com.kurui.kums.library.ImageDependentListForm;
import com.kurui.kums.library.ImageLibrary;
import com.kurui.kums.library.ImageLibraryListForm;

public interface ImageDependentBiz {

	public List list(ImageDependentListForm form) throws AppException;
	public List listViewImageLibrary(ImageLibraryListForm plf) throws AppException;

	public void delete(long id) throws AppException;

	public void deleteImageDependent(Long imageDependentId) throws AppException;

	public long save(ImageDependent imageDependent) throws AppException;

	public long update(ImageDependent imageDependent) throws AppException;

	public ImageDependent getImageDependentById(long imageDependentId) throws AppException;

	public List<ImageDependent> getImageDependentList() throws AppException;

	public List<ImageDependent> getValidImageDependentList() throws AppException;
	
	public List<ImageDependent> getImageDependentList(String tableName,long rowId)
			throws AppException;

	public List<ImageLibrary> getImageLibraryList(String tableName,long rowId)
			throws AppException;

}

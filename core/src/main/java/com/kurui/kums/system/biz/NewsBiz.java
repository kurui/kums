package com.kurui.kums.system.biz;

import java.util.List;

import com.kurui.kums.system.News;
import com.kurui.kums.system.NewsListForm;
import com.kurui.kums.base.exception.AppException;

public interface NewsBiz {
	public List getNews(NewsListForm clf) throws AppException;

	public News getNewsById(long id) throws AppException;

	public long updateInfo(News news) throws AppException;

	public long save(News news) throws AppException;

	public void deleteNewsById(int id) throws AppException;

}

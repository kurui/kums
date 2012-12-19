package com.kurui.kums.library.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.library.News;
import com.kurui.kums.library.NewsListForm;
import com.kurui.kums.library.dao.NewsDAO;

public class NewsBizImp implements NewsBiz {
	private NewsDAO newsDAO;

	public List getNews(NewsListForm clf) throws AppException {
		return newsDAO.getNews(clf);
	}

	public NewsDAO getNewsDAO() {
		return newsDAO;
	}

	public void setNewsDAO(NewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}

	public News getNewsById(long id) throws AppException {

		return newsDAO.getNewsById(id);
	}

	public long updateInfo(News news) throws AppException {
		return newsDAO.updateInfo(news);
	}

	public long save(News news) throws AppException {
		return newsDAO.save(news);
	}

	public void deleteNewsById(int id) throws AppException {
		newsDAO.deleteNewsById(id);
	}

}

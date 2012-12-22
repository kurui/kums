package com.kurui.kums.library.dao;

import java.util.List;

import org.hibernate.Query;
import org.hsqldb.lib.StringUtil;

import com.kurui.kums.base.database.hibernate.BaseDAOSupport;
import com.kurui.kums.base.database.hibernate.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.library.News;
import com.kurui.kums.library.NewsListForm;

public class NewsDAOImp extends BaseDAOSupport implements NewsDAO {

	public List getNews(NewsListForm clf) throws AppException {
		Hql hql = new Hql();
		hql.add("from News n where 1=1");
		
		if (!StringUtil.isEmpty(clf.getKeywords())) {
			hql.add(" and ( ");
			
			hql.add(" n.title like ?");
			hql.addParamter("%" + clf.getKeywords().trim() + "%");
			hql.add(" or ");
			hql.add("  n.content like ?");
			hql.addParamter("%" + clf.getKeywords().trim() + "%");
			
			hql.add(" or ");
			hql.add("  n.userName like ?");
			hql.addParamter("%" + clf.getKeywords().trim() + "%");
			
			hql.add(" ) ");
		}
		
		if (!StringUtil.isEmpty(clf.getType()+"")) {
			hql.add(" and n.type ="+clf.getType());
		}
		

		hql.add(" order by n.createTime desc");
//		System.out.println("hql===" + hql);
		return this.list(hql, clf);
	}

	public News getNewsById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from News where id=?");
		hql.addParamter(id);
//		System.out.println("HQL = " + hql);
		Query query = this.getQuery(hql);
		if (query != null && query.list() != null && query.list().size() > 0)
			return (News) query.list().get(0);
		else
			return new News();
	}

	public long updateInfo(News news) throws AppException {
		if (news.getId() > 0)
			return save(news);
		else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public long save(News news) throws AppException {
		this.getHibernateTemplate().saveOrUpdate(news);
		return news.getId();
	}

	public void deleteNewsById(int id) throws AppException {
		this.getHibernateTemplate().delete(this.getNewsById(id));
	}
}

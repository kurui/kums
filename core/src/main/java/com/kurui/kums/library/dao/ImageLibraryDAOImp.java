package com.kurui.kums.library.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.kurui.kums.base.database.hibernate.BaseDAOSupport;
import com.kurui.kums.base.database.hibernate.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.library.ImageLibrary;
import com.kurui.kums.library.ImageLibraryListForm;

public class ImageLibraryDAOImp extends BaseDAOSupport implements
		ImageLibraryDAO {

	static Logger logger = Logger.getLogger(ImageLibraryDAOImp.class.getName());

	public List list(ImageLibraryListForm plf) throws AppException {
		Hql hql = new Hql();
		hql.add("from ImageLibrary p where 1=1");

		hql.add("and p.status=" + ImageLibrary.STATES_1);

		hql.add(" order by  p.updateTime desc ");

		return this.list(hql, plf);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			ImageLibrary ImageLibrary = (ImageLibrary) this
					.getHibernateTemplate().get(ImageLibrary.class,
							new Long(id));
			this.getHibernateTemplate().delete(ImageLibrary);
		}
	}

	public long save(ImageLibrary ImageLibrary) throws AppException {
		this.getHibernateTemplate().save(ImageLibrary);
		return ImageLibrary.getId();
	}

	public long update(ImageLibrary ImageLibrary) throws AppException {
		if (ImageLibrary.getId() > 0) {
			this.getHibernateTemplate().update(ImageLibrary);
			return ImageLibrary.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public ImageLibrary getImageLibraryById(long id) throws AppException {
		ImageLibrary imageLibrary = null;
		Hql hql = new Hql();
		hql.add("from ImageLibrary p where p.id=" + id);
		Query query = this.getQuery(hql);
		ImageLibrary ImageLibrary = null;
		if (query != null) {
			List<ImageLibrary> list = query.list();
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}
		return imageLibrary;
	}

	public List<ImageLibrary> getImageLibraryByDishId(long referenceId)
			throws AppException {
		List<ImageLibrary> list = new ArrayList<ImageLibrary>();
		Hql hql = new Hql();
		hql.add("from ImageLibrary p where p.estateDish.id=" + referenceId);
		Query query = this.getQuery(hql);
		ImageLibrary ImageLibrary = null;
		if (query != null) {
			if (query.list() != null) {
				if (query.list().size() > 0) {
					list = query.list();
				}
			}
		}
		return list;
	}

	public List<ImageLibrary> getImageLibraryList() throws AppException {
		List<ImageLibrary> list = new ArrayList<ImageLibrary>();
		Hql hql = new Hql();
		hql.add("from ImageLibrary p where 1=1 ");
		Query query = this.getQuery(hql);
		if (query != null) {
			if (query.list() != null) {
				if (query.list().size() > 0) {
					list = query.list();
				}
			}
		}
		return list;
	}

	public List<ImageLibrary> getValidImageLibraryList() throws AppException {
		List<ImageLibrary> list = new ArrayList<ImageLibrary>();
		Hql hql = new Hql();
		hql.add("from ImageLibrary p where 1=1 and p.status="
				+ ImageLibrary.STATES_1);
		Query query = this.getQuery(hql);
		if (query != null) {
			if (query.list() != null) {
				if (query.list().size() > 0) {
					list = query.list();
				}
			}
		}
		return list;
	}

}

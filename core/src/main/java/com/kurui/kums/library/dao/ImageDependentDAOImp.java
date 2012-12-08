package com.kurui.kums.library.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.kurui.kums.base.database.hibernate.BaseDAOSupport;
import com.kurui.kums.base.database.hibernate.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.library.ImageDependent;
import com.kurui.kums.library.ImageDependentListForm;
import com.kurui.kums.library.ImageLibrary;
import com.kurui.kums.library.ImageLibraryListForm;

public class ImageDependentDAOImp extends BaseDAOSupport implements
		ImageDependentDAO {

	static Logger logger = Logger.getLogger(ImageDependentDAOImp.class
			.getName());

	public List list(ImageDependentListForm plf) throws AppException {
		Hql hql = new Hql();
		hql.add(" from ImageDependent p where 1=1");

		hql.add("and p.status=" + ImageDependent.STATES_1);

		hql.add(" order by  p.updateTime desc ");

		return this.list(hql, plf);
	}
	
	public List listViewImageLibrary(ImageLibraryListForm plf) throws AppException {
		Hql hql = new Hql();
		hql.add("select p.imageLibrary from ImageDependent p where 1=1 and p.status="
				+ ImageDependent.STATES_1);
		if (StringUtil.isEmpty(plf.getTableName())==false) {
			hql.add(" and p.tableName='"+plf.getTableName()+"'");
		}
		
		if (plf.getRowId()>0) {
			hql.add(" and p.rowId="+plf.getRowId());
		}

		hql.add(" order by  p.updateTime desc ");

		System.out.println(hql);
		return this.list(hql, plf);
	}
	
	

	public void delete(long id) throws AppException {
		if (id > 0) {
			ImageDependent ImageDependent = (ImageDependent) this
					.getHibernateTemplate().get(ImageDependent.class,
							new Long(id));
			this.getHibernateTemplate().delete(ImageDependent);
		}
	}

	public long save(ImageDependent ImageDependent) throws AppException {
		this.getHibernateTemplate().save(ImageDependent);
		return ImageDependent.getId();
	}

	public long update(ImageDependent ImageDependent) throws AppException {
		if (ImageDependent.getId() > 0) {
			this.getHibernateTemplate().update(ImageDependent);
			return ImageDependent.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}
	
	/**
	 * 设置相册图片类型为普通
	 * */
	public void updateImageLibraryTypeByRowId(String tableName,long rowId,long dependentType) throws AppException {
		String hql="update ImageDependent p  set p.type="+dependentType+" where 1=1 ";
		hql+=" and p.tableName='" + tableName+"'";
		hql+=" and p.rowId=" + rowId;
		
		System.out.println(hql);
		Query query =  this.getSession().createQuery(hql);
		int row = query.executeUpdate();
	}
	
	public void updateImageDependentType(String imageLibraryId,long dependentType) throws AppException {
		String hql="update ImageDependent p  set p.type="+dependentType+" where 1=1  ";
		hql+=" and p.imageLibrary.id in("+imageLibraryId+")";
		
		System.out.println(hql);
		Query query =  this.getSession().createQuery(hql);
		int row = query.executeUpdate();
	}
	

	
	/**
	 * 获取封面图片
	 * */
	public ImageLibrary getCoverImageLibraryByRowId(String tableName,long rowId) throws AppException {
		ImageLibrary imageLibrary = null;
		Hql hql = new Hql();
		hql.add("select p.imageLibrary from ImageDependent p where 1=1 ");
		hql.add(" and p.tableName='" + tableName+"'");
		hql.add(" and p.rowId=" + rowId);
		hql.add(" and p.type="+ImageDependent.TYPE_2);//封面
		
		Query query = this.getQuery(hql);
		if (query != null) {
			List<ImageLibrary> list = query.list();
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}
		return imageLibrary;
	}

	public ImageDependent getImageDependentById(long id) throws AppException {
		ImageDependent imageDependent = null;
		Hql hql = new Hql();
		hql.add("from ImageDependent p where p.id=" + id);
		Query query = this.getQuery(hql);
		if (query != null) {
			List<ImageDependent> list = query.list();
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}
		return imageDependent;
	}
	

	
	public List<ImageLibrary> getImageLibraryList(String tableName,long rowId)
			throws AppException {
		List<ImageLibrary> list = new ArrayList<ImageLibrary>();
		Hql hql = new Hql();
		hql.add("select p.imageLibrary from ImageDependent p where 1=1 and p.status="
				+ ImageDependent.STATES_1);
		hql.add(" and p.tableName="+tableName);
		hql.add(" and p.rowId="+tableName);
		
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
	
	public List<ImageDependent> getImageDependentList(String tableName,long rowId)
			throws AppException {
		List<ImageDependent> list = new ArrayList<ImageDependent>();
		Hql hql = new Hql();
		hql.add("from ImageDependent p where 1=1 and p.status="
				+ ImageDependent.STATES_1);
		hql.add(" and p.tableName="+tableName);
		hql.add(" and p.rowId="+tableName);
		
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

	public List<ImageDependent> getImageDependentList() throws AppException {
		List<ImageDependent> list = new ArrayList<ImageDependent>();
		Hql hql = new Hql();
		hql.add("from ImageDependent p where 1=1 ");
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

	public List<ImageDependent> getValidImageDependentList()
			throws AppException {
		List<ImageDependent> list = new ArrayList<ImageDependent>();
		Hql hql = new Hql();
		hql.add("from ImageDependent p where 1=1 and p.status="
				+ ImageDependent.STATES_1);
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

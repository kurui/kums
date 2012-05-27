package com.kurui.kums.market.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.jdom.Document;
import org.jdom.Element;
import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.file.XmlUtil_jdom;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.util.DateUtil;
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.market.PriceReference;
import com.kurui.kums.market.PriceReferenceListForm;

public class PriceReferenceDAOImp extends BaseDAOSupport implements
		PriceReferenceDAO {

	public List list(PriceReferenceListForm priceReferenceListForm)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from PriceReference p where 1=1");
		if (priceReferenceListForm.getName() != null
				&& (!(priceReferenceListForm.getName().equals("")))) {
			hql.add(" and p.name like '%"
					+ priceReferenceListForm.getName().trim() + "%'");
		}
		if (Constant.toLong(priceReferenceListForm.getType()) > 0) {
			hql.add(" and p.type=" + priceReferenceListForm.getType());
		}

		hql.add(" and p.status=" + PriceReference.STATES_1);

		hql.add(" order by p.type,p.code");

		return this.list(hql, priceReferenceListForm);
	}

	public void buildPriceReferenceTree() throws AppException {
		TreeUtil treeUtil = new TreeUtil();
		String treeFileName = DateUtil.getDateString("yyyyMMddHHmmss") + ".xml";
		treeFileName = "PriceReferenceTree.xml";
		String treeFilePath = Constant.SERVLET_XML_PATH + treeFileName;
		Document doc = treeUtil.buildPriceReferenceTree(treeFileName,
				treeFilePath);
		// String result = XmlUtil_jdom.getDocumentString(doc);
		// System.out.println(result);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			PriceReference platform = (PriceReference) this
					.getHibernateTemplate().get(PriceReference.class,
							new Long(id));
			this.getHibernateTemplate().delete(platform);
		}
	}

	public long save(PriceReference platform) throws AppException {
		this.getHibernateTemplate().save(platform);
		return platform.getId();
	}

	public long update(PriceReference platform) throws AppException {
		if (platform.getId() > 0) {
			this.getHibernateTemplate().update(platform);
			return platform.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public PriceReference getPriceReferenceById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from PriceReference p where p.id=" + id);
		Query query = this.getQuery(hql);
		PriceReference priceReference = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			priceReference = (PriceReference) query.list().get(0);
		}
		return priceReference;
	}

	public List<PriceReference> getPriceReferenceByIds(String referenceIds)
			throws AppException {
		List<PriceReference> list = new ArrayList<PriceReference>();
		Hql hql = new Hql();
		hql.add("from PriceReference p where 1=1  ");
		if (Constant.toString(referenceIds) != "") {
			hql.add(" and p.id in(" + referenceIds + ") ");
		}
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

	public List<PriceReference> getPriceReferenceListByIndexIds(int[] indexIds)
			throws AppException {
		List<PriceReference> list = new ArrayList<PriceReference>();
		String indexIdStr = "";
		if (indexIds != null) {
			indexIdStr = StringUtil.getStringByArray(indexIds, ",");
		}

		if (indexIdStr != "") {
			Hql hql = new Hql();
			hql.add(" from PriceReference p  where 1=1 ");
			hql.add(" and p.id in( ");
			hql
					.add(" select distinct t.priceReference.id from PriceIndex t where 1=1");
			hql.add(" and t.id in(" + indexIdStr + ")");
			hql.add(" ) ");
			hql.add(" order by p.type,p.code ");

			Query query = this.getQuery(hql);
			if (query != null) {
				list = query.list();
				if (list != null && list.size() > 0) {
					return list;
				}
			}
		}
		return list;
	}

	public List<PriceReference> getPriceReferenceList() throws AppException {
		List<PriceReference> list = new ArrayList<PriceReference>();
		Hql hql = new Hql();
		hql.add(" from PriceReference p  where 1=1 ");
		hql.add(" and p.status=" + PriceReference.STATES_1);
		hql.add(" order by p.type,p.code ");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<PriceReference> getPriceReferenceListByType(
			String priceReferenceTypes) throws AppException {
		List<PriceReference> list = new ArrayList<PriceReference>();
		Hql hql = new Hql();
		hql.add(" from PriceReference p where  1=1 ");

		if (Constant.toString(priceReferenceTypes) != "") {
			hql.add(" and p.type in(" + priceReferenceTypes + ")");
		}

		hql.add(" and p.status=" + PriceReference.STATES_1);
		hql.add(" order by p.type,p.code ");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<PriceReference> getValidPriceReferenceList()
			throws AppException {
		List<PriceReference> list = new ArrayList<PriceReference>();
		Hql hql = new Hql();
		hql.add(" from PriceReference p where 1=1 ");
		hql.add(" and p.status=" + PriceReference.STATES_1);
		hql.add(" order by p.type,p.code");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public class TreeUtil {
		private int itemIndex = 0;

		public Document buildPriceReferenceTree(String treeFileName,
				String treeFilePath) throws AppException {
			Element rootItem = new Element("tree");
			rootItem.setAttribute("id", "0");
			Document doc = new Document(rootItem);
			rootItem = doc.getRootElement();

			rootItem = PriceReference.getTypeItems(rootItem);

			createPriceReferenceItem(doc);

			XmlUtil_jdom.saveToXmlFile(doc, treeFilePath);
			return doc;
		}

		private void createPriceReferenceItem(Document doc) throws AppException {
			createPriceReferenceItem(PriceReference.TYPE_1, doc);
			createPriceReferenceItem(PriceReference.TYPE_10, doc);
			createPriceReferenceItem(PriceReference.TYPE_11, doc);
			createPriceReferenceItem(PriceReference.TYPE_20, doc);
			createPriceReferenceItem(PriceReference.TYPE_30, doc);
			createPriceReferenceItem(PriceReference.TYPE_40, doc);
			createPriceReferenceItem(PriceReference.TYPE_101, doc);
			createPriceReferenceItem(PriceReference.TYPE_102, doc);
			createPriceReferenceItem(PriceReference.TYPE_103, doc);
			createPriceReferenceItem(PriceReference.TYPE_110, doc);
			createPriceReferenceItem(PriceReference.TYPE_120, doc);
		}

		private void createPriceReferenceItem(long priceReferenceType,
				Document doc) throws AppException {
			Element rootItem = searchItemFromDoc(doc, priceReferenceType);
			if (rootItem != null) {
				List<PriceReference> priceReferenceList = getPriceReferenceListByType(priceReferenceType
						+ "");
				for (int i = 0; i < priceReferenceList.size(); i++) {
					PriceReference priceReference = priceReferenceList.get(i);
					if (priceReference != null) {
						Element thisItem = createItem(priceReference);
						rootItem.addContent(thisItem);
					}
				}
			}
		}

		private Element searchItemFromDoc(Document doc, long referenceType)
				throws AppException {
			String itemPath = "//tree/item";

			List<Element> list = XmlUtil_jdom.getAllElements(doc, itemPath);
			if (list != null) {
				for (int k = 0; k < list.size(); k++) {
					Element tempRootItem = list.get(k);
					String typeId = tempRootItem.getAttributeValue("id");
					typeId = typeId.replaceAll("type", "");
					long tempRootId = Constant.toLong(typeId);

					// System.out.println("tempRootId:" + tempRootId);
					if (tempRootId == referenceType) {
						return tempRootItem;
					}
				}
			}

			return null;
		}

		private Element createItem(PriceReference priceReference) {
			// System.out.println("--createItem:" + agent.getName());
			Element item = new Element("item");
			item.setAttribute("text", priceReference.getName());
			item.setAttribute("id", priceReference.getId() + "");

			// item.setAttribute("open", "1");
			item.setAttribute("im0", "tombs.gif");
			item.setAttribute("im1", "tombs.gif");
			item.setAttribute("im2", "iconSafe.gif");

			// item.setAttribute("itemIndex",itemIndex+"");//自定义
			// if (itemIndex == 0) {
			// item.setAttribute("call", "1");
			// item.setAttribute("select", "1");
			// }
			// itemIndex++;

			return item;
		}
	}
}

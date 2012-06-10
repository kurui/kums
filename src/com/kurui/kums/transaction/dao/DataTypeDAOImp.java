package com.kurui.kums.transaction.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.jdom.Document;
import org.jdom.Element;

import com.kurui.kums.base.Constant;
import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.file.XmlUtil_jdom;
import com.kurui.kums.base.util.DateUtil;
import com.kurui.kums.transaction.DataType;
import com.kurui.kums.transaction.DataTypeListForm;

public class DataTypeDAOImp extends BaseDAOSupport implements DataTypeDAO {

	public List list(DataTypeListForm plf) throws AppException {
		Hql hql = new Hql();
		hql.add("from DataType p where 1=1");

		if (Constant.toLong(plf.getLevel()) > 0) {
			hql.add(" and p.superNo=" + (plf.getLevel() - 1));
		}

		hql.add("and p.status=" + DataType.STATES_1);

		hql.add(" order by  p.no ");
		// System.out.println(hql);
		return this.list(hql, plf);
	}

	public void buildProductTree() throws AppException {
		TreeUtil treeUtil = new TreeUtil();
		String rootDataTypeNo = "8000";
		String treeFileName = DateUtil.getDateString("yyyyMMddHHmmss") + ".xml";
		treeFileName = "ProductTree.xml";
		String treeFilePath = Constant.SERVLET_XML_PATH + treeFileName;
		Document doc = treeUtil.buildDataTypeTree(rootDataTypeNo, treeFileName,
				treeFilePath);
//		String result = XmlUtil_jdom.getDocumentString(doc);
//		System.out.println(result);
	}
	
	public void buildProvideChainTree() throws AppException {
		TreeUtil treeUtil = new TreeUtil();
		String rootDataTypeNo = "5000";
		String treeFileName = DateUtil.getDateString("yyyyMMddHHmmss") + ".xml";
		treeFileName = "ProvideChainTree.xml";
		String treeFilePath = Constant.SERVLET_XML_PATH + treeFileName;
		Document doc = treeUtil.buildDataTypeTree(rootDataTypeNo, treeFileName,
				treeFilePath);
//		String result = XmlUtil_jdom.getDocumentString(doc);
//		System.out.println(result);
	}

	public void refactorDataTypeTree()throws AppException{
		DataType root=getDataTypeByNo("0");
		List<DataType> childList=getSubDataTypeList("0");
		refactorDataType(root, childList);
		
	}
	
	public long refactorDataType(DataType root, List<DataType> childList) throws AppException {
		long thislft = root.getRgt() + 1;
		
		
		boolean isSuperRoot=false;
		int index=root.getName().indexOf("ROOT_TAG");
		if(index>-1){
			isSuperRoot=true;
		}
		
		if(childList!=null&&childList.size()>0){
			int childListsize=childList.size();
			for (int i = 0; i < childList.size(); i++) {
//				System.out.println("-------------for i:" + i);
				DataType child = childList.get(i);

				child.setLft(thislft);
				child.setRgt(thislft + 1);
//				child = update(child);
				update(child);

				thislft = thislft + 2;

//				List<DataType> newchildList = findDepartmentsByParentId(child.getId());
				List<DataType> newchildList = getSubDataTypeList(child.getNo());
				

				if (newchildList != null && newchildList.size() > 0) {
					thislft=refactorDataType(child, newchildList);
					child.setRgt(thislft);
					update(child);
					
					thislft=thislft+1;
					
					
				} 
				
				if(isSuperRoot&&i==childListsize-1){
					root.setRgt(thislft);
					update(root);
				}
			}
		}
		
		return thislft;
	}
	public void delete(long id) throws AppException {
		if (id > 0) {
			DataType DataType = (DataType) this.getHibernateTemplate().get(
					DataType.class, new Long(id));
			this.getHibernateTemplate().delete(DataType);
		}
	}

	public long save(DataType tranType) throws AppException {
		this.getHibernateTemplate().save(tranType);
		return tranType.getId();
	}

	public long update(DataType DataType) throws AppException {
		if (DataType.getId() > 0) {
			this.getHibernateTemplate().update(DataType);
			return DataType.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public DataType getDataTypeById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from DataType p where p.id=" + id);
		Query query = this.getQuery(hql);
		DataType tranType = null;
		if (query != null) {
			if (query.list() != null) {
				if (query.list().size() > 0) {
					tranType = (DataType) query.list().get(0);
				}
			}
		}
		return tranType;
	}

	public DataType getDataTypeByNo(String no) throws AppException {
		Hql hql = new Hql();
		hql.add("from DataType p where p.no=" + no);
		Query query = this.getQuery(hql);
		DataType tranType = null;
		if (query != null) {
			if (query.list() != null) {
				if (query.list().size() > 0) {
					tranType = (DataType) query.list().get(0);
				}
			}
		}
		return tranType;
	}

	public List<DataType> getDataTypeList() throws AppException {
		List<DataType> list = new ArrayList<DataType>();
		Hql hql = new Hql();
		hql.add("from DataType p where 1=1 ");
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

	public List<DataType> getValidDataTypeList() throws AppException {
		List<DataType> list = new ArrayList<DataType>();
		Hql hql = new Hql();
		hql.add("from DataType p where 1=1 and p.status=" + DataType.STATES_1);
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

	// 直属上级
	public DataType getSupDataType(String no) throws AppException {
		DataType dataType = null;
		Hql hql = new Hql();
		hql.add(" from DataType d where d.no =(");
		hql.add(" select p.superNo from DataType p where 1=1 ");
		hql.add(" and p.no=" + no);
		hql.add(" and p.status=" + DataType.STATES_1 + ")");
		hql.add(" order by d.no ");
		// System.out.println(hql);

		Query query = this.getQuery(hql);
		List<DataType> list = new ArrayList<DataType>();
		if (query != null) {
			list = (List<DataType>) query.list();
			if (list != null) {
				if (list.size() > 0) {
					dataType = list.get(0);
				}
			}
		}
		return dataType;
	}

	// 上级
	public List<DataType> getSupDataTypeList(String no) throws AppException {
		List<DataType> list = new ArrayList<DataType>();
		Hql hql = new Hql();

		String superNo = getSupNoByThisNo(no);

		if (Constant.toString(superNo) != "") {
			hql.add(" from DataType p where p.no =" + superNo);
			hql.add(" and p.status=" + DataType.STATES_1 + ")");
			hql.add(" order by p.no ");
		}

		// System.out.println(hql);

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

	public String getSupNoByThisNo(String no) throws AppException {
		String superNo = "";
		Hql hql = new Hql();
		hql.add(" select p.superNo from DataType p where 1=1 ");

		if (Constant.toString(no) != "") {
			hql.add(" and p.no=" + no);
		}

		hql.add(" and p.status=" + DataType.STATES_1);
		hql.add(" order by p.no ");
		// System.out.println(hql);

		Query query = this.getQuery(hql);
		if (query != null) {
			List superNoList = query.list();
			if (superNoList != null) {
				if (superNoList.size() > 0) {
					superNo = (String) superNoList.get(0);
				}
			}
		}
		return superNo;
	}

	// 下级
	public List<DataType> getSubDataTypeList(String superNo)
			throws AppException {
		List<DataType> list = new ArrayList<DataType>();
		Hql hql = new Hql();
		hql.add("from DataType p where 1=1 and p.status=" + DataType.STATES_1);
		hql.add(" and p.superNo=" + superNo);
		hql.add(" order by p.no ");
		// System.out.println("hql:" + hql);

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

	// 获取平级
	public List<DataType> getSameLevelDataTypeList(long tranTypeNo)
			throws AppException {
		DataType tranType = getDataTypeByNo(tranTypeNo + "");
		if (tranType == null) {
			return null;
		}
		List<DataType> list = getSubDataTypeList(tranType.getSuperNo());

		return list;
	}

	public class TreeUtil {
		private Element rootItem;
		private List<DataType> rootDataTypeList;
		private List<DataType> subDataTypeList;
		private int currentLevel;

		public Document buildDataTypeTree(String rootDataTypeNo,
				String treeFileName, String treeFilePath) throws AppException {
			DataType rootDataType = getDataTypeByNo(rootDataTypeNo);
			rootDataTypeList = new ArrayList<DataType>();
			rootDataTypeList.add(rootDataType);

			rootItem = new Element("tree");
			rootItem.setAttribute("id", "0");
			Document doc = new Document(rootItem);
			rootItem = doc.getRootElement();

			subDataTypeList = getSubDataTypeList(rootDataTypeNo);
			// System.out.println(rootDataTypeNo + "---subDataTypeList:"
			// + subDataTypeList.size());

			rootItem = setLevelDataTypeItem(rootItem, rootDataTypeList,
					subDataTypeList);

			if (subDataTypeList.size() > 0) {
				int levelCount = 3;

				for (int i = 0; i < levelCount; i++) {
					currentLevel = i;
					setLevelDataTypeList(subDataTypeList);
					rootDataTypeList = subDataTypeList;
					rootItem = setLevelDataTypeItem(rootItem, rootDataTypeList,
							subDataTypeList);
				}
			}
			// createProductItem(doc);

			XmlUtil_jdom.saveToXmlFile(doc, treeFilePath);
			return doc;
		}

		private Element setLevelDataTypeItem(Element rootItem,
				List<DataType> rootDataTypeList, List<DataType> subDataTypeList)
				throws AppException {

			for (int i = 0; i < rootDataTypeList.size(); i++) {
				DataType tempRootDataType = rootDataTypeList.get(i);
				if(tempRootDataType!=null){
					String rootDataTypeNo = tempRootDataType.getNo();
					DataType tempSupDataType = getSupDataType(tempRootDataType
							.getNo());
					Element thisItem = null;
					if (tempSupDataType != null) {
						if (!isExistRoot(tempSupDataType.getNo(), tempRootDataType)) {
							thisItem = createItem(tempRootDataType);
						}
					} else {
						// System.out.println("tempSup is null create。。。");
						thisItem = createItem(tempRootDataType);
					}

					// System.out.println(i + "--in the rootDataTypeList "
					// + tempRootDataType.getName() + "--subDataTypeList:"
					// + subDataTypeList.size());
					for (int j = 0; j < subDataTypeList.size(); j++) {
						DataType tempDataType = subDataTypeList.get(j);
						tempSupDataType = getSupDataType(tempDataType.getNo());
						if (tempSupDataType != null) {
							if (rootDataTypeNo == tempSupDataType.getNo()) {

								if (!isExistRoot(tempSupDataType.getNo(),
										tempDataType)) {
									if (thisItem != null) {
										thisItem
												.addContent(createItem(tempDataType));
									}
								}
							}
						}
					}

					if (thisItem != null) {
						rootItem.addContent(thisItem);
					}
				}
			

			}
			return rootItem;
		}
		

		private boolean isExistRoot(String rootDataTypeNo, DataType tempDataType)
				throws AppException {
			boolean flag = false;
			String itemPath = "//tree/item/item";

			if (currentLevel > 0) {
				for (int i = 0; i < currentLevel; i++) {
					itemPath += "/item";
				}
			} else {
				itemPath = "//tree/item/item";
			}

			List<Element> list = XmlUtil_jdom.getAllElements(rootItem
					.getDocument(), itemPath);
			if(list!=null){
				for (int k = 0; k < list.size(); k++) {
					Element tempRootItem = list.get(k);
					String tempRootDataTypeNo = tempRootItem
							.getAttributeValue("id");

					// System.out.println("rootDataTypeNo:" + rootDataTypeNo
					// + "---tempRootDataTypeNo:" + tempRootDataTypeNo);
					if (tempRootDataTypeNo == rootDataTypeNo) {
						tempRootItem.addContent(createItem(tempDataType));
						flag = true;
						break;
					}
				}
			}
			
			return flag;
		}

		private void setLevelDataTypeList(List<DataType> superDataTypeList)
				throws AppException {
			List<DataType> tempDataTypeList = new ArrayList<DataType>();
			for (int j = 0; j < superDataTypeList.size(); j++) {
				DataType tempDataType = superDataTypeList.get(j);
				List<DataType> subDataTypeList = getSubDataTypeList(tempDataType
						.getNo());
				if (subDataTypeList.size() > 0) {
					// lists.add((ArrayList<DataType>) subDataTypeList);
					tempDataTypeList.addAll(subDataTypeList);
				}
			}
			subDataTypeList = tempDataTypeList;
		}

		private Element createItem(DataType dataType) {
			// System.out.println("--createItem:" + dataType.getName());
			Element item = new Element("item");
			item.setAttribute("text", dataType.getName());
			item.setAttribute("id", dataType.getNo());

			item.setAttribute("open", "1");
			item.setAttribute("im0", "tombs.gif");
			item.setAttribute("im1", "tombs.gif");
			item.setAttribute("im2", "iconSafe.gif");
			item.setAttribute("call", "1");
			// item.setAttribute("select", "1");
			return item;
		}
	}

}

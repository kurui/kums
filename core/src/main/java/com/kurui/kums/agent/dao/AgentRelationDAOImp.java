package com.kurui.kums.agent.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.jdom.Document;
import org.jdom.Element;

import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.AgentRelation;
import com.kurui.kums.agent.AgentRelationListForm;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.file.XmlUtil_jdom;
import com.kurui.kums.base.util.DateUtil;

public class AgentRelationDAOImp extends BaseDAOSupport implements
		AgentRelationDAO {
	private AgentDAO agentDAO;

	public HttpServletRequest listAgentGroup(
			AgentRelationListForm alf,
			HttpServletRequest request) {
		ArrayList<ArrayList<Agent>> lists = new ArrayList<ArrayList<Agent>>();
		try {
			long agentId = alf.getAgentId();
			Agent rootAgent = agentDAO.getAgentById(agentId);
			request.setAttribute("rootAgent", rootAgent);

			if(rootAgent!=null){
				String treeFileName=rootAgent.getTreeFileName();
				
				String treeFilePath = Constant.SERVLET_XML_PATH
						+ treeFileName;
				File treeFile=new File(treeFilePath);
				boolean isExistFile=treeFile.isFile();
				
				if(treeFileName==null||isExistFile==false){
					treeFileName = DateUtil
							.getDateString("yyyyMMddHHmmss")
							+ ".xml";
					treeFilePath = Constant.SERVLET_XML_PATH
							+ treeFileName;
					buildAgentTree(rootAgent,treeFileName,treeFilePath,true);	
					
					rootAgent.setTreeFileName(treeFileName);
					agentDAO.update(rootAgent);
				}
				

				request.setAttribute("relationTreeFileName", treeFileName);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	// 直属上级
	public Agent getSupAgent(long agentId) throws AppException {
		Agent supAgent = null;
		Hql hql = new Hql();

		hql.add("select p.rootAgent from AgentRelation p where 1=1 ");
		hql.add(" and p.status=" + AgentRelation.STATES_1);
		hql.add(" and p.relateAgent.id=" + agentId);

		// System.out.println("hql:"+hql);

		Query query = this.getQuery(hql);
		ArrayList<Agent> list = new ArrayList<Agent>();
		if (query != null) {
			list = (ArrayList<Agent>) query.list();
			if (list != null) {
				if (list.size() > 0) {
					supAgent = list.get(0);
				}
			}
		}
		return supAgent;
	}

	// 直属下级
	public ArrayList<Agent> getSubAgentList(long agentId) throws AppException {
		ArrayList<Agent> list = new ArrayList<Agent>();
		Hql hql = new Hql();

		hql.add("select p.relateAgent from AgentRelation p where 1=1 ");
		hql.add(" and p.status=" + AgentRelation.STATES_1);
		hql.add(" and p.rootAgent.id=" + agentId);

		// System.out.println("hql:"+hql);

		Query query = this.getQuery(hql);
		if (query != null) {
			if (query.list() != null) {
				if (query.list().size() > 0) {
					list = (ArrayList<Agent>) query.list();
				}
			}
		}
		return list;
	}

	public List list(AgentRelationListForm alf)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from AgentRelation a where 1=1");
		if (Constant.toLong(alf.getAgentId()) > 0) {
			hql.add(" and a.agent.id=" + alf.getAgentId());
		}

		if (Constant.toString(alf.getContactWay()) != "") {
			hql.add(" and (");
			hql.add(" ( ");
			hql.add(" a.rootAgent.name like '%"
					+ alf.getContactWay().trim() + "%'");
			hql.add(" or a.rootAgent.agentNo like '%"	+ alf.getContactWay().trim() + "%'");
			hql.add(" ) ");

			hql.add(" or ");
			hql.add(" ( ");
			hql.add(" a.relateAgent.name like '%"
					+ alf.getContactWay().trim() + "%'");
			hql.add(" or a.relateAgent.agentNo like '%"	+ alf.getContactWay().trim() + "%'");
			hql.add(" ) ");
			hql.add(" ) ");
		}
		hql.add(" and a.status=" + AgentRelation.STATES_1);
		hql.add(" order by a.rootAgent.id ");

		// System.out.println(hql);

		return this.list(hql, alf);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			AgentRelation agent = (AgentRelation) this.getHibernateTemplate()
					.get(AgentRelation.class, new Long(id));
			this.getHibernateTemplate().delete(agent);
		}
	}

	public long save(AgentRelation agent) throws AppException {
		this.getHibernateTemplate().save(agent);
		return agent.getId();
	}

	public long update(AgentRelation agent) throws AppException {
		if (agent.getId() > 0) {
			this.getHibernateTemplate().update(agent);
			return agent.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public AgentRelation getAgentRelationById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from AgentRelation a where a.id=" + id);
		Query query = this.getQuery(hql);
		AgentRelation agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (AgentRelation) query.list().get(0);
		}
		return agent;
	}

	public AgentRelation getAgentRelationByRootAgentId(long agentId)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from AgentRelation a where a.rootAgent.id=" + agentId);
		Query query = this.getQuery(hql);
		AgentRelation agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (AgentRelation) query.list().get(0);
		}
		return agent;
	}

	public List<AgentRelation> getAgentRelationList() throws AppException {
		List<AgentRelation> list = new ArrayList<AgentRelation>();
		Hql hql = new Hql();
		hql.add("from AgentRelation");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<AgentRelation> getAgentRelationList(Long type)
			throws AppException {
		List<AgentRelation> list = new ArrayList<AgentRelation>();
		Hql hql = new Hql();
		hql.add("from AgentRelation a where a.type=" + type);
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<AgentRelation> getValidAgentRelationList() throws AppException {
		List<AgentRelation> list = new ArrayList<AgentRelation>();
		Hql hql = new Hql();
		hql.add("from AgentRelation a where 1=1 ");
		hql.add("and a.status= " + AgentRelation.STATES_1);
		hql.add(" order by a.rootAgent.id ");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}
	

	
	public Document buildAgentTree(Agent rootAgent ,
			String treeFileName, String treeFilePath, boolean includeRoot)
			throws AppException {

		Element rootItem = new Element("tree");
		rootItem.setAttribute("id", "0");
		Document doc = new Document(rootItem);
		rootItem = doc.getRootElement();

		if (includeRoot) {
			Element thisItem = createItem(rootAgent);

			if (thisItem != null) {
				rootItem.addContent(thisItem);
				rootItem=thisItem;
			}
		}

		List<Agent> childList = getSubAgentList(rootAgent.getId());

		rootItem = setLevelAgentItem(rootItem, childList);

		XmlUtil_jdom.saveToXmlFile(doc, treeFilePath);
		
	
		return doc;
	}

	private Element setLevelAgentItem(Element rootItem,
			List<Agent> childList) throws AppException {

		for (int i = 0; i < childList.size(); i++) {
			Agent tempRootAgent = childList.get(i);
			if (tempRootAgent != null) {
				Element thisItem = createItem(tempRootAgent);

				if (thisItem != null) {
					rootItem.addContent(thisItem);
				}

				List<Agent> tempChildList = getSubAgentList(tempRootAgent.getId());
				if (tempChildList != null && tempChildList.size() > 0) {
					setLevelAgentItem(thisItem, tempChildList);
				}
			}

		}
		return rootItem;
	}
/*
	public class TreeUtil {
		private Element rootItem;
		private ArrayList<Agent> rootAgentList;
		private ArrayList<Agent> subAgentList;
		private ArrayList<AgentGroup> agentGroupList;
		private int currentLevel;

		public HttpServletRequest buildAgentTree(Agent rootAgent,
				ArrayList<ArrayList<Agent>> lists, HttpServletRequest request) {
			try {
				long agentId = rootAgent.getId();
				rootAgentList = new ArrayList<Agent>();
				rootAgentList.add(rootAgent);

				rootItem = new Element("tree");
				rootItem.setAttribute("id", "0");
				Document doc = new Document(rootItem);
				rootItem = doc.getRootElement();

				subAgentList = getSubAgentList(agentId);
//				System.out.println(agentId + "---subAgentList:"
//						+ subAgentList.size());

				rootItem = setLevelAgentItem(rootItem, rootAgentList,
						subAgentList);

				if (subAgentList.size() > 0) {
					lists.add((ArrayList<Agent>) subAgentList);

					agentGroupList = new ArrayList<AgentGroup>();

					int levelCount = 10;

					for (int i = 0; i < levelCount; i++) {
						currentLevel = i;
						lists = setLevelAgentList(subAgentList, lists);
						rootAgentList = subAgentList;
						rootItem = setLevelAgentItem(rootItem, rootAgentList,
								subAgentList);
					}

					request.setAttribute("agentGroupList", agentGroupList);
					request.setAttribute("subAgentList", subAgentList);
					request.setAttribute("lists", lists);

					// printListArray(lists);

					String result = XmlUtil_jdom.getDocumentString(doc);
					// System.out.println(result);
					String treeFileName = DateUtil
							.getDateString("yyyyMMddHHmmss")
							+ ".xml";
					String treeFilePath = Constant.SERVLET_XML_PATH
							+ treeFileName;
					XmlUtil_jdom.saveToXmlFile(doc, treeFilePath);
					request.setAttribute("relationTreeFileName", treeFileName);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return request;
		}

		protected Element setLevelAgentItem(Element rootItem,
				ArrayList<Agent> rootAgentList, ArrayList<Agent> subAgentList)
				throws AppException {

			for (int i = 0; i < rootAgentList.size(); i++) {
				Agent tempRootAgent = rootAgentList.get(i);
				long rootAgentId = tempRootAgent.getId();
				Agent tempSupAgent = getSupAgent(tempRootAgent.getId());
				Element thisItem = null;
				if (tempSupAgent != null) {
					if (!isExistRoot(tempSupAgent.getId(), tempRootAgent)) {
						thisItem = createItem(tempRootAgent);
					}
				} else {
					thisItem = createItem(tempRootAgent);
				}

				// System.out.println(i + "--in the rootAgentList " +
				// rootAgentName
				// + "--subAgentList:" + subAgentList.size());
				for (int j = 0; j < subAgentList.size(); j++) {
					Agent tempAgent = subAgentList.get(j);
					tempSupAgent = getSupAgent(tempAgent.getId());
					if (tempSupAgent != null) {
						if (rootAgentId == tempSupAgent.getId()) {

							if (!isExistRoot(tempSupAgent.getId(), tempAgent)) {
								if (thisItem != null) {
									thisItem.addContent(createItem(tempAgent));
								} else {
									logger.warn("can not find thisItem ");
								}
							}

						}
					}
				}

				if (thisItem != null) {
					rootItem.addContent(thisItem);
				}

			}
			return rootItem;
		}

		private boolean isExistRoot(long rootAgentId, Agent tempAgent)
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

			// System.out.println("currentLevel:"+currentLevel+"--itemPath:"+itemPath);

			List<Element> list = XmlUtil_jdom.getAllElements(rootItem
					.getDocument(), itemPath);
			for (int k = 0; k < list.size(); k++) {
				Element tempRootItem = list.get(k);
				long tempRootAgentId = Constant.toLong(tempRootItem
						.getAttributeValue("id"));

				// System.out.println("rootAgentId:" + rootAgentId
				// + "---tempRootAgentId:" + tempRootAgentId);
				if (tempRootAgentId == rootAgentId) {
					// System.out.println("tempRootAgentId:" + tempRootAgentId);
					tempRootItem.addContent(createItem(tempAgent));
					flag = true;
					break;
				}
			}
			return flag;
		}

		private ArrayList<ArrayList<Agent>> setLevelAgentList(
				ArrayList<Agent> superAgentList,
				ArrayList<ArrayList<Agent>> lists) throws AppException {

			ArrayList<Agent> tempAgentList = new ArrayList<Agent>();
			for (int j = 0; j < superAgentList.size(); j++) {
				Agent tempAgent = superAgentList.get(j);
				ArrayList<Agent> subAgentList = getSubAgentList(tempAgent
						.getId());

				if (subAgentList.size() > 0) {
					lists.add((ArrayList<Agent>) subAgentList);
					tempAgentList.addAll(subAgentList);

					AgentGroup agentGroup = new AgentGroup(tempAgent,
							subAgentList);
					agentGroupList.add(agentGroup);
				}
			}
			subAgentList = tempAgentList;

			return lists;
		}
*/
		protected Element createItem(Agent agent) {
			// System.out.println("--createItem:" + agent.getName());

			Element item = new Element("item");
			item.setAttribute("text", agent.getName());
			item.setAttribute("id", agent.getId() + "");
			item.setAttribute("open", "1");
			item.setAttribute("im0", "tombs.gif");
			item.setAttribute("im1", "tombs.gif");
			item.setAttribute("im2", "iconSafe.gif");
			item.setAttribute("call", "1");
			item.setAttribute("select", "1");

			return item;
		}
//	}

	public void setAgentDAO(AgentDAO agentDAO) {
		this.agentDAO = agentDAO;
	}

	
}

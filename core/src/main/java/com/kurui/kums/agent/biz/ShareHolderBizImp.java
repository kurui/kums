package com.kurui.kums.agent.biz;

import java.util.List;
import com.kurui.kums.agent.ShareHolder;
import com.kurui.kums.agent.ShareHolderListForm;
import com.kurui.kums.agent.dao.ShareHolderDAO;
import com.kurui.kums.base.exception.AppException;

public class ShareHolderBizImp implements ShareHolderBiz {
	private ShareHolderDAO shareHolderDAO;

	public List list(ShareHolderListForm shareHolderListForm)
			throws AppException {
		return shareHolderDAO.list(shareHolderListForm);
	}

	public void delete(long id) throws AppException {
		shareHolderDAO.delete(id);
	}

	public long save(ShareHolder shareHolder) throws AppException {
		return shareHolderDAO.save(shareHolder);
	}

	public long update(ShareHolder shareHolder) throws AppException {
		return shareHolderDAO.update(shareHolder);
	}

	public ShareHolder getShareHolderById(long id) throws AppException {
		return shareHolderDAO.getShareHolderById(id);
	}

	public ShareHolder getShareHolderByAgentId(long agentId)
			throws AppException {
		return shareHolderDAO.getShareHolderByAgentId(agentId);
	}

	public ShareHolder getShareHolderByCompanyId(long companyId)
			throws AppException {
		return shareHolderDAO.getShareHolderByCompanyId(companyId);
	}

	public List<ShareHolder> getShareHolderList() throws AppException {
		return shareHolderDAO.getShareHolderList();
	}

	public List<ShareHolder> getShareHolderList(Long type) throws AppException {
		return shareHolderDAO.getShareHolderList(type);
	}

	public List<ShareHolder> getValidShareHolderList() throws AppException {
		return shareHolderDAO.getValidShareHolderList();
	}

	public void setShareHolderDAO(ShareHolderDAO shareHolderDAO) {
		this.shareHolderDAO = shareHolderDAO;
	}

}

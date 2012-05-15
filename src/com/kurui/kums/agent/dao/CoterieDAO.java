package com.kurui.kums.agent.dao;

import java.util.List;
import com.kurui.kums.agent.Coterie;
import com.kurui.kums.agent.CoterieListForm;
import com.kurui.kums.base.exception.AppException;

public interface CoterieDAO {
	public List list(CoterieListForm agentCoterieListForm) throws AppException;

	public void delete(long id) throws AppException;

	public long save(Coterie agentCoterie) throws AppException;

	public long update(Coterie agentCoterie) throws AppException;

	public Coterie getCoterieById(long id) throws AppException;

	public Coterie getCoterieByRootAgentId(long rootAgentId)
			throws AppException;

	public List<Coterie> getCoterieList() throws AppException;

	public List<Coterie> getCoterieList(Long type) throws AppException;

	public List<Coterie> getValidCoterieList() throws AppException;
}

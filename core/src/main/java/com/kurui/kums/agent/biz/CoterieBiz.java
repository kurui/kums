package com.kurui.kums.agent.biz;

import java.util.List;

import com.kurui.kums.agent.Coterie;
import com.kurui.kums.agent.CoterieListForm;
import com.kurui.kums.base.exception.AppException;

public interface CoterieBiz {

	public List list(CoterieListForm agentCoterieListForm) throws AppException;

	public long delete(long id) throws AppException;

	public void deleteCoterie(Long id) throws AppException;

	public long save(Coterie agentCoterie) throws AppException;

	public long update(Coterie agentCoterie) throws AppException;

	public Coterie getCoterieById(long id) throws AppException;

	public List<Coterie> getCoterieList() throws AppException;

	public List<Coterie> getCoterieList(Long type) throws AppException;

}

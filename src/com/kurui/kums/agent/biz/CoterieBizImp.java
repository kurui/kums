package com.kurui.kums.agent.biz;

import java.util.List;

import com.kurui.kums.agent.biz.CoterieBiz;
import com.kurui.kums.agent.Coterie;
import com.kurui.kums.agent.CoterieListForm;
import com.kurui.kums.agent.dao.CoterieDAO;
import com.kurui.kums.base.exception.AppException;

public class CoterieBizImp implements CoterieBiz {
	private CoterieDAO coterieDAO;

	public List list(CoterieListForm coterieListForm) throws AppException {
		return coterieDAO.list(coterieListForm);
	}

	public long delete(long id) throws AppException {
		try {
			coterieDAO.delete(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void deleteCoterie(Long id) throws AppException {
		Coterie coterie = getCoterieById(id);
		coterie.setStatus(Coterie.STATES_0);// 将状态变为无效
		update(coterie);
	}

	public long save(Coterie coterie) throws AppException {
		return coterieDAO.save(coterie);
	}

	public long update(Coterie coterie) throws AppException {
		return coterieDAO.update(coterie);
	}

	public Coterie getCoterieById(long id) throws AppException {
		return coterieDAO.getCoterieById(id);
	}

	public List<Coterie> getCoterieList() throws AppException {
		return coterieDAO.getCoterieList();
	}

	public List<Coterie> getCoterieList(Long type) throws AppException {
		return coterieDAO.getCoterieList(type);
	}

	public void setCoterieDAO(CoterieDAO coterieDAO) {
		this.coterieDAO = coterieDAO;
	}
}

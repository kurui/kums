package com.kurui.kums.library.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.library.News;
import com.kurui.kums.library.NewsListForm;
import com.kurui.kums.library.biz.NewsBiz;
import com.kurui.kums.right.SysUser;
import com.kurui.kums.right.UserRightInfo;

public class NewsListAction extends BaseAction {
	private NewsBiz newsBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		NewsListForm nlf = (NewsListForm) form;
		if (nlf == null)
			nlf = new NewsListForm();

		List list = newsBiz.getNews(nlf);
		if (nlf.getSelectedItems() != null) {
			int[] items = new int[nlf.getSelectedItems().length];
			for (int i = 0; i < nlf.getSelectedItems().length; i++) {
				items[i] = 0;
			}
			nlf.setSelectedItems(items);
		}
		nlf.setList(list);
		request.setAttribute("nlf", nlf);
		forwardPage = "listNews";
		return mapping.findForward(forwardPage);
	}
	
	//动态栏
	public ActionForward listNotice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		NewsListForm nlf = (NewsListForm) form;
		if (nlf == null)
			nlf = new NewsListForm();

		nlf.setType(News.TYPE_1);
		List list = newsBiz.getNews(nlf);
		request.setAttribute("newsList", list);
		
		nlf.setType(News.TYPE_10);
		list = newsBiz.getNews(nlf);
		request.setAttribute("eventList", list);
		
		nlf.setType(News.TYPE_20);
		list = newsBiz.getNews(nlf);
		request.setAttribute("suggestList", list);
		
		request.setAttribute("nlf", nlf);
		forwardPage = "listNewsNotice";
		return mapping.findForward(forwardPage);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		NewsListForm nlf = (NewsListForm) form;
		String forwardPage = "";
		SysUser user = this.getUserByURI(request);

		News news = new News();		
		news.setThisAction("insert");
		news.setType(nlf.getType());
		
		
		request.setAttribute("news", news);
		forwardPage = "editNews";

		return (mapping.findForward(forwardPage));
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		SysUser user = this.getUserByURI(request);
		String forwardPage = "";
		News news = null;
		if (user != null) {
			NewsListForm nlf = (NewsListForm) form;
			int id = 0;
			if (nlf.getSelectedItems().length > 0) {
				id = nlf.getSelectedItems()[0];
			} else
				id = nlf.getId();
			if (id > 0) {
				news = newsBiz.getNewsById(id);
				news.setThisAction("update");
				request.setAttribute("news", news);
			} else
				request.setAttribute("news", new News());
			forwardPage = "editNews";
		} else {
			request.getSession().invalidate();
			return mapping.findForward("exit");
		}
		return (mapping.findForward(forwardPage));
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		NewsListForm nlf = (NewsListForm) form;

		String forwardPage = "";
		int id = 0;
		Inform inf = new Inform();
		try {
			for (int i = 0; i < nlf.getSelectedItems().length; i++) {
				id = nlf.getSelectedItems()[i];
				if (id > 0) {
					newsBiz.deleteNewsById(id);
					inf.setMessage("您已经成功删除了新闻！");
				}
			}
			inf.setForwardPage("/information/newsList.do");
			inf.setParamId("thisAction");
			inf.setParamValue("list");
		} catch (Exception ex) {
			inf.setMessage("删除新闻出错！错误信息是：" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		int id = Integer.parseInt(request.getParameter("id"));

		News news = (News) newsBiz.getNewsById(id);
		news.setReadNum(news.getReadNum()+1);
		newsBiz.save(news);

		news.setThisAction("view");
		request.setAttribute("news", news);

		forwardPage = "viewNews";
		return (mapping.findForward(forwardPage));
	}

	public SysUser getUserByURI(HttpServletRequest request) {
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		if (uri != null && uri.getUser() != null)
			return uri.getUser();
		else {
			return null;
		}
	}

	public void setNewsBiz(NewsBiz newsBiz) {
		this.newsBiz = newsBiz;
	}
}

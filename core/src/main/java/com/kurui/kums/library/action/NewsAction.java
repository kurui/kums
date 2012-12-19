package com.kurui.kums.library.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.library.News;
import com.kurui.kums.library.biz.NewsBiz;
import com.kurui.kums.right.SysUser;
import com.kurui.kums.right.UserRightInfo;

public class NewsAction extends BaseAction {
	private NewsBiz newsBiz;

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		News news = (News) form;
		Inform inf = new Inform();
		try {
			News tempNews = newsBiz.getNewsById(news.getId());

			tempNews.setContent(news.getContent());
			tempNews.setTitle(news.getTitle());
			tempNews.setRank(news.getRank());
			tempNews.setStatus(news.getStatus());
			tempNews.setCreateTime(new Timestamp(System.currentTimeMillis()));
			newsBiz.updateInfo(tempNews);
			request.setAttribute("news", tempNews);

			String forwardUrl="/information/newsList.do?thisAction=view&id="+tempNews.getId();
			return new ActionRedirect(forwardUrl);
			

		} catch (Exception ex) {
			inf.setMessage("更新新闻出错！错误信息是：" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {		
		News news = (News) form;
		
		Inform inf = new Inform();
			try {
				News tempNews = new News();
				tempNews
						.setCreateTime(new Timestamp(System.currentTimeMillis()));
				tempNews.setContent(news.getContent());
				tempNews.setTitle(news.getTitle());
				SysUser user = this.getUserByURI(request);
				tempNews.setUserNo(user.getUserNo());
				tempNews.setRank(news.getRank());
				tempNews.setStatus(news.getStatus());
				tempNews.setReadNum(new Long(0));
				newsBiz.save(tempNews);
				request.setAttribute("news", tempNews);

				String forwardUrl="/information/newsList.do?thisAction=view&id="+tempNews.getId();
				return new ActionRedirect(forwardUrl);
			} catch (Exception ex) {
				inf.setMessage("添加新闻出错！错误信息是：" + ex.getMessage());
				inf.setBack(true);
			}
		return forwardInformPage(inf, mapping, request);
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

	public NewsBiz getNewsBiz() {
		return newsBiz;
	}

	public void setNewsBiz(NewsBiz newsBiz) {
		this.newsBiz = newsBiz;
	}

}

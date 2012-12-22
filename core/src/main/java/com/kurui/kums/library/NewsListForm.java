package com.kurui.kums.library;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import com.kurui.kums.base.struts.ListActionForm;

public class NewsListForm extends ListActionForm {
	private static final long serialVersionUID = 1L;

	private int id = 0;

	private String title = "";
	private String content = "";
	private Long type;
	private Long status;
	private Long rank;
	private String userName = "";

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		title = "";
		userName = "";
		thisAction = "";
		this.setIntPage(1);
	}

}

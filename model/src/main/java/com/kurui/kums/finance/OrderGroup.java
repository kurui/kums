package com.kurui.kums.finance;

import com.kurui.kums.finance.entity._OrderGroup;

public class OrderGroup extends _OrderGroup
{
	private static final long serialVersionUID = 1L;

	public String getNo()
	{
		if (this.no == null || this.no.equals("")) { return "G000000000000"; }
		return no;
	}
}

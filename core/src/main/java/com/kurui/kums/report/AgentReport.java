package com.kurui.kums.report;

import com.kurui.kums.agent.Agent;
import com.kurui.kums.base.util.StringUtil;

public class AgentReport extends CommonReport {
	
	private String sex;
	private String sexInfo;
	
	public AgentReport(){
		
	}
	
	public AgentReport(Long sex,Long count){
		this.itemKey=sex+"";
		this.itemName=new Agent().getSexInfo(sex);
		this.itemValue=count+"";
	}
	
	public AgentReport(Long key,Long count,String name){
		if(StringUtil.isEmpty(name)==false){
			if("TYPE".equals(name)){
				this.itemKey=key+"";
				this.itemName=new Agent().getTypeInfo(key);
				this.itemValue=count+"";
			}else if("SEX".equals(name)){
				this.itemKey=key+"";
				this.itemName=new Agent().getSexInfo(key);
				this.itemValue=count+"";
			}
		}
		
	}

}

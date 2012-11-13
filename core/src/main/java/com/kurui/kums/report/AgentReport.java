package com.kurui.kums.report;

import com.kurui.kums.agent.Agent;
import com.kurui.kums.base.util.StringUtil;

public class AgentReport extends CommonReport {
	
	private Long sex;
	private String sexInfo;
	private Long type;
	private String typeInfo;
	private Long companyId;
	private String companyName;
	
	public AgentReport(){
		
	}

	public AgentReport(Long id,String text,Long count,String name){
		if (StringUtil.isEmpty(name)==false) {
			if ("COMPANY".equals(name)) {
				this.companyId=id;
				this.companyName=text;
				this.itemValue=count+"";
			} else {

			}
		}
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
	
	
	public AgentReport(Long type,Long sex,Long count,String name){
		if(StringUtil.isEmpty(name)==false){
			if("TYPE_SEX".equals(name)){
				this.type=type;
				this.typeInfo=new Agent().getTypeInfo(type);
				this.sex=sex;
				this.sexInfo=new Agent().getSexInfo(sex);
				this.itemValue=count+"";
			}else {
				
			}
		}else{
			
		}
	}


	public Long getSex() {
		return sex;
	}


	public void setSex(Long sex) {
		this.sex = sex;
	}


	public String getSexInfo() {
		return sexInfo;
	}


	public void setSexInfo(String sexInfo) {
		this.sexInfo = sexInfo;
	}


	public Long getType() {
		return type;
	}


	public void setType(Long type) {
		this.type = type;
	}


	public String getTypeInfo() {
		return typeInfo;
	}


	public void setTypeInfo(String typeInfo) {
		this.typeInfo = typeInfo;
	}

	
	
}

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html>
	<body>
		<hr>
		<div id="searchBarObj" class="searchBar">
			<table id="searchBarObj" cellpadding="0" cellspacing="0" border="0" class="searchPanel" style="width: 100%;">
				<tr>
					<td>
						订单
						<html:text property="orderNo" ondblclick="this.value=''" styleClass="colorblue2 p_5" style="width:120px;" />
					</td>
					<td>
						<html:text property="operatorName" ondblclick="JavaScript:this.value=''" styleClass="colorblue2 p_5" style="width:60px;" />
						近
						<html:text property="recentlyDay" styleId="recentlyDayId" ondblclick="this.value=''" title="0,空格表示所有天" style="width:30px" maxlength="3" />
						天
					</td>
					<td>
						开始
						<html:text property="startDate" styleClass="colorblue2 p_5" style="width:120px;" onfocus="WdatePicker({startDate:'%y-%M-%D 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"
							readonly="true" />
						结束
						<html:text property="endDate" styleClass="colorblue2 p_5" style="width:120px;" onfocus="WdatePicker({startDate:'%y-%M-%D 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"
							readonly="true" />
					</td>
					<td>
						排序
						<html:select property="orderBy" styleClass="colorblue2 p_5" style="width:80px;">
							<html:option value="businessTime">业务时间</html:option>
							<html:option value="updateTime">修改时间</html:option>
						</html:select>
					</td>
					<td>
						<c:if test="${ulf.showType=='listChart'}">
							<input type="button" name="button" alt="统计图表" class="btn_select_model_2_2" />
							<input type="button" name="button" alt="明细账" class="btn_select_model_1_1" onclick="selectList();" />
						</c:if>
						<c:if test="${ulf.showType=='listData'}">
							<input type="button" name="button" alt="统计图表" class="btn_select_model_2_1" onclick="selectChart();" />
							<input type="button" name="button" alt="明细账" class="btn_select_model_1_2" />
						</c:if>
						<input type="button" name="button" class="button1" onclick="selectReport();" value="导出报表" />
						<script type="text/javascript">
					function selectChart(){
						 document.forms[0].showType.value="listChart";
	    				 document.forms[0].submit();
					}
					function selectList(){
						 document.forms[0].showType.value="listData";
	    				 document.forms[0].submit();
					}
					function selectReport(){
						 document.forms[0].showType.value="listReport";
	    				 document.forms[0].submit();
					}
				</script>
					</td>
				</tr>
				<tr style="display: none">
					<td>
						买入
						<html:select property="fromPlatformId" styleClass="colorblue2 p_5" style="width:120px;">
							<html:option value="">--请选择--</html:option>
							<c:forEach items="${inPlatformList}" var="platform">
								<html:option value="${platform.id}">
									<c:out value="${platform.name }" />
								</html:option>
							</c:forEach>
						</html:select>
					</td>
					<td>
						付款
						<html:select property="fromAccountId" styleClass="colorblue2 p_5" style="width:120px;">
							<html:option value="">--请选择--</html:option>
							<c:forEach items="${outAccountList}" var="account">
								<html:option value="${account.id }">
									<c:out value="${account.showName }" />
								</html:option>
							</c:forEach>
						</html:select>
					</td>
					<td>
						卖出
						<html:select property="toPlatformId" styleClass="colorblue2 p_5" style="width:120px;">
							<html:option value="">--请选择--</html:option>
							<c:forEach items="${outPlatformList}" var="platform">
								<html:option value="${platform.id }">
									<c:out value="${platform.showName }" />
								</html:option>
							</c:forEach>
						</html:select>
					</td>
					<td>
						收款
						<html:select property="toAccountId" styleClass="colorblue2 p_5" style="width:120px;">
							<html:option value="">--请选择--</html:option>
							<c:forEach items="${inAccountList}" var="account">
								<html:option value="${account.id }">
									<c:out value="${account.showName }" />
								</html:option>
							</c:forEach>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						内容
						<html:text property="memo" ondblclick="this.value=''" styleClass="colorblue2 p_5" style="width:120px;" />
					</td>
					<td>
						<html:select property="orderType" styleClass="colorblue2 p_5" style="width:100px;">
							<html:option value="0">业务类型</html:option>
							<html:option value="1201">管理费用</html:option>
							<html:option value="15">主营业务</html:option>
							<html:option value="1400">债务</html:option>
							<html:option value="1300">债券</html:option>
						</html:select>
					</td>

					<td>
						<input type="submit" name="button" id="button" value="提交" class="submit greenBtn" />
					</td>
					<td>
					</td>
					<td>
						<input type="button" style="float: right" onclick="shrinkSearchBar()" value="收起" />
					</td>
				</tr>
			</table>
			<script>
    function selectRecent(){
        var tempDay="<c:out value='${param.recentlyDay}'/>";
        if(tempDay==null||tempDay==""){
        	tempDay="1";
        }
       	var ifRecentlyObj=document.getElementById("ifRecentlyObj");
      	if(ifRecentlyObj.checked){
      		ifRecentlyObj.Checked=false;
      		ifRecentlyObj.value="0";      		
      		document.getElementById("recentlyDayId").value=tempDay;
      	}else{
      		ifRecentlyObj.Checked=true;
      		ifRecentlyObj.value="1";
      		document.getElementById("recentlyDayId").value="";
      	}      	
      }
      $(function() {		
      	   var tempDay="<c:out value='${ulf.recentlyDay}'/>";
        	if(tempDay==null||tempDay==""){
        		tempDay="1";
        	}
        	
		  var recentlyDayValue=$("#recentlyDayId").val();
		  if(recentlyDayValue==0){
		   $("#recentlyDayId").val('');
		   $("#ifRecentlyObj").attr("checked","");
		  } else {
		    $("#recentlyDayId").val(tempDay);
		  }
		});	  
		
     function shrinkSearchBar(){
      	var searchBarObj=document.getElementById("searchBarObj");
      	var showSearchBarObj=document.getElementById("showSearchBarObj");
      	
      	if(searchBarObj.style.display==''){
      		searchBarObj.style.display='none';
      		showSearchBarObj.style.display='';
      	}else{
      		searchBarObj.style.display='';
      		showSearchBarObj.style.display='none';
      	}      	
      }
</script>
		</div>
		<input type="button" id="showSearchBarObj" style="display: none; float: right" onclick="shrinkSearchBar()" value="高级搜索" />
	</body>
</html>
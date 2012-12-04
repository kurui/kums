<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<c:if test="${URI==null}">
	<script language="JavaScript">
   	top.location="<%=path%>/login.jsp" 
	</script>
</c:if>
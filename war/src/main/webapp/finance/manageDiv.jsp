<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<script src="../_js/tsms/loadManage.js" type="text/javascript"></script>
<div id="dialog9" title="申请支付">
<form action="../airticket/airticketOrder.do?thisAction=applyPayOrder" id="form9"   method="post" >
	    <input id="oId9" name="id" type="hidden" />
	    <input id="groupId9" name="groupId" type="hidden" />
	    <table>
		   <jsp:include page="../transaction/plat2.jsp?currentObjId=9"></jsp:include>
			<tr>
			     <td><label for="password">PNR</label></td>
			     <td><input type="text" name="pnr" id="pnr9"  class="text ui-widget-content ui-corner-all" /></td>
		    </tr>
		     <tr>
			     <td><label for="password">订单号</label></td>
			     <td><input type="text" name="airOrderNo" id="airOrderNo9"  class="text ui-widget-content ui-corner-all" /></td>
		    </tr>
		    <tr>
		     <td><label for="password">金额</label></td>
		     <td><input type="text" name="totalAmount" id="totalAmount9" value="0" onkeyup="calculationLiren('tmpTotalAmount9','totalAmount9','liruen9');"
		     onkeydown="calculationLiren('tmpTotalAmount9','totalAmount9','liruen9');"
		       class="text ui-widget-content ui-corner-all" />
		     <input type="hidden" id="tmpTotalAmount9"/></td>
		    </tr>		
			<tr>
		     <td><label for="password">政策</label></td>
		     <td><input type="text" name="rebate" id="rebate9"   value="0"  class="text ui-widget-content ui-corner-all" /></td>
		    </tr>
		     <tr>
			     <td><label>时间</label></td>
			     <td><input type="text" name="optTime" id="optTime9"  ondblclick="WdatePicker({startDate:'%y-%M-%D 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" />鼠标双击修改</td>
	       </tr>  
		    <tr>
		     <td><label for="password" style="color: red">利润</label></td>
		     <td><input type="text" name="liruen" id="liruen9"   value="0"  class="text ui-widget-content ui-corner-all" style="color: red"/></td>
		    </tr>
			<tr>
			<td colspan="2" align="center">
			  <input value="提交" type="button" id="submitButton9"  onclick="submitForm9()" class="button1">
			</td>
			</tr>
		</table>
	</form>
</div>

<div id="dialog11" title="备注">
	<form action="../airticket/airticketOrder.do?thisAction=editRemark"  method="post" id="form11" >		      
		<table>
		  <tr>		    
		     <td>
		      <input id="oId11" name="id" type="hidden" />
		      <textarea rows="12" cols="60" name="memo" id="memo11"></textarea>		     
		     </td>
		  </tr>
		  <tr>
			<td align="center">
			<input value="提交" type="submit" class="button1" id="submitButton11">
			</td>
			</tr>			   
		</table>
	</form>
</div>
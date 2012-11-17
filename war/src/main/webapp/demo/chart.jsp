<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="org.jfree.chart.ChartRenderingInfo"%>
<%@ page import="org.jfree.data.general.DefaultPieDataset"%>
<%@ page import="org.jfree.chart.servlet.ServletUtilities"%>
<%@ page import="org.jfree.chart.JFreeChart"%>
<%@ page import="org.jfree.chart.plot.PiePlot3D"%>
<%@ page import="org.jfree.chart.urls.StandardPieURLGenerator"%>
<%@ page import="org.jfree.chart.entity.StandardEntityCollection"%>
<%@ page import="org.jfree.chart.ChartUtilities"%>
<%@ page import="java.io.PrintWriter"%>

<%
	DefaultPieDataset data = new DefaultPieDataset();
	data.setValue("高中以下", 370);
	data.setValue("高中", 1530);
	data.setValue("大专", 5700);
	data.setValue("本科", 8280);
	data.setValue("硕士", 4420);
	data.setValue("博士", 80);
	
	PiePlot3D plot = new PiePlot3D(data);// 3D饼图 
	plot.setURLGenerator(new StandardPieURLGenerator("barview.jsp"));// 设定链接
	JFreeChart chart = new JFreeChart("",
			JFreeChart.DEFAULT_TITLE_FONT, plot, true);
	chart.setBackgroundPaint(java.awt.Color.white);// 可选，设置图片背景色
	chart.setTitle("程序员学历情况调查表");// 可选，设置图片标题 //
	//plot.setToolTipGenerator(new StandardPieItemLabelGenerator());

	StandardEntityCollection sec = new StandardEntityCollection();
	ChartRenderingInfo info = new ChartRenderingInfo(sec);
	
	
	 // 500是图片长度，300是图片高度 
	String filename = ServletUtilities.saveChartAsPNG(chart, 500, 300,
			info, session);

	PrintWriter w = new PrintWriter(out);// 输出MAP信息
	ChartUtilities.writeImageMap(w, "map0", info, false);

	String graphURL = request.getContextPath()
			+ "/servlet/DisplayChart?filename=" + filename;
%>

<html>
	<head>
	</head>
	<body>
		<P ALIGN="CENTER">
			<img src="<%=graphURL%>" width=500 height=300 border=0 usemap="#map0">
		</P>
	</body>
</html>
package com.kurui.kums.base.chart.test;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.urls.StandardPieURLGenerator;
import org.jfree.data.general.DefaultPieDataset;

import com.kurui.kums.base.exception.AppException;

public class TestServlet {

	public static String test1(HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		try {

			HttpSession session = request.getSession();

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
			chart.setTitle("程序员学历情况调查表");// 可选，设置图片标题
			
//			plot.setToolTipGenerator(new StandardPieToolTipGenerator());//StandardPieItemLabelGenerator()

			//热点链接
			StandardEntityCollection sec = new StandardEntityCollection();
			ChartRenderingInfo info = new ChartRenderingInfo(sec);
			request.setAttribute("ChartRenderingInfo", info);

			// 设置输出编码格式
//			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();

			PrintWriter w = new PrintWriter(out);// 输出MAP信息
			
//			ServletUtil.getPrintWriter(response)

			// 500是图片长度，300是图片高度
			String filename = ServletUtilities.saveChartAsPNG(chart, 500, 300,
					info, session);
			ChartUtilities.writeImageMap(w, "map0", info, false);

			String graphURL = request.getContextPath()
					+ "/servlet/DisplayChart?filename=" + filename;
			return graphURL;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

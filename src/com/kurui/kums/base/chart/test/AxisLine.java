//package com.kurui.kums.base.chart.test;
//
//import java.awt.Color;
//import java.awt.Image;
//import java.awt.Toolkit;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.imageio.ImageIO;
//import javax.swing.JPanel;
//
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.ChartUtilities;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.axis.NumberAxis;
//import org.jfree.chart.plot.PolarPlot;
//import org.jfree.data.xy.XYDataset;
//import org.jfree.data.xy.XYSeries;
//import org.jfree.data.xy.XYSeriesCollection;
//import org.jfree.ui.ApplicationFrame;
//import org.jfree.ui.RefineryUtilities;
//
//// 带背景图的雷达图
//
//public class AxisLine {
//	private static XYDataset createDataset(int members) {
//		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
//		XYSeries xyseries;
//		List xysp = null;
//		if (members == 6) {
//			xysp = XYSeries6Point();
//		} else {
//			if (members == 20) {
//				xysp = XYSeries20Point();
//			}
//		}
//		if (xysp != null) {
//			for (int i = 0; i < xysp.size(); i++) {
//				xyseries = new XYSeries(((XYSeriesPoint) xysp.get(i)).getName());
//				xyseries.add(((XYSeriesPoint) xysp.get(i)).getDegree(), 0.0D);// 原点
//				xyseries.add(((XYSeriesPoint) xysp.get(i)).getDegree(), 100.0D);// 最大值
//				xyseriescollection.addSeries(xyseries);
//			}
//		}
//
//		XYSeries xyseries0 = new XYSeries("本企业水平");
//		xyseries0.add(0.0D, 91D);
//		xyseries0.add(90D, 100D);
//		xyseries0.add(180D, 78D);
//		xyseries0.add(270D, 84D);
//		xyseriescollection.addSeries(xyseries0);
//
//		XYSeries xyseries1 = new XYSeries("行业优秀水平");
//		xyseries1.add(90D, 11.199999999999999D);
//		xyseries1.add(180D, 21.399999999999999D);
//		xyseries1.add(250D, 17.300000000000001D);
//		xyseries1.add(355D, 10.9D);
//
//		xyseriescollection.addSeries(xyseries1);
//		XYSeries xyseries2 = new XYSeries("央企优秀水平");
//		xyseries2.add(90D, 90.199999999999999D);
//		xyseries2.add(180D, 90.399999999999999D);
//		xyseries2.add(250D, 90.300000000000001D);
//		xyseries2.add(355D, 100D);
//		xyseriescollection.addSeries(xyseries2);
//
//		return xyseriescollection;
//	}
//
//	private static String createChart(XYDataset xydataset, File file) {
//		JFreeChart jfreechart = ChartFactory.createPolarChart("企业信息化水平雷达图",
//				xydataset, true, false, false);
//		jfreechart.setBackgroundPaint(Color.white);
//
//		try {
//			Image image = ImageIO.read(new File("D:\\png\\Sunset.jpg"));
//			// jfreechart.setBackgroundImage(image);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		PolarPlot polarplot = (PolarPlot) jfreechart.getPlot();
//		polarplot.setBackgroundAlpha(0.0f);
//		polarplot.setBackgroundPaint(Color.white);
//
//		polarplot.setAngleGridlinesVisible(false);
//
//		NumberAxis numberaxis = (NumberAxis) polarplot.getAxis();
//		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//
//		outputPNG(jfreechart, file);// 输出到文件
//
//		System.out.println(Thread.currentThread().getContextClassLoader()
//				.getResource(""));
//		System.out.println(AxisLine.class.getClassLoader().getResource(""));
//		System.out.println(ClassLoader.getSystemResource(""));
//		System.out.println(AxisLine.class.getResource(""));
//		System.out.println(AxisLine.class.getResource("/"));// Class文件所在路径
//		System.out.println(new File("/").getAbsolutePath());
//		System.out.println(System.getProperty("user.dir"));
//
//		return file.getPath();
//	}
//
//	public static void main(String args[]) {
//		createChart(createDataset(6), new File("d:\\png\\6.png"));
//		createChart(createDataset(20), new File("d:\\png\\20.png"));
//	}
//
//	public static void outputPNG(JFreeChart chart, File file) {
//		try {
//			ChartUtilities.saveChartAsPNG(file, chart, 640, 640);
//		} catch (Exception e) {
//
//		}
//	}
//
//	private static List XYSeries6Point() {
//		List list = new ArrayList();
//		XYSeriesPoint xysp;
//		String labels6[] = new String[] { "领导力", "基础建设", "应用、集成与创新",
//				"IT服务管理与IT治理", "信息化人力资源", "信息化效益" };
//		for (int i = 0; i < labels6.length; i++) {
//			xysp = new XYSeriesPoint();
//			xysp.setName(labels6[i]);
//			xysp.setDegree(i * 60D);
//
//			list.add(xysp);
//		}
//
//		return list;
//	}
//
//	private static List XYSeries20Point() {
//		List list = new ArrayList();
//		XYSeriesPoint xysp;
//		String labels20[] = new String[] { "认知度与推动力", "信息化战略与规划", "信息化工作执行力",
//				"信息化投资策略与投资结构", "系统架构技术路线", "信息化标准规范与管控体系", "主营业务信息化", "管理信息化",
//				"电子商务", "信息化集成水平", "变革与创新", "信息化建设项目管理", "信息安全管理", "IT服务管理",
//				"IT绩效管理", "IT治理", "信息化培训", "信息化人才", "资金利用效率", "资金管控能力" };
//		for (int i = 0; i < labels20.length; i++) {
//			xysp = new XYSeriesPoint();
//			xysp.setName(labels20[i]);
//			xysp.setDegree(i * 18D);
//			list.add(xysp);
//		}
//		return list;
//	}
//}

package com.kurui.kums.base.chart.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

import com.kurui.kums.base.chart.MySpiderWebPlot;

public class MySpriderWebPlotTest {
	public static void main(String args[]) {
		// 在SWING中显示
		JFrame jf = new JFrame();
		jf.add(erstelleSpinnenDiagramm());
		jf.pack();
		jf.setVisible(true);
		// 将JFreeChart保存为图片存在文件路径中
		saveAsFile("E:/chart/MySpiderWebPlot.png", 500, 400);
	}

	public static JPanel erstelleSpinnenDiagramm() {
		JFreeChart jfreechart = createChart();
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		return chartpanel;
	}

	public static void saveAsFile(String outputPath, int weight, int height) {
		FileOutputStream out = null;
		try {
			File outFile = new File(outputPath);
			if (!outFile.getParentFile().exists()) {
				outFile.getParentFile().mkdirs();
			}
			out = new FileOutputStream(outputPath);

			// 保存为PNG
			ChartUtilities.writeChartAsPNG(out, createChart(), weight, height);
			// 保存为JPEG
			// ChartUtilities.writeChartAsJPEG(out, chart, 500, 400);
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// do nothing
				}
			}
		}
	}

	public static JFreeChart createChart() {
		MySpiderWebPlot spiderwebplot = new MySpiderWebPlot(createDataset());
		JFreeChart jfreechart = new JFreeChart("前三个季度水果销售报告",
				TextTitle.DEFAULT_FONT, spiderwebplot, false);
		LegendTitle legendtitle = new LegendTitle(spiderwebplot);
		legendtitle.setPosition(RectangleEdge.BOTTOM);
		jfreechart.addSubtitle(legendtitle);
		return jfreechart;
	}

	public static DefaultCategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String group1 = "苹果 ";

		dataset.addValue(5, group1, "一月份");
		dataset.addValue(6, group1, "二月份");
		dataset.addValue(4, group1, "三月份");
		dataset.addValue(2, group1, "四月份");
		dataset.addValue(5, group1, "五月份");
		dataset.addValue(5, group1, "六月份");
		dataset.addValue(5, group1, "七月份");
		dataset.addValue(8, group1, "八月份");

		String group2 = "橙子";
		dataset.addValue(3, group2, "一月份");
		dataset.addValue(3, group2, "二月份");
		dataset.addValue(4, group2, "三月份");
		dataset.addValue(7, group2, "四月份");
		dataset.addValue(4, group2, "五月份");
		dataset.addValue(5, group2, "六月份");
		dataset.addValue(3, group2, "七月份");
		dataset.addValue(3, group2, "八月份");

		String group3 = "香蕉";
		dataset.addValue(4, group3, "一月份");
		dataset.addValue(5, group3, "二月份");
		dataset.addValue(2, group3, "三月份");
		dataset.addValue(5, group3, "四月份");
		dataset.addValue(6, group3, "五月份");
		dataset.addValue(6, group3, "六月份");
		dataset.addValue(4, group3, "七月份");
		dataset.addValue(4, group3, "八月份");
		return dataset;
	}
}

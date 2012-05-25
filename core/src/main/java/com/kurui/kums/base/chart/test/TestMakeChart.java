package com.kurui.kums.base.chart.test;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.time.TimeSeries;

import com.kurui.kums.base.chart.ChartUtil;

/**
 * 实际取色的时候一定要16位的，这样比较准确
 * 
 * @author new
 */
public class TestMakeChart {
	// private static final String CHART_PATH = "F:/project/temp/chart/";

	public static void main(String[] args) {
		// 生成饼状图
//		makePieChart();
//		// 生成单组柱状图
//		makeBarChart();
//		// 生成多组柱状图
//		makeBarGroupChart();
//		// 生成堆积柱状图
//		makeStackedBarChart();
		// 生成折线图
		makeLineAndShapeChart();
	}

	/**
	 * 生成折线图
	 */
	public static void makeLineAndShapeChart() {
		double[][] data = new double[][] { { 672, 766, 223, 540, 126 },
				{ 325, 521, 210, 340, 106 }, { 332, 256, 523, 240, 526 } };
		String[] rowKeys = { "苹果", "梨子", "葡萄" };
		String[] columnKeys = { "北京", "上海", "广州", "成都", "深圳" };
		CategoryDataset dataset = ChartUtil.getBarData(data, rowKeys,
				columnKeys);
		ChartUtil.createTimeXYChart(dataset, "折线图", "x轴", "y轴", "F:/project/temp/chart/",
				"lineAndShap.png");
	}

	/**
	 * 生成分组的柱状图
	 */
	public static void makeBarGroupChart() {
		double[][] data = new double[][] { { 672, 766, 223, 540, 126 },
				{ 325, 521, 210, 340, 106 }, { 332, 256, 523, 240, 526 } };
		String[] rowKeys = { "苹果", "梨子", "葡萄" };
		String[] columnKeys = { "北京", "上海", "广州", "成都", "深圳" };
		CategoryDataset dataset = ChartUtil.getBarData(data, rowKeys,
				columnKeys);
		ChartUtil.createBarChart(dataset, "x坐标", "y坐标", "柱状图", "F:/project/temp/chart/",
				"barGroup.png");
	}

	/**
	 * 生成柱状图
	 */
	public static void makeBarChart() {
		double[][] data = new double[][] { { 672, 766, 223, 540, 126 } };
		String[] rowKeys = { "苹果" };
		String[] columnKeys = { "北京", "上海", "广州", "成都", "深圳" };
		CategoryDataset dataset = ChartUtil.getBarData(data, rowKeys,
				columnKeys);
		ChartUtil.createBarChart(dataset, "x坐标", "y坐标", "柱状图", "F:/project/temp/chart/", "bar.png");
	}

	/**
	 * 生成堆栈柱状图
	 */
	public static void makeStackedBarChart() {
		double[][] data = new double[][] { { 0.21, 0.66, 0.23, 0.40, 0.26 },
				{ 0.25, 0.21, 0.10, 0.40, 0.16 } };
		String[] rowKeys = { "苹果", "梨子" };
		String[] columnKeys = { "北京", "上海", "广州", "成都", "深圳" };
		CategoryDataset dataset = ChartUtil.getBarData(data, rowKeys,
				columnKeys);
		ChartUtil.createStackedBarChart(dataset, "x坐标", "y坐标", "柱状图", "F:/project/temp/chart/",
				"stsckedBar.png");
	}

	/**
	 * 生成饼状图
	 */
	public static void makePieChart() {
		double[] data = { 9, 91 };
		String[] keys = { "失败率", "成功率" };

		ChartUtil.createValidityComparePimChar(ChartUtil.getDataPieSetByUtil(data, keys),
				"饼状图", "F:/project/temp/chart/", "pie2.png", keys);
	}

}

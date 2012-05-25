package com.kurui.kums.base.chart.test;

import java.awt.BorderLayout;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

public class JslideJPanel extends JPanel implements ChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ChartPanel chartpanel = null;

	private JSlider sliderWest = null;

	private JSlider sliderSouth = null;

	private int maxval = 100;

	private int minval = -maxval;

	private int lastValueX = 0;

	private int lastValueY = 0;

	private NumberAxis numberaxis_Y = null;

	private double topY = 0.0;

	private double lowY = 0.0;

	private double distanceY = 0.0;

	private double xishuY = 0.0;

	DateAxis dateaxis_X = null;

	private double topX = 0.0;

	private double lowX = 0.0;

	private double distanceX = 0.0;

	private double xishuX = 0.0;

	@SuppressWarnings("unchecked")
	public JslideJPanel(ChartPanel chartpanel, boolean canZoom) {
		if (true) {
			this.setLayout(new BorderLayout());
			this.chartpanel = chartpanel;
			this.sliderSouth = new JSlider(JSlider.HORIZONTAL, minval, maxval,
					0);
			sliderSouth.setMaximum(maxval);
			sliderSouth.setMinimum(minval);
			sliderSouth.setPaintTicks(true);
			sliderSouth.setMajorTickSpacing(20);
			sliderSouth.setMinorTickSpacing(10);
			sliderSouth.setPaintLabels(true);
			sliderSouth.setPaintTrack(true);
			sliderSouth.setSnapToTicks(true);
			Hashtable table = new Hashtable();
			table.put(new Integer(minval), new JLabel("缩小"));
			table.put(new Integer(maxval), new JLabel("放大"));
			sliderSouth.setLabelTable(table);
			sliderSouth.addChangeListener(this);
			this.sliderWest = new JSlider(JSlider.VERTICAL, minval, maxval, 0);
			/*
			 * setPaintTicks()方法是设置是否在JSlider加上刻度，若为true则下面两行才有作用。
			 * 设置大刻度与小刻度之间的距离(setMajorTickSpacing()与setMinorTickSpacing()方法).例如若大刻度间距离为30，
			 * 小刻度间距离为10，则表示2个大刻度间会有3个小刻度.
			 * setSnapToTicks()方法表示一次移动一个小刻度，而不再是一次移动一个单位刻度
			 */

			sliderWest.setMaximum(maxval);
			sliderWest.setMinimum(minval);
			sliderWest.setPaintTicks(true);
			sliderWest.setMajorTickSpacing(20);
			sliderWest.setMinorTickSpacing(10);
			sliderWest.setPaintLabels(true);
			sliderWest.setPaintTrack(true);
			// sliderWest.setSnapToTicks(true);
			sliderWest.setLabelTable(table);
			sliderWest.addChangeListener(this);
			this.add(chartpanel, BorderLayout.CENTER);
			this.add(sliderSouth, BorderLayout.SOUTH);
			this.add(sliderWest, BorderLayout.WEST);
			// 得到一些初始的参数
			JFreeChart chart = chartpanel.getChart();
			XYPlot xyplot = chart.getXYPlot();
			// 处理Y轴
			numberaxis_Y = (NumberAxis) xyplot.getRangeAxis();
			topY = numberaxis_Y.getRange().getUpperBound();
			lowY = numberaxis_Y.getRange().getLowerBound();
			distanceY = (topY - lowY) / (2 * maxval);
			xishuY = (maxval - 1) / (double) maxval;
			// 这个是为了防止放大时setRange(n,n)这种情况的出现。

			// 处理X轴
			dateaxis_X = (DateAxis) xyplot.getDomainAxis();
			// 最小范围是一天，得到的毫秒数是通过查询的日期+距离0点的毫秒数得到的
			topX = dateaxis_X.getRange().getUpperBound();
			lowX = dateaxis_X.getRange().getLowerBound();
			distanceX = (topX - lowX) / (2 * maxval);
			xishuX = (maxval - 1) / (double) maxval;

		} else {
			this.setLayout(new BorderLayout());
			this.chartpanel = chartpanel;
			this.add(chartpanel, BorderLayout.CENTER);
		}

	}

	public ChartPanel getChartpanel() {
		return chartpanel;
	}

	public void setChartpanel(ChartPanel chartpanel) {
		this.chartpanel = chartpanel;
	}

	public void stateChanged(ChangeEvent e) {
		System.out.println(sliderSouth.getValue() + "::::::::::::::::::::"
				+ sliderWest.getValue());

		if (e.getSource() == sliderWest) {
			// 处理y轴的缩放
			int slideInt = sliderWest.getValue();
			double distanceHere = distanceY * slideInt;
			if (slideInt >= 0) {
				// 乘以一个系数是为了防止setRange(a,a)这样的情况发生
				numberaxis_Y.setRange(lowY + distanceHere, topY - xishuY
						* distanceHere);
			} else {
				numberaxis_Y.setRange(lowY + distanceHere, topY - distanceHere);
			}
		} else if (e.getSource() == sliderSouth) {
			// 处理X轴的缩放
			int slideInt = sliderSouth.getValue();
			double distanceHere = distanceX * slideInt;
			if (slideInt >= 0) {
				// 乘以一个系数是为了防止setRange(a,a)这样的情况发生
				dateaxis_X.setRange(lowX + distanceHere, topX - xishuX
						* distanceHere);
			} else {
				dateaxis_X.setRange(lowX + distanceHere, topX - distanceHere);
			}
		}

		// DateAxis numberaxis_X = (DateAxis) xyplot.getDomainAxis();
		// //最小范围是一天，得到的毫秒数是通过查询的日期+距离0点的毫秒数得到的
		// System.out.println(numberaxis_X.getRange().getUpperBound()+":......."+(numberaxis_X.getRange().getUpperBound()%86400000)/3600000);
		// System.out.println(numberaxis_X.getRange().getLowerBound()+":......."+(numberaxis_X.getRange().getLowerBound()%86400000)/3600000);
		// // numberaxis_X.setRange(arg0, arg1)
		// 怎么区分四种操作
		// 1通过e.getSource() == sliderWest可以区分是哪边需要放大或者缩小
		// 2通过与前一个值的大小的比较，确定是放大还是缩小，通过值的比较确定放大缩小的次数
		// chartpanel.zoomInDomain(arg0, arg1);//domain是横坐标
		// if(e.getSource() == sliderWest)
		// {
		// chartpanel.zoomOutBoth(sliderSouth.getValue(),
		// sliderWest.getValue());
		// }else if(e.getSource() == sliderSouth){
		// chartpanel.zoomOutBoth(sliderSouth.getValue(),
		// sliderWest.getValue());
		// }

	}

}

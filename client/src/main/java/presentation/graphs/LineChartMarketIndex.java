package presentation.graphs;

/**
 * Created by pc on 2016/3/26.
 */

import bl.ShowIndexData;
import org.jfree.chart.ChartPanel;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import vo.IndexVO;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class LineChartMarketIndex {
    ChartPanel panel;
    TimeSeries timeSeries1 = new TimeSeries("", Day.class);

    public LineChartMarketIndex() throws IOException {
        createTimeSeriesChart();
    }

    public void createTimeSeriesChart() throws IOException {
        JFreeChart timeSeriesChart = ChartFactory.createTimeSeriesChart("", "日期", "成交量", createDataset(), true, true, false);
        timeSeriesChart.setBackgroundPaint(Color.WHITE);
        XYPlot plot = timeSeriesChart.getXYPlot();
        setXYPolt(plot);
        panel = new ChartPanel(timeSeriesChart, true);
        LegendTitle legendTitle = timeSeriesChart.getLegend();
        legendTitle.setVisible(false);
        Font font = new Font("宋体", Font.BOLD, 16);
        TextTitle title = new TextTitle("大盘指数", font);//副标题 
        //    TextTitle subtitle=new TextTitle("副标题", new Font("黑体",Font.BOLD,12));
        //   timeSeriesChart.addSubtitle(subtitle);
        timeSeriesChart.setTitle(title);//标题
        NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
        ValueAxis domainAxis = plot.getDomainAxis();

        /*------设置X轴坐标上的文字-----------*/
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
        /*------设置X轴的标题文字------------*/
        domainAxis.setLabelFont(new Font("黑体", Font.PLAIN, 18));
        /*------设置Y轴坐标上的文字-----------*/
        numberAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
        /*------设置Y轴的标题文字------------*/
        numberAxis.setLabelFont(new Font("黑体", Font.PLAIN, 18));
        // x轴 // 分类轴网格是否可见
        plot.setDomainGridlinesVisible(true);
        // y轴 //数据轴网格是否可见
        plot.setRangeGridlinesVisible(true);
        // 设置网格背景颜色
        plot.setBackgroundPaint(Color.white);
        // 设置网格竖线颜色
        plot.setDomainGridlinePaint(Color.pink);
        // 设置网格横线颜色
        plot.setRangeGridlinePaint(Color.pink);
        //设置日期显示格式
        DateAxis dateaxiss=(DateAxis)plot.getDomainAxis();
        SimpleDateFormat frm = new SimpleDateFormat("MM.dd");
    }

    public XYDataset createDataset() throws IOException {

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        IndexVO index = new ShowIndexData().getLatestIndexData();
        int length = index.getDate().length;
        for (int i = 1; i <= length; i++) {
            int year = Integer.parseInt(index.getDate()[length - i].substring(0, 4));
            int month = Integer.parseInt(index.getDate()[length - i].substring(5, 7));
            int day = Integer.parseInt(index.getDate()[length - i].substring(8, 10));
            Day d = new Day(day, month, year);
            timeSeries1.add(d, index.getVolume()[length - i]);

            dataset.addSeries(timeSeries1);
        }
        return dataset;
    }

    public void setXYPolt(XYPlot xyPolt) {
        xyPolt.setDomainGridlinePaint(Color.LIGHT_GRAY);
        xyPolt.setRangeGridlinePaint(Color.LIGHT_GRAY);
        XYItemRenderer r = xyPolt.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(false);
        }
    }

    public ChartPanel getChartPanel() {
        return panel;
    }

    public static void main(String[] args) throws IOException {
        JFrame jFrame = new JFrame();
        jFrame.add(new LineChartMarketIndex().getChartPanel());
        jFrame.setBounds(50, 50, 800, 600);
        jFrame.setVisible(true);
        LineChartMarketIndex g = new LineChartMarketIndex();
    }


}

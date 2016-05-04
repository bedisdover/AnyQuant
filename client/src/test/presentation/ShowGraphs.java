package presentation;

import java.awt.*;
import java.text.SimpleDateFormat;

import com.sun.javafx.charts.Legend;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.ui.Drawable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;

/**
 * Created by user on 2016/3/22.
 */
public class ShowGraphs {
    ChartPanel panel;

    public ShowGraphs() {
        createTimeSeriesChart();
    }

    public void createTimeSeriesChart() {
        JFreeChart timeSeriesChart = ChartFactory.createTimeSeriesChart("浦发银行", "时间", "收盘价", createDataset(), true, true, false);
        timeSeriesChart.setBackgroundPaint(Color.WHITE);
        XYPlot plot = timeSeriesChart.getXYPlot();
        setXYPolt(plot);
        panel = new ChartPanel(timeSeriesChart,true);
        LegendTitle legendTitle = timeSeriesChart.getLegend();
        legendTitle.setVisible(false);
//        ChartFrame frame = new ChartFrame("vo.Test", timeSeriesChart);
//        frame.pack();
//        frame.setVisible(true);
    }

    public XYDataset createDataset(){
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        Day day = new Day(24, 3, 2016);
        Hour hour9 = new Hour(9, day);
        Hour hour10 = new Hour(10, day);

        TimeSeries timeSeries1 = new TimeSeries("", Minute.class);

        timeSeries1.add(new Minute(30, hour9), 12.80);
        timeSeries1.add(new Minute(35, hour9), 12.59);
        timeSeries1.add(new Minute(40, hour9), 12.38);
        timeSeries1.add(new Minute(45, hour9), 12.35);
        timeSeries1.add(new Minute(50, hour9), 12.34);
        timeSeries1.add(new Minute(55, hour9), 12.31);
        timeSeries1.add(new Minute(0, hour10), 12.28);
        timeSeries1.add(new Minute(5, hour10), 12.25);
        timeSeries1.add(new Minute(10, hour10), 12.16);
        timeSeries1.add(new Minute(15, hour10), 12.16);
        timeSeries1.add(new Minute(20, hour10), 11.95);
        timeSeries1.add(new Minute(25, hour10), 11.93);
        timeSeries1.add(new Minute(30, hour10), 11.86);
        timeSeries1.add(new Minute(35, hour10), 11.84);
        timeSeries1.add(new Minute(40, hour10), 11.79);
        timeSeries1.add(new Minute(45, hour10), 11.72);
        dataset.addSeries(timeSeries1);
        return dataset;
    }

    public void setXYPolt(XYPlot xyPolt){
        xyPolt.setDomainGridlinePaint(Color.LIGHT_GRAY);
        xyPolt.setRangeGridlinePaint(Color.LIGHT_GRAY);
        XYItemRenderer r = xyPolt.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(false);
        }
    }

    public ChartPanel getChartPanel(){
        return panel;
    }

    public static void main(String[] args){
        JFrame jFrame = new JFrame();
        jFrame.add(new ShowGraphs().getChartPanel());
        jFrame.setBounds(50, 50, 800, 600);
        jFrame.setVisible(true);
        ShowGraphs g = new ShowGraphs();
    }
}

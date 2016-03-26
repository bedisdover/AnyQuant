package presentation.graphs;

/**
 * Created by pc on 2016/3/26.
 */

import bl.ShowIndexData;
import org.jfree.chart.ChartPanel;

import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import vo.IndexVO;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LineChartMarketIndex {
    ChartPanel panel;


    public LineChartMarketIndex(){
        createTimeSeriesChart();
    }

    public void createTimeSeriesChart() {
        JFreeChart timeSeriesChart = ChartFactory.createTimeSeriesChart("大盘指数", "日期", "成交量", createDataset(), true, true, false);
        timeSeriesChart.setBackgroundPaint(Color.WHITE);
        XYPlot plot = timeSeriesChart.getXYPlot();
        setXYPolt(plot);
        panel = new ChartPanel(timeSeriesChart,true);
        LegendTitle legendTitle = timeSeriesChart.getLegend();
        legendTitle.setVisible(false);

    }

    public XYDataset createDataset() throws IOException {

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        IndexVO index = null;

        index = new ShowIndexData().getLatestIndexData();
TimeSeries timeSeries1=new TimeSeries();
        timeSeries1.add(index.getDate(),index.getVolume());

//        TimeSeries timeSeries1 = new TimeSeries("", Minute.class);
//
//
//        timeSeries1.add(new Minute(40, hour10), 11.79);
//        timeSeries1.add(new Minute(45, hour10), 11.72);
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
        jFrame.add(new LineChartMarketIndex().getChartPanel());
        jFrame.setBounds(50, 50, 800, 600);
        jFrame.setVisible(true);
        LineChartMarketIndex g = new LineChartMarketIndex();
    }


}

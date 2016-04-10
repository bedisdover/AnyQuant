package presentation.graphs;

import bl.ShowIndexData;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleEdge;
import vo.IndexVO;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by pc on 2016/3/31.
 */
public class LineChartParent implements ChartMouseListener {
    ChartPanel panel;
    JFreeChart timeSeriesChart;
    XYPlot plot;
    int dataLength=365;
    String title[]=new String[2];
    String date[]=new String[dataLength];
    double data[]=new double[dataLength];
    TimeSeries timeSeries1 = new TimeSeries("", Day.class);

    public LineChartParent(String name[], String x[], double y[]) throws IOException {
        title=name;
        date=x;
        data=y;
        createTimeSeriesChart(name, x, y);
        this.panel.setMouseZoomable(false, false);
        this.panel.addChartMouseListener(this);
    }

    public void createTimeSeriesChart(String name[], String x[], double y[]) throws IOException {
        timeSeriesChart = ChartFactory.createTimeSeriesChart("", name[0], name[1], createDataset(x, y), true, true, false);
        timeSeriesChart.setBackgroundPaint(Color.WHITE);
        plot = timeSeriesChart.getXYPlot();
        setXYPolt(plot);
        panel = new ChartPanel(timeSeriesChart, true);
        LegendTitle legendTitle = timeSeriesChart.getLegend();
        legendTitle.setVisible(false);
        Font font = new Font("宋体", Font.BOLD, 16);
     //   TextTitle title = new TextTitle(name[2], font);//副标题 
        //    TextTitle subtitle=new TextTitle("副标题", new Font("黑体",Font.BOLD,12));
        //   timeSeriesChart.addSubtitle(subtitle);
   //     timeSeriesChart.setTitle(title);//标题
        NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
        ValueAxis domainAxis = plot.getDomainAxis();

        //设置日期显示格式
        DateAxis dateAxis = (DateAxis) plot.getDomainAxis();
        SimpleDateFormat frm = new SimpleDateFormat("MM.dd");

        //   dateAxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 30, frm));




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

    }

    public XYDataset createDataset(String date[], double data[]) throws IOException {

        TimeSeriesCollection dataset = new TimeSeriesCollection();

        int length = date.length;
        for (int i = 1; i <= length; i++) {
            int year = Integer.parseInt(date[length - i].substring(0, 4));
            int month = Integer.parseInt(date[length - i].substring(5, 7));
            int day = Integer.parseInt(date[length - i].substring(8, 10));
            Day d = new Day(day, month, year);
            timeSeries1.add(d, data[length - i]);

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


            renderer.setBaseShapesVisible(false);
            renderer.setBaseShapesFilled(false);
        }
    }

    public ChartPanel getChartPanel() {
        return panel;
    }

    @Override
    public void chartMouseClicked(ChartMouseEvent chartMouseEvent) {
    }


    @Override
    public void chartMouseMoved(ChartMouseEvent chartMouseEvent) {
        int xPos = chartMouseEvent.getTrigger().getX();
        int yPos = chartMouseEvent.getTrigger().getY();
        System.out.println("x= "+xPos);
        panel.setHorizontalAxisTrace(true);
        panel.setVerticalAxisTrace(true);
        ChartEntity chartEntity = panel.getEntityForPoint(xPos, yPos);
        String[] info = chartEntity.toString().split(" ");
        String getDate=date[xPos];
        double getData=data[xPos];
            TextTitle textTitle = this.timeSeriesChart.getTitle();
               textTitle.setText(title[1]+" : "+getData+" "+title[0]+" : "+getDate);

//        Point2D point2D = this.panel.translateScreenToJava2D(new Point(xPos, yPos));
//        ChartRenderingInfo chartRenderingInfo = this.panel.getChartRenderingInfo();
//        Rectangle2D rectangle2D = chartRenderingInfo.getPlotInfo().getDataArea();
//        ValueAxis valueAxis1 = plot.getDomainAxis();
//        RectangleEdge rectangleEdge1 = plot.getDomainAxisEdge();
//        ValueAxis valueAxis2 = plot.getRangeAxis();
//        RectangleEdge rectangleEdge2 = plot.getRangeAxisEdge();
//        double d1 = valueAxis1.java2DToValue(point2D.getX(), rectangle2D, rectangleEdge1);
//        double d2 = valueAxis2.java2DToValue(point2D.getY(), rectangle2D, rectangleEdge2);
        }
    }


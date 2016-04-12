package presentation.graphs;

import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

//import javax.servlet.http.HttpSession;
import javax.swing.JPanel;

import data.JuheDemo;
import data.ReadData;
import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;


/**
 * Created by syz on 2016/4/9.
 */

public class TimeSeriesChart extends ApplicationFrame implements ChartMouseListener {
    private static final long serialVersionUID = -4172191391806537567L;

    JFreeChart jfreechart;
    ChartPanel chartpanel;
    static String result;
    //日期
    static String date;


    public TimeSeriesChart(String stockName) {
        super("Time series chart");
        XYDataset xydataset = createDataset(stockName);
        jfreechart = createChart(xydataset);
        chartpanel = new ChartPanel(jfreechart, false);
        chartpanel.setPreferredSize(new Dimension(500, 270));
        chartpanel.setMouseZoomable(false, false);
        chartpanel.addChartMouseListener(this);
        setContentPane(chartpanel);
    }

    private static JFreeChart createChart(XYDataset xydataset) {
        StandardChartTheme standardChartTheme = new StandardChartTheme("name");
        //可以改变轴向的字体
        standardChartTheme.setLargeFont(new Font("楷体",Font.BOLD, 14));
        //可以改变图例的字体
        standardChartTheme.setRegularFont(new Font("宋体",Font.BOLD, 10));
        //可以改变图标的标题字体
        standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
        ChartFactory.setChartTheme(standardChartTheme);// 设置主题
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(
                "Time Series Chart", date , "Price Per Unit",
                xydataset, true, true, true);
        jfreechart.setBackgroundPaint(Color.white);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        xyplot.setBackgroundPaint(Color.lightGray);
        xyplot.setDomainGridlinePaint(Color.white);
        xyplot.setRangeGridlinePaint(Color.white);
        xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
        xyplot.setDomainCrosshairVisible(true);
        xyplot.setRangeCrosshairVisible(true);
        org.jfree.chart.renderer.xy.XYItemRenderer xyitemrenderer = xyplot
                .getRenderer();
        if (xyitemrenderer instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyitemrenderer;
            xylineandshaperenderer.setBaseShapesVisible(false);
            xylineandshaperenderer.setBaseShapesFilled(false);
            xylineandshaperenderer.setBaseStroke(new BasicStroke(1.6f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        }
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("HH:mm"));
        return jfreechart;
    }

    private static XYDataset createDataset(String stockName) {
        result = new JuheDemo().getRequest1(stockName);
        ReadData rd = new ReadData();
        String str = rd.parseJSON(rd.parseJSON(rd.parseJSON(result,"result"),"timeChart"),"p");
        str = str.substring(1,str.length()-1);
        String[] s = str.split("\\{");
        for(int i = 1;i<s.length;i++){
            s[i] = "{"+s[i];
            if(s[i].endsWith(",")){
                s[i] = s[i].substring(0,s[i].length()-1);
            }
        }

        date = rd.parseJSON(s[1],"date");
        System.out.println(date);
        String[] d = date.split("/");
        int year,month,day;
        year = Integer.parseInt(d[0]);
        month = Integer.parseInt(d[1]);
        day = Integer.parseInt(d[2].split(" ")[0]);
        System.out.println(day);

        TimeSeries timeseries = new TimeSeries("L&G European Index Trust",
                org.jfree.data.time.Minute.class);

        for(int i = 1;i<s.length-1;i++){
            double price = Double.parseDouble(rd.parseJSON(s[i],"price"));
            int hour,minute;
            hour = Integer.parseInt(rd.parseJSON(s[i],"time").split(":")[0]);
            minute = Integer.parseInt(rd.parseJSON(s[i],"time").split(":")[1]);
            timeseries.add(new Minute(minute,hour,day,month,year),price);
        }
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        timeseriescollection.addSeries(timeseries);
//        timeseriescollection.addSeries(timeseries1);
        return timeseriescollection;
    }

    public static JPanel createDemoPanel(String stockName) {
        JFreeChart jfreechart = createChart(createDataset(stockName));
        return new ChartPanel(jfreechart);
    }

    // 根据JFreeChart对象生成对应的图片
//    public static String generateLineChart(HttpSession session, PrintWriter pw) {
//        String filename = null;
//        JFreeChart chart = createChart(createDataset());
//        // chart.setBackgroundPaint(java.awt.Color.white);
//
//        ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
//        try {
//            filename = ServletUtilities.saveChartAsPNG(chart, 500, 350, info, session);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            ChartUtilities.writeImageMap(pw, filename, info, false);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        pw.flush();
//        return filename;
//    }

    public static void main(String args[]) {
        TimeSeriesChart timeseriesdemo2 = new TimeSeriesChart("长江实业");
        timeseriesdemo2.pack();
        RefineryUtilities.centerFrameOnScreen(timeseriesdemo2);
        timeseriesdemo2.setVisible(true);
    }

    @Override
    public void chartMouseClicked(ChartMouseEvent chartMouseEvent) {

    }

    @Override
    public void chartMouseMoved(ChartMouseEvent chartMouseEvent) {
        int xPos = chartMouseEvent.getTrigger().getX();
        int yPos = chartMouseEvent.getTrigger().getY();

        this.chartpanel.setHorizontalAxisTrace(true);
        this.chartpanel.setVerticalAxisTrace(true);
//        ChartEntity chartEntity = this.chartpanel.getEntityForPoint(xPos,yPos);
//        String[] info = chartEntity.toString().split(" ");
//        if(info[1].equals("series")){
//            int item = Integer.parseInt(info[6].substring(0,info[6].length()-1));
//            System.out.println(indexVO.getDate()[num-90+item]);
//        }
    }
}

package presentation.graphs;

import java.awt.*;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import data.JuheDemo;
import data.ReadData;
import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
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
    static String[] s;

    public TimeSeriesChart(String stockName) {
        super("");
        XYDataset xydataset = createDataset(stockName);
        jfreechart = createChart(xydataset);
        chartpanel = new ChartPanel(jfreechart, false);
        chartpanel.setPreferredSize(new Dimension(800, 470));
        chartpanel.setMouseZoomable(false, false);
        chartpanel.addChartMouseListener(this);
        setContentPane(chartpanel);
    }

    private static JFreeChart createChart(XYDataset xydataset) {
        StandardChartTheme standardChartTheme = new StandardChartTheme("name");
        //可以改变轴向的字体
        standardChartTheme.setLargeFont(new Font("楷体", Font.BOLD, 14));
        //可以改变图例的字体
        standardChartTheme.setRegularFont(new Font("宋体", Font.BOLD, 10));
        //可以改变图标的标题字体
        standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
        ChartFactory.setChartTheme(standardChartTheme);// 设置主题
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(
                "", date, "Price Per Unit",
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
            xylineandshaperenderer.setBaseStroke(new BasicStroke(1.6f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        }
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("HH:mm"));
        dateaxis.setPositiveArrowVisible(true);
        return jfreechart;
    }

    public ChartPanel getChartPanel() {
        return chartpanel;
    }

    private static XYDataset createDataset(String stockName) {
        result = new JuheDemo().getRequest1(stockName);
        ReadData rd = new ReadData();
        String str = rd.parseJSON(rd.parseJSON(rd.parseJSON(result, "result"), "timeChart"), "p");
        str = str.substring(1, str.length() - 1);
        s = str.split("\\{");
        for (int i = 1; i < s.length; i++) {
            s[i] = "{" + s[i];
            if (s[i].endsWith(",")) {
                s[i] = s[i].substring(0, s[i].length() - 1);
            }
        }

        date = rd.parseJSON(s[1], "date");
        String[] d = date.split("/");
        int year, month, day;
        year = Integer.parseInt(d[0]);
        month = Integer.parseInt(d[1]);
        day = Integer.parseInt(d[2].split(" ")[0]);

        TimeSeries timeSeries1 = new TimeSeries("时刻价格",
                org.jfree.data.time.Minute.class);

        for (int i = 1; i < s.length - 1; i++) {
            double price = Double.parseDouble(rd.parseJSON(s[i], "price"));
            int hour, minute;
            hour = Integer.parseInt(rd.parseJSON(s[i], "time").split(":")[0]);
            minute = Integer.parseInt(rd.parseJSON(s[i], "time").split(":")[1]);
            if (hour != 12) {
                timeSeries1.add(new Minute(minute, hour, day, month, year), price);
            }
        }

        TimeSeries timeSeries2 = new TimeSeries("平均价格",
                org.jfree.data.time.Minute.class);

        for (int i = 1; i < s.length - 1; i++) {
            double sum = 0;
            double avg = 0;
            int j;
            for (j = 1; j <= i; j++) {
                double price = Double.parseDouble(rd.parseJSON(s[j], "price"));
                sum += price;
            }
            j--;
            avg = sum / j;
            int hour, minute;
            hour = Integer.parseInt(rd.parseJSON(s[i], "time").split(":")[0]);
            minute = Integer.parseInt(rd.parseJSON(s[i], "time").split(":")[1]);
            if (hour != 12) {
                timeSeries2.add(new Minute(minute, hour, day, month, year), avg);
            }
        }

        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        timeseriescollection.addSeries(timeSeries1);
        timeseriescollection.addSeries(timeSeries2);
        return timeseriescollection;
    }

    public static JPanel createDemoPanel(String stockName) {
        JFreeChart jfreechart = createChart(createDataset(stockName));
        return new ChartPanel(jfreechart);
    }


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
        chartpanel.setHorizontalAxisTrace(true);
        chartpanel.setVerticalAxisTrace(true);
        ChartEntity chartEntity = chartpanel.getEntityForPoint(xPos, yPos);
        String[] info = chartEntity.toString().split(" ");
        System.out.println(chartEntity.toString());
        if (info[1].equals("series")) {
            int item = Integer.parseInt(info[6].substring(0, info[6].length() - 1));
            TextTitle textTitle = this.jfreechart.getTitle();
            ReadData rd = new ReadData();
            int seriesNum = Integer.parseInt(info[3].substring(0,1));
            double value;
            String text;
            if (seriesNum == 0) {
                value = Double.parseDouble(rd.parseJSON(s[item + 1], "price"));
                text = "价格:" + value;
            } else {
                double sum = 0;
                double avg = 0;
                int j;
                for (j = 1; j <= item+1; j++) {
                    double price = Double.parseDouble(rd.parseJSON(s[j], "price"));
                    sum += price;
                }
                j--;
                avg = sum / j;

                text = "平均价格:" + avg;
            }
            textTitle.setText(text);
            textTitle.setFont(new Font("黑体", Font.PLAIN, 18));
        } else {
            TextTitle textTitle = this.jfreechart.getTitle();
            textTitle.setText("价格为空");
            textTitle.setFont(new Font("黑体", Font.PLAIN, 18));
        }
    }
}

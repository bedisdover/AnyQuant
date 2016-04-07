package presentation.panel;

import bl.ShowIndexData;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import vo.IndexVO;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by user on 2016/3/29.
 */
public class IndexKLine_Weekly implements ChartMouseListener{
    ChartPanel chartPanel;
    DrawKLineHelper drawKLineHelper;
    IndexVO indexVO;
    int num;

    public IndexKLine_Weekly() throws IOException {
        drawKLineHelper = new DrawKLineHelper();
        createChart();
    }

    public void createChart() throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
        double highValue = Double.MIN_VALUE;// 设置K线数据当中的最大值
        double minValue = Double.MAX_VALUE;// 设置K线数据当中的最小值
        double volumeHighValue = Double.MIN_VALUE;// 设置成交量的最大值
        double volumeMinValue = Double.MAX_VALUE;// 设置成交量的最低值
        ShowIndexData getIndexData = new ShowIndexData();
        indexVO = getIndexData.getLatestIndexData();
        num = indexVO.getDate().length;

        drawKLineHelper.addPMA(indexVO,500,5);

        OHLCSeries series = new OHLCSeries("");// 高开低收数据序列，股票K线图的四个数据，依次是开，高，低，收
        for(int i=num-1;i>=num-500;i-=5){
            String[] days = indexVO.getDate()[i].split("-");
            series.add(new Day(Integer.parseInt(days[2]),Integer.parseInt(days[1]),Integer.parseInt(days[0])),indexVO.getOpen()[i],indexVO.getHigh()[i],indexVO.getLow()[i],indexVO.getClose()[i]);
        }
        final OHLCSeriesCollection seriesCollection = new OHLCSeriesCollection();// 保留K线数据的数据集，必须申明为final，后面要在匿名内部类里面用到
        seriesCollection.addSeries(series);

        TimeSeries series2=new TimeSeries("");// 对应时间成交量数据
        for(int i=num-1;i>=num-500;i-=5){
            String[] days = indexVO.getDate()[i].split("-");
            series2.add(new Day(Integer.parseInt(days[2]),Integer.parseInt(days[1]),Integer.parseInt(days[0])),indexVO.getVolume()[i]/100);
        }
        TimeSeriesCollection timeSeriesCollection=new TimeSeriesCollection();// 保留成交量数据的集合
        timeSeriesCollection.addSeries(series2);

        // 获取K线数据的最高值和最低值
        int seriesCount = seriesCollection.getSeriesCount();// 一共有多少个序列，目前为一个
        for (int i = 0; i < seriesCount; i++) {
            int itemCount = seriesCollection.getItemCount(i);// 每一个序列有多少个数据项
            for (int j = 0; j < itemCount; j++) {
                if (highValue < seriesCollection.getHighValue(i, j)) {// 取第i个序列中的第j个数据项的最大值
                    highValue = seriesCollection.getHighValue(i, j);
                }
                if (minValue > seriesCollection.getLowValue(i, j)) {// 取第i个序列中的第j个数据项的最小值
                    minValue = seriesCollection.getLowValue(i, j);
                }
            }
        }
        // 获取最高值和最低值
        int seriesCount2 = timeSeriesCollection.getSeriesCount();// 一共有多少个序列，目前为一个
        for (int i = 0; i < seriesCount2; i++) {
            int itemCount = timeSeriesCollection.getItemCount(i);// 每一个序列有多少个数据项
            for (int j = 0; j < itemCount; j++) {
                if (volumeHighValue < timeSeriesCollection.getYValue(i,j)) {// 取第i个序列中的第j个数据项的值
                    volumeHighValue = timeSeriesCollection.getYValue(i,j);
                }
                if (volumeMinValue > timeSeriesCollection.getYValue(i, j)) {// 取第i个序列中的第j个数据项的值
                    volumeMinValue = timeSeriesCollection.getYValue(i, j);
                }
            }
        }


        final CandlestickRenderer candlestickRender=new CandlestickRenderer();// 设置K线图的画图器，必须申明为final，后面要在匿名内部类里面用到
        drawKLineHelper.setCandlestickRenderer(candlestickRender);

        DateAxis x1Axis=new DateAxis();// 设置x轴，也就是时间轴
        drawKLineHelper.setXAxis(x1Axis,indexVO.getDate()[num-500],getLatestFriday());

        NumberAxis y1Axis=new NumberAxis();// 设定y轴，就是数字轴
        drawKLineHelper.setY1Axis(y1Axis,minValue*0.95,highValue*1.05);

        XYPlot plot1=new XYPlot(seriesCollection,x1Axis,y1Axis,candlestickRender);// 设置画图区域对象
        drawKLineHelper.setXYPlot(1,plot1,drawKLineHelper.timeSeriesCollectionPMA5,Color.ORANGE);
        drawKLineHelper.setXYPlot(2,plot1,drawKLineHelper.timeSeriesCollectionPMA10,Color.MAGENTA);
        drawKLineHelper.setXYPlot(3,plot1,drawKLineHelper.timeSeriesCollectionPMA20,Color.CYAN);
        drawKLineHelper.setXYPlot(4,plot1,drawKLineHelper.timeSeriesCollectionPMA30,Color.GREEN);
        drawKLineHelper.setXYPlot(5,plot1,drawKLineHelper.timeSeriesCollectionPMA60,Color.BLUE);

        XYBarRenderer xyBarRender=new XYBarRenderer(){
            private static final long serialVersionUID = 1L;// 为了避免出现警告消息，特设定此值
            public Paint getItemPaint(int i, int j){// 匿名内部类用来处理当日的成交量柱形图的颜色与K线图的颜色保持一致
                if(seriesCollection.getCloseValue(i,j)>seriesCollection.getOpenValue(i,j)){// 收盘价高于开盘价，股票上涨，选用股票上涨的颜色
                    return candlestickRender.getUpPaint();
                }else{
                    return candlestickRender.getDownPaint();
                }
            }};

        xyBarRender.setMargin(0.1);// 设置柱形图之间的间隔

        NumberAxis y2Axis=new NumberAxis();// 设置Y轴，为数值,后面的设置，参考上面的y轴设置
        drawKLineHelper.setY2Axis(y2Axis,volumeMinValue*0.9,volumeHighValue*1.1);

        XYPlot plot2=new XYPlot(timeSeriesCollection,null,y2Axis,xyBarRender);// 建立第二个画图区域对象，主要此时的x轴设为了null值，因为要与第一个画图区域对象共享x轴
        CombinedDomainXYPlot combineddomainxyplot = new CombinedDomainXYPlot(x1Axis);// 建立一个恰当的联合图形区域对象，以x轴为共享轴
        combineddomainxyplot.add(plot1, 2);// 添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域2/3
        combineddomainxyplot.add(plot2, 1);// 添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域1/3
        combineddomainxyplot.setGap(20);// 设置两个图形区域对象之间的间隔空间
        JFreeChart chart = new JFreeChart("沪深300", JFreeChart.DEFAULT_TITLE_FONT, combineddomainxyplot, false);
        chartPanel = new ChartPanel(chart,true);
    }

    public ChartPanel getChartPanel(){
        return chartPanel;
    }


    private String getLatestFriday(){
        Calendar calendar = Calendar.getInstance();
        Date d = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        String s = simpleDateFormat.format(d);
        while(!s.equals("星期五")){
            calendar.add(calendar.DATE,-1);
            d = calendar.getTime();
            s = simpleDateFormat.format(d);
        }
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        s = simpleDateFormat.format(d);
        System.out.println(s);
        return s;
    }
    public static void main(String[] args){
        JFrame jFrame = new JFrame();
        try {
            jFrame.add(new IndexKLine_Weekly().getChartPanel());
            jFrame.setBounds(50, 50, 1024, 768);
            jFrame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void chartMouseClicked(ChartMouseEvent chartMouseEvent) {

    }

    @Override
    public void chartMouseMoved(ChartMouseEvent chartMouseEvent) {

    }
}

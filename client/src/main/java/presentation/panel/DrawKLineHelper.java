package presentation.panel;

import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import vo.IndexVO;

import java.awt.*;

/**
 * Created by user on 2016/4/1.
 */
public class DrawKLineHelper {
    public void addPMA(IndexVO indexVO){
        int num = indexVO.getDate().length;
        TimeSeries seriesPMA5 = new TimeSeries("");//对应五日均线数据
        for(int i=num-1;i>=num-90;i--){
            double pma5 = 0;
            for(int j=i;j>i-5;j--){
                pma5 += indexVO.getClose()[j];
            }
            pma5 /= 5;
            String[] days = indexVO.getDate()[i].split("-");
            seriesPMA5.add(new Day(Integer.parseInt(days[2]),Integer.parseInt(days[1]),Integer.parseInt(days[0])),pma5);
        }
        TimeSeriesCollection timeSeriesCollectionPMA5 = new TimeSeriesCollection();//保留五日均线数据的集合
        timeSeriesCollectionPMA5.addSeries(seriesPMA5);

        TimeSeries seriesPMA10 = new TimeSeries("");//对应十日均线数据
        for(int i=num-1;i>=num-90;i--){
            double pma10 = 0;
            for(int j=i;j>i-10;j--){
                pma10 += indexVO.getClose()[j];
            }
            pma10 /= 10;
            String[] days = indexVO.getDate()[i].split("-");
            seriesPMA10.add(new Day(Integer.parseInt(days[2]),Integer.parseInt(days[1]),Integer.parseInt(days[0])),pma10);
        }
        TimeSeriesCollection timeSeriesCollectionPMA10 = new TimeSeriesCollection();//保留十日均线数据的集合
        timeSeriesCollectionPMA10.addSeries(seriesPMA10);

        TimeSeries seriesPMA20 = new TimeSeries("");//对应二十日均线数据
        for(int i=num-1;i>=num-90;i--){
            double pma20 = 0;
            for(int j=i;j>i-20;j--){
                pma20 += indexVO.getClose()[j];
            }
            pma20 /= 20;
            String[] days = indexVO.getDate()[i].split("-");
            seriesPMA20.add(new Day(Integer.parseInt(days[2]),Integer.parseInt(days[1]),Integer.parseInt(days[0])),pma20);
        }
        TimeSeriesCollection timeSeriesCollectionPMA20 = new TimeSeriesCollection();//保留二十日均线数据的集合
        timeSeriesCollectionPMA20.addSeries(seriesPMA20);

        TimeSeries seriesPMA30 = new TimeSeries("");//对应三十日均线数据
        for(int i=num-1;i>=num-90;i--){
            double pma30 = 0;
            for(int j=i;j>i-30;j--){
                pma30 += indexVO.getClose()[j];
            }
            pma30 /= 30;
            String[] days = indexVO.getDate()[i].split("-");
            seriesPMA30.add(new Day(Integer.parseInt(days[2]),Integer.parseInt(days[1]),Integer.parseInt(days[0])),pma30);
        }
        TimeSeriesCollection timeSeriesCollectionPMA30 = new TimeSeriesCollection();//保留三十日均线数据的集合
        timeSeriesCollectionPMA30.addSeries(seriesPMA30);

        TimeSeries seriesPMA60 = new TimeSeries("");//对应六十日均线数据
        for(int i=num-1;i>=num-90;i--){
            double pma60 = 0;
            for(int j=i;j>i-60;j--){
                pma60 += indexVO.getClose()[j];
            }
            pma60 /= 60;
            String[] days = indexVO.getDate()[i].split("-");
            seriesPMA60.add(new Day(Integer.parseInt(days[2]),Integer.parseInt(days[1]),Integer.parseInt(days[0])),pma60);
        }
        TimeSeriesCollection timeSeriesCollectionPMA60 = new TimeSeriesCollection();//保留六十日均线数据的集合
        timeSeriesCollectionPMA60.addSeries(seriesPMA60);
    }

    public void setCandlestickRenderer(CandlestickRenderer candlestickRenderer){
        candlestickRenderer.setUseOutlinePaint(true); // 设置是否使用自定义的边框线，程序自带的边框线的颜色不符合中国股票市场的习惯
        candlestickRenderer.setAutoWidthMethod(CandlestickRenderer.WIDTHMETHOD_AVERAGE);// 设置如何对K线图的宽度进行设定
        candlestickRenderer.setAutoWidthGap(0.001);// 设置各个K线图之间的间隔
        candlestickRenderer.setUpPaint(Color.RED);// 设置股票上涨的K线图颜色
        candlestickRenderer.setDownPaint(Color.GREEN);// 设置股票下跌的K线图颜色
    }

    public void setXAxis(DateAxis dateAxis){

    }
}

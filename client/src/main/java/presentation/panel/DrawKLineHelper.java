package presentation.panel;

import org.jfree.chart.axis.*;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import vo.IndexVO;
import vo.StockVO;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by user on 2016/4/1.
 */
public class DrawKLineHelper {
    TimeSeriesCollection timeSeriesCollectionPMA5;
    TimeSeriesCollection timeSeriesCollectionPMA10;
    TimeSeriesCollection timeSeriesCollectionPMA20;
    TimeSeriesCollection timeSeriesCollectionPMA30;
    TimeSeriesCollection timeSeriesCollectionPMA60;

    public DrawKLineHelper(){
        timeSeriesCollectionPMA5 = new TimeSeriesCollection();
        timeSeriesCollectionPMA10 = new TimeSeriesCollection();
        timeSeriesCollectionPMA20 = new TimeSeriesCollection();
        timeSeriesCollectionPMA30 = new TimeSeriesCollection();
        timeSeriesCollectionPMA60 = new TimeSeriesCollection();
    }

    public void addPMA(StockVO stockVO,int n,int gap){
        int num = stockVO.getDate().length;
        TimeSeries seriesPMA5 = new TimeSeries("");//对应五日均线数据
        for(int i=num-1;i>=num-n;i-=gap){
            double pma5 = 0;
            for(int j=i;j>i-5;j--){
                pma5 += stockVO.getClose()[j];
            }
            pma5 /= 5;

            String[] days = stockVO.getDate()[i].split("-");
            seriesPMA5.add(new Day(Integer.parseInt(days[2]),Integer.parseInt(days[1]),Integer.parseInt(days[0])),pma5);
        }
        timeSeriesCollectionPMA5.addSeries(seriesPMA5);//保留五日均线数据的集合


        TimeSeries seriesPMA10 = new TimeSeries("");//对应十日均线数据
        for(int i=num-1;i>=num-n;i-=gap){
            double pma10 = 0;
            for(int j=i;j>i-10;j--){
                pma10 += stockVO.getClose()[j];
            }
            pma10 /= 10;
            String[] days = stockVO.getDate()[i].split("-");
            seriesPMA10.add(new Day(Integer.parseInt(days[2]),Integer.parseInt(days[1]),Integer.parseInt(days[0])),pma10);
        }
        timeSeriesCollectionPMA10.addSeries(seriesPMA10);//保留十日均线数据的集合


        TimeSeries seriesPMA20 = new TimeSeries("");//对应二十日均线数据
        for(int i=num-1;i>=num-n;i-=gap){
            double pma20 = 0;
            for(int j=i;j>i-20;j--){
                pma20 += stockVO.getClose()[j];
            }
            pma20 /= 20;
            String[] days = stockVO.getDate()[i].split("-");
            seriesPMA20.add(new Day(Integer.parseInt(days[2]),Integer.parseInt(days[1]),Integer.parseInt(days[0])),pma20);
        }
        timeSeriesCollectionPMA20.addSeries(seriesPMA20);//保留二十日均线数据的集合


        TimeSeries seriesPMA30 = new TimeSeries("");//对应三十日均线数据
        for(int i=num-1;i>=num-n;i-=gap){
            double pma30 = 0;
            for(int j=i;j>i-30;j--){
                pma30 += stockVO.getClose()[j];
            }
            pma30 /= 30;
            String[] days = stockVO.getDate()[i].split("-");
            seriesPMA30.add(new Day(Integer.parseInt(days[2]),Integer.parseInt(days[1]),Integer.parseInt(days[0])),pma30);
        }
        timeSeriesCollectionPMA30.addSeries(seriesPMA30);//保留三十日均线数据的集合


        TimeSeries seriesPMA60 = new TimeSeries("");//对应六十日均线数据
        for(int i=num-1;i>=num-n;i-=gap){
            double pma60 = 0;
            for(int j=i;j>i-60;j--){
                pma60 += stockVO.getClose()[j];
            }
            pma60 /= 60;
            String[] days = stockVO.getDate()[i].split("-");
            seriesPMA60.add(new Day(Integer.parseInt(days[2]),Integer.parseInt(days[1]),Integer.parseInt(days[0])),pma60);
        }
        timeSeriesCollectionPMA60.addSeries(seriesPMA60);//保留六十日均线数据的集合
    }

    public void addPMA(IndexVO indexVO,int n,int gap){
        int num = indexVO.getDate().length;
        TimeSeries seriesPMA5 = new TimeSeries("");//对应五日均线数据
        for(int i=num-1;i>=num-n;i-=gap){
            double pma5 = 0;
            for(int j=i;j>i-5;j--){
                pma5 += indexVO.getClose()[j];
            }
            pma5 /= 5;
            String[] days = indexVO.getDate()[i].split("-");
            seriesPMA5.add(new Day(Integer.parseInt(days[2]),Integer.parseInt(days[1]),Integer.parseInt(days[0])),pma5);
        }
        timeSeriesCollectionPMA5.addSeries(seriesPMA5);//保留五日均线数据的集合


        TimeSeries seriesPMA10 = new TimeSeries("");//对应十日均线数据
        for(int i=num-1;i>=num-n;i-=gap){
            double pma10 = 0;
            for(int j=i;j>i-10;j--){
                pma10 += indexVO.getClose()[j];
            }
            pma10 /= 10;
            String[] days = indexVO.getDate()[i].split("-");
            seriesPMA10.add(new Day(Integer.parseInt(days[2]),Integer.parseInt(days[1]),Integer.parseInt(days[0])),pma10);
        }
        timeSeriesCollectionPMA10.addSeries(seriesPMA10);//保留十日均线数据的集合


        TimeSeries seriesPMA20 = new TimeSeries("");//对应二十日均线数据
        for(int i=num-1;i>=num-n;i-=gap){
            double pma20 = 0;
            for(int j=i;j>i-20;j--){
                pma20 += indexVO.getClose()[j];
            }
            pma20 /= 20;
            String[] days = indexVO.getDate()[i].split("-");
            seriesPMA20.add(new Day(Integer.parseInt(days[2]),Integer.parseInt(days[1]),Integer.parseInt(days[0])),pma20);
        }
        timeSeriesCollectionPMA20.addSeries(seriesPMA20);//保留二十日均线数据的集合


        TimeSeries seriesPMA30 = new TimeSeries("");//对应三十日均线数据
        for(int i=num-1;i>=num-n;i-=gap){
            double pma30 = 0;
            for(int j=i;j>i-30;j--){
                pma30 += indexVO.getClose()[j];
            }
            pma30 /= 30;
            String[] days = indexVO.getDate()[i].split("-");
            seriesPMA30.add(new Day(Integer.parseInt(days[2]),Integer.parseInt(days[1]),Integer.parseInt(days[0])),pma30);
        }
        timeSeriesCollectionPMA30.addSeries(seriesPMA30);//保留三十日均线数据的集合


        TimeSeries seriesPMA60 = new TimeSeries("");//对应六十日均线数据
        for(int i=num-1;i>=num-n;i-=gap){
            double pma60 = 0;
            for(int j=i;j>i-60;j--){
                pma60 += indexVO.getClose()[j];
            }
            pma60 /= 60;
            String[] days = indexVO.getDate()[i].split("-");
            seriesPMA60.add(new Day(Integer.parseInt(days[2]),Integer.parseInt(days[1]),Integer.parseInt(days[0])),pma60);
        }
        timeSeriesCollectionPMA60.addSeries(seriesPMA60);//保留六十日均线数据的集合

    }

    public void setCandlestickRenderer(CandlestickRenderer candlestickRenderer){
        candlestickRenderer.setUseOutlinePaint(true); // 设置是否使用自定义的边框线，程序自带的边框线的颜色不符合中国股票市场的习惯
        candlestickRenderer.setAutoWidthMethod(CandlestickRenderer.WIDTHMETHOD_AVERAGE);// 设置如何对K线图的宽度进行设定
        candlestickRenderer.setAutoWidthGap(0.001);// 设置各个K线图之间的间隔
        candlestickRenderer.setUpPaint(Color.RED);// 设置股票上涨的K线图颜色
        candlestickRenderer.setDownPaint(Color.GREEN);// 设置股票下跌的K线图颜色
        candlestickRenderer.setCandleWidth(8);
    }

    public void setXAxis(DateAxis dateAxis,String date1,String date2){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateAxis.setAutoRange(false);
        try {
            Calendar c = new GregorianCalendar();
            String[] time = date2.split("-");
            c.set(Integer.parseInt(time[0]),Integer.parseInt(time[1])-1,Integer.parseInt(time[2]));
            c.add(c.DATE,1);
            Date date = c.getTime();
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            String d = simpleDateFormat1.format(date);//得到date2的后一天

            dateAxis.setRange(simpleDateFormat.parse(date1),simpleDateFormat.parse(d));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateAxis.setTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());// 设置时间线显示的规则，用这个方法就摒除掉了周六和周日这些没有交易的日期(很多人都不知道有此方法)，使图形看上去连续
        dateAxis.setAutoTickUnitSelection(false);// 设置不采用自动选择刻度值
        dateAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);// 设置标记的位置
        dateAxis.setStandardTickUnits(DateAxis.createStandardDateTickUnits());// 设置标准的时间刻度单位
        dateAxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY,90));// 设置时间刻度的间隔，一般以周为单位
        dateAxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));// 设置显示时间的格式
        dateAxis.setVisible(true);
    }

    public void setY1Axis(NumberAxis numberAxis,double min,double max){
        numberAxis.setAutoRange(false);// 不使用自动设定范围
        numberAxis.setRange(min, max);// 设定y轴值的范围，比最低值要低一些，比最大值要大一些，这样图形看起来会美观些
        numberAxis.setTickUnit(new NumberTickUnit((max-min)/10));// 设置刻度显示的密度
    }

    public void setY2Axis(NumberAxis numberAxis,double min,double max){
        numberAxis.setAutoRange(false);
        numberAxis.setRange(min, max);
        numberAxis.setTickUnit(new NumberTickUnit((max-min)/4));
    }

    public void setXYPlot(int i, XYPlot xyPlot,TimeSeriesCollection timeSeries,Color color){
        XYLineAndShapeRenderer xyLineAndShapeRenderer = new XYLineAndShapeRenderer();
        xyLineAndShapeRenderer.setBaseLinesVisible(true);
        xyLineAndShapeRenderer.setBaseShapesVisible(false);
        xyLineAndShapeRenderer.setSeriesPaint(0,color);
        xyLineAndShapeRenderer.setSeriesStroke(0,new BasicStroke(2.0f));
        xyPlot.setDataset(i,timeSeries);
        xyPlot.setRenderer(i,xyLineAndShapeRenderer);
    }


}

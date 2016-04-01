package presentation.panel;

import bl.ShowIndexData;
import org.jfree.chart.ChartPanel;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import vo.IndexVO;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by user on 2016/3/29.
 */
public class IndexKLine_Weekly {
    ChartPanel chartPanel;
    DrawKLineHelper drawKLineHelper;

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
        int num = 0;
        IndexVO indexVO = getIndexData.getLatestIndexData();
        num = indexVO.getDate().length;

        OHLCSeries series = new OHLCSeries("");// 高开低收数据序列，股票K线图的四个数据，依次是开，高，低，收
        for(int i=num-1;i>=num-90;i--){
            String[] days = indexVO.getDate()[i].split("-");
            series.add(new Day(Integer.parseInt(days[2]),Integer.parseInt(days[1]),Integer.parseInt(days[0])),indexVO.getOpen()[i],indexVO.getHigh()[i],indexVO.getLow()[i],indexVO.getClose()[i]);
        }
        final OHLCSeriesCollection seriesCollection = new OHLCSeriesCollection();// 保留K线数据的数据集，必须申明为final，后面要在匿名内部类里面用到
        seriesCollection.addSeries(series);

        TimeSeries series2=new TimeSeries("");// 对应时间成交量数据
        for(int i=num-1;i>=num-90;i--){
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
        IndexKLine_Weekly indexKLine_weekly = null;
        try {
            indexKLine_weekly = new IndexKLine_Weekly();
            indexKLine_weekly.getLatestFriday();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package presentation.graphs;

/**
 * Created by zmj on 2016/3/26.
 */

import bl.ShowIndexData;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.entity.ChartEntity;
import presentation.UltraSwing.UltraScrollPane;
import vo.IndexVO;

import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LineChartMarketIndex {
    public static LineChartParent lineChartParent;
    int showLength = 365;

    public LineChartMarketIndex(String nameInit[], double dataInit[], String dateChoose[]) throws ParseException {
        String name[] = nameInit;
        //处理两个日期之间的天数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(dateChoose[0]));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(dateChoose[1]));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        showLength = (int) between_days;


        try {
            IndexVO index = new ShowIndexData().getLatestIndexData();
            String dateInit[] = index.getDate();
            //结束时间对应data第几项
            int position = -1;
            for (int i = 0; i < dateInit.length; i++) {
                if (dateInit[i].equals(dateChoose[1])) {
                    position = i;
                }
            }
            double data[] = new double[showLength];
            for (int i = 0; i < showLength; i++) {
                data[showLength - 1 - i] = dataInit[position - i];
            }

            String date[] = new String[showLength];
            for (int i = 0; i < showLength; i++) {
                date[showLength - 1 - i] = dateInit[position - i];
            }

            lineChartParent = new LineChartParent(name, date, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LineChartMarketIndex(String nameInit[], long dataInit[], String dateChoose[]) throws ParseException {
        String name[] = nameInit;
        //处理两个日期之间的天数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(dateChoose[0]));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(dateChoose[1]));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        showLength = (int) between_days;




        try { IndexVO index = new ShowIndexData().getLatestIndexData();
            String dateInit[] = index.getDate();
            //结束时间对应data第几项
            int position = -1;
            for (int i = 0; i < dateInit.length; i++) {
                if (dateInit[i].equals(dateChoose[1])) {
                    position = i;
                }
            }
            double data[] = new double[showLength];
            System.out.println(showLength+"showlength");
            System.out.println(position+"position");
            for (int i = 0; i < showLength; i++) {
                data[showLength - 1 - i] = dataInit[position - i];

            }
            String date[] = new String[showLength];
            for (int i = 0; i < showLength; i++) {
                date[showLength - 1 - i] = dateInit[position - i];
            }
            lineChartParent = new LineChartParent(name, date, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ChartPanel getChartPanel() {
        ChartPanel chartPanel = lineChartParent.getChartPanel();
        return chartPanel;
    }


}
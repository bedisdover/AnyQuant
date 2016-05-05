package presentation.graphs;

/**
 * Created by zmj on 2016/3/26.
 */

import bl.ShowIndexData;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.entity.ChartEntity;
import presentation.UltraSwing.UltraScrollPane;
import presentation.frame.MainFrame;
import vo.IndexVO;

import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class LineChartMarketIndex {
    public static LineChartParent lineChartParent;

    public LineChartMarketIndex(String nameInit[], double dataInit[], String dateChoose[]) throws ParseException {
        String name[] = nameInit;
        try {
            IndexVO index = new ShowIndexData().getLatestIndexData();
            String dateInit[] = index.getDate();
            String start = dateChoose[0];
            String end = dateChoose[1];
            ArrayList dateArray = new ArrayList();
            ArrayList dataArray = new ArrayList();
            for (int i = 0; i < dateInit.length; i++) {
                if (start.compareTo(dateInit[i]) <= 0 && end.compareTo(dateInit[i]) >= 0) {
                    dateArray.add(dateInit[i]);
                    dataArray.add(dataInit[i]);
                }
            }
            String date[] = new String[dateArray.size()];
            double data[] = new double[dataArray.size()];
            for (int i = 0; i < dateArray.size(); i++) {
                date[i] = dateArray.get(i).toString();
                data[i] = Double.parseDouble(dataArray.get(i).toString());
                    //     System.out.println(date[i]+"你们都是谁");
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
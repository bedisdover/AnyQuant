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

public class LineChartMarketIndex {
    public static LineChartParent lineChartParent;
    int showLength=365;

    public LineChartMarketIndex(String nameInit[], double dataInit[]) {
        String name[] = nameInit;
        double data[] = new double[showLength];
        int length = dataInit.length;
        for (int i = 0; i < showLength; i++) {
            data[showLength-1 - i] = dataInit[length - 1 - i];
        }

        try {
            IndexVO index = new ShowIndexData().getLatestIndexData();
            String dateInit[] = index.getDate();
            String date[] = new String[showLength];
            int len = dateInit.length;
            for (int i = 0; i < showLength; i++) {
                date[showLength-1 - i] = dateInit[len - 1 - i];
            }

            lineChartParent = new LineChartParent(name, date, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LineChartMarketIndex(String nameInit[], long dataInit[]) {
        String name[] = nameInit;
        int length = dataInit.length;
        double data[] = new double[showLength];
        for (int i = 0; i < showLength; i++) {
            data[showLength-1 - i] = (double) dataInit[length - 1 - i];
        }
        try {
            IndexVO index = new ShowIndexData().getLatestIndexData();
            String dateInit[] = index.getDate();
            String date[] = new String[showLength];
            for (int i = 0; i < showLength; i++) {
                date[showLength-1 - i] = dateInit[dataInit.length - 1 - i];
            }

            lineChartParent = new LineChartParent(name, date, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ChartPanel getChartPanel() {
        ChartPanel chartPanel = lineChartParent.getChartPanel();
//        chartPanel.setSize(1000, 300);
        return chartPanel;
    }

//    public UltraScrollPane drawLineChartMarketIndex() {
//        UltraScrollPane scroll = new UltraScrollPane(getChartPanel());
//        return scroll;
//    }


}
package presentation.graphs;

/**
 * Created by zmj on 2016/3/26.
 */

import bl.ShowIndexData;
import org.jfree.chart.ChartPanel;
import presentation.UltraSwing.UltraScrollPane;
import vo.IndexVO;

import javax.swing.*;
import java.io.IOException;

public class LineChartMarketIndex {
    public static LineChartParent lineChartParent;

    public LineChartMarketIndex(String nameInit[], double dataInit[]) {
        String name[] = nameInit;
        double data[]=new double[365];
        int length=dataInit.length;
        for(int i=0;i<365;i++){
            data[364-i]=dataInit[length-1-i];
        }

        try {
            IndexVO index = new ShowIndexData().getLatestIndexData();
            String dateInit[] = index.getDate();
            String date[]=new String[365];
            int len=dateInit.length;
            for(int i=0;i<365;i++){
                date[364-i]=dateInit[len-1-i];
            }

            lineChartParent = new LineChartParent(name, date, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LineChartMarketIndex(String nameInit[], long dataInit[]) {
        String name[] = nameInit;
        int length=dataInit.length;
        double data[] = new double[365];
        for(int i=0;i<365;i++) {
            data[364 - i] = (double)dataInit[length - 1 - i];
        }
        try {
            IndexVO index = new ShowIndexData().getLatestIndexData();
            String dateInit[] = index.getDate();
            String date[]=new String[365];
            for(int i=0;i<365;i++){
                date[364-i]=dateInit[dataInit.length-1-i];
            }

            lineChartParent = new LineChartParent(name, date, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ChartPanel getChartPanel() {
        return lineChartParent.getChartPanel();
    }

    public JScrollPane drawLineChartMarketIndex() {
        UltraScrollPane scroll = new UltraScrollPane(getChartPanel());
        scroll.setBounds();
        return scroll;
        return scroll;
    }
}

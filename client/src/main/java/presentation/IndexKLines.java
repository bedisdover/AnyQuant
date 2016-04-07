package presentation;

import org.jfree.chart.ChartPanel;
import presentation.panel.IndexKLine_Daily;
import presentation.panel.IndexKLine_Monthly;
import presentation.panel.IndexKLine_Weekly;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by user on 2016/4/7.
 */
public class IndexKLines {
    JTabbedPane jTabbedPane;
    ChartPanel chartPanel1;
    ChartPanel chartPanel2;
    ChartPanel chartPanel3;

    public IndexKLines() throws IOException {
        jTabbedPane = new JTabbedPane();
        chartPanel1 = new IndexKLine_Daily().getChartPanel();
        chartPanel2 = new IndexKLine_Weekly().getChartPanel();
        chartPanel3 = new IndexKLine_Monthly().getChartPanel();
        jTabbedPane.addTab("日k",chartPanel1);
        jTabbedPane.addTab("周k",chartPanel2);
        jTabbedPane.addTab("月k",chartPanel3);
    }

    public JTabbedPane getjTabbedPane(){
        return jTabbedPane;
    }
}

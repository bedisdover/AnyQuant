package presentation.panel;

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
        ImageIcon image1 = new ImageIcon("src/main/resources/images/dayK.png");
        ImageIcon image2 = new ImageIcon("src/main/resources/images/weekK.png");
        ImageIcon image3 = new ImageIcon("src/main/resources/images/monthK.png");
        chartPanel1 = new IndexKLine_Daily().getChartPanel();
        chartPanel2 = new IndexKLine_Weekly().getChartPanel();
        chartPanel3 = new IndexKLine_Monthly().getChartPanel();
        jTabbedPane.addTab(null,image1,chartPanel1,"日k线");
        jTabbedPane.addTab(null,image2,chartPanel2,"周k线");
        jTabbedPane.addTab(null,image3,chartPanel3,"月k线");
    }

    public JTabbedPane getjTabbedPane(){
        return jTabbedPane;
    }
}

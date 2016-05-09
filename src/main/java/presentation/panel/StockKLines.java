package presentation.panel;

import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by user on 2016/4/9.
 */
public class StockKLines {
    JTabbedPane jTabbedPane;
    ChartPanel chartPanel1;
    ChartPanel chartPanel2;
    ChartPanel chartPanel3;

    public StockKLines(String id) throws IOException {
        jTabbedPane = new JTabbedPane();
        ImageIcon image1 = new ImageIcon("src/main/resources/images/dayK.png");
        ImageIcon image2 = new ImageIcon("src/main/resources/images/weekK.png");
        ImageIcon image3 = new ImageIcon("src/main/resources/images/monthK.png");
        chartPanel1 = new StockKLine_Daily(id).getChartPanel();
        chartPanel2 = new StockKLine_Weekly(id).getChartPanel();
        chartPanel3 = new StockKLine_Monthly(id).getChartPanel();
        jTabbedPane.addTab(null,image1,chartPanel1,"日k线");
        jTabbedPane.addTab(null,image2,chartPanel2,"周k线");
        jTabbedPane.addTab(null,image3,chartPanel3,"月k线");
    }

    public JTabbedPane getjTabbedPane(){
        return jTabbedPane;
    }
}

package presentation.panel;

import org.jfree.chart.ChartPanel;

/**
 * Created by user on 2016/4/4.
 */
public class StockKLine_Daily {
    ChartPanel chartPanel;
    DrawKLineHelper drawKLineHelper;

    public StockKLine_Daily(String stockID){
        drawKLineHelper = new DrawKLineHelper();
        createChart(stockID);
    }

    public void createChart(String stockID){

    }
}

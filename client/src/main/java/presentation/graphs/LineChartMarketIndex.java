package presentation.graphs;

/**
 * Created by pc on 2016/3/26.
 */

import bl.ShowIndexData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import presentation.frame.MainFrame;
import presentation.panel.info.IndexDataPanel;
import vo.IndexVO;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class LineChartMarketIndex extends LineChartParent {
   public static  LineChartParent lineChartParent;
public LineChartMarketIndex(){
    String name[]={"日期","成交量","大盘指数"};

    try {
        IndexVO index = new ShowIndexData().getLatestIndexData();
        String dateString[]=index.getDate();
        long volumeLong[]=index.getVolume();

        double date[]=new double[dateString.length];
        double volume[]=new double[volumeLong.length];
        for(int i=0;i<dateString.length;i++){
            date[i]=Double.parseDouble(dateString[i]);
            volume[i]= (double)volumeLong[i];
        }
        lineChartParent=new LineChartParent(name,date,volume);
    } catch (IOException e) {
        e.printStackTrace();
    }


}

    public ChartPanel getChartPanel() {
        return lineChartParent.getChartPanel();
    }




    public static void main(String[] args) throws IOException {
        JFrame jFrame = new JFrame();
        jFrame.add(new LineChartMarketIndex().getChartPanel());
        jFrame.setBounds(50, 50, 800, 600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}

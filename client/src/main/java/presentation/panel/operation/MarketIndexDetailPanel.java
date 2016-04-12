package presentation.panel.operation;

import bl.ShowIndexData;
import javafx.scene.chart.Chart;
import org.jfree.chart.ChartPanel;
import presentation.UltraSwing.UltraScrollPane;
import presentation.frame.MainFrame;
import presentation.graphs.LineChartMarketIndex;
import presentation.panel.IndexKLine_Daily;
import presentation.panel.IndexKLine_Monthly;
import presentation.panel.IndexKLine_Weekly;
import vo.IndexVO;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by zmj on 2016/3/31.
 */
public class MarketIndexDetailPanel extends JPanel {
    private JTabbedPane tabbedPane;
    private ChartPanel indexKLine_Daily;
    private ChartPanel indexKLine_Monthly;
    private ChartPanel indexKLine_Weekly;
    private ChartPanel lineChartMarketIndexVolume;
    private ChartPanel lineChartMarketIndexHigh;
    private ChartPanel lineChartMarketIndexLow;
    private ChartPanel lineChartMarketIndexOpen;
    private ChartPanel lineChartMarketIndexClose;
    private ChartPanel lineChartMarketIndexADJPrice;
    String chooseDate[];

    public MarketIndexDetailPanel(String chooseDateInit[]) throws IOException, ParseException {
        chooseDate=chooseDateInit;
        this.setLayout(new BorderLayout());
        init();
        initComponents();
    }

    private void init() throws IOException, ParseException {
       indexKLine_Daily = new IndexKLine_Daily().getChartPanel();
        indexKLine_Weekly = new IndexKLine_Weekly().getChartPanel();
        indexKLine_Monthly = new IndexKLine_Monthly().getChartPanel();
        IndexVO index = new ShowIndexData().getLatestIndexData();
        String nameVolume[] = {"日期", "成交量"};
        long volume[] = index.getVolume();
        lineChartMarketIndexVolume = new LineChartMarketIndex(nameVolume, volume,chooseDate).getChartPanel();
        String nameHigh[] = {"日期", "最高价"};
        double high[] = index.getHigh();
        lineChartMarketIndexHigh = new LineChartMarketIndex(nameHigh, high,chooseDate).getChartPanel();
        String nameLow[] = {"日期", "最低价"};
        double low[] = index.getLow();
        lineChartMarketIndexLow = new LineChartMarketIndex(nameLow, low,chooseDate).getChartPanel();
        String nameOpen[] = {"日期", "开盘价"};
        double open[] = index.getOpen();
        lineChartMarketIndexOpen = new LineChartMarketIndex(nameOpen, open,chooseDate).getChartPanel();
        String nameClose[] = {"日期", "收盘价"};
        double close[] = index.getClose();
        lineChartMarketIndexClose = new LineChartMarketIndex(nameClose, close,chooseDate).getChartPanel();
        String namePrice[] = {"日期", "最新价"};
        double price[] = index.getAdj_price();
        lineChartMarketIndexADJPrice = new LineChartMarketIndex(namePrice, price,chooseDate).getChartPanel();

        tabbedPane = new JTabbedPane();
        ImageIcon image1 = new ImageIcon("client/src/main/resources/images/dayK.png");
        ImageIcon image2 = new ImageIcon("client/src/main/resources/images/weekK.png");
        ImageIcon image3 = new ImageIcon("client/src/main/resources/images/monthK.png");
        ImageIcon image4= new ImageIcon("client/src/main/resources/images/volume.png");
        ImageIcon image5 = new ImageIcon("client/src/main/resources/images/high.png");
        ImageIcon image6 = new ImageIcon("client/src/main/resources/images/low.png");
        ImageIcon image7 = new ImageIcon("client/src/main/resources/images/price.png");
        ImageIcon image8 = new ImageIcon("client/src/main/resources/images/open.png");
        ImageIcon image9 = new ImageIcon("client/src/main/resources/images/close.png");
        //tabbedPane.addTab(title, icon, component, tip);
        //添加由 title 和/或 icon 表示的 component 和 tip，其中任意一个都可以为 null。
        tabbedPane.addTab(null,image1,indexKLine_Daily,"日k线");
        tabbedPane.addTab(null,image2,indexKLine_Weekly,"周k线");
        tabbedPane.addTab(null,image3,indexKLine_Monthly,"月k线");
        tabbedPane.addTab(null, image4, lineChartMarketIndexVolume, "成交量");
        tabbedPane.addTab(null, image5, lineChartMarketIndexHigh, "最高价");
        tabbedPane.addTab(null, image6, lineChartMarketIndexLow, "最低价");
        tabbedPane.addTab(null, image7, lineChartMarketIndexADJPrice, "最新价");
        tabbedPane.addTab(null, image8, lineChartMarketIndexOpen, "开盘价");
        tabbedPane.addTab(null, image9, lineChartMarketIndexClose, "收盘价");

    }

    private void initComponents() {
        tabbedPane.setTabPlacement(JTabbedPane.NORTH);
        this.add(tabbedPane);
    }

    public static void main(String[] args) throws IOException {
        JFrame jFrame = new JFrame();
        IndexVO index = new ShowIndexData().getLatestIndexData();
        String nameVolume[] = {"日期", "成交量", "大盘指数"};
        long volume[] = index.getVolume();
//        ChartPanel lineChartMarket=new LineChartMarketIndex(nameVolume, volume).getChartPanel();
//        jFrame.add(lineChartMarket);

        jFrame.setBounds(50, 50, 800, 600);
        jFrame.setVisible(true);
    }

}

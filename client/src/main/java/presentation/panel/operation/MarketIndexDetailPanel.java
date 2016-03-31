package presentation.panel.operation;

import presentation.frame.MainFrame;
import presentation.graphs.LineChartMarketIndex;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by pc on 2016/3/31.
 */
public class MarketIndexDetailPanel extends JPanel {
    MainFrame mainFrame;
    private JTabbedPane tabbedPane;
    private JPanel lineChartMarketIndex ;
    public MarketIndexDetailPanel() throws IOException{
        this.setLayout(new BorderLayout());
        this.setBounds(0, 0, MainFrame.getMainFrame().getWidth(), MainFrame.getMainFrame().getHeight());
        init();
        initComponents();
    }

    private void init() throws IOException{
            lineChartMarketIndex = new LineChartMarketIndex().getChartPanel();
        tabbedPane = new JTabbedPane();
        ImageIcon image1=new ImageIcon("source\\tabbedpanel\\成交量.png");

        //tabbedPane.addTab(title, icon, component, tip);
        //添加由 title 和/或 icon 表示的 component 和 tip，其中任意一个都可以为 null。

        tabbedPane.addTab(null,image1,lineChartMarketIndex,"成交量");

    }

    private void initComponents(){
        tabbedPane.setTabPlacement(JTabbedPane.NORTH);
        //	tabbedPane.setTabPlacement(JTabbedPane.LEFT);
        this.add(tabbedPane);
    }
    public static void main(String[] args)throws IOException{
        JFrame jFrame = new JFrame();
        jFrame.add(new MarketIndexDetailPanel());
        jFrame.setBounds(50, 50, 800, 600);
        jFrame.setVisible(true);
    }

}

package presentation.graphs;

/**
 * Created by zmj on 2016/3/26.
 */

import bl.ShowIndexData;
import org.jfree.chart.ChartPanel;
import vo.IndexVO;

import javax.swing.*;
import java.io.IOException;

public class LineChartMarketIndex {
   public static  LineChartParent lineChartParent;
public  LineChartMarketIndex(){
    String name[]={"日期","成交量","大盘指数"};

    try {
        IndexVO index = new ShowIndexData().getLatestIndexData();
        String date[]=index.getDate();
        long volumeLong[]=index.getVolume();
//System.out.println(dateString[0]+"看看你是什么鬼");
        double volume[]=new double[volumeLong.length];
        for(int i=0;i<date.length;i++){
            volume[i]= (double)volumeLong[i];
        }
//        System.out.println(date[0]+"你又是什么鬼");
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

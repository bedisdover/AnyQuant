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
public  LineChartMarketIndex(String nameInit[],double data[]){
    String name[]=nameInit;

    try {
        IndexVO index = new ShowIndexData().getLatestIndexData();
        String date[]=index.getDate();
        lineChartParent=new LineChartParent(name,date,data);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    public LineChartMarketIndex(String nameInit[],long data[]){
        String name[]=nameInit;
        double dataDouble[]=new double[data.length];
for(int i=0;i<data.length;i++){
    dataDouble[i]=(double)data[i];
}
        try {
            IndexVO index = new ShowIndexData().getLatestIndexData();
            String date[]=index.getDate();
            lineChartParent=new LineChartParent(name,date,dataDouble);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ChartPanel getChartPanel() {
        return lineChartParent.getChartPanel();
    }

}

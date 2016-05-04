package presentation.graphs;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;
import javax.swing.*;
import java.awt.*;

/**
 * Created by zmj on 2016/4/30.
 */
public class RadarChartParent {

    public static JPanel erstelleSpinnenDiagramm(String title,double data[],String name[]) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String group1 = title;
        int length=data.length;
        for(int i=0;i<length;i++){
            dataset.addValue(data[i],group1,name[i]);
        }
        MySpiderWebPlot spiderwebplot = new MySpiderWebPlot(dataset);
        JFreeChart jfreechart = new JFreeChart("标题", TextTitle.DEFAULT_FONT,spiderwebplot, false);
        LegendTitle legendtitle = new LegendTitle(spiderwebplot);
        legendtitle.setPosition(RectangleEdge.BOTTOM);
        jfreechart.addSubtitle(legendtitle);
        ChartPanel chartpanel = new ChartPanel(jfreechart);
        chartpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        return chartpanel;
    }

    public static void main(String args[]) {
        JFrame jf = new JFrame();
        double data[]={5,6,4,2,5,5,5,8};
        String name[]={"w1","w2","w3","w4","w5","w6","w7","w8"};
        jf.add(erstelleSpinnenDiagramm("大盘指数",data,name));
        jf.pack();//这句不加会变成最小化
        jf.setVisible(true);
    }
}

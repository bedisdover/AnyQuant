package presentation.graphs;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
        JFreeChart jfreechart = new JFreeChart(title, TextTitle.DEFAULT_FONT,spiderwebplot, false);
        LegendTitle legendtitle = new LegendTitle(spiderwebplot);
        legendtitle.setPosition(RectangleEdge.BOTTOM);
        jfreechart.addSubtitle(legendtitle);
        ChartPanel chartpanel = new ChartPanel(jfreechart);
        chartpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
//        ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
//        String fileName = null;
//        try {
//            fileName = ServletUtilities.saveChartAsPNG(jfreechart, 700, 300, info, null);//生成图片
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return chartpanel;
    }
//这个可以做两个的对比，画在一个里面
    //title是图的标题，firstGroup和secondGroup可以是两个股票的名字，两个name应该是一样的=。=
    public static JPanel erstelleSpinnenDiagramm(String title,String firstGroup,String secondGroup,double firstData[],double secondData[],String firstName[],String secondName[]) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String group1 = firstGroup;
        String group2=secondGroup;
        int length1=firstData.length;
        for(int i=0;i<length1;i++){
            dataset.addValue(firstData[i],group1,firstName[i]);
        }
        int length2=secondData.length;
        for(int i=0;i<length2;i++){
            dataset.addValue(secondData[i],group2,secondName[i]);
        }
        MySpiderWebPlot spiderwebplot = new MySpiderWebPlot(dataset);
        JFreeChart jfreechart = new JFreeChart(title, TextTitle.DEFAULT_FONT,spiderwebplot, false);
        LegendTitle legendtitle = new LegendTitle(spiderwebplot);
        legendtitle.setPosition(RectangleEdge.BOTTOM);
        jfreechart.addSubtitle(legendtitle);
        ChartPanel chartpanel = new ChartPanel(jfreechart);
        chartpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

//        ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
//        String fileName = null;
//        try {
//            fileName = ServletUtilities.saveChartAsPNG(jfreechart, 700, 300, info, null);//生成图片
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return chartpanel;
    }

    public static void main(String args[]) {
        JFrame jf = new JFrame();
        double data1[]={5,6,4,2,5,5,5,8};
        String name1[]={"w1","w2","w3","w4","w5","w6","w7","w8"};
        double data2[]={1,2,3,4,5,6,7,8};
        String name2[]={"w1","w2","w3","w4","w5","w6","w7","w8"};
    //    jf.add(erstelleSpinnenDiagramm("大盘指数","1","2",data1,data2,name1,name2));
        jf.pack();//这句不加会变成最小化
        jf.setVisible(true);
    }
}

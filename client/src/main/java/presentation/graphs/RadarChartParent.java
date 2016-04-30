package presentation.graphs;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by zmj on 2016/4/30.
 */
public class RadarChartParent {


    public static JPanel erstelleSpinnenDiagramm() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String group1 = "大盘指数 ";

        dataset.addValue(5, group1, "w1");
        dataset.addValue(6, group1, "w2");
        dataset.addValue(4, group1, "w3");
        dataset.addValue(2, group1, "w4");
        dataset.addValue(5, group1, "w5");
        dataset.addValue(5, group1, "w6");
        dataset.addValue(5, group1, "w7");
        dataset.addValue(8, group1, "w8");
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
        jf.add(erstelleSpinnenDiagramm());
        jf.pack();//这句不加会变成最小化
        jf.setVisible(true);
    }
}

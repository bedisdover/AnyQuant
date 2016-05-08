package presentation;

/**
 * Created by lenovo2014 on 2016/3/26.
 */
import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.general.DefaultPieDataset;
public class BingDemo {
    public static void main(String[] args) {
        StandardChartTheme sct = new StandardChartTheme("CN");
        sct.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
        sct.setRegularFont(new Font("隶书", Font.BOLD, 20));
        sct.setLargeFont(new Font("隶书", Font.BOLD, 20));
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("苹果", 100);
        dataset.setValue("梨子", 200);
        dataset.setValue("葡萄", 300);
        dataset.setValue("香蕉", 400);
        dataset.setValue("荔枝", 500);
        ChartFactory.setChartTheme(sct);
        JFreeChart jfreechart = ChartFactory.createPieChart3D("水果产量图", dataset,
                true, true, true);
        ChartFrame frame = new ChartFrame("报表练习", jfreechart);
        frame.setVisible(true);
        frame.pack();
    }
}
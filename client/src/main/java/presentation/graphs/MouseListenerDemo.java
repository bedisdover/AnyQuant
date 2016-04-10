package presentation.graphs;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

public class MouseListenerDemo extends ApplicationFrame implements ChartMouseListener{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JFreeChart chart;
    private ChartPanel chartPanel;

    public MouseListenerDemo(String paramString){
        super(paramString);
        String applicationTitle = "Mouse Listener Demo";
        XYDataset xyDataset = createDataset();
        this.chart = ChartFactory.createXYLineChart(applicationTitle, "X", "Y", xyDataset,
                PlotOrientation.VERTICAL, true, true, false);
        this.chartPanel = new ChartPanel(this.chart);
        this.chartPanel.setPreferredSize(new Dimension(700, 450));
        this.chartPanel.setMouseZoomable(true, false);
        this.chartPanel.addChartMouseListener(this);
        setContentPane(this.chartPanel);
    }

    public XYDataset createDataset(){
        XYSeries xySeries = new XYSeries("Series 1");
        xySeries.add(12.0D, 10.0D);
        xySeries.add(13.0D, 15.0D);
        xySeries.add(14.0D, 16.0D);
        xySeries.add(15.5D, 17.0D);
        xySeries.add(17.0D, 20.0D);
        xySeries.add(18.0D, 18.0D);
        xySeries.add(19.0D, 16.0D);
        xySeries.add(20.0D, 15.0D);
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        xySeriesCollection.addSeries(xySeries);
        return xySeriesCollection;
    }

    public void chartMouseClicked(ChartMouseEvent paramChartMouseEvent){

    }

    public void chartMouseMoved(ChartMouseEvent paramChartMouseEvent){
//        int xPos = paramChartMouseEvent.getTrigger().getX();
//        int yPos = paramChartMouseEvent.getTrigger().getY();
//        System.out.println("x = " + xPos + ", y = " + yPos);
//        Point2D point2D = this.chartPanel.translateScreenToJava2D(new Point(xPos, yPos));
//        XYPlot xyPlot = (XYPlot)this.chart.getPlot();
//        ChartRenderingInfo chartRenderingInfo = this.chartPanel.getChartRenderingInfo();
//        Rectangle2D rectangle2D = chartRenderingInfo.getPlotInfo().getDataArea();
//        ValueAxis valueAxis1 = xyPlot.getDomainAxis();
//        RectangleEdge rectangleEdge1 = xyPlot.getDomainAxisEdge();
//        ValueAxis valueAxis2 = xyPlot.getRangeAxis();
//        RectangleEdge rectangleEdge2 = xyPlot.getRangeAxisEdge();
//        double d1 = valueAxis1.java2DToValue(point2D.getX(), rectangle2D, rectangleEdge1);
//        double d2 = valueAxis2.java2DToValue(point2D.getY(), rectangle2D, rectangleEdge2);
//        System.out.println("Chart: x = " + d1 + ", y = " + d2);
        int xPos = paramChartMouseEvent.getTrigger().getX();
        int yPos = paramChartMouseEvent.getTrigger().getY();

        this.chartPanel.setHorizontalAxisTrace(true);
        this.chartPanel.setVerticalAxisTrace(true);
        ChartEntity chartEntity = this.chartPanel.getEntityForPoint(xPos,yPos);
        String[] info = chartEntity.toString().split(" ");
        if(info[1].equals("series")){
            int item = Integer.parseInt(info[6].substring(0,info[6].length()-1));
    }}

    public static void main(String[] paramArrayOfString){
        MouseListenerDemo localMouseListenerDemo = new MouseListenerDemo("JFreeChart: MouseListenerDemo.java");
        localMouseListenerDemo.pack();
        RefineryUtilities.centerFrameOnScreen(localMouseListenerDemo);
        localMouseListenerDemo.setVisible(true);
    }
}
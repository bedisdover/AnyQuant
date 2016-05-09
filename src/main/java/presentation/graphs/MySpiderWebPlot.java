package presentation.graphs;

import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.data.category.CategoryDataset;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.*;
import java.text.NumberFormat;

/**
 * Created by zmj on 2016/4/30.
 */
//带刻度问题比较复杂，JFreeChart本身没有提供其功能，需要继承SpiderWebPlot，覆盖drawLabel方法。
public class MySpiderWebPlot extends SpiderWebPlot {
    private int ticks = DEFAULT_TICKS;
    private static final int DEFAULT_TICKS = 5;
    private NumberFormat format = NumberFormat.getInstance();
    private static final double PERPENDICULAR = 90;
    private static final double TICK_SCALE = 0.015;
    private int valueLabelGap = DEFAULT_GAP;
    private static final int DEFAULT_GAP = 10;
    private static final double THRESHOLD = 15;



    MySpiderWebPlot(CategoryDataset createCategoryDataset) {
        super(createCategoryDataset);
    }
    @Override
    protected void drawLabel(final Graphics2D g2, final Rectangle2D plotArea, final double value,
                             final int cat, final double startAngle, final double extent) {
        super.drawLabel(g2, plotArea, value, cat, startAngle, extent);
        final FontRenderContext frc = g2.getFontRenderContext();
        final double[] transformed = new double[2];
        final double[] transformer = new double[2];
        final Arc2D arc1 = new Arc2D.Double(plotArea, startAngle, 0, Arc2D.OPEN);
        for (int i = 1; i <= ticks; i++) {
            final Point2D point1 = arc1.getEndPoint();
            final double deltaX = plotArea.getCenterX();
            final double deltaY = plotArea.getCenterY();
            double labelX = point1.getX() - deltaX;
            double labelY = point1.getY() - deltaY;
            final double scale = ((double) i / (double) ticks);
            final AffineTransform tx = AffineTransform.getScaleInstance(scale, scale);
            final AffineTransform pointTrans = AffineTransform.getScaleInstance(scale + TICK_SCALE, scale + TICK_SCALE);
            transformer[0] = labelX;
            transformer[1] = labelY;
            pointTrans.transform(transformer, 0, transformed, 0, 1);
            final double pointX = transformed[0] + deltaX;
            final double pointY = transformed[1] + deltaY;
            tx.transform(transformer, 0, transformed, 0, 1);
            labelX = transformed[0] + deltaX;
            labelY = transformed[1] + deltaY;
            double rotated = (PERPENDICULAR);
            AffineTransform rotateTrans = AffineTransform.getRotateInstance(Math.toRadians(rotated), labelX, labelY);
            transformer[0] = pointX;
            transformer[1] = pointY;
            rotateTrans.transform(transformer, 0, transformed, 0, 1);
            final double x1 = transformed[0];
            final double y1 = transformed[1];
            rotated = (-PERPENDICULAR);
            rotateTrans = AffineTransform.getRotateInstance(Math.toRadians(rotated), labelX, labelY);
            rotateTrans.transform(transformer, 0, transformed, 0, 1);
            final Composite saveComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            g2.draw(new Line2D.Double(transformed[0], transformed[1], x1, y1));
            if (startAngle == this.getStartAngle()) {
                final String label = format.format(((double) i / (double) ticks) * this.getMaxValue());
                final Rectangle2D labelBounds = getLabelFont().getStringBounds(label, frc);
                final LineMetrics lm = getLabelFont().getLineMetrics(label, frc);
                final double ascent = lm.getAscent();
                if (Math.abs(labelX - plotArea.getCenterX()) < THRESHOLD) {
                    labelX += valueLabelGap;
                    labelY += ascent / (float) 2;
                } else if (Math.abs(labelY - plotArea.getCenterY()) < THRESHOLD) {
                    labelY += valueLabelGap;
                } else if (labelX >= plotArea.getCenterX()) {
                    if (labelY < plotArea.getCenterY()) {
                        labelX += valueLabelGap;
                        labelY += valueLabelGap;
                    } else {
                        labelX -= valueLabelGap;
                        labelY += valueLabelGap;
                    }
                } else {
                    if (labelY > plotArea.getCenterY()) {
                        labelX -= valueLabelGap;
                        labelY -= valueLabelGap;
                    } else {
                        labelX += valueLabelGap;
                        labelY -= valueLabelGap;
                    }
                }
                g2.setPaint(getLabelPaint());
                g2.setFont(getLabelFont());
                g2.drawString(label, (float) labelX, (float) labelY);
            }
            g2.setComposite(saveComposite);
        }
    }
}


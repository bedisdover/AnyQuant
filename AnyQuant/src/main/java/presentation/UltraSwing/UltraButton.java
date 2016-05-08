package presentation.UltraSwing;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 宋益明 on 16-3-17.
 * <p>
 * 自定义按钮
 * 可以根据所显示的文本自动调节大小
 */
public class UltraButton extends JButton {

    /**
     * 初始文本
     */
    private String text;

    /**
     * 按钮的宽高
     */
    public int width, height;

    public UltraButton(String text) {
        super(text);

        this.text = text;

        init();
    }

    public UltraButton(int num) {
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        setBorderPainted(false);
        setBorder(null);
        setContentAreaFilled(false);


        Image imageLineChart = Toolkit.getDefaultToolkit().getImage("client/src/main/resources/images/linechart.png");
        Image imageKLine = Toolkit.getDefaultToolkit().getImage("client/src/main/resources/images/klineicon.jpg");
        Image imageAna = Toolkit.getDefaultToolkit().getImage("client/src/main/resources/images/analysis.jpg");
        Image imageTime = Toolkit.getDefaultToolkit().getImage("client/src/main/resources/images/timeseries.png");
        ImageIcon iconLineChart = new ImageIcon(imageLineChart);
        ImageIcon iconKLine = new ImageIcon(imageKLine);
        ImageIcon iconAna = new ImageIcon(imageAna);
        ImageIcon iconTime = new ImageIcon(imageTime);


        if (num == 1) {
            //K线图
            this.setIcon(iconKLine);
            this.setToolTipText("K线图");
        }
        if (num == 2) {
            //折线图
            this.setIcon(iconLineChart);
            this.setToolTipText("折线图");
        }
        if (num == 3) {
            //综合分析
            this.setIcon(iconAna);
            this.setToolTipText("综合分析");
        }
        if (num == 4) {
            //分时图
            this.setIcon(iconTime);
            this.setToolTipText("分时图");
        }
    }

    /**
     * 初始化
     */
    private void init() {
        Font font = getFont();
        width = text.length() * font.getSize() + 30;
        height = font.getSize() + 15;
        setPreferredSize(new Dimension(width, height));
    }
}

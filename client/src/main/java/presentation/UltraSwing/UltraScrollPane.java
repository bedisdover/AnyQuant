package presentation.UltraSwing;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by 宋益明 on 16-3-17.
 * <p>
 * 滚动面板
 * 可调节透明度
 */
public class UltraScrollPane extends JScrollPane {
    public UltraScrollPane(Component view) {
        super(view);

        init();
    }

    /**
     * 初始化
     */
    private void init() {
        setBackground(new Color(0, 0, 0, 0));
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}
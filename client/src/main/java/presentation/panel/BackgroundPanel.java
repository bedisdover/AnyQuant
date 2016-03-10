package presentation.panel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 宋益明 on 16-3-5.
 *
 * 背景面板
 * 负责绘制最底层的背景,可通过getBackdrop方法和setBackdrop方法获取或设置背景
 */
public class BackgroundPanel extends JPanel {

    /**w
     * 背景图片
     */
    private Image backdrop;

    private final float TRANSPARENCY = 0.5f;

    public BackgroundPanel(Image image) {
        this.backdrop = image;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        g.drawImage(backdrop, 0, 0, getWidth(), getHeight(), null);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, TRANSPARENCY));
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
    }

    public Image getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(Image backdrop) {
        this.backdrop = backdrop;
    }
}

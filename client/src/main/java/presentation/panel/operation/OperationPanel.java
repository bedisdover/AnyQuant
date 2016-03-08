package presentation.panel.operation;

import presentation.frame.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by song on 16-3-3.
 * <p>
 * 操作面板,区别于菜单面板
 * 其他面板均为操作面板的超类
 */
public class OperationPanel extends JPanel {

    /**
     * 面板宽度,高度(讲道理的话,宽度和高度是不确定的,但为了程序方便,
     * 而且真实高度和宽度都是根据主界面的宽度和高度确定的,所以这里仅
     * 简单地计算出宽度和高度)
     */
    protected final int WIDTH, HEIGHT;

    /**
     * 外边距
     */
    protected final int MARGIN;

    /**
     * 内边距
     */
    protected final int PADDING;


    public OperationPanel() {
        WIDTH = MainFrame.getMainFrame().getWidth() * 4 / 5;
        HEIGHT = MainFrame.getMainFrame().getHeight();
        MARGIN = MainFrame.getMainFrame().getWidth() / 25;
        PADDING = MainFrame.getMainFrame().getWidth() / 20;

        setOpaque(false);
    }


    protected void createUICompontents() {

    }

    protected void addListener() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setColor(new Color(158, 76, 152));
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}

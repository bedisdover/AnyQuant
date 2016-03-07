package presentation.panel.operation;

import javax.swing.*;
import java.awt.*;

/**
 * Created by song on 16-3-3.
 * <p>
 * 操作面板,区别于菜单面板
 * 其他面板均为操作面板的超类
 */
public class OperationPanel extends JPanel {

    protected JButton btnSearch;

    public OperationPanel() {
        setOpaque(false);
    }



    protected void createUICompontents() {

    }

    protected void addListener() {

    }

//    TODO 不知为何会影响到子类的透明度,并不影响本类组件
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setColor(new Color(158, 76, 152));
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}

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

    protected final float TRANSPARENCY = 0.5f;

    public OperationPanel() {

    }

    protected void createUICompontents() {

    }

    protected void addListener() {

    }

//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D graphics2D = (Graphics2D) g;
//
////        graphics2D.setColor(Color.red);
//        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, TRANSPARENCY));
//        graphics2D.fillRect(0, 0, 100, getHeight());
//    }
}

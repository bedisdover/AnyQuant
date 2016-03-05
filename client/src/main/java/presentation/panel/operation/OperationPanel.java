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

    protected JButton labelSearch;

    public OperationPanel() {
        setOpaque(false);
        labelSearch = new JButton("搜索");

        labelSearch.setBounds(100, 100, 100, 100);

        add(labelSearch);
    }

    protected void createUICompontents() {

    }

    protected void addListener() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setColor(Color.white);
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}

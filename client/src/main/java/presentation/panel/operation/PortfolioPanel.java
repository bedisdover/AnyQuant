package presentation.panel.operation;

import javax.swing.*;
import java.awt.*;

/**
 * Created by song on 16-3-2.
 *
 * 自选面板
 */
public class PortfolioPanel extends OperationPanel {



    public PortfolioPanel() {
        init();
        createUIComponents();
    }

    private void init() {
        setLayout(null);
    }

    private void createUIComponents() {
        String[] columnNames = {"编号", "最新", "涨幅", "最高", "最低", "test", "test1"};
        int[] list = {40, 87, 30};
        JScrollPane scrollPane = new TableCopy().drawTable(columnNames, list);
        scrollPane.setBounds(10, 30, 500, 300);
        add(scrollPane);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics2D = (Graphics2D) g;
//        graphics2D.drawImage(ImageLoader.nothing, 0, 0, getWidth() * 4 / 5, getHeight(), null);

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}

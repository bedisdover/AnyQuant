package presentation.panel.operation;

import bl.SelfSelectStock_stub;
import po.StockPO;
import presentation.frame.MainFrame;
import presentation.panel.info.StockInfoPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

/**
 * Created by song on 16-3-2.
 *
 * 自选面板
 */
public class PortfolioPanel extends OperationPanel {

    private JButton button;

    public PortfolioPanel() {
        init();
        createUIComponents();
        addListeners();
    }

    protected void init() {
        setLayout(null);
    }

    protected void createUIComponents() {
        button = new JButton("查看");

        button.setBounds(MARGIN, MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT);

        add(button);
    }

    private void addListeners() {
        Iterator<StockPO> stocks = new SelfSelectStock_stub().getFollowed();
        final StockInfoPanel stockInfoPanel = new StockInfoPanel(this, stocks.next());
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    MainFrame.getMainFrame().addOperationPanel(stockInfoPanel);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics2D = (Graphics2D) g;
//        graphics2D.drawImage(ImageLoader.nothing, 0, 0, getWidth() * 4 / 5, getHeight(), null);

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}

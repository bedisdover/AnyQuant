package presentation.panel.operation;

import bl.SelfSelectStock;
import vo.StockVO;

import java.awt.*;
import java.util.Iterator;

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

    protected void init() {
        setLayout(null);
    }

    protected void createUIComponents() {
        Iterator<StockVO> stocks = new SelfSelectStock().getFollowed();
//        if (stocks.hasNext()) {
//            createTable(stocks);
//        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics2D = (Graphics2D) g;
//        graphics2D.drawImage(ImageLoader.nothing, 0, 0, getWidth() * 4 / 5, getHeight(), null);

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}

package presentation.panel.operation;

import presentation.util.ImageLoader;

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

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

        graphics2D.drawImage(ImageLoader.nothing, 0, 0, getWidth() * 4 / 5, getHeight(), null);
    }
}

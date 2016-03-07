package presentation.panel.operation;

import java.awt.*;

/**
 * Created by song on 16-3-2.
 *
 * 自选面板
 */
public class PortfolioPanel extends OperationPanel {


    public PortfolioPanel() {
//        super();
        repaint();
    }

    private void init() {
        setLayout(null);
    }

//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//        System.out.println(1);
//        g.drawImage(ImageLoader.nothing, 0, 0, getWidth(), getHeight(), null);
//    }
//
//    @Override
//    public void paintComponents(Graphics g) {
//        super.paintComponents(g);
//
//        System.out.println(2);
//    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        System.out.println(1);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}

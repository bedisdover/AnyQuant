package presentation.panel.operation;

import bl.ShowIndexData;
import presentation.util.DateChooser;
import vo.IndexVO;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by 宋益明 on 16-3-2.
 * <p>
 * 大盘指数面板
 */
public class MarketIndexPanel extends OperationPanel {

    private DateChooser dateChooser;

    public MarketIndexPanel() {
        init();
        createUIComponents();
//        addListeners();
    }

    protected void init() {

        this.setLayout(null);
    }

    protected void createUIComponents() {
//        dateChooser=new DateChooser(this,MARGIN, MARGIN, BUTTON_WIDTH * 2, BUTTON_HEIGHT);

        IndexVO index = null;
        try {
            index = new ShowIndexData().getLatestIndexData();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(MarketIndexPanel.this,"请检查网络连接！");
        }
        createTable(index);
    }

//    private void addListeners() {
//        addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                int tableHeight = Math.min((data.length + 1) * table.getRowHeight()
//                                + scrollPane.getHorizontalScrollBar().getHeight(),
//                        PANEL_HEIGHT - MARGIN * 2 - PADDING - BUTTON_HEIGHT);
//                int tableWidth = Math.min(table.getColumnModel().getTotalColumnWidth()
//                                + scrollPane.getVerticalScrollBar().getWidth(),
//                        PANEL_WIDTH - MARGIN * 2);
//
//                if (tableWidth == PANEL_WIDTH - MARGIN * 2) {
//                    scrollPane.setBounds(MARGIN, MARGIN + PADDING, PANEL_WIDTH - 2 * MARGIN, tableHeight);
//                } else {
//
//                }
//            }
//        });


//        /**
//         * todo
//         */
//        search.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                String text=searchInput.getText();
//                Date date=dateChooser.getDate();
//
//            }
//        });
//        /**
//         * todo
//         */
//        more.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                String text=searchInput.getText();
//                Date date=dateChooser.getDate();
//
//            }
//        });
//    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}

package presentation.panel.operation;

import bl.SelfSelectStock;
import data.GetStockData;
import presentation.frame.MainFrame;
import vo.StockVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by song on 16-3-2.
 *
 * 自选面板
 */
public class PortfolioPanel extends OperationPanel {

    /**
     * 取消关注按钮
     */
    private JButton cancel;

    public PortfolioPanel() {
        init();
        createUIComponents();
        addListeners();
    }

    protected void init() {
        setLayout(null);
    }

    protected void createUIComponents() {
        cancel = new JButton("取消关注");

        cancel.setBounds(WIDTH - MARGIN * 2 - BUTTON_WIDTH, MARGIN, BUTTON_WIDTH + MARGIN, BUTTON_HEIGHT);

        add(cancel);

        Iterator<String> stockID = new SelfSelectStock().getFollowed();
        List<StockVO> list = new ArrayList<StockVO>();
        GetStockData getStockData = new GetStockData();
        while (stockID.hasNext()) {
            list.add(new StockVO(getStockData.getStockData_name(stockID.next())));
        }

        createTable(list);
    }

    private void addListeners() {
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int line = table.getSelectedRow();
                if (line != -1) {
                    try {
                        new SelfSelectStock().removeStock((String) table.getValueAt(line, 1));
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "取消关注成功!");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    MainFrame.getMainFrame().addOperationPanel(new PortfolioPanel());
                }
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

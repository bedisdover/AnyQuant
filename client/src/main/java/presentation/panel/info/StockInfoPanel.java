package presentation.panel.info;

import bl.SelfSelectStock;
import blservice.SelfSelectStockService;
import po.StockPO;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by 宋益明 on 16-3-8.
 * <p>
 * 股票详细信息面板
 * 通过表格展现股票的所有信息
 * 通过图表展示数据变化情况
 */
public class StockInfoPanel extends InfoPanel {

    /**
     * 关注按钮
     */
    private JButton follow;

    private String stockID;

    public StockInfoPanel(JPanel parent, String stockID) {
        super(parent, stockID);

        this.stockID = stockID;
    }

    public StockInfoPanel(JPanel parent, StockPO stock) {
        super(parent, stock);
    }

    @Override
    protected void createUIComponents() {
        super.createUIComponents();

        follow = new JButton("关注");

        follow.setBounds(PANEL_WIDTH / 2, MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT);

        add(follow);
    }

    @Override
    protected void addListeners() {
        super.addListeners();

        follow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                follow(stockID);
            }
        });
    }

    /**
     * 关注股票
     *
     * @param name 股票名称(代码)
     */
    private void follow(String name) {
        SelfSelectStockService selfSelect = new SelfSelectStock();

        try {
            selfSelect.addStock(name);
            JOptionPane.showMessageDialog(this, "添加成功!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "呀!出错啦...");
        }
    }
}

package presentation.panel.info;

import po.current.CurrentStockPO;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 宋益明 on 16-4-12.
 *
 * 股票信息面板（美股）
 * 包含当前信息及分时图
 */
public class US_StockInfoPanel extends JPanel {

    /**
     * 股票名称
     */
    private String name;

    private CurrentStockPO stock;


    US_StockInfoPanel(String name) {

    }

    private void init() {
        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 0, 0));
    }

    private void createUIComponents() {

    }

    /**
     * 创建北部面板，包括一个按钮
     */
    private void createNorthPanel() {

    }

    /**
     * 创建中部面板，包括实时数据
     */
    private void createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        {//西部面板，包括名称，ID
            JPanel westPanel = new JPanel();
            westPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

            JLabel labelName = new JLabel(stock.getName());
            JLabel labelID = new JLabel(stock.getId());

            westPanel.add(labelName);
            westPanel.add(labelID);
        }

        {//中部面板，包括最新价格、涨跌额、涨跌百分比
            JPanel cenPanel = new JPanel();
            cenPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            JLabel labelPrice = new JLabel(stock.getPrice() + "");
            JLabel labelIncrease = new JLabel(stock.getIncrease() + "");
            JLabel labelIncreasePer = new JLabel(stock.getIncreasePer() + "");

            cenPanel.add(labelPrice);
            cenPanel.add(labelIncrease);
            cenPanel.add(labelIncreasePer);
        }

        add(centerPanel, BorderLayout.CENTER);
    }
}

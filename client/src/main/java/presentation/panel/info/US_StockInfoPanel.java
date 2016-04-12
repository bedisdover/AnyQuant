package presentation.panel.info;

import data.currentdata.CurrentStockData;
import po.current.CurrentStockPO;
import presentation.UltraSwing.UltraPanel;
import presentation.graphs.TimeSeriesChart;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by 宋益明 on 16-4-12.
 *
 * 股票信息面板（美股）
 * 包含当前信息及分时图
 */
public class US_StockInfoPanel extends UltraPanel {

    /**
     * 股票持久化对象
     */
    private CurrentStockPO stock;

    public US_StockInfoPanel(String id) throws Exception {
        stock = new CurrentStockData().getCurrentStockPO_US(id);

        init();
        createUIComponents();
    }

    /**
     * 初始化
     */
    private void init() {
        setLayout(new BorderLayout());
    }

    /**
     * 创建组件
     */
    private void createUIComponents() {
        createNorthPanel();
        createCenterPanel();
        createSouthPanel();
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
        UltraPanel centerPanel = new UltraPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        centerPanel.setBackground(new Color(175, 152, 139, 56));

        {//西部面板，包括名称，ID
            UltraPanel westPanel = new UltraPanel();
            new BoxLayout(westPanel, BoxLayout.Y_AXIS);
            westPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
            westPanel.setBackground(new Color(140, 175, 146, 58));

            JLabel labelName = new JLabel(stock.getName());
            JLabel labelID = new JLabel(stock.getId());

            westPanel.add(labelName);
            westPanel.add(labelID);

            centerPanel.add(westPanel, BorderLayout.WEST);
        }

        {//中部面板，包括最新价格、涨跌额、涨跌百分比
            UltraPanel cenPanel = new UltraPanel();
            cenPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            JLabel labelPrice = new JLabel(stock.getPrice() + "");
            JLabel labelIncrease = new JLabel(stock.getIncrease() + "");
            JLabel labelIncreasePer = new JLabel(stock.getIncreasePer() + "");

            cenPanel.add(labelPrice);
            cenPanel.add(labelIncrease);
            cenPanel.add(labelIncreasePer);

            centerPanel.add(cenPanel, BorderLayout.CENTER);
        }

        {//东部面板，包括最高、最低、开盘、收盘、成交量、市值
            UltraPanel eastPanel = new UltraPanel();
            eastPanel.setLayout(new BorderLayout());

            {//包括最高、最低
                UltraPanel highPanel = new UltraPanel();
                new BoxLayout(eastPanel, BoxLayout.Y_AXIS);

                JLabel labelHigh = new JLabel("最高" + stock.getHigh() + "");
                JLabel labelLow = new JLabel("最低" + stock.getLow() + "");

                highPanel.add(labelHigh);
                highPanel.add(labelLow);

                eastPanel.add(highPanel, BorderLayout.WEST);
            }

            {//包括开盘、收盘
                UltraPanel openPanel = new UltraPanel();
                new BoxLayout(openPanel, BoxLayout.Y_AXIS);

                JLabel labelOpen = new JLabel(stock.getOpen() + "");
                JLabel labelClose = new JLabel(stock.getClose() + "");

                openPanel.add(labelOpen);
                openPanel.add(labelClose);
                eastPanel.add(openPanel, BorderLayout.CENTER);
            }

            {//包括成交量、市值
                UltraPanel amountPanel = new UltraPanel();
                new BoxLayout(amountPanel, BoxLayout.Y_AXIS);

                JLabel labelNum = new JLabel(stock.getDealNum());
                //市值
                JLabel labelValue = new JLabel(stock.getDealAmount());

                amountPanel.add(labelNum);
                amountPanel.add(labelValue);

                eastPanel.add(amountPanel, BorderLayout.EAST);
            }
        }

        add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * 创建南部面板，包括分时图
     */
    private void createSouthPanel() {
        JPanel southPanel = new TimeSeriesChart("中国银行").getChartPanel();

        add(southPanel, BorderLayout.SOUTH);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setColor(new Color(158, 76, 152));
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}

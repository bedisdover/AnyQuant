package presentation.panel.info;

import bl.SelfSelectStock;
import blservice.SelfSelectStockService;
import data.currentdata.CurrentStockData;
import po.current.CurrentStockPO;
import presentation.graphs.TimeSeriesChart;
import presentation.panel.operation.OperationPanel;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by 宋益明 on 16-4-12.
 *
 * 股票信息面板（美股）
 * 包含当前信息及分时图
 */
public class US_StockInfoPanel extends OperationPanel {

    /**
     * 股票持久化对象
     */
    private CurrentStockPO stock;

    /**
     * 关注按钮
     */
    private JButton btnFollow;

    /**
     * 当前信息面板
     */
    private JPanel currentInfo;

    /**
     * 分时图面板
     */
    private JPanel timePanel;

    /**
     * 刷新界面标记，界面发生切换后，停止刷新
     */
    private boolean updateFlag;

    public US_StockInfoPanel(String id) throws Exception {
        stock = new CurrentStockData().getCurrentStockPO_US(id);

        init();
        createUIComponents();
        addListeners();
    }

    /**
     * 初始化
     */
    protected void init() {
        setLayout(null);
        updateFlag = true;

        update();
    }

    /**
     * 创建组件
     */
    protected void createUIComponents() {
        btnFollow = new JButton("关注");

        try {
            currentInfo = new StockCurrentInfoPanel(stock.getId());
            timePanel = new TimeSeriesChart(stock.getName()).getChartPanel();
        } catch (Exception e) {
            e.printStackTrace();
        }

        add(btnFollow);
        add(currentInfo);
        add(timePanel);
    }

    protected void addListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                assignment();
            }
        });

        btnFollow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                follow(stock.getId());
            }
        });
    }

    /**
     * 界面大小发生变化时，重新布局所有组件
     */
    private void assignment() {
        super.assignmentValue();

        btnFollow.setBounds(PANEL_WIDTH - MARGIN - BUTTON_WIDTH,
                MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT);
        currentInfo.setBounds(MARGIN, btnFollow.getY() + BUTTON_HEIGHT + PADDING / 4,
                PANEL_WIDTH - MARGIN * 2, LocationValue.INFO_PANEL_HEIGHT);
        timePanel.setBounds(MARGIN, currentInfo.getY() + currentInfo.getHeight() + PADDING / 4,
                PANEL_WIDTH - MARGIN * 2,
                PANEL_HEIGHT - (currentInfo.getY() + currentInfo.getHeight() + PADDING / 4) - MARGIN);

        revalidate();
        repaint();
    }

    /**
     * 关注股票
     *
     * @param name 股票名称(代码)
     */
    private void follow(String name) {
        SelfSelectStockService selfSelect = new SelfSelectStock();

        try {
            boolean exist = selfSelect.addStock(name);
            if (exist) {
                JOptionPane.showMessageDialog(this, "添加成功!");
            } else {
                JOptionPane.showMessageDialog(this, "您添加的股票已在关注列表中");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "呀!出错啦...");
        }
    }

    /**
     * 固定时间间隔刷新面板
     */
    private void update() {
        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    while (updateFlag) {
                        Thread.sleep(100);
                        assignment();
                    }
                } catch (Exception e) {}
            }
        }.start();
    }



//    /**
//     * 创建北部面板，包括一个按钮
//     */
//    private void createNorthPanel() {
//
//    }
//
//    /**
//     * 创建中部面板，包括实时数据
//     */
//    private void createCenterPanel() {
//        UltraPanel centerPanel = new UltraPanel();
//        centerPanel.setLayout(new BorderLayout());
//        centerPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
//        centerPanel.setBackground(new Color(175, 152, 139, 56));
//
//        {//西部面板，包括名称，ID
//            UltraPanel westPanel = new UltraPanel();
//            new BoxLayout(westPanel, BoxLayout.Y_AXIS);
//            westPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
//            westPanel.setBackground(new Color(140, 175, 146, 58));
//
//            JLabel labelName = new JLabel(stock.getName());
//            JLabel labelID = new JLabel(stock.getId());
//
//            westPanel.add(labelName);
//            westPanel.add(labelID);
//
//            centerPanel.add(westPanel, BorderLayout.WEST);
//        }
//
//        {//中部面板，包括最新价格、涨跌额、涨跌百分比
//            UltraPanel cenPanel = new UltraPanel();
//            cenPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//
//            JLabel labelPrice = new JLabel(stock.getPrice() + "");
//            JLabel labelIncrease = new JLabel(stock.getIncrease() + "");
//            JLabel labelIncreasePer = new JLabel(stock.getIncreasePer() + "");
//
//            cenPanel.add(labelPrice);
//            cenPanel.add(labelIncrease);
//            cenPanel.add(labelIncreasePer);
//
//            centerPanel.add(cenPanel, BorderLayout.CENTER);
//        }
//
//        {//东部面板，包括最高、最低、开盘、收盘、成交量、市值
//            UltraPanel eastPanel = new UltraPanel();
//            eastPanel.setLayout(new BorderLayout());
//
//            {//包括最高、最低
//                UltraPanel highPanel = new UltraPanel();
//                new BoxLayout(eastPanel, BoxLayout.Y_AXIS);
//
//                JLabel labelHigh = new JLabel("最高" + stock.getHigh() + "");
//                JLabel labelLow = new JLabel("最低" + stock.getLow() + "");
//
//                highPanel.add(labelHigh);
//                highPanel.add(labelLow);
//
//                eastPanel.add(highPanel, BorderLayout.WEST);
//            }
//
//            {//包括开盘、收盘
//                UltraPanel openPanel = new UltraPanel();
//                new BoxLayout(openPanel, BoxLayout.Y_AXIS);
//
//                JLabel labelOpen = new JLabel(stock.getOpen() + "");
//                JLabel labelClose = new JLabel(stock.getClose() + "");
//
//                openPanel.add(labelOpen);
//                openPanel.add(labelClose);
//                eastPanel.add(openPanel, BorderLayout.CENTER);
//            }
//
//            {//包括成交量、市值
//                UltraPanel amountPanel = new UltraPanel();
//                new BoxLayout(amountPanel, BoxLayout.Y_AXIS);
//
//                JLabel labelNum = new JLabel(stock.getDealNum());
//                //市值
//                JLabel labelValue = new JLabel(stock.getDealAmount());
//
//                amountPanel.add(labelNum);
//                amountPanel.add(labelValue);
//
//                eastPanel.add(amountPanel, BorderLayout.EAST);
//            }
//        }
//
//        add(centerPanel, BorderLayout.CENTER);
//    }
//
//    /**
//     * 创建南部面板，包括分时图
//     */
//    private void createSouthPanel() {
//        JPanel southPanel = new TimeSeriesChart("中国银行").getChartPanel();
//
//        add(southPanel, BorderLayout.SOUTH);
//    }
//
//    @Override
//    public void paintComponents(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D graphics2D = (Graphics2D) g;
//
//        graphics2D.setColor(new Color(158, 76, 152));
//        graphics2D.fillRect(0, 0, getWidth(), getHeight());
//        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
//    }
}

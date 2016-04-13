package presentation.panel.info;

import config.SystemConfig;
import data.currentdata.CurrentStockData;
import dataservice.current.CurrentStockDataService;
import org.dom4j.DocumentException;
import po.StockType;
import po.current.CurrentStockPO;
import presentation.UltraSwing.UltraLabel;
import presentation.util.ImageLoader;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;

import static presentation.panel.info.LocationValue.*;

/**
 * Created by 宋益明 on 16-3-31.
 * <p>
 * 股票当前信息面板
 */
public class StockCurrentInfoPanel extends JPanel {

    /**
     * 股票当前数据值对象
     */
    private CurrentStockPO stock;

    /**
     * 股票ID
     */
    private String id;

    /**
     * 当前价格
     */
    private UltraLabel labelPrice;

    /**
     * 涨跌额（涨跌幅）
     */
    private UltraLabel labelIncrease;

    /**
     * 涨跌图标
     */
    private UltraLabel labelIncreaseIcon;

    /**
     * 开盘价
     */
    private UltraLabel labelOpen;

    /**
     * 收盘价（昨天）
     */
    private UltraLabel labelClose;

    /**
     * 最高价
     */
    private UltraLabel labelHigh;

    /**
     * 最低价
     */
    private UltraLabel labelLow;

    /**
     * 成交量
     */
    private UltraLabel labelAmount;

    /**
     * 成交额
     */
    private UltraLabel labelNumber;

    /**
     * 从左到右分为三个部分，每个部分包含一个面板
     */
    private JPanel namePanel, leftPanel, rightPanel;

    StockCurrentInfoPanel(String id) throws Exception {
        this.id = id;
        CurrentStockDataService stockData = new CurrentStockData();

        if (StockType.isUS(id)) {
            stock = stockData.getCurrentStockPO_US(id);
        } else {
            stock = stockData.getCurrentStockPO(id);
        }

        init();
        createUIComponents();
        setText();
        addListeners();
    }

    /**
     * 初始化
     */
    private void init() throws Exception {
        setLayout(null);
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setBackground(new Color(175, 152, 139, 56));
        updateData();
    }

    /**
     * 创建组件
     */
    private void createUIComponents() {
        namePanel = new StockNamePanel(stock.getName(), stock.getId());

        {
            leftPanel = new JPanel();
            leftPanel.setLayout(null);
            leftPanel.setBackground(new Color(0, 0, 0, 0));

            labelPrice = new UltraLabel(stock.getPrice() + "", 30);
            labelIncrease = new UltraLabel();
            labelIncreaseIcon = new UltraLabel();

            leftPanel.add(labelIncrease);
            leftPanel.add(labelIncreaseIcon);
            leftPanel.add(labelPrice);
        }

        {
            rightPanel = new JPanel();
            rightPanel.setLayout(null);
            rightPanel.setBackground(new Color(0, 0, 0, 0));

            labelOpen = new UltraLabel();
            labelClose = new UltraLabel();
            labelHigh = new UltraLabel();
            labelLow = new UltraLabel();
            labelAmount = new UltraLabel();
            labelNumber = new UltraLabel();

            rightPanel.add(labelOpen);
            rightPanel.add(labelClose);
            rightPanel.add(labelHigh);
            rightPanel.add(labelLow);
            rightPanel.add(labelAmount);
            rightPanel.add(labelNumber);
        }

        add(namePanel);
        add(leftPanel);
        add(rightPanel);
    }

    /**
     * 添加事件监听器
     */
    private void addListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                assignment();

                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem menuItem = new JMenuItem(ImageLoader.addPortfolio);
                    popupMenu.add(menuItem);

                    popupMenu.show(StockCurrentInfoPanel.this, e.getX(), e.getY());
                }
            }
        });
    }

    /**
     * 界面大小发生变化时，重新定位各组件
     */
    private void assignment() {
        LocationValue.updateValue();

        namePanel.setBounds(0, 0, NAME_PANEL_WIDTH, INFO_PANEL_HEIGHT);

        leftPanel.setBounds(NAME_PANEL_WIDTH, 0,
                (INFO_PANEL_WIDTH - NAME_PANEL_WIDTH) / 2, INFO_PANEL_HEIGHT);
        labelPrice.setBounds(MARGIN, MARGIN * 2,
                labelPrice.getPreferredSize().width, labelPrice.getPreferredSize().height);
        labelIncreaseIcon.setBounds(labelPrice.getX() + labelPrice.getWidth(), MARGIN * 2,
                ImageLoader.increase.getIconWidth(), ImageLoader.increase.getIconHeight());
        labelIncrease.setBounds(labelIncreaseIcon.getX() + labelIncreaseIcon.getWidth(), MARGIN * 2,
                leftPanel.getWidth(), labelPrice.getHeight());

        rightPanel.setBounds(leftPanel.getX() + leftPanel.getWidth(), 0,
                (INFO_PANEL_WIDTH - NAME_PANEL_WIDTH) / 2, INFO_PANEL_HEIGHT);
        labelAmount.setBounds(rightPanel.getWidth() - BUTTON_WIDTH * 2, MARGIN,
                BUTTON_WIDTH * 2, BUTTON_HEIGHT);
        labelNumber.setBounds(labelAmount.getX(), MARGIN + BUTTON_HEIGHT,
                BUTTON_WIDTH * 2, BUTTON_HEIGHT);
        labelOpen.setBounds(labelAmount.getX() - BUTTON_WIDTH - PADDING, MARGIN,
                BUTTON_WIDTH, BUTTON_HEIGHT);
        labelClose.setBounds(labelOpen.getX(), MARGIN + BUTTON_HEIGHT,
                BUTTON_WIDTH, BUTTON_HEIGHT);
        labelHigh.setBounds(labelOpen.getX() - BUTTON_WIDTH - PADDING, MARGIN,
                BUTTON_WIDTH, BUTTON_HEIGHT);
        labelLow.setBounds(labelHigh.getX(), MARGIN + BUTTON_HEIGHT,
                BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    /**
     * 设置各种label的文本及颜色
     * 名称和涨跌额（涨跌幅）无需设置中文文本，需设置提示文本
     */
    private void setText() {
        labelIncrease.setText(stock.getIncrease() + "(" + stock.getIncreasePer() + ")");

        labelPrice.setToolTipText("当前股价");
        labelIncrease.setToolTipText("涨跌额（涨跌幅）");

        labelOpen.setText("今开：" + stock.getOpen() + "");
        labelClose.setText("昨收：" + stock.getClose() + "");
        labelHigh.setText("最高：" + stock.getHigh() + "");
        labelLow.setText("最低：" + stock.getLow() + "");
        labelAmount.setText("成交量：" + stock.getDealNum() + "");
        labelNumber.setText("成交额：" + stock.getDealAmount() + "");

        if (stock.getIncrease() > 0) {
            setTextColor(Color.red);
            labelIncreaseIcon.setIcon(ImageLoader.increase);
        } else if (stock.getIncrease() < 0) {
            setTextColor(Color.green);
            labelIncreaseIcon.setIcon(ImageLoader.decrease);
        } else {
            setTextColor(Color.black);
            labelIncreaseIcon.setIcon(ImageLoader.dull);
        }
    }

    /**
     * 设置文本颜色及字体
     *
     * @param color 颜色
     */
    private void setTextColor(Color color) {
        labelPrice.setForeground(color);
        labelIncrease.setForeground(color);
        labelPrice.setFont(new Font("", Font.BOLD, 30));
        labelIncrease.setFont(new Font("", Font.BOLD, 30));
    }

    /**
     * 固定时间间隔更新数据
     */
    private void updateData() {
        new Thread() {
            @Override
            public void run() {
                try {
                    int lag = SystemConfig.getDataConfig().getUpdateLag();
                    CurrentStockDataService stockData = new CurrentStockData();

                    if (StockType.isUS(id)) {
                        stock = stockData.getCurrentStockPO_US(id);
                    } else {
                        stock = stockData.getCurrentStockPO(id);
                    }

                    Thread.sleep(60000 * lag);
                    setText();

                    repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}

/**
 * 股票名称代码
 * 除包含中文名称、代码外，还包含关注按钮
 */
class StockNamePanel extends JPanel {

    /**
     * 名称
     */
    private JLabel labelName;

    /**
     * id
     */
    private JLabel labelID;

    StockNamePanel(String name, String id) {
        labelName = new JLabel(name);
        labelID = new JLabel(id);

        setLayout(null);
        setBackground(new Color(140, 175, 146, 58));
        setBorder(new BevelBorder(BevelBorder.RAISED));

        createUIComponents();
        addListeners();
    }

    private void createUIComponents() {
        labelName.setFont(new Font("", Font.PLAIN, 16));

        add(labelName);
        add(labelID);
    }

    private void addListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                labelName.setBounds(LocationValue.PADDING * 2, LocationValue.MARGIN,
                        LocationValue.BUTTON_WIDTH, LocationValue.BUTTON_HEIGHT);
                labelID.setBounds(labelName.getX(), LocationValue.MARGIN + LocationValue.PADDING,
                        LocationValue.BUTTON_WIDTH, LocationValue.BUTTON_HEIGHT);

                repaint();
            }
        });
    }
}
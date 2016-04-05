package presentation.panel.info;

import data.currentdata.CurrentStockData;
import dataservice.current.CurrentStockDataService;
import po.current.CurrentStockPO;
import presentation.UltraSwing.UltraLabel;
import presentation.util.ImageLoader;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;

import static presentation.panel.info.LocationValue.*;

/**
 * Created by 宋益明 on 16-3-31.
 * <p>
 * 股票简要信息面板
 */
public class StockBriefInfoPanel extends JPanel {

    /**
     * 股票当前数据值对象
     */
    private CurrentStockPO stock;

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
    private UltraLabel labelVolume;

    /**
     * 成交额
     */
    private UltraLabel labelNumber;

    public StockBriefInfoPanel(String id) throws Exception {
        init(id);
        createUIComponents();
        setText();
        addListeners();
    }

    /**
     * 初始化
     * @param id 股票ID
     */
    private void init(String id) throws Exception {
        CurrentStockDataService stockData = new CurrentStockData();
        stock = stockData.getCurrentStockPO(id);

        setLayout(null);
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setBackground(new Color(175, 152, 139, 56));
    }

    /**
     * 创建组件
     */
    private void createUIComponents() {
        StockNamePanel namePanel = new StockNamePanel(stock.getName(), stock.getId());
        namePanel.setBounds(0, 0, NAME_PANEL_WIDTH, INFO_PANEL_HEIGHT);

        labelPrice = new UltraLabel(30);
        labelIncrease = new UltraLabel(30);
        labelIncreaseIcon = new UltraLabel();
        labelOpen = new UltraLabel();
        labelClose = new UltraLabel();
        labelHigh = new UltraLabel();
        labelLow = new UltraLabel();
        labelVolume = new UltraLabel();
        labelNumber = new UltraLabel();

        add(namePanel);
        add(labelPrice);
        add(labelIncrease);
        add(labelIncreaseIcon);
        add(labelOpen);
        add(labelClose);
        add(labelHigh);
        add(labelLow);
        add(labelVolume);
        add(labelNumber);
    }

    /**
     * 添加事件监听器
     */
    private void addListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                assignment();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem menuItem = new JMenuItem(ImageLoader.addPortfolio);
                    popupMenu.add(menuItem);

                    popupMenu.show(StockBriefInfoPanel.this, e.getX(), e.getY());
                }
            }
        });
    }

    /**
     * 界面大小发生变化时，重新定位各组件
     */
    private void assignment() {
        labelPrice.setBounds(NAME_PANEL_WIDTH + PADDING, MARGIN * 2,
                labelPrice.getPreferredSize().width, labelPrice.getPreferredSize().height);
        labelIncreaseIcon.setBounds(labelPrice.getX() + BUTTON_HEIGHT + PADDING,
                PADDING, BUTTON_HEIGHT, BUTTON_HEIGHT);
    }

    /**
     * 设置各种label的文本及颜色
     * 名称和涨跌额（涨跌幅）无需设置中文文本，需设置提示文本
     */
    private void setText() {
        labelPrice.setText(stock.getPrice() + "");
        labelIncrease.setText(stock.getIncrease()
                + "(" + stock.getIncreasePer() + ")");

        labelPrice.setToolTipText("当前股价");
        labelIncrease.setToolTipText("涨跌额（涨跌幅）");

        labelOpen.setText("今开：" + stock.getOpen() + "");
        labelClose.setText("昨收：" + stock.getClose() + "");
        labelHigh.setText("最高：" + stock.getHigh() + "");
        labelLow.setText("最低：" + stock.getLow() + "");
        labelVolume.setText("成交量：" + stock.getDealNum() + "");
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
}

/**
 * 股票名称代码
 * 除包含中文名称、代码外，还包含关注按钮
 */
class StockNamePanel extends IndexNamePanel {

    /**
     * 关注按钮
     */
    private JButton btnFollow;

    StockNamePanel(String name, String id) {
        super(name, id);

        createUIComponents();
    }

    private void createUIComponents() {
        btnFollow = new JButton("关注");

        add(btnFollow);
    }
}

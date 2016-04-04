package presentation.panel.info;

import presentation.util.ImageLoader;
import vo.StockVO;

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
     * 股票值对象
     */
    private StockVO stock;

    /**
     * 当前价格
     */
    private JLabel labelPrice;

    /**
     * 涨跌额（涨跌幅）
     */
    private JLabel labelIncrease;

    /**
     * 开盘价
     */
    private JLabel labelOpen;

    /**
     * 收盘价（昨天）
     */
    private JLabel labelClose;

    /**
     * 最高价
     */
    private JLabel labelHigh;

    /**
     * 最低价
     */
    private JLabel labelLow;

    /**
     * 成交量
     */
    private JLabel labelVolume;

    /**
     * 成交额
     */
    private JLabel labelNumber;

    public StockBriefInfoPanel(StockVO stock) {
        this.stock = stock;

        init();
        createUIComponents();
        setText();
        addListeners();
    }

    /**
     * 初始化
     */
    private void init() {
        setLayout(null);
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setBackground(new Color(175, 152, 139, 56));
    }

    /**
     * 创建组件
     */
    private void createUIComponents() {
        StockNamePanel namePanel = new StockNamePanel(stock.getName(), stock.getId());
        namePanel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

        labelPrice = new JLabel();
        labelIncrease = new JLabel();
        labelOpen = new JLabel();
        labelClose = new JLabel();
        labelHigh = new JLabel();
        labelLow = new JLabel();
        labelVolume = new JLabel();
        labelNumber = new JLabel();

        add(namePanel);
        add(labelPrice);
        add(labelIncrease);
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
    private void setLocation() {
        labelPrice.setBounds(PANEL_WIDTH + PADDING, PADDING,
                BUTTON_WIDTH, BUTTON_HEIGHT);

    }

    /**
     * 设置各种label的文本及颜色
     * 名称和涨跌额（涨跌幅）无需设置中文文本，需设置提示文本
     */
    private void setText() {
        labelPrice.setText(stock.getHigh()[0] + "");
        labelIncrease.setText(stock.getIncrease_decreaseNum()[0]
                + "(" + stock.getIncrease_decreaseRate()[0] + ")");

        labelPrice.setToolTipText("当前股价");
        labelIncrease.setToolTipText("涨跌额（涨跌幅）");

        labelOpen.setText("今开：" + stock.getOpen()[0] + "");
        labelClose.setText("昨收：" + stock.getClose() + "");
        labelHigh.setText("最高：" + stock.getHigh()[0] + "");
        labelLow.setText("最低：" + stock.getLow()[0] + "");
        labelVolume.setText("成交量：" + stock.getVolume()[0] + "");
        labelNumber.setText("成交额：" + stock.getTurnover()[0] + "");

        if (stock.getIncrease_decreaseNum()[0] > 0) {
            setTextColor(Color.red);
        } else if (stock.getIncrease_decreaseNum()[0] < 0) {
            setTextColor(Color.green);
        } else {
            setTextColor(Color.black);
        }
    }

    /**
     * 设置文本颜色
     *
     * @param color 颜色
     */
    private void setTextColor(Color color) {
        labelPrice.setForeground(color);
        labelIncrease.setForeground(color);
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

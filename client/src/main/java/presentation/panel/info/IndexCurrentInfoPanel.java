package presentation.panel.info;

import bl.ShowIndexData;
import vo.IndexVO;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

/**
 * Created by 宋益明 on 16-3-31.
 *
 * 大盘指数当前（最新）信息面板
 * 包含
 *      中文名称                        今开      最高     成交量
 *        ID        涨跌额（涨跌幅）      昨收     最低     成交额
 */
public class IndexCurrentInfoPanel extends JPanel {

    private final int LABEL_WIDTH = 100;

    /**
     * 大盘指数值对象
     */
    private IndexVO index;

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

    public IndexCurrentInfoPanel(IndexVO index) {
//        this.index = index;
        try {
            this.index = new ShowIndexData().getLatestIndexData();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        //TODO id
        IndexNamePanel namePanel = new IndexNamePanel("沪深300", "1B0300");
        namePanel.setBounds(0, 0, LABEL_WIDTH, 100);

        labelPrice = new JLabel();
        labelIncrease = new JLabel();
        labelOpen = new JLabel();
        labelClose = new JLabel();
        labelHigh = new JLabel();
        labelLow = new JLabel();
        labelVolume = new JLabel();
        labelNumber = new JLabel();

        add(namePanel);
    }

    /**
     * 添加事件监听器
     */
    private void addListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                assignment();

                revalidate();
                repaint();
            }
        });
    }

    /**
     * 为组件赋值
     */
    private void assignment() {
        labelIncrease = new JLabel();
        labelOpen = new JLabel();
        labelClose = new JLabel();
        labelHigh = new JLabel();
        labelLow = new JLabel();
        labelVolume = new JLabel();
        labelNumber = new JLabel();

        labelPrice.setBounds(100, 40, 50, 30);
//        labelIncrease.setBounds();
    }

    /**
     * 设置各种label的文本及颜色
     * 名称和涨跌额（涨跌幅）无需设置中文文本，需设置提示文本
     */
    private void setText() {
        //TODO high
        labelPrice.setText(index.getHigh()[0] + "");
        labelIncrease.setText(index.getHigh()[0]
                + "(" + index.getHigh()[0] + ")");

        labelPrice.setToolTipText("当前股价");
        labelIncrease.setToolTipText("涨跌额（涨跌幅）");

        labelOpen.setText("今开：" + index.getOpen()[0] + "");
        labelClose.setText("昨收：" + index.getClose()[0] + "");
        labelHigh.setText("最高：" + index.getHigh()[0] + "");
        labelLow.setText("最低：" + index.getLow()[0] + "");
        labelVolume.setText("成交量：" + index.getVolume()[0] + "");
        //TODO VOLUME
        labelNumber.setText("成交额：" + index.getVolume()[0] + "");
        labelPrice.setToolTipText("当前股价");
        labelIncrease.setToolTipText("涨跌额（涨跌幅）");

        //TODO 涨跌幅
//        if (index.ge) {
//            setTextColor(Color.red);
//            setTextColor(Color.green);
//        } else {
//            setTextColor(Color.black);
//        }
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
 * 大盘指数名称面板
 * 包含中文名称与ID
 */
class IndexNamePanel extends JPanel {

    /**
     * 名称
     */
    private JLabel labelName;

    /**
     * id
     */
    private JLabel labelID;

    IndexNamePanel(String name, String id) {
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

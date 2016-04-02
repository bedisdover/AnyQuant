package presentation.panel.info;

import vo.IndexVO;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by 宋益明 on 16-3-31.
 *
 * 大盘指数简要信息面板
 * 包含
 *      中文名称                        今开      最高     成交量
 *        ID        涨跌额（涨跌幅）      昨收     最低     成交额
 */
public class IndexBriefInfoPanel extends JPanel {

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

    public IndexBriefInfoPanel(IndexVO index) {
        this.index = index;

        init();
        createUIComponents();
        setText();
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
     * 设置各种label的文本及颜色
     * 名称和涨跌额（涨跌幅）无需设置中文文本，需设置提示文本
     */
    private void setText() {

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
 * 大盘指数名称面板
 * 包含中文名称与ID
 */
class IndexNamePanel extends JPanel {
    IndexNamePanel(String name, String id) {
        setBackground(new Color(140, 175, 146, 58));
        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        setBorder(new BevelBorder(BevelBorder.RAISED));

        createUIComponents(name, id);
    }

    private void createUIComponents(String name, String id) {
        JLabel labelName = new JLabel(name);
        JLabel labelID = new JLabel(id);

        labelName.setBounds(25, 10, 60, 20);
        labelID.setBounds(25, 30, 60, 20);

        add(labelName);
        add(labelID);
    }
}

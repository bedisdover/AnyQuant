package presentation.panel.info;

import bl.ShowIndexData;
import presentation.UltraSwing.UltraLabel;
import presentation.UltraSwing.UltraPanel;
import presentation.util.ImageLoader;
import vo.IndexVO;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static presentation.panel.info.LocationValue.*;

/**
 * Created by 宋益明 on 16-3-31.
 *
 * 大盘指数当前（最新）信息面板
 * 包含
 *      中文名称                        今开      最高     成交量
 *        ID        涨跌额（涨跌幅）      昨收     最低     成交额
 */
public class IndexCurrentInfoPanel extends UltraPanel {

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
     * 涨跌图标
     */
    private UltraLabel labelIncreaseIcon;

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

    /**
     * 从左到右分为三个部分，每个部分包含一个面板
     */
    private JPanel namePanel, leftPanel, rightPanel;

    public IndexCurrentInfoPanel() {
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        initData();
        setLayout(null);
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setBackground(new Color(175, 152, 139, 56));
    }

    /**
     * 加载数据（indexVO）
     */
    private void initData() {
        new SwingWorker<IndexVO, IndexVO>() {
            @Override
            protected void process(List<IndexVO> chunks) {
                super.process(chunks);
            }

            @Override
            protected void done() {
                try {
                    index = get();
                    createUIComponents();
                    setText();
                    addListeners();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected IndexVO doInBackground() throws Exception {
                return new ShowIndexData().getLatestIndexData();
            }
        }.execute();
    }

    /**
     * 创建组件
     */
    private void createUIComponents() {
        namePanel = new IndexNamePanel("沪深300", index.getName());
        namePanel.setBounds(0, 0, LABEL_WIDTH, 100);

        {
            leftPanel = new JPanel();
            leftPanel.setLayout(null);
            leftPanel.setBackground(new Color(0, 0, 0, 0));

            labelPrice = new UltraLabel(index.getAdj_price()[0] + "", 30);
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
            labelVolume = new UltraLabel();
            labelNumber = new UltraLabel();

            rightPanel.add(labelOpen);
            rightPanel.add(labelClose);
            rightPanel.add(labelHigh);
            rightPanel.add(labelLow);
            rightPanel.add(labelVolume);
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

                revalidate();
                repaint();
            }
        });
    }

    /**
     * 为组件赋值
     */
    private void assignment() {
        LocationValue.updateValue();

        namePanel.setBounds(0, 0, NAME_PANEL_WIDTH, INFO_PANEL_HEIGHT);

        leftPanel.setBounds(NAME_PANEL_WIDTH, 0,
                (INFO_PANEL_WIDTH - NAME_PANEL_WIDTH) / 2, INFO_PANEL_HEIGHT);
        labelPrice.setBounds(0, MARGIN * 2,
                labelPrice.getPreferredSize().width, labelPrice.getPreferredSize().height);
        labelIncreaseIcon.setBounds(labelPrice.getX() + labelPrice.getWidth(), MARGIN * 2,
                ImageLoader.increase.getIconWidth(), ImageLoader.increase.getIconHeight());
        labelIncrease.setBounds(labelIncreaseIcon.getX() + labelIncreaseIcon.getWidth(), MARGIN * 2,
                leftPanel.getWidth(), labelPrice.getHeight());

        rightPanel.setBounds(leftPanel.getX() + leftPanel.getWidth(), 0,
                (INFO_PANEL_WIDTH - NAME_PANEL_WIDTH) / 2, INFO_PANEL_HEIGHT);
//        labelVolume.setBounds(rightPanel.getWidth() - labelVolume.getPreferredSize().width - PADDING, MARGIN,
//                labelVolume.getPreferredSize().width, labelVolume.getPreferredSize().height);
//        labelNumber.setBounds(labelVolume.getX(), INFO_PANEL_HEIGHT - MARGIN - labelNumber.getPreferredSize().height,
//                labelNumber.getPreferredSize().width, labelNumber.getPreferredSize().height);
//        labelOpen.setBounds(labelVolume.getX() - labelOpen.getPreferredSize().width - PADDING, MARGIN,
//                labelOpen.getPreferredSize().width, labelOpen.getPreferredSize().height);
//        labelClose.setBounds(labelOpen.getX(), labelNumber.getY(),
//                labelClose.getPreferredSize().width, labelClose.getPreferredSize().height);
        labelVolume.setBounds(rightPanel.getWidth() - BUTTON_WIDTH * 2, MARGIN,
                BUTTON_WIDTH * 2, BUTTON_HEIGHT);
        labelNumber.setBounds(labelVolume.getX(), MARGIN + BUTTON_HEIGHT,
                BUTTON_WIDTH * 2, BUTTON_HEIGHT);
        labelOpen.setBounds(labelVolume.getX() - BUTTON_WIDTH - PADDING, MARGIN,
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
        String increaseNum =
                String.format("%.2f", index.getIncrease_decreaseNum()[index.getDate().length - 1]);
        double increasePer = ((double) Math.round(index.getIncrease_decreaseRate()[index.getDate().length - 1]
                * 100 * 100)) / 100;

        labelIncrease.setText(increaseNum
                + "(" + increasePer + ")");

        labelPrice.setToolTipText("当前股价");
        labelIncrease.setToolTipText("涨跌额（涨跌幅）");

        labelOpen.setText("今开：" + index.getOpen()[0] + "");
        labelClose.setText("昨收：" + index.getClose()[0] + "");
        labelHigh.setText("最高：" + index.getHigh()[0] + "");
        labelLow.setText("最低：" + index.getLow()[0] + "");
        labelVolume.setText("成交量：" + index.getDealNum() + "");
        labelNumber.setText("成交额：" + index.getDealAmount() + "");
        labelPrice.setToolTipText("当前股价");
        labelIncrease.setToolTipText("涨跌额（涨跌幅）");

        if (increasePer > 0) {
            setTextColor(Color.red);
            setTextColor(Color.green);
        } else if (increasePer < 0){
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
                labelName.setBounds(LocationValue.MARGIN * 2, LocationValue.MARGIN,
                        LocationValue.BUTTON_WIDTH, LocationValue.BUTTON_HEIGHT);
                labelID.setBounds(labelName.getX() + LocationValue.MARGIN,
                        LocationValue.MARGIN + LocationValue.PADDING,
                        LocationValue.BUTTON_WIDTH, LocationValue.BUTTON_HEIGHT);

                repaint();
            }
        });
    }
}

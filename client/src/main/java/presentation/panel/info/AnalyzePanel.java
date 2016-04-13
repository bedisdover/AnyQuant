package presentation.panel.info;

import bl.CalculateIndex;
import presentation.frame.MainFrame;
import presentation.panel.operation.OperationPanel;
import vo.IndexVO;
import vo.StockVO;
import vo.TheIndexVO;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by 宋益明 on 16-4-13.
 *
 * 分析面板
 * 包含各种指标及分析
 */
public class AnalyzePanel extends OperationPanel {

    /**
     * 指标值对象
     */
    private TheIndexVO index;

    /**
     * 父面板(前一个面板)
     */
    private JPanel parent;

    /**
     * 返回按钮
     * 返回父面板
     */
    private JButton back;

    /**
     * 名称、id
     */
    private String name, id;

    /**
     * 名称面板、指标面板
     */
    private JPanel namePanel, indexPanel;

    AnalyzePanel(JPanel parent, StockVO stock) {
        this.index = new CalculateIndex().getTheIndex(stock);
        this.parent = parent;
        this.name = stock.getName();
        this.id = stock.getId();

        init();
        createUIComponents();
        addListeners();
    }

    AnalyzePanel(JPanel parent, IndexVO indexVO) {
        this.index = new CalculateIndex().getTheIndex(indexVO);
        this.parent = parent;
        this.name = "沪深300";
        this.id = indexVO.getName();

        init();
        createUIComponents();
        addListeners();
    }

    @Override
    protected void init() {
        setLayout(null);
    }

    @Override
    protected void createUIComponents() {
        back = new JButton("返回");

        namePanel = new NamePanel(name, id);
        indexPanel = new IndexPanel(index);

        add(back);
        add(namePanel);
        add(indexPanel);
    }

    private void assignment() {
        assignmentValue();
        back.setBounds(MARGIN, MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT);
        namePanel.setBounds((PANEL_WIDTH - BUTTON_WIDTH * 6) / 2,
                back.getY() + back.getHeight(),
                BUTTON_WIDTH * 6, BUTTON_HEIGHT + MARGIN / 2);
        indexPanel.setBounds(MARGIN, (PANEL_HEIGHT - BUTTON_HEIGHT * 6) / 2,
                BUTTON_WIDTH * 2 + MARGIN, BUTTON_HEIGHT * 6);
    }


    protected void addListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                assignment();

                revalidate();
                repaint();
            }
        });

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onBack();
            }
        });

        back.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                onBack();
            }
        });
    }

    /**
     * 返回操作
     */
    private void onBack() {
        MainFrame.getMainFrame().addOperationPanel(parent);
    }

    /**
     * 名称面板，包含名称和ID
     */
    private class NamePanel extends JPanel {

        /**
         * 名称
         */
        private JLabel labelName;

        /**
         * ID
         */
        private JLabel labelID;

        NamePanel(String name, String id) {
            this.labelName = new JLabel("名称： " + name);
            this.labelID = new JLabel("代码： " + id);

            init();
        }

        private void init() {
            setBackground(new Color(100, 87, 100));
            setLayout(new FlowLayout(FlowLayout.CENTER));

            labelName.setFont(new Font("", Font.PLAIN, 20));
            labelID.setFont(new Font("", Font.PLAIN, 20));

            add(labelName);
            add(labelID);
        }
    }

    /**
     * 指标面板
     */
    private class IndexPanel extends JPanel {
        /**
         * 指标值对象
         */
        private TheIndexVO index;

        /**
         * 乖离率、相对强弱指标、威廉超买超卖指标、人气指标、意愿指标
         */
        private JLabel labelBias, labelRSI, labelWM, labelAR, labelBR;

        /**
         * 上述指标对应的值
         */
        private JLabel bias, RSI, WM, AR, BR;

        IndexPanel(TheIndexVO index) {
            this.index = index;

            init();
            createUIComponents();
            setTextFont();
            setTips();
        }

        private void init() {
            setLayout(null);
            setBackground(Color.lightGray);
            setBorder(new BevelBorder(BevelBorder.LOWERED));
        }

        private void createUIComponents() {
            labelBias = new JLabel("bias：");
            labelRSI = new JLabel("RSI：");
            labelWM = new JLabel("WM：");
            labelAR = new JLabel("AR：");
            labelBR = new JLabel("BR：");

            bias = new JLabel(index.getBias() + "");
            RSI = new JLabel(index.getRSI() + "");
            WM = new JLabel(index.getWM() + "");
            AR = new JLabel(index.getAR() + "");
            BR = new JLabel(index.getBR() + "");

            labelBias.setBounds(MARGIN, MARGIN / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
            labelRSI.setBounds(MARGIN, BUTTON_HEIGHT + MARGIN / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
            labelWM.setBounds(MARGIN, BUTTON_HEIGHT * 2 + MARGIN / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
            labelAR.setBounds(MARGIN, BUTTON_HEIGHT * 3 + MARGIN / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
            labelBR.setBounds(MARGIN, BUTTON_HEIGHT * 4 + MARGIN / 2, BUTTON_WIDTH, BUTTON_HEIGHT);

            bias.setBounds(BUTTON_WIDTH + MARGIN, MARGIN / 2,
                    BUTTON_WIDTH, BUTTON_HEIGHT);
            RSI.setBounds(BUTTON_WIDTH + MARGIN, BUTTON_HEIGHT + MARGIN / 2,
                    BUTTON_WIDTH, BUTTON_HEIGHT);
            WM.setBounds(BUTTON_WIDTH + MARGIN, BUTTON_HEIGHT * 2 + MARGIN / 2,
                    BUTTON_WIDTH, BUTTON_HEIGHT);
            AR.setBounds(BUTTON_WIDTH + MARGIN, BUTTON_HEIGHT * 3 + MARGIN / 2,
                    BUTTON_WIDTH, BUTTON_HEIGHT);
            BR.setBounds(BUTTON_WIDTH + MARGIN, BUTTON_HEIGHT * 4 + MARGIN / 2,
                    BUTTON_WIDTH, BUTTON_HEIGHT);

            add(labelBias);
            add(labelRSI);
            add(labelWM);
            add(labelAR);
            add(labelBR);
            add(bias);
            add(RSI);
            add(WM);
            add(AR);
            add(BR);
        }

        private void setTextFont() {
            labelBias.setFont(new Font("", Font.PLAIN, 16));
            labelRSI.setFont(new Font("", Font.PLAIN, 16));
            labelWM.setFont(new Font("", Font.PLAIN, 16));
            labelAR.setFont(new Font("", Font.PLAIN, 16));
            labelBR.setFont(new Font("", Font.PLAIN, 16));
            bias.setFont(new Font("", Font.PLAIN, 16));
            RSI.setFont(new Font("", Font.PLAIN, 16));
            WM.setFont(new Font("", Font.PLAIN, 16));
            AR.setFont(new Font("", Font.PLAIN, 16));
            BR.setFont(new Font("", Font.PLAIN, 16));
        }

        private void setTips() {
            labelBias.setToolTipText("乖离率");
            labelRSI.setToolTipText("相对强弱指标");
            labelWM.setToolTipText("威廉超买超卖指标");
            labelAR.setToolTipText("人气指标");
            labelBR.setToolTipText("意愿指标");
            bias.setToolTipText("乖离率");
            RSI.setToolTipText("相对强弱指标");
            WM.setToolTipText("威廉超买超卖指标");
            AR.setToolTipText("人气指标");
            BR.setToolTipText("意愿指标");
        }
    }
}

package presentation.panel.index;

import bl.ShowIndexData;
import presentation.frame.MainFrame;
import presentation.panel.IndexKLines;
import presentation.panel.info.IndexCurrentInfoPanel;
import presentation.panel.operation.OperationPanel;
import vo.IndexVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by 宋益明 on 16-4-14.
 * <p>
 * 大盘指数K线图面板
 * 包含简要信息（中文名称                        今开     最高     成交量)
 * (  ID        涨跌额（涨跌幅）      昨收     最低     成交额)
 * 及K线图
 */
public class IndexKLinePanel extends OperationPanel {

    /**
     * 当前信息面板
     */
    private JPanel currentInfoPanel;

    /**
     * 图表面板
     */
    private JTabbedPane chartPanel;

    /**
     * 父面板
     */
    private JPanel parent;

    /**
     * 返回按钮
     */
    private JButton back;

    public IndexKLinePanel(JPanel parent) {
        this.parent = parent;

        init();
        createUIComponents();
        addListeners();
    }

    @Override
    protected void init() {
        this.setLayout(null);

        update();
    }

    @Override
    protected void createUIComponents() {
        back = new JButton("返回");

        currentInfoPanel = new IndexCurrentInfoPanel();

        try {
            chartPanel = new IndexKLines().getjTabbedPane();
        } catch (IOException e) {
            e.printStackTrace();
        }

        add(currentInfoPanel);
        add(chartPanel);
        add(back);
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

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame.getMainFrame().addOperationPanel(parent);
            }
        });
    }

    /**
     * 界面大小发生变化时，组件重新布局
     */
    private void assignment() {
        super.assignmentValue();

        back.setBounds(MARGIN, MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT);
        currentInfoPanel.setBounds(MARGIN, MARGIN + BUTTON_HEIGHT + PADDING / 2,
                PANEL_WIDTH - MARGIN * 2, BUTTON_HEIGHT + PADDING);
        chartPanel.setBounds(MARGIN, currentInfoPanel.getY() + currentInfoPanel.getHeight() + PADDING / 2,
                PANEL_WIDTH - MARGIN * 2, PANEL_HEIGHT - getX() - PADDING);

        revalidate();
        repaint();
    }

    /**
     * 定时刷新界面
     */
    private void update() {
        new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        assignment();

                        Thread.sleep(100);
                    }
                } catch (Exception e) {
                    //有可能会出现空指针异常，对程序运行无影响，不予处理
                }
            }
        }.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}

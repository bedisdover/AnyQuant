package presentation.panel.info;

import presentation.frame.MainFrame;
import presentation.panel.operation.OperationPanel;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by 宋益明 on 16-3-8.
 * <p>
 * 信息面板
 * 用于展示大盘指数和股票的详细信息
 */
public abstract class InfoPanel extends OperationPanel {
    /**
     * 父面板(前一个面板)
     */
    protected JPanel parent;

    /**
     * 日期选择框
     * 通过日期选择框改变日期查看其它日期的数据
     */
//    private DateChooser dateChooser;

    /**
     * 返回按钮
     * 返回父面板
     */
    private JButton back;

    InfoPanel(JPanel parent) {
        this.parent = parent;

        init();
    }

    protected void init() {
        setLayout(null);
    }

    protected void createUIComponents() {
        back = new JButton("返回");

        add(back);
        assignment();
//        add(dateChooser);
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


    private void assignment() {
//        if (dateChooser != null) {
//            dateChooser.setEnabled(false);
//        }
//
//        dateChooser = new DateChooser(this,
//                INFO_PANEL_WIDTH - MARGIN - BUTTON_WIDTH - PADDING,
//                MARGIN, BUTTON_WIDTH + PADDING, BUTTON_HEIGHT);

        LocationValue.updateValue();
        back.setBounds(MARGIN, MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT);

//        revalidate();
//        repaint();
    }

    /**
     * 返回操作
     */
    private void onBack() {
        MainFrame.getMainFrame().addOperationPanel(parent);
    }
}
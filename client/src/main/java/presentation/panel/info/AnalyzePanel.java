package presentation.panel.info;

import bl.CalculateIndex;
import presentation.frame.MainFrame;
import presentation.panel.operation.OperationPanel;
import vo.IndexVO;
import vo.StockVO;
import vo.TheIndexVO;

import javax.swing.*;
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
    protected JPanel parent;

    /**
     * 返回按钮
     * 返回父面板
     */
    private JButton back;

    public AnalyzePanel(JPanel parent, StockVO stock) {
        this.index = new CalculateIndex().getTheIndex(stock);
        this.parent = parent;
    }

    public AnalyzePanel(JPanel parent, IndexVO index) {
        this.index = new CalculateIndex().getTheIndex(index);
        this.parent = parent;
    }

    @Override
    protected void init() {
        setLayout(null);
    }

    @Override
    protected void createUIComponents() {
        back = new JButton("返回");

        add(back);
        assignment();
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
//        LocationValue.updateValue();
        back.setBounds(MARGIN, MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    /**
     * 返回操作
     */
    private void onBack() {
        MainFrame.getMainFrame().addOperationPanel(parent);
    }
}

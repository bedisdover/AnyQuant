package presentation.panel.info;

import presentation.frame.MainFrame;
import presentation.panel.operation.OperationPanel;
import presentation.util.DateChooser;
import vo.StockVO;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 宋益明 on 16-3-8.
 * <p>
 * 信息面板
 * 用于展示大盘指数和股票的详细信息
 * 以无表头表格展示详细信息
 * 以图标展示变化情况
 */
public abstract class InfoPanel extends OperationPanel {
    /**
     * 父面板(前一个面板)
     */
    private JPanel parent;

    /**
     * 日期选择框
     * 通过日期选择框改变日期查看其它日期的数据
     */
    private DateChooser dateChooser;

    /**
     * 返回按钮
     * 返回父面板
     */
    private JButton back;

    /**
     * 此面板持有的股票值对象
     */
    private StockVO stock;

    public InfoPanel(JPanel parent, StockVO stock) {
        this.parent = parent;
        this.stock = stock;

        init();
        createUIComponents();
        addListeners();
    }

    protected void init() {
        setLayout(null);
    }

    protected void createUIComponents() {
        back = new JButton("返回");
        dateChooser = new DateChooser(this, MARGIN, MARGIN, BUTTON_WIDTH + PADDING, BUTTON_HEIGHT);

        back.setBounds(MARGIN, MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT);

        add(dateChooser);
        add(back);
    }


    private void addListeners() {
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame.getMainFrame().addOperationPanel(parent);
            }
        });

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                MainFrame.getMainFrame().addOperationPanel(parent);
            }
        });
    }

    protected void displayInfo(StockVO stock) {

    }
}

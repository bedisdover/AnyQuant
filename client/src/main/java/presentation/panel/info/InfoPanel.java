package presentation.panel.info;

import data.GetStockData;
import po.StockPO;
import presentation.frame.MainFrame;
import presentation.panel.operation.OperationPanel;
import vo.StockVO;

import javax.swing.*;
import java.awt.event.*;

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
//    private DateChooser dateChooser;

    /**
     * 返回按钮
     * 返回父面板
     */
    private JButton back;

    /**
     * 此面板持有的股票值对象
     */
    private StockPO stock;

    protected InfoPanel(JPanel parent, String stockID) {
        this(parent, new GetStockData().getStockData_name(stockID));
    }

    protected InfoPanel(JPanel parent, StockPO stock) {
        this.parent = parent;
        this.stock = stock;

        init();
        createUIComponents();
        addListeners();

        displayInfo(stock);
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
            }
        });

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


    private void assignment() {
//        if (dateChooser != null) {
//            dateChooser.setEnabled(false);
//        }
//
//        dateChooser = new DateChooser(this,
//                PANEL_WIDTH - MARGIN - BUTTON_WIDTH - PADDING,
//                MARGIN, BUTTON_WIDTH + PADDING, BUTTON_HEIGHT);

        back.setBounds(MARGIN, MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT);

        repaint();
    }

    protected void displayInfo(StockPO stock) {
        createTable(new StockVO(stock));
    }
}

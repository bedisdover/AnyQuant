package presentation.panel.info;

import po.StockPO;
import presentation.frame.MainFrame;
import presentation.panel.operation.OperationPanel;
import presentation.util.DateChooser;

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
    private StockPO stock;

    public InfoPanel(JPanel parent, StockPO stock) {
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

    protected void displayInfo(StockPO stock) {
        Object[][] data = new Object[][] {
                {"名称", stock.getName(), "成交量", stock.getVolume()[29]},
                {"平均市净率", stock.getPb()[29], "最高", stock.getHigh()[29]},
                {"最低", stock.getLow()[29], "市盈率", stock.getPe_ttm()[29]},
                {"?????", stock.getAdj_price()[29], "收盘价", stock.getClose()[29]},
                {"开盘价", stock.getOpen()[29], "周转率", stock.getTurnover()[29]}
        };
        String[] columnNames = new String[] {
                "1", "2", "3", "4"
        };

        JTable table = new JTable(data, columnNames);
        table.setBorder(BorderFactory.createEtchedBorder());
        table.setBounds(MARGIN, MARGIN + PADDING * 2, WIDTH - 2 * MARGIN, data.length * 30);
        add(table);

    }
}

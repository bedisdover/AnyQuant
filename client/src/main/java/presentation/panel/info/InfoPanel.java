package presentation.panel.info;

import data.GetStockData;
import po.StockPO;
import presentation.frame.MainFrame;
import presentation.panel.operation.OperationPanel;
import presentation.panel.operation.TableCopy;
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
        dateChooser = new DateChooser(this, WIDTH - MARGIN - BUTTON_WIDTH - PADDING, MARGIN, BUTTON_WIDTH + PADDING, BUTTON_HEIGHT);

        back.setBounds(MARGIN, MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT);

        add(dateChooser);
        add(back);
    }


    protected void addListeners() {
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
        Object[][] data = new Object[stock.getDate().length][];
        String[] columnNames = new String[]{
                "日期", "成交量", "市净率", "最高", "最低", "市盈率", "最新", "收盘价", "开盘价", "周转率"

        };

        for (int i = 0; i < data.length; i++) {
            data[i] = new Object[]{
                    stock.getDate()[i],
                    stock.getVolume()[i],
                    stock.getPb()[i],
                    stock.getHigh()[i],
                    stock.getLow()[i],
                    stock.getPe_ttm()[i],
                    stock.getAdj_price()[i],
                    stock.getClose()[i],
                    stock.getOpen()[i],
                    stock.getTurnover()[i]
            };
        }

        TableCopy table = new TableCopy(data, columnNames);
        JScrollPane scrollPane = table.drawTable();
        int tableHeight = Math.min(data.length * 30 + 60, HEIGHT - MARGIN * 2 - PADDING * 2);
        scrollPane.setBounds(MARGIN, MARGIN + BUTTON_HEIGHT + PADDING, WIDTH - 2 * MARGIN, tableHeight);
        add(scrollPane);
    }
}

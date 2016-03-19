package presentation.panel.operation;

import presentation.frame.MainFrame;
import presentation.util.Table;
import vo.IndexVO;
import vo.StockVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

/**
 * Created by 宋益明 on 16-3-3.
 * <p>
 * 操作面板,区别于菜单面板
 * 其他面板均为操作面板的超类
 */
public abstract class OperationPanel extends JPanel {

    /**
     * 面板宽度,高度(讲道理的话,宽度和高度是不确定的,但为了程序方便,
     * 而且真实高度和宽度都是根据主界面的宽度和高度确定的,所以这里仅
     * 简单地计算出宽度和高度)
     */
    protected int PANEL_WIDTH, PANEL_HEIGHT;

    /**
     * 外边距
     */
    protected int MARGIN;

    /**
     * 内边距
     */
    protected int PADDING;

    /**
     * 按钮宽度
     */
    protected int BUTTON_WIDTH;

    /**
     * 按钮高度
     */
    protected int BUTTON_HEIGHT;

    protected int TEXT_FIELD_WIDTH;

    protected Table table;

    protected JScrollPane scrollPane;

    protected Object[][] data;

    public OperationPanel() {
        setOpaque(false);

        {   //以下各值均为常量
            //当界面大小改变时,无需再次赋值
            MARGIN = MainFrame.DEFAULT_WIDTH / 25;
            PADDING = MainFrame.DEFAULT_WIDTH / 20;
            BUTTON_WIDTH = PADDING + MARGIN;
            BUTTON_HEIGHT = MARGIN;
            TEXT_FIELD_WIDTH = BUTTON_WIDTH + PADDING * 2;
        }

        assignment();
        addListeners();
    }

    /**
     * 界面大小发生变化时，对各种组件大小赋值
     */
    private void assignment() {
        PANEL_WIDTH = MainFrame.getMainFrame().getWidth() - MainFrame.MENU_WIDTH;
        PANEL_HEIGHT = MainFrame.getMainFrame().getHeight();

        if (data != null && table != null) {
            int tableHeight = Math.min(
                    (data.length + 1) * table.getRowHeight()
                            + scrollPane.getHorizontalScrollBar().getHeight(),
                    PANEL_HEIGHT - MARGIN * 3 - PADDING - BUTTON_HEIGHT
            );
            int tableWidth =
                    table.getColumnModel().getTotalColumnWidth()
                            + scrollPane.getVerticalScrollBar().getWidth();

            //若界面高度超过表格高度(包含间距)
            //此时无需垂直滚动条,tableWidth需减去滚动条的宽度
            //至于为什么要加6,我也不知道.....
            if (PANEL_HEIGHT > MARGIN * 3 + PADDING + BUTTON_HEIGHT + tableHeight) {
                tableWidth -= scrollPane.getVerticalScrollBar().getWidth();
                tableWidth += 6;
            }

            //若界面宽度超过表格宽度(包含外间距)
            //表格居中显示,此时无需平行滚动条,tableHeight需减去滚动条的高度
            if (PANEL_WIDTH > MARGIN * 2 + tableWidth) {
                scrollPane.setBounds(
                        (PANEL_WIDTH - table.getWidth()) / 2, MARGIN + PADDING + BUTTON_HEIGHT,
                        tableWidth, tableHeight - scrollPane.getHorizontalScrollBar().getHeight()
                );
            } else {
                scrollPane.setBounds(MARGIN, MARGIN + PADDING + BUTTON_HEIGHT,
                        PANEL_WIDTH - 2 * MARGIN, tableHeight);
            }
        }
    }

    protected abstract void init();

    protected abstract void createUIComponents();

    private void addListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                assignment();
            }
        });
    }

    private Table createTable(JPanel parent, String[] columnNames) {
        table = new Table(parent, data, columnNames);

        scrollPane = table.drawTable();

        if (data.length != 0) {
            assignment();
            add(scrollPane);
        }

        return table;
    }

    /**
     * 创建表格
     *
     * @param stockList 股票列表
     */
    protected Table createTable(List<StockVO> stockList) {
        data = new Object[stockList.size()][];

        String[] columnNames = {
                "序号", "名称", "代码", "涨跌额", "涨跌幅", "成交量", "市净率", "最高",
                "最低", "市盈率", "后复权价", "收盘价", "开盘价", "周转率"
        };

        StockVO stock;
        for (int i = 0; i < stockList.size(); ) {
            stock = stockList.get(i);
            data[i] = new Object[]{
                    ++i, stock.getName(), stock.getId(),
                    stock.getIncrease_decreaseNum(),
                    stock.getIncrease_decreaseRate() * 100 + "%",
                    stock.getVolume()[0], stock.getPb()[0],
                    stock.getHigh()[0], stock.getLow()[0],
                    stock.getPe_ttm()[0], stock.getAdj_price()[0],
                    stock.getClose()[0], stock.getOpen()[0],
                    stock.getTurnover()[0]
            };
        }

        return createTable(this, columnNames);
    }

    protected Table createTable(IndexVO index) {
        data = new Object[index.getDate().length][];
        String[] columnNames = new String[]{
                "日期", "成交量", "最高", "最低", "最新", "收盘价", "开盘价"
        };

        for (int i = 0; i < data.length; i++) {
            data[i] = new Object[]{
                    index.getDate()[i],
                    index.getVolume()[i],
                    index.getHigh()[i],
                    index.getLow()[i],
                    index.getAdj_price()[i],
                    index.getClose()[i],
                    index.getOpen()[i],
            };
        }

        return createTable(this, columnNames);
    }

    protected Table createTable(StockVO stock) {
        data = new Object[stock.getDate().length][];
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

        return createTable(this, columnNames);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setColor(new Color(158, 76, 152));
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}

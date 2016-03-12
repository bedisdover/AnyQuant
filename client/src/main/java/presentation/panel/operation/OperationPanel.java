package presentation.panel.operation;

import presentation.frame.MainFrame;
import vo.StockVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

/**
 * Created by song on 16-3-3.
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
    protected int WIDTH, HEIGHT;

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

    protected TableCopy table;

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
        WIDTH = MainFrame.getMainFrame().getWidth() - MainFrame.MENU_WIDTH;
        HEIGHT = MainFrame.getMainFrame().getHeight();

        if (data != null) {
            int tableHeight = Math.min(
                    (data.length + 1) * table.getRowHeight(),
                    HEIGHT - MARGIN * 2 - PADDING * 2
            );
            int tableWidth = table.getWidth() + scrollPane.getVerticalScrollBarPolicy();

            //若界面宽度超过表格宽度(包含外间距)
            //表格居中显示
            if (WIDTH > MARGIN * 2 + tableWidth) {
                scrollPane.setBounds(
                        (WIDTH - table.getWidth()) / 2, MARGIN + PADDING * 2,
                        tableWidth, tableHeight
                );
            } else {
                scrollPane.setBounds(MARGIN, MARGIN + PADDING * 2, WIDTH - 2 * MARGIN,
                        tableHeight);
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

    /**
     * 创建表格
     *
     * @param stockList 股票列表
     */
    protected TableCopy createTable(List<StockVO> stockList) {
        data = new Object[stockList.size()][];

        String[] columnNames = {
                "序号", "名称", "代码", "成交量", "市净率", "最高",
                "最低", "市盈率", "最新", "收盘价", "开盘价", "周转率"
        };

        StockVO stock;
        for (int i = 0; i < stockList.size(); ) {
            stock = stockList.get(i);
            data[i] = new Object[]{
                    ++i, stock.getName(), stock.getId(),
                    stock.getVolume()[0], stock.getPb()[0],
                    stock.getHigh()[0], stock.getLow()[0],
                    stock.getPe_ttm()[0], stock.getAdj_price()[0],
                    stock.getClose()[0], stock.getOpen()[0],
                    stock.getTurnover()[0]
            };
        }

        table = new TableCopy(this, data, columnNames);

        scrollPane = table.drawTable();

        if (data.length != 0) {
            add(scrollPane);
        }

        return table;
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

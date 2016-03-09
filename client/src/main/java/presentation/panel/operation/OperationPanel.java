package presentation.panel.operation;

import presentation.frame.MainFrame;
import vo.StockVO;

import javax.swing.*;
import java.awt.*;
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
    protected final int WIDTH, HEIGHT;

    /**
     * 外边距
     */
    protected final int MARGIN;

    /**
     * 内边距
     */
    protected final int PADDING;

    /**
     * 按钮宽度
     */
    protected final int BUTTON_WIDTH;

    /**
     * 按钮高度
     */
    protected final int BUTTON_HEIGHT;

    protected final int TEXT_FIELD_WIDTH;

    protected TableCopy table;

    public OperationPanel() {
        WIDTH = MainFrame.getMainFrame().getWidth() * 4 / 5;
        HEIGHT = MainFrame.getMainFrame().getHeight();
        MARGIN = MainFrame.getMainFrame().getWidth() / 25;
        PADDING = MainFrame.getMainFrame().getWidth() / 20;
        BUTTON_WIDTH = PADDING + MARGIN;
        BUTTON_HEIGHT = MARGIN;
        TEXT_FIELD_WIDTH = BUTTON_WIDTH + PADDING * 2;

        setOpaque(false);
    }

    protected abstract void init();

    protected abstract void createUIComponents();

    /**
     * 创建表格
     *
     * @param stockList 股票列表
     */
    protected void createTable(List<StockVO> stockList) {
        Object[][] data = new Object[stockList.size()][];

        String[] columnNames = {
                "序号", "名称", "成交量", "平均市净率", "最高",
                "最低", "市盈率", "???", "收盘价", "开盘价", "周转率"
        };

        StockVO stock;
        for (int i = 0; i < stockList.size(); ) {
            stock = stockList.get(i);
            data[i] = new Object[]{
                    ++i, stock.getName(), stock.getVolume()[0], stock.getPb()[0],
                    stock.getHigh()[0], stock.getLow()[0], stock.getPe_ttm()[0],
                    stock.getAdj_price()[0], stock.getClose()[0], stock.getOpen()[0],
                    stock.getTurnover()[0]
            };
        }

        table = new TableCopy(this, data, columnNames);

        JScrollPane scrollPane = table.drawTable();
        int tableHeight = Math.min(data.length * 30 + 60, HEIGHT - MARGIN * 2 - PADDING * 2);
        scrollPane.setBounds(MARGIN, MARGIN + PADDING * 2, WIDTH - 2 * MARGIN, tableHeight);
        add(scrollPane);
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

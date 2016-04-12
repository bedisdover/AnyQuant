package presentation.panel.info;

import data.GetStockData;
import po.StockPO;
import presentation.util.Table;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 宋益明 on 16-4-12.
 * <p>
 * 股票详细数据面板
 */
public class DetailedInfoPanel extends JPanel {

    /**
     * 股票ID
     */
    private String id;

    /**
     * 关注按钮
     */
    private JButton follow;

    /**
     * 复选框，对应表格中显示的列
     * "最高", "最低", "开盘价", "收盘价", "成交量", "市净率", "市盈率", "后复权价", "周转率"
     */
    private JCheckBox high, low, open, close, volume, pb, pe_ttm, adjPrice, turnOver;

    /**
     * 表格的列名，包含日期及上述复选框中选择显示的列
     */
    private List<String> columns;

    /**
     * K线图、折线图
     */
    private JLabel labelK_Line, labelBrokenLien;

    DetailedInfoPanel(String id) {
        this.id = id;

        init();
        createUIComponents();
    }

    private void init() {
        setLayout(new BorderLayout());
    }

    private void createUIComponents() {
        createNorthPanel();
        createCenterPanel();
        createSouthPanel();
    }

    /**
     * 创建北部面板，包含日期选择框及关注按钮
     */
    private void createNorthPanel() {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());

        {
            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            JLabel labelTo = new JLabel("至");

            leftPanel.add(labelTo);

            northPanel.add(leftPanel, BorderLayout.WEST);
        }

        {
            JPanel rightPanel = new JPanel();
            rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            follow = new JButton("关注");
            rightPanel.add(follow);

            northPanel.add(rightPanel, BorderLayout.EAST);
        }

        add(northPanel, BorderLayout.NORTH);
    }

    /**
     * 创建中心面板，包含表格
     */
    private void createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        {
            JPanel columnsPanel = new JPanel();
            columnsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            high = new JCheckBox("最高价");
            low = new JCheckBox("最低价");
            open = new JCheckBox("开盘价");
            close = new JCheckBox("收盘价");
            volume = new JCheckBox("成交量");
            pb = new JCheckBox("市净率");
            pe_ttm = new JCheckBox("市盈率");
            adjPrice = new JCheckBox("后复权价");
            turnOver = new JCheckBox("转手率");

            columnsPanel.add(high);
            columnsPanel.add(low);
            columnsPanel.add(open);
            columnsPanel.add(close);
            columnsPanel.add(volume);
            columnsPanel.add(pb);
            columnsPanel.add(pe_ttm);
            columnsPanel.add(adjPrice);
            columnsPanel.add(turnOver);

            centerPanel.add(columnsPanel, BorderLayout.NORTH);
        }

        {

        }

        add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * 创建南部面板，包含图表类型选项
     */
    private void createSouthPanel() {
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        labelK_Line = new JLabel("K 线图");
        labelBrokenLien = new JLabel("折线图");

        southPanel.add(labelK_Line);
        southPanel.add(labelBrokenLien);

        add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * 初始化列名
     */
    private void initColumns() {
        high.setSelected(true);
        low.setSelected(true);
        open.setSelected(true);
        close.setSelected(true);
        volume.setSelected(true);
        pb.setSelected(true);
        pe_ttm.setSelected(true);
        adjPrice.setSelected(true);
        turnOver.setSelected(true);

        columns = new ArrayList<>();
    }

    /**
     * 创建显示所有属性的表格
     *
     * @return
     */
    private Table createTable() {
        StockPO stock = null;
        try {
            stock = new GetStockData().getStockData_name(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Object[][] data = new Object[stock.getDate().length][];
        String[] columnNames = new String[]{
                "日期", "成交量", "市净率", "最高", "最低", "市盈率", "后复权价", "收盘价", "开盘价", "周转率"
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

//        return new    Table();
        return null;

    }
}

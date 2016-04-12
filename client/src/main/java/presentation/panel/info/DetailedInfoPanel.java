package presentation.panel.info;

import data.GetStockData;
import po.StockPO;
import presentation.UltraSwing.UltraScrollPane;
import presentation.util.Table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 宋益明 on 16-4-12.
 * <p>
 * 股票详细数据面板
 */
public class DetailedInfoPanel extends JPanel implements ItemListener {

    /**
     * 股票ID
     */
    private String id;

    /**
     * 股票对象
     */
    private StockPO stock;

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
    private List<Integer> columnSelect;

    /**
     * 所有数据
     */
    private Object[][] allData;

    /**
     * 所有列
     */
    private String[] allColumns;

    /**
     * 需要显示的数据
     */
    private Object[][] data;

    /**
     * 需要显示的列
     */
    private String[] columns;

    /**
     * 表格对象
     */
    private Table table;

    /**
     * K线图、折线图
     */
    private JLabel labelK_Line, labelBrokenLien;

    DetailedInfoPanel(String id) {
        this.id = id;

        init();
        createUIComponents();
        initColumns();
    }

    private void init() {
        setLayout(new BorderLayout());

        try {
            stock = new GetStockData().getStockData_name(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        table = createTable();
    }

    /**
     * 初始化表格数据
     */
    private Table createTable() {
        allData = new Object[stock.getDate().length][];
        allColumns = new String[]{
                "日期", "最高", "最低", "开盘价", "收盘价",
                "成交量", "市净率", "市盈率", "后复权价", "周转率"
        };

        for (int i = 0; i < stock.getDate().length; i++) {
            allData[i] = new Object[]{
                    stock.getDate()[i],
                    stock.getHigh()[i],
                    stock.getLow()[i],
                    stock.getOpen()[i],
                    stock.getClose()[i],
                    stock.getVolume()[i],
                    stock.getPb()[i],
                    stock.getPe_ttm()[i],
                    stock.getAdj_price()[i],
                    stock.getTurnover()[i]
            };
        }

        return new Table(allData, allColumns);
    }

    /**
     * 创建组件
     */
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

            columnsPanel.setPreferredSize(new Dimension(500, 100));
            centerPanel.add(columnsPanel, BorderLayout.NORTH);
        }

        {
            JPanel southPanel = new JPanel();
            southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            JScrollPane scrollPane = new UltraScrollPane(table);
            southPanel.add(scrollPane);
            centerPanel.add(southPanel, BorderLayout.SOUTH);
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

        high.addItemListener(this);
        low.addItemListener(this);
        open.addItemListener(this);
        close.addItemListener(this);
        volume.addItemListener(this);
        pb.addItemListener(this);
        pe_ttm.addItemListener(this);
        adjPrice.addItemListener(this);
        turnOver.addItemListener(this);
    }

    /**
     * 创建显示选中属性的表格
     *
     * @return 表格对象
     */
    private Table createSelectTable() {
        columnSelect = getSelectColumns();

        data = new Object[allData.length][];
        columns = new String[columnSelect.size()];

        for (int i = 0; i < columnSelect.size(); i++) {
            columns[i] = allColumns[columnSelect.get(i)];
        }
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < columnSelect.size(); j++) {
                data[i][j] = allData[i][columnSelect.get(j)];
            }
        }
        return new Table(data, columns);
    }

    /**
     * 获得选中的列
     *
     * @return 列编号，日期必须显示
     */
    private List<Integer> getSelectColumns() {
        List<Integer> temp = new ArrayList<>();
        temp.add(0);
        if (high.isSelected()) {
            temp.add(1);
        }
        if (low.isSelected()) {
            temp.add(2);
        }
        if (open.isSelected()) {
            temp.add(3);
        }
        if (close.isSelected()) {
            temp.add(4);
        }
        if (volume.isSelected()) {
            temp.add(5);
        }
        if (pb.isSelected()) {
            temp.add(6);
        }
        if (pe_ttm.isSelected()) {
            temp.add(7);
        }
        if (adjPrice.isSelected()) {
            temp.add(8);
        }
        if (turnOver.isSelected()) {
            temp.add(9);
        }

        return temp;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        table = createSelectTable();
//        table.setModel(new DefaultTableModel(data, columns));
        repaint();
    }
}

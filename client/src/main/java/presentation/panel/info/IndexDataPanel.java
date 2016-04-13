package presentation.panel.info;

import bl.ShowIndexData;
import presentation.UltraSwing.UltraButton;
import presentation.UltraSwing.UltraPanel;
import presentation.UltraSwing.UltraScrollPane;
import presentation.frame.MainFrame;
import presentation.panel.operation.MarketIndexPanel;
import presentation.panel.operation.OperationPanel;
import presentation.util.DateChooser;
import presentation.util.ImageLoader;
import presentation.util.Table;
import vo.IndexVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 宋益明 on 16-3-28.
 * <p>
 * 大盘指数数据面板
 * 用于展示大盘指数的详细数据
 */
public class IndexDataPanel extends OperationPanel implements ItemListener {

    /**
     * 大盘指数值对象
     */
    private IndexVO index;

    /**
     * 从北到南的三个Panel
     */
    private UltraPanel northPanel, centerPanel, southPanel;

    /**
     * 表格面板、选项面板
     */
    private UltraPanel tablePanel, optionsPanel;

    /**
     * 复选框，对应表格中显示的列
     * "最高", "最低", "开盘价", "收盘价", "成交量", "后复权价"
     */
    private JCheckBox high, low, open, close, volume, adjPrice;

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
     * 开始日期、结束日期
     */
    private DateChooser dcStart, dcEnd;

    /**
     * 确认日期选择，生成对应折线图
     */
    private UltraButton confirm;

    /**
     * K线图、折线图、综合分析
     */
    private JButton labelK_Line, labelBrokenLien, labelAnalyze;

    public IndexDataPanel() {
        init();
        createUIComponents();
        initColumns();
        addListeners();
    }

    protected void init() {
        setLayout(null);

        try {
            index = new ShowIndexData().getLatestIndexData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        table = createTable();
        update();
    }

    /**
     * 初始化表格数据
     */
    private Table createTable() {
        allData = new Object[index.getDate().length][];
        allColumns = new String[]{
                "日期", "最高", "开盘价", "收盘价", "最低", "成交量", "后复权价"};

        for (int i = 0; i < index.getDate().length; i++) {
            allData[i] = new Object[]{
                    index.getDate()[i],
                    index.getHigh()[i],
                    index.getLow()[i],
                    index.getOpen()[i],
                    index.getClose()[i],
                    index.getVolume()[i],
                    index.getAdj_price()[i],
            };
        }

        return new Table(allData, allColumns);
    }

    /**
     * 创建组件
     */
    protected void createUIComponents() {
        createNorthPanel();
        createCenterPanel();
        createSouthPanel();
    }

    /**
     * 创建北部面板，包含日期选择框
     */
    private void createNorthPanel() {
        northPanel = new UltraPanel();
        northPanel.setLayout(null);

        dcStart = new DateChooser(northPanel, MARGIN, MARGIN / 2, BUTTON_WIDTH + PADDING, BUTTON_HEIGHT);
        JLabel labelTo = new JLabel("至");
        labelTo.setBounds(BUTTON_WIDTH + MARGIN * 2, MARGIN / 2, BUTTON_HEIGHT, BUTTON_HEIGHT);
        dcEnd = new DateChooser(northPanel, labelTo.getX() + BUTTON_HEIGHT + MARGIN,
                MARGIN / 2, BUTTON_WIDTH + PADDING, BUTTON_HEIGHT);
        confirm = new UltraButton("生成");

        confirm.setBounds(labelTo.getX() + BUTTON_WIDTH + PADDING * 4, MARGIN / 2,
                BUTTON_WIDTH, BUTTON_HEIGHT);

        northPanel.add(confirm);
        northPanel.add(labelTo);

        add(northPanel);
    }

    /**
     * 创建中心面板，包含当前信息
     */
    private void createCenterPanel() {
        centerPanel = new IndexCurrentInfoPanel();

        add(centerPanel);
    }

    /**
     * 创建南部面板，包含图表
     */
    private void createSouthPanel() {
        southPanel = new UltraPanel();
        southPanel.setLayout(null);

        tablePanel = createTablePanel();
        optionsPanel = createOptionsPanel();

        southPanel.add(tablePanel);
        southPanel.add(optionsPanel);

        add(southPanel);
    }

    /**
     * 创建并返回表格面板，包含表格选项（所有复选框）、表格
     *
     * @return 表格面板
     */
    private UltraPanel createTablePanel() {
        UltraPanel tablePanel = new UltraPanel();
        tablePanel.setLayout(null);

        {//复选框面板，包含所有复选框
            UltraPanel columnsPanel = new UltraPanel();
            columnsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            high = new JCheckBox("最高价");
            low = new JCheckBox("最低价");
            open = new JCheckBox("开盘价");
            close = new JCheckBox("收盘价");
            volume = new JCheckBox("成交量");
            adjPrice = new JCheckBox("后复权价");

            columnsPanel.add(high);
            columnsPanel.add(low);
            columnsPanel.add(open);
            columnsPanel.add(close);
            columnsPanel.add(volume);
            columnsPanel.add(adjPrice);

            columnsPanel.setBounds(0, 0, PANEL_WIDTH - MARGIN * 3 - BUTTON_WIDTH, BUTTON_HEIGHT);
            tablePanel.add(columnsPanel);
        }

        {
            JScrollPane scrollPane = new UltraScrollPane(table);
            scrollPane.setBounds(0, BUTTON_HEIGHT, PANEL_HEIGHT - MARGIN * 3 - BUTTON_HEIGHT * 2,
                    table.getColumnModel().getTotalColumnWidth());
            tablePanel.add(scrollPane);
        }

        return tablePanel;
    }

    /**
     * 创建并返回选项面板，包含“K线图“、”折线图“、”综合分析“
     *
     * @return 选项面板
     */
    private UltraPanel createOptionsPanel() {
        UltraPanel optionsPanel = new UltraPanel();
        optionsPanel.setLayout(null);

        labelK_Line = new JButton("K 线图");
        labelBrokenLien = new JButton("折线图");
        labelAnalyze = new JButton("综合分析");

//        labelK_Line.setIcon(new ImageIcon(ImageLoader.kLine));
//        labelBrokenLien.setIcon(new ImageIcon(ImageLoader.brokenLine));

        labelK_Line.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_WIDTH));
        labelBrokenLien.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_WIDTH));
//        southPanel.setPreferredSize(new Dimension(MainFrame.getMainFrame().getWidth(), BUTTON_HEIGHT));

        labelK_Line.setBounds(0, 0, BUTTON_WIDTH, BUTTON_WIDTH);
        labelBrokenLien.setBounds(0, labelK_Line.getY() + labelK_Line.getHeight() + PADDING,
                BUTTON_WIDTH, BUTTON_WIDTH);
        labelAnalyze.setBounds(0, labelBrokenLien.getY() + labelBrokenLien.getHeight() + PADDING,
                BUTTON_WIDTH, BUTTON_WIDTH);

        optionsPanel.add(labelK_Line);
        optionsPanel.add(labelBrokenLien);
        optionsPanel.add(labelAnalyze);

        return optionsPanel;
    }

    /**
     * 添加事件监听器
     */
    private void addListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                assignment();
            }
        });

        confirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //TODO
            }
        });

        labelK_Line.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame.getMainFrame().addOperationPanel(
                        new MarketIndexPanel("kLine"));
            }
        });

        labelBrokenLien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame.getMainFrame().addOperationPanel(
                        new MarketIndexPanel("brokenLine"));
            }
        });
    }

    /**
     * 界面大小发生变化时，对组件位置重新赋值
     */
    private void assignment() {
        northPanel.setBounds(0, 0, PANEL_WIDTH, BUTTON_HEIGHT + MARGIN);
        centerPanel.setBounds(MARGIN, northPanel.getHeight(), PANEL_WIDTH - MARGIN * 2,
                BUTTON_HEIGHT + PADDING);
        southPanel.setBounds(MARGIN, centerPanel.getY() + centerPanel.getHeight(),
                PANEL_WIDTH - MARGIN * 2, PANEL_HEIGHT - southPanel.getY() - MARGIN * 2);
        tablePanel.setBounds(0, 0, PANEL_WIDTH - BUTTON_WIDTH - MARGIN * 2,
                southPanel.getHeight());
        optionsPanel.setBounds(PANEL_WIDTH - BUTTON_WIDTH - MARGIN * 2, 0,
                BUTTON_WIDTH, southPanel.getHeight());

        revalidate();
        repaint();
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
        adjPrice.setSelected(true);

        high.addItemListener(this);
        low.addItemListener(this);
        open.addItemListener(this);
        close.addItemListener(this);
        volume.addItemListener(this);
        adjPrice.addItemListener(this);
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
        if (adjPrice.isSelected()) {
            temp.add(6);
        }

        return temp;
    }

    /**
     * 固定时间间隔刷新面板
     */
    private void update() {
        new Thread() {
            @Override
            public void run() {
                super.run();

//                try {
//                    while (true) {
//                        Thread.sleep(1000);
//                        repaint();
//                    }
//                } catch (Exception e) {
//                }
            }
        }.start();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        table = createSelectTable();
//        table.setModel(new DefaultTableModel(data, columns));
        repaint();
    }
}

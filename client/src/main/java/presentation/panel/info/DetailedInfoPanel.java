package presentation.panel.info;

import bl.SelfSelectStock;
import blservice.SelfSelectStockService;
import config.StockDataConfig;
import config.SystemConfig;
import data.GetStockData;
import org.dom4j.DocumentException;
import po.StockPO;
import presentation.UltraSwing.UltraButton;
import presentation.UltraSwing.UltraPanel;
import presentation.UltraSwing.UltraScrollPane;
import presentation.frame.MainFrame;
import presentation.panel.operation.OperationPanel;
import presentation.util.DateChooser;
import presentation.util.Table;
import vo.StockVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 宋益明 on 16-4-12.
 * <p>
 * 股票详细数据面板
 */
public class DetailedInfoPanel extends OperationPanel implements ItemListener {

    /**
     * 股票ID
     */
    private String id;

    /**
     * 股票对象
     */
    private StockPO stock;

    /**
     * 北部面板，承载日期选择框、关注按钮
     */
    private UltraPanel northPanel;

    /**
     * 开始日期、结束日期
     */
    private DateChooser dcStart, dcEnd;


    /**
     * 确认日期选择，生成对应折线图
     */
    private UltraButton confirm;

    /**
     * 关注按钮
     */
    private JButton btnFollow;

    /**
     * 表格选项面板
     */
    private UltraPanel columnsPanel;

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
     * K线图、折线图、综合分析
     * TODO BUTTON
     */
    private UltraButton labelK_Line, labelBrokenLien, labelAnalyze;

    /**
     * 父面板
     */
    private JPanel parent;

    /**
     * 返回按钮
     */
    private JButton back;

    /**
     * 配置对象
     */
    private StockDataConfig config;

    public DetailedInfoPanel(JPanel parent, String id) {
        this.parent = parent;
        this.id = id;

        init();
        createUIComponents();
        initColumns();
        addListeners();
    }

    protected void init() {
        setLayout(null);

        try {
            stock = new GetStockData().getStockData_name(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        initTable();
        update();
    }

    /**
     * 初始化表格数据
     */
    private void initTable() {
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
                    stock.getVolume()[i] / 1000 + "万",
                    stock.getPb()[i],
                    stock.getPe_ttm()[i],
                    stock.getAdj_price()[i],
                    stock.getTurnover()[i]
            };
        }

        table = new Table(this, allData, allColumns);
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
     * 创建北部面板，包含日期选择框及关注按钮
     */
    private void createNorthPanel() {
        northPanel = new UltraPanel();
        northPanel.setLayout(null);

        {
            back = new JButton("返回");
            dcStart = new DateChooser(northPanel,
                    PANEL_WIDTH - MARGIN - BUTTON_WIDTH * 2 - PADDING * 7 - BUTTON_HEIGHT * 2,
                    MARGIN, BUTTON_WIDTH + PADDING, BUTTON_HEIGHT);
            dcEnd = new DateChooser(northPanel,
                    PANEL_WIDTH - MARGIN - BUTTON_WIDTH * 2- PADDING * 4 - BUTTON_HEIGHT,
                    MARGIN, BUTTON_WIDTH + PADDING, BUTTON_HEIGHT);
            confirm = new UltraButton("生成");

            northPanel.add(confirm);
            northPanel.add(back);
        }

        {
            btnFollow = new JButton("关 注");

            northPanel.add(btnFollow);
        }

        add(northPanel);
    }

    /**
     * 创建中心面板，包含表格
     */
    private void createCenterPanel() {
//        centerPanel = new UltraPanel();
//        centerPanel.setLayout(new BorderLayout());
//        centerPanel.setPreferredSize(new Dimension(PANEL_WIDTH,
//                PANEL_HEIGHT - BUTTON_HEIGHT * 2 - MARGIN * 2));
        {
            columnsPanel = new UltraPanel();
            columnsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            columnsPanel.setPreferredSize(new Dimension(PANEL_WIDTH - MARGIN * 2,
                    BUTTON_HEIGHT));

            high = new JCheckBox("最高价");
            low = new JCheckBox("最低价");
            open = new JCheckBox("开盘价");
            close = new JCheckBox("收盘价");
            volume = new JCheckBox("成交量");
            pb = new JCheckBox("市净率");
            pe_ttm = new JCheckBox("市盈率");
            adjPrice = new JCheckBox("后复权价");
            turnOver = new JCheckBox("周转率");

            columnsPanel.add(high);
            columnsPanel.add(low);
            columnsPanel.add(open);
            columnsPanel.add(close);
            columnsPanel.add(volume);
            columnsPanel.add(pb);
            columnsPanel.add(pe_ttm);
            columnsPanel.add(adjPrice);
            columnsPanel.add(turnOver);

//            columnsPanel.setPreferredSize(new Dimension(700, 100));
//            centerPanel.add(columnsPanel, BorderLayout.NORTH);
            add(columnsPanel);
        }

        {
//            UltraPanel southPanel = new UltraPanel();
//            southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//            southPanel.setPreferredSize(new Dimension(PANEL_WIDTH - MARGIN * 2,
//                    centerPanel.getPreferredSize().height - BUTTON_HEIGHT));
            initColumns();
            table = createSelectTable();
            scrollPane = new UltraScrollPane(table);
//            scrollPane.setPreferredSize(new Dimension(700, 400));
//            southPanel.add(scrollPane);

//            centerPanel.setPreferredSize(new Dimension(700, 500));
//            centerPanel.add(scrollPane, BorderLayout.SOUTH);
//            centerPanel.add(southPanel, BorderLayout.SOUTH);
        }

//        centerPanel.setBounds(0, BUTTON_HEIGHT + MARGIN,
//                PANEL_WIDTH, PANEL_HEIGHT - BUTTON_HEIGHT * 2 - MARGIN * 2);
//        add(centerPanel);
        add(scrollPane);
    }

    /**
     * 创建南部面板，包含图表类型选项
     */
    private void createSouthPanel() {
//        southPanel = new UltraPanel();
//        southPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//        southPanel.setPreferredSize(new Dimension(PANEL_WIDTH, BUTTON_HEIGHT + MARGIN));

        labelK_Line = new UltraButton(1);
        labelBrokenLien = new UltraButton(2);
        labelAnalyze = new UltraButton(3);
        labelK_Line.setSize(BUTTON_WIDTH,BUTTON_WIDTH);
        labelBrokenLien.setSize(BUTTON_WIDTH,BUTTON_WIDTH);
        labelAnalyze.setSize(BUTTON_WIDTH,BUTTON_WIDTH);
        add(labelAnalyze);
        add(labelK_Line);
        add(labelBrokenLien);
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

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame.getMainFrame().addOperationPanel(parent);
            }
        });

        confirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    stock = new GetStockData().getStockData_name(stock.getId(),
                            dcStart.getTime(), dcEnd.getTime());

                    initTable();
                    scrollPane.getViewport().remove(table);
                    table = createSelectTable();
                    scrollPane.getViewport().add(table);

                    assignment();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnFollow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                follow(stock.getId());
            }
        });

        labelK_Line.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame.getMainFrame().addOperationPanel(
                        new StockInfoPanel(DetailedInfoPanel.this, stock));
            }
        });

        labelBrokenLien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "开发中，敬请期待！");
            }
        });

        labelAnalyze.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    MainFrame.getMainFrame().addOperationPanel(
                            new AnalyzePanel(DetailedInfoPanel.this, new StockVO(stock)));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    /**
     * 关注股票
     *
     * @param name 股票名称(代码)
     */
    private void follow(String name) {
        SelfSelectStockService selfSelect = new SelfSelectStock();

        try {
            boolean exist = selfSelect.addStock(name);
            if (exist) {
                JOptionPane.showMessageDialog(this, "添加成功!");
            } else {
                JOptionPane.showMessageDialog(this, "您添加的股票已在关注列表中");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "呀!出错啦...");
        }
    }

    /**
     * 界面大小发生变化时，对组件重新赋值
     */
    private void assignment() {
        back.setBounds(MARGIN, MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT);
        confirm.setBounds(PANEL_WIDTH - MARGIN * 2 - BUTTON_WIDTH * 2, MARGIN,
                BUTTON_WIDTH, BUTTON_HEIGHT);
        btnFollow.setBounds(PANEL_WIDTH - MARGIN - BUTTON_WIDTH, MARGIN,
                BUTTON_WIDTH, BUTTON_HEIGHT);
        northPanel.setBounds(0, 0, PANEL_WIDTH, BUTTON_HEIGHT + MARGIN);
        columnsPanel.setBounds(MARGIN, BUTTON_HEIGHT + MARGIN * 2,
                PANEL_WIDTH - MARGIN * 2, BUTTON_HEIGHT);
        scrollPane.setBounds(MARGIN, columnsPanel.getY() + columnsPanel.getHeight(),
                table.getColumnModel().getTotalColumnWidth() + 20,
                PANEL_HEIGHT - (columnsPanel.getY() + columnsPanel.getHeight()) - PADDING);

        labelK_Line.setBounds(PANEL_WIDTH - PADDING - BUTTON_WIDTH, scrollPane.getY(),
                BUTTON_WIDTH,BUTTON_WIDTH);
        labelBrokenLien.setBounds(labelK_Line.getX(), labelK_Line.getY() + BUTTON_WIDTH+ PADDING,
                BUTTON_WIDTH, BUTTON_WIDTH);
        labelAnalyze.setBounds(labelK_Line.getX(), labelBrokenLien.getY() + BUTTON_WIDTH + PADDING,
                BUTTON_WIDTH, BUTTON_WIDTH);



        revalidate();
        repaint();
    }

    /**
     * 初始化列名
     */
    private void initColumns() {
        try {
            config = SystemConfig.getStockDataConfig();
        } catch (MalformedURLException | DocumentException e) {
            e.printStackTrace();
        }

        assert config != null;
        high.setSelected(config.isHighSelected());
        low.setSelected(config.isLowSelected());
        open.setSelected(config.isOpenSelected());
        close.setSelected(config.isCloseSelected());
        volume.setSelected(config.isVolumeSelected());
        pb.setSelected(config.isPbSelected());
        pe_ttm.setSelected(config.isPe_ttmSelected());
        adjPrice.setSelected(config.isAdjPriceSelected());
        turnOver.setSelected(config.isTurnOverSelected());

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
            data[i] = new Object[columnSelect.size()];
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

    /**
     * 固定时间间隔刷新面板
     */
    private void update() {
        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    while (true) {
                        Thread.sleep(1000);
                        repaint();
                    }
                } catch (Exception e) {}
            }
        }.start();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        scrollPane.getViewport().remove(table);
        table = createSelectTable();
        scrollPane.getViewport().add(table);

        storeChange((JCheckBox) e.getSource());

        assignment();
    }

    /**
     * 存储变更
     *
     * @param item 变更的项目
     */
    private void storeChange(JCheckBox item) {
        if (item == high) {
            config.setHigh(high.isSelected());
        }
        if (item == low) {
            config.setLow(low.isSelected());
        }
        if (item == open) {
            config.setOpen(open.isSelected());
        }
        if (item == close) {
            config.setClose(close.isSelected());
        }
        if (item == volume) {
            config.setVolume(volume.isSelected());
        }
        if (item == pb) {
            config.setPb(pb.isSelected());
        }
        if (item == pe_ttm) {
            config.setPe_ttm(pe_ttm.isSelected());
        }
        if (item == adjPrice) {
            config.setAdjPrice(adjPrice.isSelected());
        }
        if (item == turnOver) {
            config.setTurnOver(turnOver.isSelected());
        }
    }
}

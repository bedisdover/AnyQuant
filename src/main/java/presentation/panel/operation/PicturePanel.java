package presentation.panel.operation;

import bl.SelfSelectStock;
import bl.SortStock;
import blservice.SelfSelectStockService;
import data.GetStockData;
import po.StockID;
import po.StockPO;
import presentation.UltraSwing.UltraButton;
import presentation.UltraSwing.UltraScrollPane;
import presentation.frame.MainFrame;
import presentation.panel.info.DetailedInfoPanel;
import presentation.util.DateChooser;
import presentation.util.Table;
import vo.StockVO;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 宋益明 on 16-3-2.
 * <p>
 * 行情面板
 */
public class PicturePanel extends OperationPanel {

    /**
     * 搜索框,用于输入股票名称或代码
     * 无搜索任务时自动隐藏
     */
    private JTextField searchInput;

    /**
     * 搜索按钮
     */
    private UltraButton btnSearch;

//    /**
//     * 日期选择框
//     * 通过日期选择框改变日期查看其它日期的数据
//     */
//    private DateChooser dateChooser;

    /**
     * 榜单面板
     */
    private ListPanel listPanel;

    /**
     * 获取table
     */
    private Table table;
    private Table table1;
    private Table table2;
    private Table table3;

    private JPopupMenu[] popupMenu;
    private JMenuItem[] menuItem;
    private MainFrame mainFrame;

    public PicturePanel() {
        init();
        createUIComponents();
        addListeners();
    }

    protected void init() {
        setLayout(null);
        popupMenu = new JPopupMenu[3];
        menuItem = new JMenuItem[6];
        for (int i = 0; i < 6; i++) {
            menuItem[i] = new JMenuItem();
            if (i % 2 == 0) {
                menuItem[i].setText("显示详细信息");
            } else {
                menuItem[i].setText("添加关注");
            }
        }
        popupMenu[0] = new JPopupMenu();
        popupMenu[0].add(menuItem[0]);
//        popupMenu[0].add(menuItem[1]);
        popupMenu[1] = new JPopupMenu();
        popupMenu[1].add(menuItem[2]);
//        popupMenu[1].add(menuItem[3]);
        popupMenu[2] = new JPopupMenu();
        popupMenu[2].add(menuItem[4]);
//        popupMenu[2].add(menuItem[5]);
    }

    protected void createUIComponents() {
//        dateChooser = new DateChooser(this, MARGIN, MARGIN, BUTTON_WIDTH + PADDING, BUTTON_HEIGHT);
        btnSearch = new UltraButton("搜索");
        searchInput = new JTextField();

        listPanel = new ListPanel();

        add(btnSearch);
        add(listPanel);
    }

    private void addListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                btnSearch.setBounds(PANEL_WIDTH - MARGIN - btnSearch.width,
                        MARGIN, btnSearch.width, btnSearch.height);
                searchInput.setBounds(btnSearch.getX() - TEXT_FIELD_WIDTH,
                        MARGIN, TEXT_FIELD_WIDTH, BUTTON_HEIGHT);
                listPanel.setBounds(MARGIN, MARGIN + BUTTON_HEIGHT + PADDING / 2,
                        PANEL_WIDTH - MARGIN * 2, PANEL_HEIGHT - MARGIN * 2 - PADDING - BUTTON_HEIGHT);

                revalidate();
                repaint();
            }
        });

        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && !searchInput.getText().equals("")) {
                    table1.searchStock(searchInput.getText());
                    table2.searchStock(searchInput.getText());
                    table3.searchStock(searchInput.getText());
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (searchInput.getText().equals("")) {
                    searchInput.setText("输入股票名称或代码");
                }
                add(searchInput);
                repaint();

                //显示搜索框后,若点击除搜索框和搜索按钮以外的其他区域,
                //自动隐藏搜索框
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getSource() != btnSearch && e.getSource() != searchInput) {
                            remove(searchInput);
                            repaint();
                        }
                    }
                });
            }
        });

        searchInput.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                searchInput.setText("");
            }
        });

        btnSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    table1.searchStock(searchInput.getText());
                    table2.searchStock(searchInput.getText());
                    table3.searchStock(searchInput.getText());
                }
            }
        });

        searchInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    table1.searchStock(searchInput.getText());
                    table2.searchStock(searchInput.getText());
                    table3.searchStock(searchInput.getText());
                }
            }
        });
    }

    private void addTableListener(Table t, int i) {
        /**
         * todo 给table[i]添加鼠标右键监听
         */
        final int finalI = i;
        t.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
                    Point p = e.getPoint();
                    int row = table.rowAtPoint(p);
                    int column = table.columnAtPoint(p);
                    if (!e.isControlDown() & !e.isShiftDown() & row != -1 & column != -1) {
                        table.changeSelection(row, column, true, e.isShiftDown());
                    }
                }
            }

            public void mouseReleased(MouseEvent e) {
                if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
                    showMenuList(e.getXOnScreen(), e.getYOnScreen(), finalI);
                }
            }
        });

        /**
         * todo 给JMenuItem添加事件监听
         */
        //显示详细信息
        menuItem[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
//                System.out.println("this is menuItem 0");
                showDetailedInfo();
            }
        });
        //添加关注
        menuItem[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("this is menuItem 1");
                String stockId;
                int line = table1.getSelectedRow();
                if (line == -1) {
                    line = table2.getSelectedRow();
                    if (line == -1) {
                        line = table3.getSelectedRow();
                        stockId = (String) table3.getValueAt(line, 2);
                    } else {
                        stockId = (String) table2.getValueAt(line, 2);
                    }
                } else {
                    stockId = (String) table1.getValueAt(line, 2);
                }
                System.out.println(stockId);
                follow(stockId);
            }
        });
        //显示详细信息
        menuItem[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("this is menuItem 2");
                showDetailedInfo();
            }
        });
        //添加关注
        menuItem[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("this is menuItem 3");
                String stockId;
                int line = table1.getSelectedRow();
                if (line == -1) {
                    line = table2.getSelectedRow();
                    if (line == -1) {
                        line = table3.getSelectedRow();
                        stockId = (String) table3.getValueAt(line, 2);
                    } else {
                        stockId = (String) table2.getValueAt(line, 2);
                    }
                } else {
                    stockId = (String) table1.getValueAt(line, 2);
                }
                System.out.println(stockId);
                follow(stockId);
            }
        });
        //显示详细信息
        menuItem[4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("this is menuItem 4");
                showDetailedInfo();
            }
        });
        //添加关注
        menuItem[5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("this is menuItem 5");
                String stockId;
                int line = table1.getSelectedRow();
                if (line == -1) {
                    line = table2.getSelectedRow();
                    if (line == -1) {
                        line = table3.getSelectedRow();
                        stockId = (String) table3.getValueAt(line, 2);
                    } else {
                        stockId = (String) table2.getValueAt(line, 2);
                    }
                } else {
                    stockId = (String) table1.getValueAt(line, 2);
                }
                System.out.println(stockId);
                follow(stockId);
            }
        });
    }

    private void showMenuList(int x, int y, int i) {
        mainFrame = MainFrame.getMainFrame();
        popupMenu[i - 1].show(mainFrame, x - mainFrame.getX(), y - mainFrame.getY());
//        System.out.println("this is popupmenu " + (i - 1));
    }

    private void showDetailedInfo() {
        int selectRow = table1.getSelectedRow();
        String name;
        if (selectRow == -1) {
            selectRow = table2.getSelectedRow();
            if (selectRow == -1) {
                selectRow = table3.getSelectedRow();
                name = (String) table3.getValueAt(selectRow, 2);
            } else {
                name = (String) table2.getValueAt(selectRow, 2);
            }
        } else {
            name = (String) table1.getValueAt(selectRow, 2);
        }

        StockPO stock = null;
        try {
            stock = new GetStockData().getStockData_name(name);
        } catch (ClassCastException e1) {
            //若某表格第2项不是字符串,捕获此异常
            //此时无需显示详细信息
            return;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "请检查网络连接！");
        }

        MainFrame.getMainFrame().addOperationPanel(
                new DetailedInfoPanel(PicturePanel.this, name));
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }

    /**
     * 创建榜单
     *
     * @param stockList 股票列表
     * @return 目标榜单
     */
    private UltraScrollPane createRankingTable(List<StockVO> stockList) {
        data = new Object[stockList.size()][];

        String[] columnNames = {
                "序号", "名称", "代码", "涨跌额", "涨跌幅", "成交量", "市净率", "最高",
                "最低", "市盈率", "后复权价", "收盘价", "开盘价", "周转率"
        };

        StockVO stock;
        for (int i = 0; i < stockList.size(); ) {
            stock = stockList.get(i);
            int length = stock.getDate().length - 1;
            data[i] = new Object[]{
                    ++i, stock.getName(), stock.getId(),
                    stock.getIncrease_decreaseNum()[length],
                    ((double) Math.round(stock.getIncrease_decreaseRate()[length]
                            * 100 * 100)) / 100 + "%",
                    stock.getVolume()[length], stock.getPb()[length],
                    stock.getHigh()[length], stock.getLow()[length],
                    stock.getPe_ttm()[length], stock.getAdj_price()[length],
                    stock.getClose()[length], stock.getOpen()[length],
                    stock.getTurnover()[length]
            };
        }

        table = new Table(this, data, columnNames) {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                int modelRow = convertRowIndexToModel(row);
                int modelColumn = convertColumnIndexToModel(column);
                Component comp = super.prepareRenderer(renderer, row, column);

                double increase = Double.parseDouble(this.getModel().getValueAt(modelRow, 3).toString());
                if (increase > 0) {
                    if (modelColumn == 3 || modelColumn == 4) {
                        comp.setForeground(new Color(179, 43, 56));
                    } else {
                        comp.setForeground(new Color(62, 56, 49, 240));
                    }
                } else if (increase < 0) {
                    if (modelColumn == 3 || modelColumn == 4) {
                        comp.setForeground(new Color(37, 120, 38));
                    } else {
                        comp.setForeground(new Color(62, 56, 49, 240));
                    }
                }

                return comp;
            }
        };

        UltraScrollPane resultScrollPane = new UltraScrollPane(table);

        resultScrollPane.setPreferredSize(new Dimension(
                table.getColumnModel().getTotalColumnWidth()
                        + resultScrollPane.getVerticalScrollBar().getWidth(),
                table.getRowHeight() * table.getRowCount()));

        return resultScrollPane;
    }

    /**
     * 各种榜单面板
     * 位于行情面板中央位置
     */
    class ListPanel extends JScrollPane {

        /**
         * 中心面板
         * 放置所有榜单
         * 为了能够显示滚动条,添加中心面板,否则无法正常显示滚动条
         */
        private JPanel centerPanel;

        /**
         * 涨幅榜,跌幅榜,成交额榜,换手率榜 对应的label
         */
        private JLabel labelIncrease, labelDecrease, labelTurnVolume;

        /**
         * label的宽度
         */
        private final int LABEL_WIDTH = BUTTON_WIDTH + PADDING;

        /**
         * 涨幅榜,跌幅榜,成交额榜,换手率榜
         */
        private UltraScrollPane scrollIncrease, scrollDecrease, scrollTurnVolume;

        /**
         * 单个榜单的高度
         */
        private final int SCROLL_HEIGHT = 200;

        /**
         * 自定义按钮
         */
        private UltraButton btnCustom;

        /**
         * 榜单状态Map
         * 记录 涨幅榜,跌幅榜,成交额榜,换手率榜 是否处于展开状态
         * 若处于展开状态, 对应值为true, 否则为false
         */
        private Map<JScrollPane, Boolean> expand;

        ListPanel() {
            super();

            init();
            createUIComponents();
            addListeners();
        }

        /**
         * 初始化
         */
        protected void init() {
            setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
            setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
            setBackground(new Color(0, 0, 0, 0));
        }

        /**
         * 创建组件
         */
        protected void createUIComponents() {
            centerPanel = new JPanel();

            centerPanel.setLayout(null);
            centerPanel.setBackground(new Color(0, 0, 0, 0));

            labelIncrease = new JLabel("↓  涨幅榜");
            labelDecrease = new JLabel("↓  跌幅榜");
            labelTurnVolume = new JLabel("↓  成交量榜");

            btnCustom = new UltraButton("自定义");
            btnCustom.setToolTipText("自定义股票列表");

            try {
                SortStock sortStock = new SortStock();
                scrollIncrease = createRankingTable(sortStock.increase_sort());
                table1 = table;
                addTableListener(table1, 1);
                scrollDecrease = createRankingTable(sortStock.decrease_sort());
                table2 = table;
                addTableListener(table2, 2);
                scrollTurnVolume = createRankingTable(sortStock.volume_sort());
                table3 = table;
                addTableListener(table3, 3);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(PicturePanel.this, "请检查网络连接！");
            }

            centerPanel.add(labelIncrease);
            centerPanel.add(labelDecrease);
            centerPanel.add(labelTurnVolume);
            centerPanel.add(scrollIncrease);
            centerPanel.add(scrollDecrease);
            centerPanel.add(scrollTurnVolume);
            centerPanel.add(btnCustom);
            getViewport().add(centerPanel);

            expand = new HashMap<>();
            expand.put(scrollIncrease, true);
            expand.put(scrollDecrease, true);
            expand.put(scrollTurnVolume, true);
        }

        /**
         * 添加事件监听器
         */
        private void addListeners() {
            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    assignment();

                    revalidate();
                    repaint();
                }
            });

            getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
                @Override
                public void adjustmentValueChanged(AdjustmentEvent e) {
                    repaint();
                }
            });

            getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {
                @Override
                public void adjustmentValueChanged(AdjustmentEvent e) {
                    repaint();
                }
            });

            btnCustom.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        showCustomDialog();
                    }
                }
            });

            labelIncrease.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    increaseOperation();
                    assignment();
                }
            });

            labelDecrease.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    decreaseOperation();
                    assignment();
                }
            });

            labelTurnVolume.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    turnVolumeOperation();
                    assignment();
                }
            });
        }

        /**
         * 界面大小发生变化时,对所有组件的位置进行重新赋值
         */
        private void assignment() {
            int temp = 0;//记录展开的榜单数量
            int width = PANEL_WIDTH - MARGIN * 2;//榜单面板宽度
            //单个榜单的宽度
            int scrollPane_width = (int) Math.min(width - MARGIN * 2,
                    scrollIncrease.getPreferredSize().getWidth());
            {
                labelIncrease.setBounds(MARGIN, PADDING / 2, LABEL_WIDTH, BUTTON_HEIGHT);
                scrollIncrease.setBounds(
                        (width - scrollPane_width) / 2, labelIncrease.getY() + BUTTON_HEIGHT,
                        scrollPane_width, SCROLL_HEIGHT);
            }

            {
                if (expand.get(scrollIncrease)) {
                    labelDecrease.setBounds(MARGIN, scrollIncrease.getY() + scrollIncrease.getHeight(),
                            LABEL_WIDTH, BUTTON_HEIGHT);
                    temp++;
                } else {
                    labelDecrease.setBounds(MARGIN, labelIncrease.getY() + BUTTON_HEIGHT,
                            LABEL_WIDTH, BUTTON_HEIGHT);
                }
                scrollDecrease.setBounds(
                        (width - scrollPane_width) / 2, labelDecrease.getY() + BUTTON_HEIGHT,
                        scrollPane_width, SCROLL_HEIGHT);
            }

            {
                if (expand.get(scrollDecrease)) {
                    labelTurnVolume.setBounds(MARGIN, scrollDecrease.getY() + scrollDecrease.getHeight(),
                            LABEL_WIDTH, BUTTON_HEIGHT);
                    temp++;
                } else {
                    labelTurnVolume.setBounds(MARGIN, labelDecrease.getY() + BUTTON_HEIGHT,
                            LABEL_WIDTH, BUTTON_HEIGHT);
                }
                scrollTurnVolume.setBounds(
                        (width - scrollPane_width) / 2, labelTurnVolume.getY() + BUTTON_HEIGHT,
                        scrollPane_width, SCROLL_HEIGHT);
            }

            if (expand.get(scrollTurnVolume)) {
                temp++;
            }

            btnCustom.setBounds(getWidth() - MARGIN - btnCustom.width, labelIncrease.getY() / 2,
                    btnCustom.width, btnCustom.height);

            centerPanel.setPreferredSize(
                    new Dimension(
                            PANEL_WIDTH - 4 * PADDING, temp * SCROLL_HEIGHT + 5 * BUTTON_HEIGHT));

            repaint();
        }

        /**
         * 显示自定义对话框
         */
        private void showCustomDialog() {
            JDialog dialog = new CustomDialog();
            dialog.setVisible(true);
        }

        /**
         * 自定义对话框操作后更新数据
         */
        void updateData() {
            try {
                centerPanel.remove(scrollIncrease);
                centerPanel.remove(scrollDecrease);
                centerPanel.remove(scrollTurnVolume);

                SortStock sortStock = new SortStock();
                scrollIncrease = createRankingTable(sortStock.increase_sort());
                scrollDecrease = createRankingTable(sortStock.decrease_sort());
                scrollTurnVolume = createRankingTable(sortStock.volume_sort());

                expand = new HashMap<>();
                centerPanel.add(scrollIncrease);
                centerPanel.add(scrollDecrease);
                centerPanel.add(scrollTurnVolume);
                expand.put(scrollIncrease, true);
                expand.put(scrollDecrease, true);
                expand.put(scrollTurnVolume, true);

                assignment();
                repaint();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * '涨幅榜'单击后的操作
         */
        private void increaseOperation() {
            if (changeText(labelIncrease)) {
                centerPanel.add(scrollIncrease);
                expand.put(scrollIncrease, true);
            } else {
                centerPanel.remove(scrollIncrease);
                expand.put(scrollIncrease, false);
            }
        }

        /**
         * '跌幅榜'单击后的操作
         */
        private void decreaseOperation() {
            if (changeText(labelDecrease)) {
                centerPanel.add(scrollDecrease);
                expand.put(scrollDecrease, true);
            } else {
                centerPanel.remove(scrollDecrease);
                expand.put(scrollDecrease, false);
            }
        }

        /**
         * '成交量榜'单击后的操作
         */
        private void turnVolumeOperation() {
            if (changeText(labelTurnVolume)) {
                centerPanel.add(scrollTurnVolume);
                expand.put(scrollTurnVolume, true);
            } else {
                centerPanel.remove(scrollTurnVolume);
                expand.put(scrollTurnVolume, false);
            }
        }

        /**
         * 单击label后,修改label显示的文字
         * 将'→'与'↓'互换
         *
         * @return 若单击后对应榜单处于展开状态, 返回true, 否则返回false
         */
        private boolean changeText(JLabel label) {
            String text = label.getText();
            if (text.startsWith("↓")) {
                text = text.replace('↓', '→');
                label.setText(text);

                return false;
            } else {
                text = text.replace('→', '↓');
                label.setText(text);
            }

            return true;
        }
    }

    /**
     * 自定义对话框
     */
    class CustomDialog extends JDialog {

        /**
         * 股票列表文件路径
         */
        private final String FILE_NAME = "src/main/resources/bank_stock.txt";

        /**
         * 主面板
         */
        private JPanel contentPane;

        /**
         * 确认按钮
         */
        private JButton btnOK;

        /**
         * 应用按钮
         */
        private JButton btnApply;

        /**
         * 取消按钮
         */
        private JButton btnCancel;

        /**
         * 表格对象
         */
        private Table table;

        /**
         * 股票列表
         */
        private List<StockID> stockList;

        private CustomDialog() {
            super(MainFrame.getMainFrame(), "自定义股票列表", true);

            init();
            createUIComponents();
            addListeners();
        }

        /**
         * 初始化
         */
        private void init() {
            setSize(new Dimension(250, 400));
            setResizable(false);
            setLocationRelativeTo(MainFrame.getMainFrame());
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            setModal(true);
        }

        /**
         * 创建组件
         */
        private void createUIComponents() {
            contentPane = new JPanel();
            contentPane.setLayout(new BorderLayout());

            setContentPane(contentPane);

            addStockList();
            addButtonPanel();
        }

        /**
         * 添加股票列表
         */
        private void addStockList() {
            String[] columnNames = new String[]{"名称", "代码", "显示"};
            table = new Table(loadStockList(), columnNames);
            JScrollPane scrollPane = new JScrollPane(table);

            scrollPane.setBounds(0, 0, getWidth(), getHeight() - 45);
            contentPane.add(scrollPane, BorderLayout.CENTER);
        }

        /**
         * 添加按钮面板
         */
        private void addButtonPanel() {
            btnOK = new JButton("确 认");
            btnApply = new JButton("应 用");
            btnCancel = new JButton("取 消");

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.setBackground(Color.lightGray);
            buttonPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
            buttonPanel.setBounds(0, getHeight() - 45,
                    getWidth(), 45);

            contentPane.add(buttonPanel, BorderLayout.SOUTH);

            buttonPanel.add(btnOK);
            buttonPanel.add(btnApply);
            buttonPanel.add(btnCancel);

            getRootPane().setDefaultButton(btnOK);
        }

        /**
         * 添加时间监听器
         */
        private void addListeners() {
            btnOK.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onOK();
                }
            });

            btnApply.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onApply();
                }
            });

            btnCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onCancel();
                }
            });

            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    onCancel();
                }
            });

            contentPane.registerKeyboardAction(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            onCancel();
                        }
                    },
                    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                    JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    changeDisplay();
                }
            });

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    MainFrame.getMainFrame().repaint();
                }
            });
        }

        /**
         * 确认操作
         */
        private void onOK() {
            onApply();
            dispose();
        }

        /**
         * 应用操作
         * 存储更改，并刷新界面
         */
        private void onApply() {
            storeStockList();
            listPanel.updateData();
        }

        /**
         * 取消操作
         */
        private void onCancel() {
            dispose();
        }

        /**
         * 单击表格中的某只股票时,改变其"显示"状态
         */
        private void changeDisplay() {
            int lineNum = table.getSelectedRow();

            String name = (String) table.getValueAt(lineNum, 0);
            boolean display = (boolean) table.getValueAt(lineNum, 2);

            table.setValueAt(!display, lineNum, 2);

            for (StockID temp : stockList) {
                if (temp.getName().equals(name)) {
                    temp.exchangeDisplay();
                }
            }

            repaint();
        }

        /**
         * 加载股票列表
         *
         * @return 股票列表
         */
        private Object[][] loadStockList() {
            stockList = new ArrayList<>();
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream(FILE_NAME)));

                String line;
                while ((line = reader.readLine()) != null) {
                    stockList.add(new StockID(line));
                }

                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Object[][] data = new Object[stockList.size()][];

            for (int i = 0; i < stockList.size(); i++) {
                data[i] = new Object[]{
                        stockList.get(i).getName(),
                        stockList.get(i).getId(),
                        stockList.get(i).isDisplay()
                };
            }

            return data;
        }

        /**
         * 存储股票列表
         */
        private void storeStockList() {
            try {
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(FILE_NAME, false)));

                for (int i = 0; i < stockList.size(); i++) {
                    writer.write(stockList.get(i).toString());
                }

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
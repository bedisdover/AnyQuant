package presentation.panel.operation;

import presentation.util.DateChooser;
import presentation.util.Table;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
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
    private JButton btnSearch;

    /**
     * 日期选择框
     * 通过日期选择框改变日期查看其它日期的数据
     */
    private DateChooser dateChooser;

    /**
     * 榜单面板
     */
    private ListPanel listPanel;

    /**
     * 获取table
     */
    private Table table;

    private JPopupMenu popupMenu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;

    public PicturePanel() {
        init();
        createUIComponents();
        addListeners();
    }

    protected void init() {
        setLayout(null);
        popupMenu1 = new JPopupMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();
        menuItem4 = new JMenuItem();
        menuItem1.setText("显示详细信息");
        menuItem2.setText("显示增幅跌幅");
        menuItem3.setText("添加关注");
        menuItem4.setText("范围查询");
        popupMenu1.add(menuItem1);
        popupMenu1.add(menuItem2);
        popupMenu1.add(menuItem3);
        popupMenu1.add(menuItem4);
    }

    protected void createUIComponents() {
        dateChooser = new DateChooser(this, MARGIN, MARGIN, BUTTON_WIDTH + PADDING, BUTTON_HEIGHT);
        btnSearch = new JButton("搜索");
        searchInput = new JTextField();

        listPanel = new ListPanel();

        add(listPanel);
        add(btnSearch);
    }

    private void addListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                btnSearch.setBounds(PANEL_WIDTH - MARGIN - BUTTON_WIDTH,
                        MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT);
                searchInput.setBounds(btnSearch.getX() - TEXT_FIELD_WIDTH,
                        MARGIN, TEXT_FIELD_WIDTH, BUTTON_HEIGHT);
                listPanel.setBounds(0, MARGIN + BUTTON_HEIGHT + PADDING / 2,
                        PANEL_WIDTH, PANEL_HEIGHT - listPanel.getY());
            }
        });

        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!searchInput.getText().equals("")) {
                    int k = table.searchStock(searchInput.getText());
                    if (k > 0) {
                        System.out.println(table.getRowCount());
                        table.setRowSelectionInterval(k, k);
                        System.out.println(k);
                    }
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
                    int k = table.searchStock(searchInput.getText());
                    if (k > 0) {
                        table.setRowSelectionInterval(k, k);
                    }
                }
            }
        });

        searchInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int k = table.searchStock(searchInput.getText());
                    if (k > 0) {
                        table.setRowSelectionInterval(k, k);
                    }
                }
            }
        });
    }

//    /**
//     * 显示"沪市"的股票列表
//     */
//    private void displayBlock_SH() {
//        if (scrollPane != null) {
//            remove(scrollPane);
//        }
//
//        repaint();
//
//        table = createTable(new ShowStockData().getLatestStockData());
//
//        addTableListener();
//    }
//
//    /**
//     * 显示"深市"的股票列表
//     */
//    private void displayBlock_SZ() {
//        if (scrollPane != null) {
//            remove(scrollPane);
//        }
//
//        repaint();
//
//        table = createTable(new ShowStockData().getLatestStockData_sz());
//
//        addTableListener();
//    }
//
//    private void addTableListener() {
//        class RightClickListener extends MouseAdapter {
//        }
//        /**
//         * todo 给table添加鼠标右键监听
//         */
//        table.addMouseListener(new RightClickListener() {
//            public void mousePressed(MouseEvent e) {
//                JTable table = (JTable) e.getSource();
//                if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
//                    Point p = e.getPoint();
//                    int row = table.rowAtPoint(p);
//                    int column = table.columnAtPoint(p);
//                    if (!e.isControlDown() & !e.isShiftDown() & row != -1 & column != -1) {
//                        table.changeSelection(row, column, e.isControlDown(), e.isShiftDown());
//                    }
//                }
//            }
//
//            public void mouseReleased(MouseEvent e) {
//                if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
//                    System.out.println(e.getX() + "  " + e.getY());
//                    showMenuList(e.getX() + MARGIN, e.getY() + MARGIN + PADDING * 2 + 29);
//                }
//            }
//        });
//
//        /**
//         * todo 给JMenuItem添加事件监听
//         */
//        //显示详细信息
//        menuItem1.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent arg0) {
//                showDetailedInfo();
//            }
//        });
//        menuItem2.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent arg0) {
//
//            }
//        });
//        //添加关注
//        menuItem3.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent arg0) {
//                int line = table.getSelectedRow();
//                if (line != -1) {
//                    try {
//                        boolean exist = new SelfSelectStock().addStock((String) table.getValueAt(line, 2));
//                        if (exist) {
//                            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "添加关注成功!");
//                        } else {
//                            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "提示",
//                                    "您添加的股票已在自选列表中", JOptionPane.WARNING_MESSAGE);
//                        }
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    }
//                }
//            }
//        });
//        menuItem4.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent arg0) {
//
//            }
//        });
//    }
//
//    private void showMenuList(int x, int y) {
//        popupMenu1.show(this, x, y);
//    }
//
//    private void showDetailedInfo() {
//        String name = (String) table.getValueAt(table.getSelectedRow(), 2);
//        StockPO stock = new GetStockData().getStockData_name(name);
//
//        MainFrame.getMainFrame().addOperationPanel(new StockInfoPanel(this, stock));
//    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }

    /**
     * 各种榜单面板
     * 位于行情面板中央位置
     */
    class ListPanel extends JScrollPane {

        /**
         * 涨幅榜,跌幅榜,成交额榜,换手率榜 对应的label
         */
        private JLabel labelIncrease, labelDecrease, labelTurnVolume, labelTurnOverRate;

        /**
         * label的宽度
         */
        private final int LABEL_WIDTH = BUTTON_WIDTH + PADDING;

        /**
         * 涨幅榜,跌幅榜,成交额榜,换手率榜
         */
        private JScrollPane scrollIncrease, scrollDecrease, scrollTurnVolume, scrollTurnOverRate;

        /**
         * 单个榜单的高度
         */
        private final int SCROLL_HEIGHT = 200;

        /**
         * 榜单状态Map
         * 记录 涨幅榜,跌幅榜,成交额榜,换手率榜 是否处于展开状态
         * 若处于展开状态, 对应值为true, 否则为false
         */
        private Map<JScrollPane, Boolean> expand;

        public ListPanel() {
            super();

            init();
            createUIComponents();
            addListeners();
        }

        /**
         * 初始化
         */
        protected void init() {
            setLayout(null);
//            setBorder(new BevelBorder(BevelBorder.LOWERED));
            setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
            setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
        }

        /**
         * 创建组件
         */
        protected void createUIComponents() {
            labelIncrease = new JLabel("↓  涨幅榜");
            labelDecrease = new JLabel("↓  跌幅榜");
            labelTurnVolume = new JLabel("↓  成交量榜");
            labelTurnOverRate = new JLabel("↓  转手率榜");

            scrollIncrease = new JScrollPane(null,
                    VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollDecrease = new JScrollPane(null,
                    VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollTurnVolume = new JScrollPane(null,
                    VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollTurnOverRate = new JScrollPane(null,
                    VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollIncrease.setBorder(new BevelBorder(BevelBorder.LOWERED));
            scrollDecrease.setBorder(new BevelBorder(BevelBorder.LOWERED));
            scrollTurnVolume.setBorder(new BevelBorder(BevelBorder.LOWERED));
            scrollTurnOverRate.setBorder(new BevelBorder(BevelBorder.LOWERED));

            add(labelIncrease);
            add(labelDecrease);
            add(labelTurnVolume);
            add(labelTurnOverRate);
            add(scrollIncrease);
            add(scrollDecrease);
            add(scrollTurnVolume);
            add(scrollTurnOverRate);

            expand = new HashMap<>();
            expand.put(scrollIncrease, true);
            expand.put(scrollDecrease, true);
            expand.put(scrollTurnVolume, true);
            expand.put(scrollTurnOverRate, true);
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

            addContainerListener(new ContainerAdapter() {
                @Override
                public void componentAdded(ContainerEvent e) {

                }

                @Override
                public void componentRemoved(ContainerEvent e) {

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

            labelTurnOverRate.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    turnOverRateOperation();
                    assignment();
                }
            });
        }

        /**
         * 界面大小发生变化时,对所有组件的位置进行重新赋值
         */
        private void assignment() {
            {
                labelIncrease.setBounds(MARGIN, PADDING / 2, LABEL_WIDTH, BUTTON_HEIGHT);
                scrollIncrease.setBounds(MARGIN, labelIncrease.getY() + BUTTON_HEIGHT,
                        PANEL_WIDTH - MARGIN * 2, SCROLL_HEIGHT);
            }

            {
                if (expand.get(scrollIncrease)) {
                    labelDecrease.setBounds(MARGIN, scrollIncrease.getY() + scrollIncrease.getHeight(),
                            LABEL_WIDTH, BUTTON_HEIGHT);
                } else {
                    labelDecrease.setBounds(MARGIN, labelIncrease.getY() + BUTTON_HEIGHT,
                            LABEL_WIDTH, BUTTON_HEIGHT);
                }
                scrollDecrease.setBounds(MARGIN, labelDecrease.getY() + BUTTON_HEIGHT,
                        PANEL_WIDTH - MARGIN * 2, SCROLL_HEIGHT);
            }

            {
                if (expand.get(scrollDecrease)) {
                    labelTurnVolume.setBounds(MARGIN, scrollDecrease.getY() + scrollDecrease.getHeight(),
                            LABEL_WIDTH, BUTTON_HEIGHT);
                } else {
                    labelTurnVolume.setBounds(MARGIN, labelDecrease.getY() + BUTTON_HEIGHT,
                            LABEL_WIDTH, BUTTON_HEIGHT);
                }
                scrollTurnVolume.setBounds(MARGIN, labelTurnVolume.getY() + BUTTON_HEIGHT,
                        PANEL_WIDTH - MARGIN * 2, SCROLL_HEIGHT);
            }

            {
                if (expand.get(scrollTurnVolume)) {
                    labelTurnOverRate.setBounds(MARGIN,
                            scrollTurnVolume.getY() + scrollTurnVolume.getHeight(),
                            LABEL_WIDTH, BUTTON_HEIGHT);
                } else {
                    labelTurnOverRate.setBounds(MARGIN, labelTurnVolume.getY() + BUTTON_HEIGHT,
                            LABEL_WIDTH, BUTTON_HEIGHT);
                }
                if (expand.get(scrollTurnOverRate)) {
                    scrollTurnOverRate.setBounds(MARGIN, labelTurnOverRate.getY() + BUTTON_HEIGHT,
                            PANEL_WIDTH - MARGIN * 2, SCROLL_HEIGHT);
                }
            }

            repaint();
        }

        /**
         * '涨幅榜'单击后的操作
         */
        private void increaseOperation() {
            if (changeText(labelIncrease)) {
                add(scrollIncrease);
                expand.put(scrollIncrease, true);
            } else {
                remove(scrollIncrease);
                expand.put(scrollIncrease, false);
            }
        }

        /**
         * '跌幅榜'单击后的操作
         */
        private void decreaseOperation() {
            if (changeText(labelDecrease)) {
                add(scrollDecrease);
                expand.put(scrollDecrease, true);
            } else {
                remove(scrollDecrease);
                expand.put(scrollDecrease, false);
            }
        }

        /**
         * '成交量榜'单击后的操作
         */
        private void turnVolumeOperation() {
            if (changeText(labelTurnVolume)) {
                add(scrollTurnVolume);
                expand.put(scrollTurnVolume, true);
            } else {
                remove(scrollTurnVolume);
                expand.put(scrollTurnVolume, false);
            }
        }

        /**
         * '转手率榜'单击后的操作
         */
        private void turnOverRateOperation() {
            if (changeText(labelTurnOverRate)) {
                add(scrollTurnOverRate);
                expand.put(scrollTurnOverRate, true);
            } else {
                remove(scrollTurnOverRate);
                expand.put(scrollTurnOverRate, false);
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

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D) g;

            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }
    }

}
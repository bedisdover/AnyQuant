package presentation.panel.operation;

import presentation.util.DateChooser;
import presentation.util.Table;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;

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
                btnSearch.setBounds(WIDTH - MARGIN - BUTTON_WIDTH, MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT);
                searchInput.setBounds(btnSearch.getX() - TEXT_FIELD_WIDTH, MARGIN, TEXT_FIELD_WIDTH, BUTTON_HEIGHT);
                listPanel.setBounds(0, MARGIN + BUTTON_HEIGHT + PADDING, WIDTH, HEIGHT - listPanel.getY());
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
}

/**
 * 各种榜单面板
 * 位于行情面板中央位置
 */
class ListPanel extends OperationPanel {

    /**
     * 日期选择框
     * 通过日期选择框改变日期查看其它日期的数据
     */
    private DateChooser dateChooser;

    /**
     * 涨幅榜,跌幅榜,成交额榜,换手率榜
     */
    private JLabel labelIncrease, labelDecrease, labelTurnVolume, labelTurnOverRate;

    private JPanel pNorth, pSouth, subMenuContainer;
    private JScrollPane pCenter;

    private JButton[] btn = null;
    private static boolean expand = false;

    public ListPanel() {
        init();
        createUIComponents();
        addListeners();
    }

    /**
     * 初始化
     */
    protected void init() {
        setLayout(new BorderLayout());
        setBorder(new BevelBorder(BevelBorder.LOWERED));
    }

    /**
     * 创建组件
     */
    protected void createUIComponents() {
        labelIncrease = new JLabel("涨幅榜");
        labelDecrease = new JLabel("跌幅榜");
        labelTurnVolume = new JLabel("成交量榜");
        labelTurnOverRate = new JLabel("转手率榜");

        pNorth = new JPanel();
        pNorth.setLayout(new GridLayout(1, 1));
        pNorth.add(labelIncrease);

        pSouth = new JPanel();
        pSouth.setLayout(new GridLayout(3, 1));
        pSouth.add(labelDecrease);
        pSouth.add(labelTurnVolume);
        pSouth.add(labelTurnOverRate);

        subMenuContainer = new JPanel();
        subMenuContainer.setLayout(new GridLayout(1, 1));


        pCenter = new JScrollPane(subMenuContainer);

        add(pNorth, "North");
        add(pCenter, "Center");
        add(pSouth, "South");

    }

    private class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }

    }

    /**
     * 添加事件监听器
     */
    private void addListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repaint();
            }
        });


        labelIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (expand) {//折叠
                    pNorth.setLayout(new GridLayout(2, 1));
                    pNorth.remove(labelDecrease);
                    pSouth.add(labelDecrease);
                    validate();
                    repaint();
                    expand = false;
                } else {//展开
                    pSouth.removeAll();
                    pNorth.setLayout(new GridLayout(5, 1));
                    pNorth.add(labelDecrease);
                    pNorth.repaint();
                    pCenter.repaint();
                    pSouth.repaint();
                    validate();
                    repaint();
                    expand = true;
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
    }
}

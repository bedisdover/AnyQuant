package presentation.panel.operation;

import bl.SelfSelectStock;
import bl.ShowStockData;
import data.GetStockData;
import po.StockPO;
import presentation.frame.MainFrame;
import presentation.panel.info.StockInfoPanel;
import presentation.util.DateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * Created by song on 16-3-2.
 * <p>
 * 行情面板
 */
public class PicturePanel extends OperationPanel {

    /**
     * "上证"按钮
     */
    private JButton btnSh;

    /**
     * "深证"按钮
     */
    private JButton btnSz;

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
     * 获取table
     */
    private TableCopy table;

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
        btnSh = new JButton("沪市");
        btnSz = new JButton("深市");
//        dateChooser = new DateChooser(this, WIDTH / 2, MARGIN, BUTTON_WIDTH + PADDING, BUTTON_HEIGHT);
        btnSearch = new JButton("搜索");
        searchInput = new JTextField();

        add(btnSh);
        add(btnSz);
//        add(dateChooser);
        add(btnSearch);
    }

    private void addListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                btnSh.setBounds(MARGIN, MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT);
                btnSz.setBounds(MARGIN + BUTTON_WIDTH, MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT);
                btnSearch.setBounds(WIDTH - MARGIN - BUTTON_WIDTH, MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT);
                searchInput.setBounds(btnSearch.getX() - TEXT_FIELD_WIDTH, MARGIN, TEXT_FIELD_WIDTH, BUTTON_HEIGHT);
            }
        });

        btnSh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                displayBlock_SH();
            }
        });

        btnSz.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                displayBlock_SZ();
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
                super.focusGained(e);
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

    /**
     * 显示"上证"的股票列表
     */
    private void displayBlock_SH() {
        if (scrollPane != null) {
            remove(scrollPane);
        }
        repaint();
        table = createTable(new ShowStockData().getLatestStockData());

        addTableListener();
    }

    /**
     * 显示"深证"的股票列表
     */
    private void displayBlock_SZ() {
        if (scrollPane != null) {
            remove(scrollPane);
        }
        repaint();
        table = createTable(new ShowStockData().getLatestStockData_sz());
        addTableListener();
    }

    private void addTableListener(){
        class RightClickListener extends MouseAdapter{}
        /**
         * todo 给table添加鼠标右键监听
         */
        table.addMouseListener(new RightClickListener(){
            public void mousePressed(MouseEvent e){
                JTable table = (JTable) e.getSource();
                if((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
                    Point p = e.getPoint();
                    int row = table.rowAtPoint(p);
                    int column = table.columnAtPoint(p);
                    if (!e.isControlDown() & !e.isShiftDown() & row != -1 & column != -1) {
                        table.changeSelection(row, column, e.isControlDown(), e.isShiftDown());
                    }
                }
            }
            public void mouseReleased(MouseEvent e) {
                if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
                    System.out.println(e.getX()+"  "+e.getY());
                    showMenuList(e.getX()+MARGIN, e.getY()+MARGIN + PADDING * 2+29);
                }
            }
        });

        /**
         * todo 给JMenuItem添加事件监听
         */
        //显示详细信息
        menuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                showDetailedinfo();
            }
        });
        menuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

            }
        });
        //添加关注
        menuItem3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int line = table.getSelectedRow();
                if (line != -1) {
                    try {
                        new SelfSelectStock().addStock((String) table.getValueAt(line, 2));
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "添加关注成功!");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        menuItem4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

            }
        });
    }

    private void showMenuList(int x,int y){
        popupMenu1.show(this,x,y);
    }

    private void showDetailedinfo(){
        String name = (String) table.getValueAt(table.getSelectedRow(), 2);
        StockPO stock = new GetStockData().getStockData_name(name);

        MainFrame.getMainFrame().addOperationPanel(new StockInfoPanel(this, stock));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}

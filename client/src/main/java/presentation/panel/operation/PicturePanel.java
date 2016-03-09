package presentation.panel.operation;

import bl.ShowStockData;
import presentation.util.DateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    public PicturePanel() {
        init();
        createUIComponents();
        addListeners();
    }

    protected void init() {
        setLayout(null);
    }

    protected void createUIComponents() {
        btnSh = new JButton("上证");
        btnSz = new JButton("深证");
        dateChooser = new DateChooser(this, WIDTH / 2, MARGIN, BUTTON_WIDTH + PADDING, BUTTON_HEIGHT);
        btnSearch = new JButton("搜索");
        searchInput = new JTextField();

        btnSh.setBounds(MARGIN, MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT);
        btnSz.setBounds(MARGIN + BUTTON_WIDTH, MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT);
        btnSearch.setBounds(WIDTH - MARGIN - BUTTON_WIDTH, MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT);
        searchInput.setBounds(btnSearch.getX() - TEXT_FIELD_WIDTH, MARGIN, TEXT_FIELD_WIDTH, BUTTON_HEIGHT);

        add(btnSh);
        add(btnSz);
        add(dateChooser);
        add(btnSearch);
    }

    private void addListeners() {
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
        createTable(new ShowStockData().getLatestStockData());
    }

    /**
     * 显示"深证"的股票列表
     */
    private void displayBlock_SZ() {
        createTable(new ShowStockData().getLatestStockData_sz());
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}

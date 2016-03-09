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
     * 搜索框,用于输入股票名称或代码
     * 无搜索任务时自动隐藏
     */
    private JTextField searchInput;

    /**
     * 搜索按钮
     */
    private JButton search;

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
        dateChooser = new DateChooser(this, MARGIN, MARGIN, BUTTON_WIDTH + PADDING, BUTTON_HEIGHT);
        search = new JButton("搜索");
        searchInput = new JTextField();

        search.setBounds(WIDTH - MARGIN - BUTTON_WIDTH, MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT);
        searchInput.setBounds(search.getX() - TEXT_FIELD_WIDTH, MARGIN, TEXT_FIELD_WIDTH, BUTTON_HEIGHT);

        add(dateChooser);
        add(search);

        createTable(new ShowStockData().getLatestStockData());
    }

    private void addListeners() {
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!searchInput.getText().equals("")) {
                    int k = table.searchStock(searchInput.getText());
                    if(k>0){
                        System.out.println(table.getRowCount());
                        table.setRowSelectionInterval(k,k);
                        System.out.println(k);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (searchInput.getText().equals("")){
                    searchInput.setText("输入股票名称或代码");
                }
                add(searchInput);
                repaint();

                //显示搜索框后,若点击除搜索框和搜索按钮以外的其他区域,
                //自动隐藏搜索框
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getSource() != search && e.getSource() != searchInput) {
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

        search.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int k = table.searchStock(searchInput.getText());
                    if(k>0){
                        table.setRowSelectionInterval(k,k);
                    }
                }
            }
        });

        searchInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int k = table.searchStock(searchInput.getText());
                    if(k>0){
                        table.setRowSelectionInterval(k,k);
                    }
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}

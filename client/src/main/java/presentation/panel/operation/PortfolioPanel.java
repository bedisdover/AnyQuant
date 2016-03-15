package presentation.panel.operation;

import bl.HistoryRecordStock;
import bl.SelfSelectStock;
import data.GetStockData;
import po.StockPO;
import presentation.frame.MainFrame;
import presentation.panel.info.StockInfoPanel;
import presentation.util.Table;
import vo.StockVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 宋益明 on 16-3-2.
 * <p>
 * 自选面板
 */
public class PortfolioPanel extends OperationPanel {

    /**
     * 取消关注按钮
     */
    private JButton cancel;

    private Table table;
    private JPopupMenu popupMenu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;

    public PortfolioPanel() {
        init();
        createUIComponents();
        addListeners();
    }

    protected void init() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setLayout(null);
        popupMenu1 = new JPopupMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();
        menuItem4 = new JMenuItem();
        menuItem1.setText("显示详细信息");
        menuItem2.setText("显示增幅跌幅");
        menuItem3.setText("取消关注");
        menuItem4.setText("范围查询");
        popupMenu1.add(menuItem1);
        popupMenu1.add(menuItem2);
        popupMenu1.add(menuItem3);
        popupMenu1.add(menuItem4);
    }

    protected void createUIComponents() {
        cancel = new JButton("取消关注");

        Iterator<String> stockID = new SelfSelectStock().getFollowed();
        List<StockVO> list = new ArrayList<>();
        GetStockData getStockData = new GetStockData();
        while (stockID.hasNext()) {
            String string = stockID.next();
            list.add(new StockVO(getStockData.getStockData_name(string)));
        }

        //若关注列表为空,不显示"取消关注"按钮
        if (list.size() != 0) {
            add(cancel);
        }
        table = createTable(list);
    }

    protected void showMenuList(int x, int y) {
        popupMenu1.show(this, x, y);
    }

    private void showDetailedInfo() {
        String name = (String) table.getValueAt(table.getSelectedRow(), 2);
        StockPO stock = new GetStockData().getStockData_name(name);

        MainFrame.getMainFrame().addOperationPanel(new StockInfoPanel(this, stock));
    }

    private void addListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                cancel.setBounds(WIDTH - MARGIN * 2 - BUTTON_WIDTH, MARGIN, BUTTON_WIDTH + MARGIN, BUTTON_HEIGHT);
            }
        });

        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int line = table.getSelectedRow();
                if (line != -1) {
                    try {
                        new SelfSelectStock().removeStock((String) table.getValueAt(line, 2));
                        new HistoryRecordStock().addStock((String) table.getValueAt(line, 2));
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "取消关注成功!可在历史记录中查看");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    MainFrame.getMainFrame().addOperationPanel(new PortfolioPanel());
                }
            }
        });

        class RightClickListener extends MouseAdapter {
        }
        /**
         * todo 给table添加鼠标右键监听
         */
        table.addMouseListener(new RightClickListener() {
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
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
                    showMenuList(e.getX() + MARGIN, e.getY() + MARGIN + PADDING * 2 + 29);
                }
            }
        });


        /**
         * todo 给JMenuItem添加事件监听
         */
        //显示详细信息
        menuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                showDetailedInfo();
            }
        });
        menuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

            }
        });
        //取消关注
        menuItem3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int line = table.getSelectedRow();
                if (line != -1) {
                    try {
                        new SelfSelectStock().removeStock((String) table.getValueAt(line, 2));
                        new HistoryRecordStock().addStock((String) table.getValueAt(line, 2));
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "取消关注成功!可在历史记录中查看");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    MainFrame.getMainFrame().addOperationPanel(new PortfolioPanel());
                }
            }
        });
        menuItem4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics2D = (Graphics2D) g;
//        graphics2D.drawImage(ImageLoader.nothing, 0, 0, getWidth() * 4 / 5, getHeight(), null);

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}

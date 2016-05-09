package presentation.util;

import data.GetStockData;
import po.StockPO;
import presentation.frame.MainFrame;
import presentation.panel.info.DetailedInfoPanel;
import presentation.panel.info.StockInfoPanel;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static java.awt.event.InputEvent.BUTTON3_MASK;

/**
 * Created by zmj on 2016/3/6.
 * <p>
 * 列表测试类
 */
public class Table extends JTable {

    private Object[][] data;

    private String[] columnNames;

    /**
     * table所在面板
     */
    private JPanel parent;

    public Table(Object[][] data, String[] columnNames) {
        this(null, data, columnNames);
    }

    public Table(JPanel parent, Object[][] data, String[] columnNames) {
        super(data, columnNames);

        this.data = data;
        this.columnNames = columnNames;
        this.parent = parent;

        init();
        addListeners();
    }

    private void init() {
        // TODO 行宽
        setRowHeight(30);
        setSelectionBackground(new Color(88, 93, 103, 200));
        setSelectionForeground(new Color(255, 255, 255, 230));
        //无法修改表头大小
        getTableHeader().setResizingAllowed(false);
        //无法拖动表头
        getTableHeader().setReorderingAllowed(false);
        //取消自动调整大小
        //设置每列的宽度
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        {
            //设置table表头居中
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(SwingConstants.CENTER);
            getTableHeader().setDefaultRenderer(tcr);
            //设置table内容居中
            setDefaultRenderer(Object.class, tcr);
        }
        //设置table内容不可编辑
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            public Class<?> getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        setModel(model);
        //设置表头排序方式
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        setRowSorter(sorter);
    }

    /**
     * 添加监听事件
     * 双击跳转到详细信息界面
     */
    private void addListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    StockPO stock = null;
                    try {
                        String name = (String) getValueAt(getSelectedRow(), 2);
                        stock = new GetStockData().getStockData_name(name);
                    } catch (ClassCastException e1) {
                        //若某表格第2项不是字符串,捕获此异常
                        //此时无需显示详细信息
                        return;
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(Table.this, "请检查网络连接！");
                    }

                    if (parent != null) {
//                        MainFrame.getMainFrame().addOperationPanel(new StockInfoPanel(parent, stock));
                        MainFrame.getMainFrame().addOperationPanel(
                                new DetailedInfoPanel(parent, (String) getValueAt(getSelectedRow(), 2)));
                    }
                }
            }
        });
    }

    /**
     * 用JScrollPane装载表格
     * 装载表格后显示所有信息,故添加监听事件,可通过双击访问详细信息;
     * 详细面板中的表格无需表头,所以无需装载,同样不需要添加监听事件
     *
     * @return 装载table的JScrollPane
     */
    public JScrollPane drawTable() {
        JScrollPane scroll = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        addListeners();
        return scroll;
    }

    /**
     * 根据名称搜索股票,高亮搜索结果
     *
     * @param name 股票名称
     * @return 搜索成功返回true, 否则返回false
     */
    public void searchStock(String name) {
        for (int i = 0; i < data.length; i++) {
            if (name.equals(data[i][2])||name.equals(data[i][1])) {
                this.setRowSelectionInterval(i, i);
                return;
            }
        }
        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "无记录,请确认输入是否正确");
    }

    public int getRowCount() {
        return data.length;
    }
}

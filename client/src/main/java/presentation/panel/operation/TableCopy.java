package presentation.panel.operation;

import data.GetStockData;
import po.StockPO;
import presentation.frame.MainFrame;
import presentation.panel.info.StockInfoPanel;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by pc on 2016/3/6.
 * <p>
 * 列表测试类
 */
public class TableCopy extends JTable {

    private Object[][] data;

    private String[] columnNames;

    /**
     * table所在面板
     */
    private JPanel parent;

    /**
     * 表中最后一行
     */
    private int currentRow = 0;

    public TableCopy(Object[][] data, String[] columnNames) {
        this(null, data, columnNames);
    }

    public TableCopy(JPanel parent, Object[][] data, String[] columnNames) {
        super(data, columnNames);

        this.data = data;
        this.columnNames = columnNames;
        this.parent = parent;

        init();
    }

    private void init() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        //表头字体
        Font font = new Font("微软雅黑", Font.PLAIN, 16);
        getTableHeader().setFont(font);
        //行宽
        setRowHeight(30);
        //无法修改表头大小
        getTableHeader().setResizingAllowed(false);
        //无法拖动表头
        getTableHeader().setReorderingAllowed(false);
        //取消自动调整大小
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //设置table内容居中
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        setDefaultRenderer(Object.class, tcr);
        //设置table内容不可编辑
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        setModel(model);
    }

    /**
     * 添加监听事件
     * 双击跳转到详细信息界面
     */
    private void addListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String name = (String) getValueAt(getSelectedRow(), 1);
                    StockPO stock = new GetStockData().getStockData_name(name);

                    MainFrame.getMainFrame().addOperationPanel(new StockInfoPanel(parent, stock));
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
        JScrollPane scroll = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        addListeners();
        return scroll;
    }

    public void setValueAt(int r, int c, String value) {
        data[r][c] = value;
    }

    public void setValueAt(int row, String[] values) {
        System.arraycopy(values, 0, data[row], 1, values.length);

        if (row != getSelectedRow()) {
            currentRow++;
        }

        this.identify();
    }

//    public String getValueAt(int r, int c) {
//        return (String) data[r][c];
//    }

    public int numOfEmpty() {
        int count = 0;
        while (data[count][0] != null) {
            count++;
        }
        return count;
    }


    /**
     * 判断表中是否已存在指定内容（data）
     *
     * @param column 列数（参数中从1开始计数）
     * @param data   指定内容
     * @return 返回指定内容所在行数，若不存在，返回-1
     */
    public int alreadyExisted(int column, String data) {
        for (int i = 0; i < currentRow; i++) {
            if (this.data[i][column - 1].toString().equals(data)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 给表格中的项编号
     */
    private void identify() {
        for (int i = 0; i < currentRow; i++) {
            data[i][0] = i + 1;
        }
    }

    /**
     * 清空表
     */
    public void clean() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = null;
            }
        }
    }


    /**
     * 根据名称搜索股票,高亮搜索结果
     *
     * @param name 股票名称
     * @return 搜索成功返回true, 否则返回false
     */
    protected int searchStock(String name) {
        System.out.println(this.numOfEmpty());
        for (int i = 0; i < this.numOfEmpty(); i++) {
            if (name.equals(data[i][1])) {
                this.setRowSelectionInterval(i, i);
                return i;
            }
        }
        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "无记录,请确认输入是否正确");
        return -1;
    }

    public int getRowCount() {
        return data.length;
    }

}

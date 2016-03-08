package presentation.panel.operation;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by pc on 2016/3/6.
 * <p>
 * 列表测试类
 */
public class TableCopy extends JTable {

    private Object[][] data;

    private String[] columnNames;

    /**
     * 表的列数
     */
    private int columnNum = 0;

    /**
     * 总行数
     */
    private int rowNum = 0;

    /**
     * 表中最后一行
     */
    private int currentRow = 0;

    public TableCopy(Object[][] data, String[] columnNames) {
        this.data = data;
        this.columnNames = columnNames;

        init();
    }

    private void init() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
    }

    public JScrollPane drawTable() {
        JTable table = new JTable(data, columnNames);

        Font font = new Font("Courier", Font.PLAIN, 16);
        table.getTableHeader().setFont(font);
        table.setRowHeight(30);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        /**
         * 设置table内容居中
         */
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
        table.setDefaultRenderer(Object.class, tcr);

        JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
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

    public String getValueAt(int r, int c) {
        return (String) data[r][c];
    }

    public int getSelectedRow() {
        return getSelectedRow();
    }

    public int numOfEmpty() {
        int count = 0;
        while (data[count][0] != null) {
            count++;
        }
        return count;
    }

    public ArrayList<String> getValueAt(int r) {
        ArrayList<String> al = new ArrayList<String>();
        for (int i = 0; i < columnNum; i++) {
            if (data[r][i] == null) {
                return null;
            }
            al.add(data[r][i].toString());
        }
        return al;
    }

    public int columnNum() {
        return columnNum;
    }

    // 删除某一行
    public void remove(int r) {
        for (int i = r; i < rowNum - 1; i++) {
            for (int j = 0; j < columnNum; j++) {
                setValueAt(i, j, data[i + 1][j].toString());
            }
        }
        for (int k = 0; k < columnNum; k++) {
            setValueAt(rowNum - 1, k, "");
        }
    }

    public void removeLine(int row) {
        for (int i = row; i < numOfEmpty(); i++) {
            for (int j = 0; j < columnNum; j++) {
                data[i][j] = data[i + 1][j];
            }
        }

        currentRow--;

        this.identify();
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
}

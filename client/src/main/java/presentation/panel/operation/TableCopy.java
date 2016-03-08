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

    private Object[][] rowData;

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

    public TableCopy() {
        init();
    }

    private void init() {
        setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
    }

//    public UltraScrollPane drawTable(String[] columnNames, )

    public JScrollPane drawTable(String[] name, int[] list) {
        /**
         * list里面参数分别为需要的行数，每一列的宽度,设置第一行行宽
         *
         * 调用的时候在你的panel构造函数里 Table table=new Table(); add(table.drawTable(name,
         * list));
         */
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        columnNum = name.length;
        rowNum = list[0];
        rowData = new Object[rowNum][columnNum];
        JTable table = new JTable(rowData, name);

        Font font = new Font("Courier", Font.PLAIN, 16);
        table.getTableHeader().setFont(font);
        table.setRowHeight(30);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setDraggedDistance(0);
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
        rowData[r][c] = value;
    }

    public void setValueAt(int row, String[] values) {
        System.arraycopy(values, 0, rowData[row], 1, values.length);

        if (row != getSelectedRow()) {
            currentRow++;
        }

        this.identify();
    }

    public String getValueAt(int r, int c) {
        return (String) rowData[r][c];
    }

    public int getSelectedRow() {
        return getSelectedRow();
    }

    public int numOfEmpty() {
        int count = 0;
        while (rowData[count][0] != null) {
            count++;
        }
        return count;
    }

    public ArrayList<String> getValueAt(int r) {
        ArrayList<String> al = new ArrayList<String>();
        for (int i = 0; i < columnNum; i++) {
            if (rowData[r][i] == null) {
                return null;
            }
            al.add(rowData[r][i].toString());
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
                setValueAt(i, j, rowData[i + 1][j].toString());
            }
        }
        for (int k = 0; k < columnNum; k++) {
            setValueAt(rowNum - 1, k, "");
        }
    }

    public void removeLine(int row) {
        for (int i = row; i < numOfEmpty(); i++) {
            for (int j = 0; j < columnNum; j++) {
                rowData[i][j] = rowData[i + 1][j];
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
            if (rowData[i][column - 1].toString().equals(data)) {
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
            rowData[i][0] = i + 1;
        }
    }

    /**
     * 清空表
     */
    public void clean() {
        for (int i = 0; i < rowData.length; i++) {
            for (int j = 0; j < rowData[0].length; j++) {
                rowData[i][j] = null;
            }
        }
    }
}

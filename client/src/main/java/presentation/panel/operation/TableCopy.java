package presentation.panel.operation;

import presentation.frame.MainFrame;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created by pc on 2016/3/6.
 * <p>
 * 列表测试类
 */
public class TableCopy extends JTable {

    private Object[][] data;

    private String[] columnNames;


    /**
     * 表中最后一行
     */
    private int currentRow = 0;

    public TableCopy(Object[][] data, String[] columnNames) {
        super(data, columnNames);

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
        Font font = new Font("微软雅黑", Font.PLAIN, 16);
        getTableHeader().setFont(font);

        setRowHeight(30);
        getTableHeader().setResizingAllowed(false);
        getTableHeader().setReorderingAllowed(false);
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        /**
         * 设置table内容居中
         */
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
        setDefaultRenderer(Object.class, tcr);
    }

    public JScrollPane drawTable() {
        JScrollPane scroll = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
        for(int i = 0;i<data.length;i++) {
            if (name.equals(data[i][1])) {
                this.setRowSelectionInterval(i,i);
                return i;
            }
        }
        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "无记录,请确认输入是否正确");
        return -1;
    }

    public int getRowCount(){
        return data.length;
    }

}

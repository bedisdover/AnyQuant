package presentation;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

public class TableSortDemo extends JPanel {
    private static final long serialVersionUID = 1L;
    private boolean DEBUG = false;

    public TableSortDemo() {
        super(new GridLayout(1, 0));

        JTable table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        //这句话是重点
        // Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"First Name", "Last Name", "Sport", "# of Years", "Vegetarian"};
        private Object[][] data = {{"Mary", "Campione", "Snowboarding", new Integer(5), new Boolean(false)}, {"Alison", "Huml", "Rowing", new Integer(3), new Boolean(true)}, {"Kathy", "Walrath", "Knitting", new Integer(2), new Boolean(false)}, {"Sharon", "Zakhour", "Speed reading", new Integer(20), new Boolean(true)}, {"Philip", "Milne", "Pool", new Integer(10), new Boolean(false)},};

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /**
         * JTable uses this method to determine the default renderer/
         * editor for each cell. If we didn't implement this method,
         * then the* last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /**
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            // no matter where the cell appears onscreen.
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }

        /**
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at" + row + "," + col + "to" + value + "(an instance of" + value.getClass() + ")");
            }
            data[row][col] = value;
            // Normally, one should call fireTableCellUpdated() when
            // a value is changed. However, doing so in this demo
            // causes a problem with TableSorter.
        }

        // The tableChanged()
        // call on TableSorter that results from calling
        // fireTableCellUpdated() causes the indices to be regenerated
        // when they shouldn't be. Ideally, TableSorter should be
        // given a more intelligent tableChanged() implementation,
        // and then the following line can be uncommented.
        // fireTableCellUpdated(row, col);
//        if(DEBUG)
//
//        {
//            System.out.println("New value of data:");
//            printDebugData();
//        }

//        private void printDebugData() {
//            int numRows = getRowCount();
//            int numCols = getColumnCount();
//            for (int i = 0; i < numRows; i++) {
//                System.out.print("row" + i + ":");
//                for (int j = 0; j < numCols; j++) {
//                    System.out.print("" + data[i][j]);
//                }
//                System.out.println();
//            }
//            System.out.println("--------------------------");
//        }

    }

    private static void createAndShowGUI() { //Create and set up the window.
        JFrame frame = new JFrame("TableSortDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Create and set up the content pane.
        TableSortDemo newContentPane = new TableSortDemo();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane); //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {//Schedule a job for the event-dispatching thread: //creating and showing this application's GUI.
        SwingUtilities.invokeLater(TableSortDemo::createAndShowGUI);
    }
}

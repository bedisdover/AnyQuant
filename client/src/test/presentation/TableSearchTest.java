package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

public class TableSearchTest {
    public static void main(String[] args) {
        Object[][] data = { { "A", 15 }, { "B", 1 }, { "C", 24 }, { "D", 8 } };
        String columnNames[] = { "Item", "Value" };
        TableModel model = new DefaultTableModel(data, columnNames) {
            public Class<?> getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        JFrame frame = new JFrame("Sorting Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scrollPane);
        frame.setSize(300, 200);
        frame.setVisible(true);

        System.out.println("Input search text:");
        Scanner in = new Scanner(System.in);
        String a = in.next();
        for(int i = 0;i<data.length;i++){
            if(data[i][0].equals(a)){
                table.setRowSelectionInterval(i,i);
                System.out.println("Search successful!");
                return;
            }
        }
        System.out.println("Not Found");
    }


}
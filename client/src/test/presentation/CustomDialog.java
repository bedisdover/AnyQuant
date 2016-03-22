package presentation;

import po.StockID;
import presentation.frame.MainFrame;
import presentation.util.Table;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 宋益明 on 16-3-22.
 * <p>
 * 测试自定义对话框
 */
public class CustomDialog extends JDialog {

    private final String FILE_NAME = "client/src/main/resources/bank_stock.txt";

    /**
     * 主面板
     */
    private JPanel contentPane;

    /**
     * 确认按钮
     */
    private JButton btnOK;

    /**
     * 应用按钮
     */
    private JButton btnApply;

    /**
     * 取消按钮
     */
    private JButton btnCancel;

    /**
     * 表格对象
     */
    private Table table;

    /**
     * 股票列表
     */
    private List<StockID> stockList;

    private CustomDialog() {
        super(MainFrame.getMainFrame(), "自定义股票列表", true);

        init();
        createUIComponents();
        addListeners();
    }

    /**
     * 初始化
     */
    private void init() {
        setSize(new Dimension(250, 400));
        setResizable(false);
        setLocationRelativeTo(MainFrame.getMainFrame());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setModal(true);
    }

    /**
     * 创建组件
     */
    private void createUIComponents() {
        contentPane = new JPanel();
        contentPane.setLayout(null);

        setContentPane(contentPane);

        addStockList();
        addButtonPanel();
    }

    /**
     * 添加股票列表
     */
    private void addStockList() {
        String[] columnNames = new String[]{"名称", "代码", "显示"};
        table = new Table(loadStockList(), columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setBounds(0, 0, getWidth(), getHeight() - 45);
        contentPane.add(scrollPane);
    }

    /**
     * 添加按钮面板
     */
    private void addButtonPanel() {
        btnOK = new JButton("确 认");
        btnApply = new JButton("应 用");
        btnCancel = new JButton("取 消");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.lightGray);
        buttonPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        buttonPanel.setBounds(0, getHeight() - 45,
                getWidth(), 45);

        contentPane.add(buttonPanel);

        buttonPanel.add(btnOK);
        buttonPanel.add(btnApply);
        buttonPanel.add(btnCancel);

        getRootPane().setDefaultButton(btnOK);
    }

    /**
     * 添加时间监听器
     */
    private void addListeners() {
        btnOK.addActionListener(e -> onOK());

        btnApply.addActionListener(e -> onApply());

        btnCancel.addActionListener(e -> onCancel());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(e -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeDisplay();
            }
        });
    }

    /**
     * 确认操作
     */
    private void onOK() {
        onApply();
        dispose();
    }

    /**
     * 应用操作
     */
    private void onApply() {
        storeStockList();
    }

    /**
     * 取消操作
     */
    private void onCancel() {
        dispose();
    }

    /**
     * 单击表格中的某只股票时,改变其"显示"状态
     */
    private void changeDisplay() {
        int lineNum = table.getSelectedRow();

        String name = (String) table.getValueAt(lineNum, 0);
        boolean display = (boolean) table.getValueAt(lineNum, 2);

        table.setValueAt(!display, lineNum, 2);

        for (StockID temp : stockList) {
            if (temp.getName().equals(name)) {
                temp.exchangeDisplay();
            }
        }

        repaint();
    }

    /**
     * 加载股票列表
     *
     * @return 股票列表
     */
    private Object[][] loadStockList() {
        stockList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(FILE_NAME)));

            String line;
            while ((line = reader.readLine()) != null) {
                stockList.add(new StockID(line));
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Object[][] data = new Object[stockList.size()][];

        for (int i = 0; i < stockList.size(); i++) {
            data[i] = new Object[]{
                    stockList.get(i).getName(),
                    stockList.get(i).getId(),
                    stockList.get(i).isDisplay()
            };
        }

        return data;
    }

    /**
     * 存储股票列表
     */
    private void storeStockList() {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(FILE_NAME, false)));

            for (int i = 0; i < stockList.size(); i++) {
                writer.write(stockList.get(i).toString());
            }

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CustomDialog customDialog = new CustomDialog();
        customDialog.setVisible(true);
    }
}

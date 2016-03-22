package presentation;

import po.StockID;
import presentation.frame.MainFrame;
import presentation.util.Table;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
        setSize(new Dimension(400, 300));
        setLocationRelativeTo(MainFrame.getMainFrame());
        //TODO 关闭事件
//            setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
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

        addStockPanel();
        addButtonPanel();
    }

    /**
     * 添加股票列表面板
     */
    private void addStockPanel() {
        JPanel stockPanel = new JPanel();
        stockPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        stockPanel.setBounds(0, 0, getWidth(), getHeight() - 45);

        String[] columnNames = new String[]{"名称", "代码", "显示"};
        Table table = new Table(this, loadStockList(), columnNames);

        contentPane.add(stockPanel);
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
     * 加载股票列表
     *
     * @return 股票列表
     */
    private Object[][] loadStockList() {
        List<StockID> stockIDs = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(FILE_NAME)));

            String line;
            while ((line = reader.readLine()) != null) {
                stockIDs.add(new StockID(line));
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Object[][] stockList = new Object[stockIDs.size()][];

        for (int i = 0; i < stockIDs.size(); i++) {
            stockList[i] = new Object[]{
                    stockIDs.get(i).getName(),
                    stockIDs.get(i).getId(),
                    stockIDs.get(i).isDisplay()
            };
        }

        return stockList;
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

    }

    /**
     * 取消操作
     */
    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        CustomDialog customDialog = new CustomDialog();
        customDialog.setVisible(true);
    }
}

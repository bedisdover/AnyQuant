package presentation.panel.operation;

import bl.HistoryRecordStock;
import blservice.HistoryRecordStockService;
import data.GetStockData;
import presentation.frame.MainFrame;
import presentation.util.Table;
import vo.StockVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 宋益明 on 16-3-2.
 * <p>
 * 历史面板
 */
public class HistoryPanel extends OperationPanel {

    /**
     * 清空按钮
     */
    private JButton btnClear;

    /**
     * 股票列表
     */
    private Table table;

    /**
     * 所持有的历史记录对象
     */
    private HistoryRecordStockService historyRecord;

    public HistoryPanel() {
        init();
        createUIComponents();
        addListeners();
    }

    protected void init() {
        setLayout(null);
        historyRecord = new HistoryRecordStock();
    }

    protected void createUIComponents() {
        btnClear = new JButton("清空历史");
        add(btnClear);

        Iterator<String> stockID = historyRecord.getRecord();
        List<StockVO> list = new ArrayList<>();
        GetStockData getStockData = new GetStockData();

        while (stockID.hasNext()) {
            String string = stockID.next();
            try {
                list.add(new StockVO(getStockData.getStockData_name(string)));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(HistoryPanel.this,"请检查网络连接！");
            }
        }

        if (list.size() == 0) {
            return;
        }

        table = createTable(list);
    }

    private void addListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                HistoryPanel.super.assignmentValue();

                btnClear.setBounds(PANEL_WIDTH - MARGIN * 2 - BUTTON_WIDTH, MARGIN,
                        BUTTON_WIDTH + MARGIN, BUTTON_HEIGHT);

                revalidate();
                repaint();
            }
        });

        btnClear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int[] rows = table.getSelectedRows();

                try {
                    for (int row : rows) {
                        if (row != -1) {
                            historyRecord.removeStock((String) table.getValueAt(row, 2));
                        }
                    }

                    MainFrame.getMainFrame().addOperationPanel(new HistoryPanel());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}

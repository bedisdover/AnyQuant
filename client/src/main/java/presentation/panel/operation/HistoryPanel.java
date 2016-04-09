package presentation.panel.operation;

import bl.HistoryRecordStock;
import data.GetStockData;
import vo.StockVO;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
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

    public HistoryPanel() {
        init();
        createUIComponents();
    }

    protected void init() {
        setLayout(null);
    }

    protected void createUIComponents() {
        Iterator<String> stockID = new HistoryRecordStock().getRecord();
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

        createTable(list);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}

package presentation.panel.operation;

import bl.HistoryRecordStock;
import data.GetStockData;
import vo.StockVO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 宋益明 on 16-3-2.
 * <p>
 * 自选面板
 */
public class HistoryPanel extends OperationPanel {


    private TableCopy table;

    public HistoryPanel() {
        init();
        createUIComponents();
    }

    protected void init() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setLayout(null);
    }

    protected void createUIComponents() {
        Iterator<String> stockID = new HistoryRecordStock().getRecord();
        List<StockVO> list = new ArrayList<>();
        GetStockData getStockData = new GetStockData();
        System.out.println("history");
        while (stockID.hasNext()) {
            String string = stockID.next();
            list.add(new StockVO(getStockData.getStockData_name(string)));
        }

        table = createTable(list);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics2D = (Graphics2D) g;
//        graphics2D.drawImage(ImageLoader.nothing, 0, 0, getWidth() * 4 / 5, getHeight(), null);

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}

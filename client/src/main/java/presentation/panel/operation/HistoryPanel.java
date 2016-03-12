package presentation.panel.operation;

<<<<<<< HEAD
import bl.HistoryRecordStock;
import bl.SelfSelectStock;
import data.GetStockData;
import po.StockPO;
import presentation.frame.MainFrame;
import presentation.panel.info.StockInfoPanel;
import vo.StockVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
=======
import presentation.frame.MainFrame;
import presentation.util.DateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
>>>>>>> 23360abba4a34e9b49b87ec008c8f974962c8a13

/**
 * Created by song on 16-3-2.
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


<<<<<<< HEAD
        Iterator<String> stockID = new HistoryRecordStock().getRecord();
        List<StockVO> list = new ArrayList<>();
        GetStockData getStockData = new GetStockData();
        System.out.println("history");
        while (stockID.hasNext()) {
            String string = stockID.next();
            System.out.println(string+"wuwuwu");
            list.add(new StockVO(getStockData.getStockData_name(string)));
        }
=======
        this.add(searchInput);
        this.add(table);
        this.add(search);
        this.add(dateChooser);

//        String[] columnNames = {"", "最新", "涨幅", "最高", "最低"};
//        int[] list = {40, 87, 14, 30, 27, MENU_WIDTH - WIDTH, LOCATION_Y + HEIGHT + DISTANCE * 3, WIDTH * 7, HEIGHT * 10};
//        add(table.drawTable(columnNames, list));

//        Iterator<String> stockid = new HistoryRecordStock().getFollowed();
//        table = createTable()

    }

    private void addListeners() {
        /**
         * todo
         */
        search.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String text = searchInput.getText();
                Date date = dateChooser.getDate();

            }
        });
>>>>>>> 23360abba4a34e9b49b87ec008c8f974962c8a13

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

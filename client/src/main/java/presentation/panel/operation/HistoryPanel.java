package presentation.panel.operation;

import presentation.frame.MainFrame;
import presentation.util.DateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

/**
 * Created by song on 16-3-2.
 * <p>
 * 历史记录面板
 */
public class HistoryPanel extends OperationPanel {
    /**
     * 日期距离菜单栏的位置
     */
    private static final int LOCATION_X = 70;
    /**
     * 日期距离上边框的距离
     */
    private static final int LOCATION_Y = 50;
    /**
     * 按钮等组件的宽度
     */
    private static final int WIDTH = 100;
    /**
     * 按钮等组件的高度
     */
    private static final int HEIGHT = 30;
    /**
     * 组件之间的距离
     */
    private static final int DISTANCE = 10;
    /**
     * 菜单栏的宽度
     */
    private static final int MENU_WIDTH = MainFrame.getMainFrame().getWidth() / 5;


    private JTextField searchInput;
    private TableCopy table;
    private JButton search;
    private DateChooser dateChooser;

    public HistoryPanel() {
        init();
        createUIComponents();
        addListeners();
    }

    protected void init() {

        this.setLayout(null);
        this.setBounds(MENU_WIDTH, 0, MainFrame.getMainFrame().getWidth(), MainFrame.getMainFrame().getHeight());
        this.setOpaque(false);
    }

    protected void createUIComponents() {
        searchInput = new JTextField("啊啊啊");
        search = new JButton("搜索");
        dateChooser = new DateChooser(this, MENU_WIDTH + LOCATION_X, LOCATION_Y, WIDTH, HEIGHT);

        searchInput.setBounds(MENU_WIDTH + LOCATION_X + WIDTH + DISTANCE * 3, LOCATION_Y, WIDTH, HEIGHT);
        search.setBounds(MENU_WIDTH + LOCATION_X + WIDTH * 2 + DISTANCE * 4, LOCATION_Y, WIDTH / 2, HEIGHT);

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

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}

package presentation.panel.operation;

import bl.ShowIndexData;
import presentation.util.Table;
import vo.IndexVO;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 宋益明 on 16-3-2.
 *
 * 大盘指数面板
 */
public class MarketIndexPanel extends OperationPanel {

//    /**
//     * 日期距离菜单栏的位置
//     */
//    private static final int LOCATION_X=70;
//    /**
//     * 日期距离上边框的距离
//     */
//    private static final int LOCATION_Y=50;
//    /**
//     * 按钮等组件的宽度
//     */
//    private static final int WIDTH=100;
//    /**
//     * 按钮等组件的高度
//     */
//    private static final int HEIGHT=30;
//    /**
//     * 组件之间的距离
//     */
//    private static final int DISTANCE=10;
//    /**
//     * 菜单栏的宽度
//     */
//    private static final int DEFAULT_WIDTH= MainFrame.getMainFrame().getWidth() / 5;
//
//
//    private JTextField searchInput;
//    private Table table;
//    private JButton search;
//    private JButton more;
//    private DateChooser dateChooser;
//    private JComboBox comboBox;

    public MarketIndexPanel() {
        init();
        createUIComponents();
//        addListeners();
    }
    protected void init(){

        this.setLayout(null);
    }
    protected void createUIComponents() {
//        table=new Table();
//        search=new JButton("搜索");
//        more=new JButton("更多");
//        comboBox=new JComboBox();
//        dateChooser=new DateChooser(this,DEFAULT_WIDTH+LOCATION_X,LOCATION_Y, WIDTH,HEIGHT);
//
//        searchInput.setBounds(DEFAULT_WIDTH+LOCATION_X+WIDTH+DISTANCE*3,LOCATION_Y,WIDTH,HEIGHT);
//        search.setBounds(DEFAULT_WIDTH+LOCATION_X+WIDTH*2+DISTANCE*4,LOCATION_Y,WIDTH/2,HEIGHT);
//        comboBox.setBounds(DEFAULT_WIDTH+LOCATION_X+WIDTH+DISTANCE,LOCATION_Y*2,WIDTH,HEIGHT);
//        comboBox.addItem("涨幅榜");
//        comboBox.addItem("跌幅榜");
//        more.setBounds(DEFAULT_WIDTH+LOCATION_X+WIDTH+HEIGHT,LOCATION_Y*3,WIDTH,HEIGHT);
//        this.add(searchInput);
//        this.add(table);
//        this.add(search);
//        this.add(dateChooser);
//        this.add(comboBox);
//        this.add(more);
//
//        String[] columnNames = { "", "最新", "涨幅", "最高", "最低" };
//        int[] list = { 40, 87, 14, 30,27, DEFAULT_WIDTH-WIDTH,LOCATION_Y+HEIGHT+DISTANCE*3,  WIDTH*7, HEIGHT*10 };
//        add(table.drawTable(columnNames, list));
//

        IndexVO index = new ShowIndexData().getLatestIndexData();
        displayInfo(index);
        System.out.println(index.getDate()[1]);

    }

    protected void displayInfo(IndexVO index) {
        Object[][] data = new Object[index.getDate().length][];
        String[] columnNames = new String[] {
                "日期", "成交量","最高","最低","最新","收盘价","开盘价"
        };

        for(int i = 0;i<data.length;i++){
            data[i] = new Object[]{
                    index.getDate()[i],
                    index.getVolume()[i],
                    index.getHigh()[i],
                    index.getLow()[i],
                    index.getAdj_price()[i],
                    index.getClose()[i],
                    index.getOpen()[i],
            };
        }

        Table table = new Table(data, columnNames);
        JScrollPane scrollPane = table.drawTable();
        int tableHeight = Math.min(data.length * 30 + 60, HEIGHT - MARGIN * 2 - PADDING);
        scrollPane.setBounds(MARGIN, MARGIN, WIDTH - 2 * MARGIN, tableHeight);
        add(scrollPane);
    }
//    private void addListeners(){
//        /**
//         * todo
//         */
//        search.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                String text=searchInput.getText();
//                Date date=dateChooser.getDate();
//
//            }
//        });
//        /**
//         * todo
//         */
//        more.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                String text=searchInput.getText();
//                Date date=dateChooser.getDate();
//
//            }
//        });
//    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}

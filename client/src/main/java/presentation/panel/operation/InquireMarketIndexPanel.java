package presentation.panel.operation;

import presentation.frame.MainFrame;
import presentation.util.DateChooser;
import presentation.util.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

/**
 * Created by zmj on 2016/3/6.
 */
public class InquireMarketIndexPanel extends OperationPanel {


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
    private Table table;
    private JLabel title;
    private JButton search;
    private JButton back;
    private DateChooser dateChooser;

    private PopupMenu popupMenu1;
    private MenuItem menuItem1;
    private MenuItem menuItem2;
    private MenuItem menuItem3;
    private MenuItem menuItem4;

    public InquireMarketIndexPanel() {
        init();
        createUIComponents();
        addListeners();
    }

    protected void init() {

        this.setLayout(null);
        this.setBounds(MENU_WIDTH, 0, MainFrame.getMainFrame().getWidth(), MainFrame.getMainFrame().getHeight());
        this.setOpaque(false);

        popupMenu1 = new PopupMenu();
        menuItem1 = new MenuItem();
        menuItem2 = new MenuItem();
        menuItem3 = new MenuItem();
        menuItem4 = new MenuItem();
        menuItem1.setLabel("显示详细信息");
        menuItem2.setLabel("显示增幅跌幅");
        menuItem3.setLabel("添加关注");
        menuItem4.setLabel("范围查询");
        popupMenu1.add(menuItem1);
        popupMenu1.add(menuItem2);
        popupMenu1.add(menuItem3);
        popupMenu1.add(menuItem4);
    }

    protected void createUIComponents() {
        table = new Table();
        dateChooser = new DateChooser(this, MENU_WIDTH + LOCATION_X, LOCATION_Y, WIDTH, HEIGHT);
        title = new JLabel();

        search.setBounds(MENU_WIDTH + LOCATION_X + WIDTH * 2 + DISTANCE * 4, LOCATION_Y, WIDTH / 2, HEIGHT);

        this.add(searchInput);
        this.add(table);
        this.add(search);
        this.add(dateChooser);

        String[] columnNames = {"", "最新", "涨幅", "最高", "最低"};
        int[] list = {40, 87, 14, 30, 27, MENU_WIDTH - WIDTH, LOCATION_Y + HEIGHT + DISTANCE * 3, WIDTH * 7, HEIGHT * 10};
        add(table.drawTable(columnNames, list));

    }

    protected void showMenuList(int x,int y){
        popupMenu1.show(this,x,y);
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

        class RightClickListener extends MouseAdapter{}
        /**
         * todo 给table添加鼠标右键监听
         */
        table.table.addMouseListener(new RightClickListener(){
            public void mousePressed(MouseEvent e){
                JTable table = (JTable) e.getSource();
                if((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
                    Point p = e.getPoint();
                    int row = table.rowAtPoint(p);
                    int column = table.columnAtPoint(p);
                    if (!e.isControlDown() & !e.isShiftDown() & row != -1 & column != -1)
                        table.changeSelection(row, column, e.isControlDown(), e.isShiftDown());
                }
            }
            public void mouseReleased(MouseEvent e) {
                if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
                    showMenuList(e.getX()+384, e.getY()+126+30);
                }
            }
        });

        //给menuItem添加时间监听
        menuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

            }
        });
        menuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

            }
        });
        menuItem3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

            }
        });
        menuItem4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

    }
}

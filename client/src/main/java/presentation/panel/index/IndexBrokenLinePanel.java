package presentation.panel.index;

import bl.ShowIndexData;
import presentation.UltraSwing.UltraButton;
import presentation.frame.MainFrame;
import presentation.panel.info.IndexCurrentInfoPanel;
import presentation.panel.operation.MarketIndexDetailPanel;
import presentation.panel.operation.OperationPanel;
import presentation.util.DateChooser;
import vo.IndexVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 宋益明 on 16-3-2.
 * <p>
 * 大盘指数折线图面板
 * 包含简要信息（中文名称                        今开     最高     成交量)
 * (  ID        涨跌额（涨跌幅）      昨收     最低     成交额)
 * 及折线图
 */
public class IndexBrokenLinePanel extends OperationPanel {

    /**
     * 确认日期选择，生成对应折线图
     */
    private UltraButton confirm;
    /**
     * 开始日期、结束日期
     */
    private DateChooser dcStart, dcEnd;

    /**
     * 当前信息面板
     */
    private JPanel currentInfoPanel;

    /**
     * 图表面板
     */
    private JPanel chartPanel;

    /**
     * 父面板
     */
    private JPanel parent;

    /**
     * 返回按钮
     */
    private JButton back;

    public IndexBrokenLinePanel(JPanel parent) {
        this.parent = parent;

        init();
        createUIComponents();
        addListeners();
    }

    protected void init() {
        this.setLayout(null);

        update();
    }

    @Override
    protected void createUIComponents() {
        String startTime = null;
        try {
            currentInfoPanel = new IndexCurrentInfoPanel();

            dcStart = new DateChooser(this, PANEL_WIDTH - MARGIN - BUTTON_WIDTH - PADDING * 6 - BUTTON_HEIGHT * 2,
                    MARGIN, BUTTON_WIDTH + PADDING, BUTTON_HEIGHT);
            dcEnd = new DateChooser(this, PANEL_WIDTH - MARGIN - BUTTON_WIDTH - PADDING * 3 - BUTTON_HEIGHT
                    , MARGIN, BUTTON_WIDTH + PADDING, BUTTON_HEIGHT);

            IndexVO index = new ShowIndexData().getLatestIndexData();
            String date[] = index.getDate();
            //now 2016-04-12 latest 2016-04-11

            SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, -1);
            String yesterday = df.format(c.getTime()).substring(0, 10);//2016-04-11
            c.add(Calendar.DATE, -366);
            String yesterday_365 = df.format(c.getTime()).substring(0, 10);//2015-04-11
            startTime = yesterday_365;

            String chooseD[] = {yesterday_365, yesterday};
            remove(dcStart);
            remove(dcEnd);
            int yearS = Integer.parseInt(yesterday_365.substring(0, 4));
            int monthS = Integer.parseInt(yesterday_365.substring(5, 7));
            int dayS = Integer.parseInt(yesterday_365.substring(8,10));
            Date startDate=new Date(yearS,monthS,dayS);
            int yearE = Integer.parseInt(yesterday.substring(0, 4));
            int monthE = Integer.parseInt(yesterday.substring(5, 7));
            int dayE = Integer.parseInt(yesterday.substring(8,10));
            Date endDate=new Date(yearE,monthE,dayE);
            dcStart=new DateChooser(startDate, this, PANEL_WIDTH - MARGIN - BUTTON_WIDTH - PADDING * 6 - BUTTON_HEIGHT * 2,
                    MARGIN, BUTTON_WIDTH + PADDING, BUTTON_HEIGHT);
            dcEnd=new DateChooser( endDate,this, PANEL_WIDTH - MARGIN - BUTTON_WIDTH - PADDING * 3 - BUTTON_HEIGHT
                    , MARGIN, BUTTON_WIDTH + PADDING, BUTTON_HEIGHT);

            chartPanel = new MarketIndexDetailPanel(chooseD);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "请检查网络链接！");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        confirm = new UltraButton("生成");
        back = new JButton("返回");

        add(back);
        add(currentInfoPanel);
        add(chartPanel);
        add(confirm);


    }

    /**
     * 添加事件监听器
     */
    private void addListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                assignment();
            }
        });

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame.getMainFrame().addOperationPanel(parent);
            }
        });

        confirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String start = dcStart.getTime();
                String startDate = start.substring(0, 4) + "-" + start.substring(4, 6) + "-" + start.substring(6, 8);
                String end = dcEnd.getTime();
                String endDate = end.substring(0, 4) + "-" + end.substring(4, 6) + "-" + end.substring(6, 8);
                String chooseDate[] = {startDate, endDate};
                //         System.out.println(startDate+"你是谁哦");
                //         System.out.println(endDate+"你又是谁哦");
                try {
                    remove(chartPanel);
                    chartPanel = new MarketIndexDetailPanel(chooseDate);
                    add(chartPanel);

                    update();
                } catch (IOException | ParseException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    /**
     * 界面大小发生变化时，组件重新布局
     */
    private void assignment() {
        super.assignmentValue();

        back.setBounds(MARGIN, MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT);
        confirm.setBounds(PANEL_WIDTH - MARGIN - BUTTON_WIDTH, MARGIN,
                BUTTON_WIDTH, BUTTON_HEIGHT);
        currentInfoPanel.setBounds(MARGIN, MARGIN + BUTTON_HEIGHT + PADDING / 2,
                PANEL_WIDTH - MARGIN * 2, BUTTON_HEIGHT + PADDING);
        chartPanel.setBounds(MARGIN, currentInfoPanel.getY() + currentInfoPanel.getHeight() + PADDING / 2,
                PANEL_WIDTH - MARGIN * 2, PANEL_HEIGHT - getX() - PADDING);

        revalidate();
        repaint();
    }

    /**
     * 定时刷新界面
     */
    private void update() {
        new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        assignment();

                        Thread.sleep(100);
                    }
                } catch (Exception e) {
                    //有可能会出现空指针异常，对程序运行无影响，不予处理
                }
            }
        }.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }


    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        JPanel panel = new JPanel();
        DateChooser dc = new DateChooser(panel, 100, 400);
        dc.setTime("2015-10-08");
        // jFrame.add(new IndexBrokenLinePanel("kLine"));
        jFrame.add(panel);
        jFrame.setBounds(50, 50, 1024, 768);
        jFrame.setVisible(true);
    }
}

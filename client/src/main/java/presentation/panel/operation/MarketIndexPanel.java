package presentation.panel.operation;

import bl.ShowIndexData;
import po.IndexPO;
import presentation.UltraSwing.UltraButton;
import presentation.frame.MainFrame;
import presentation.panel.IndexKLines;
import presentation.panel.info.IndexCurrentInfoPanel;
import presentation.panel.info.IndexDataPanel;
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

/**
 * Created by 宋益明 on 16-3-2.
 * <p>
 * 大盘指数面板
 * 包含简要信息（中文名称                        今开     最高     成交量)
 *           (  ID        涨跌额（涨跌幅）      昨收     最低     成交额)
 *    及k-线图
 */
public class MarketIndexPanel extends OperationPanel {

    /**
     * 显示详细数据按钮
     */
    private UltraButton btnData;
    /**
     * 确认日期选择，生成对应折线图
     */
    private UltraButton confirm;
    /**
     * 开始日期、结束日期
     */
    DateChooser dcStart;
    DateChooser dcEnd;
    /**
     * 简要信息面板
     */
    private JPanel currentInfoPanel;

    /**
     * 图表面板
     */
    private JPanel chartPanel;

    /**
     * 刷新界面标记，界面发生切换后，停止刷新
     */
    private boolean updateFlag;

    public MarketIndexPanel(String type) {
        init();
        createUIComponents(type);
        addListeners();
    }

    protected void init() {
        this.setLayout(null);

        updateFlag = true;

        update();
    }

    @Override
    protected void createUIComponents() {}

    protected void createUIComponents(String type) {
        String startTime = null;
        try {
            currentInfoPanel = new IndexCurrentInfoPanel();

            IndexVO index = new ShowIndexData().getLatestIndexData();
            String date[]=index.getDate();
            //now 2016-04-12 latest 2016-04-11

            SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE,-1);
            String yesterday=df.format(c.getTime()).substring(0,10);//2016-04-11
            c.add(Calendar.DATE,-366);
            String yesterday_365=df.format(c.getTime()).substring(0,10);//2015-04-11
            startTime = yesterday_365;

            String chooseD[]={yesterday_365,yesterday};

            if (type.equals("kLine")) {
                chartPanel = new JPanel();
                chartPanel.add(new IndexKLines().getjTabbedPane());
            } else {
                chartPanel = new MarketIndexDetailPanel(chooseD);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "请检查网络链接！");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        btnData = new UltraButton("详细数据");
        confirm=new UltraButton("生成");

        add(currentInfoPanel);
        add(chartPanel);
        add(btnData);
        add(confirm);

        dcStart=new DateChooser(this, MARGIN, MARGIN, BUTTON_WIDTH * 2, BUTTON_HEIGHT);
        dcEnd=new DateChooser(this, MARGIN*10, MARGIN, BUTTON_WIDTH * 2, BUTTON_HEIGHT);
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

        btnData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame.getMainFrame().addOperationPanel(new IndexDataPanel());
            }
        });
        confirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String start=dcStart.getTime();
                String startDate=start.substring(0,4)+"-"+start.substring(4,6)+"-"+start.substring(6,8);
                String end=dcEnd.getTime();
                String endDate=end.substring(0,4)+"-"+end.substring(4,6)+"-"+end.substring(6,8);
                String chooseDate[]={startDate,endDate};
                try {
                    remove(chartPanel);
                    chartPanel =new MarketIndexDetailPanel(chooseDate);
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

        btnData.setBounds(PANEL_WIDTH - MARGIN * 2 - BUTTON_WIDTH, MARGIN,
                BUTTON_WIDTH + MARGIN, BUTTON_HEIGHT);
        confirm.setBounds(PANEL_WIDTH - MARGIN * 2 - BUTTON_WIDTH*3, MARGIN,
                BUTTON_WIDTH + MARGIN, BUTTON_HEIGHT);
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


    public static void main(String[] args){
        JFrame jFrame = new JFrame();
        jFrame.add(new MarketIndexPanel("kLine"));
        jFrame.setBounds(50, 50, 1024, 768);
        jFrame.setVisible(true);
    }
}

package presentation.panel.operation;

import presentation.util.DateChooser;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by 宋益明 on 16-3-15.
 * <p>
 * 行情面板
 */
public class PictureTest extends OperationPanel {

    /**
     * 搜索框,用于输入股票名称或代码
     * 无搜索任务时自动隐藏
     */
    private JTextField searchInput;

    /**
     * 搜索按钮
     */
    private JButton btnSearch;

    /**
     * 日期选择框
     * 通过日期选择框改变日期查看其它日期的数据
     */
    private DateChooser dateChooser;

    /**
     * 顶部面板,用于放置日期选择框,搜索框和搜索按钮
     */
    private JPanel topPanel;



    /**
     * 中心面板,用于放置股票列表
     * 股票类表分类展示,所有类别均可实现折叠/展开功能
     */
    private JPanel centerPanel;

    private JButton btn1, btn2, btn3, btn4, btn5;
    private JPanel pNorth, pSouth, subMenuContainer;
    private JScrollPane pCenter;

    private JButton[] btn = null;
    private static boolean expand = false;

    public PictureTest() {
        init();
        createUIComponents();
        addListeners();
    }

    /**
     * 初始化
     */
    protected void init() {
        setLayout(null);
        setBorder(new BevelBorder(BevelBorder.LOWERED));
    }

    /**
     * 创建组件
     */
    protected void createUIComponents() {
        {
            topPanel = new JPanel();
            topPanel.setLayout(null);
            topPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));

            dateChooser = new DateChooser(this, MARGIN, MARGIN, BUTTON_WIDTH + PADDING, BUTTON_HEIGHT);

            btnSearch = new JButton("搜索");
            searchInput = new JTextField();

            topPanel.add(btnSearch);
            topPanel.add(searchInput);

            this.add(topPanel);
        }

        {
            centerPanel = new JPanel(new BorderLayout());

            btn1 = new JButton("Grade1 menu1");
            btn2 = new JButton("Grade1 menu2");
            btn3 = new JButton("Grade1 menu3");
            btn3.addActionListener(new ActionHandler());

            btn4 = new JButton("Grade1 menu4");
            btn5 = new JButton("Grade1 menu5");
            pNorth = new JPanel();
            pNorth.setLayout(new GridLayout(3, 1));
            pSouth = new JPanel();
            pSouth.setLayout(new GridLayout(2, 1));
            subMenuContainer = new JPanel();
            subMenuContainer.setLayout(new GridLayout(25, 1));

            btn = new JButton[25];
            for (int i = 0; i < btn.length; i++) {
                btn[i] = new JButton("[菜单" + i + "]");
            }
            pNorth.add(btn1);
            pNorth.add(btn2);
            pNorth.add(btn3);
            for (int i = 0; i < btn.length; i++) {
                subMenuContainer.add(btn[i]);
            }
            pCenter = new JScrollPane(subMenuContainer);

            pSouth.add(btn4);
            pSouth.add(btn5);
            centerPanel.add(pNorth, "North");
            centerPanel.add(pCenter, "Center");
            centerPanel.add(pSouth, "South");

            add(centerPanel);
        }
    }

    private class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (btn3 == e.getSource()) {
                if (expand) {//折叠
                    pNorth.setLayout(new GridLayout(3, 1));
                    pNorth.remove(btn4);
                    pNorth.remove(btn5);
                    pSouth.add(btn4);
                    pSouth.add(btn5);
                    for (int i = 0; i < btn.length; i++) {
                        subMenuContainer.add(btn[i]);
                    }
                    validate();
                    repaint();
                    expand = false;
                } else {//展开
                    for (int i = 0; i < btn.length; i++) {
                        subMenuContainer.remove(btn[i]);
                    }
                    pSouth.removeAll();
                    pNorth.setLayout(new GridLayout(5, 1));
                    pNorth.add(btn4);
                    pNorth.add(btn5);
                    pNorth.repaint();
                    pCenter.repaint();
                    pSouth.repaint();
                    validate();
                    repaint();
                    expand = true;
                }
            }
        }

    }

    /**
     * 添加事件监听器
     */
    private void addListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                btnSearch.setBounds(WIDTH - MARGIN - BUTTON_WIDTH, MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT);
                searchInput.setBounds(btnSearch.getX() - TEXT_FIELD_WIDTH, MARGIN, TEXT_FIELD_WIDTH, BUTTON_HEIGHT);
                topPanel.setBounds(0, 0, WIDTH, MARGIN + BUTTON_HEIGHT + PADDING);
                centerPanel.setBounds(0, topPanel.getHeight(), WIDTH, HEIGHT - topPanel.getHeight());

                repaint();
            }
        });

        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!searchInput.getText().equals("")) {
//                    int k = table.searchStock(searchInput.getText());
//                    if (k > 0) {
//                        System.out.println(table.getRowCount());
//                        table.setRowSelectionInterval(k, k);
//                        System.out.println(k);
//                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (searchInput.getText().equals("")) {
                    searchInput.setText("输入股票名称或代码");
                }
                add(searchInput);
                repaint();

                //显示搜索框后,若点击除搜索框和搜索按钮以外的其他区域,
                //自动隐藏搜索框
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getSource() != btnSearch && e.getSource() != searchInput) {
                            remove(searchInput);
                            repaint();
                        }
                    }
                });
            }
        });

        searchInput.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                searchInput.setText("");
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

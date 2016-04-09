package presentation.panel;

import presentation.frame.MainFrame;
import presentation.panel.operation.HistoryPanel;
import presentation.panel.operation.MarketIndexPanel;
import presentation.panel.operation.PicturePanel;
import presentation.panel.operation.PortfolioPanel;
import presentation.settings.SettingsDialog;
import presentation.util.ExampleFileFilter;
import presentation.util.ImageLoader;
import presentation.util.NetWorkState;
import presentation.util.Portrait;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static presentation.frame.MainFrame.getMainFrame;

/**
 * Created by 宋益明 on 16-3-2.
 * <p>
 * 菜单面板
 */
public class MenuPanel extends JPanel {

    /**
     * 头像直径
     */
    private final int PORTRAIT_DIAMETER;

    /**
     * 外边距
     */
    private final int MARGIN;

    /**
     * 内边距
     */
    private final int PADDING;

    /**
     * 按钮宽度
     */
    private final int BUTTON_WIDTH;

    /**
     * 按钮高度
     */
    private final int BUTTON_HEIGHT;

    /**
     * 面板透明度
     */
    private final float TRANSPARENCY = 0.5f;

    /**
     * 头像文件
     */
    private static final File PORTRAIT = new File("client/src/main/resources/images/portrait.png");

    /**
     * 原始图片
     */
    private BufferedImage image;

    /**
     * 头像图片
     */
    private BufferedImage portraitImage;

    /**
     * "自选"按钮
     * portfolio---证券投资组合
     * 自选股没有这个说法，Yahoo! Finance上的Add to Portfolio的功能比较接近这个～
     */
    private JButton btnPortfolio;

    /**
     * 行情按钮
     */
    private JButton btnPicture;

    /**
     * 行情选项面板，承载沪深按钮和美股按钮
     */
    private JPanel options;

    /**
     * 沪深按钮
     */
    private JButton btnSH_SZ;

    /**
     * 美股按钮
     */
    private JButton btnUS;

    /**
     * 大盘指数按钮
     */
    private JButton btnMarketIndex;

    /**
     * 历史按钮
     */
    private JButton btnHistory;

    /**
     * 设置按钮(因显示要求,使用JLabel)
     */
    private JLabel btnSettings;

    /**
     * 换肤按钮
     */
    private JLabel btnSkin;

    /**
     * 头像选择器
     */
    private Portrait portrait;

    /**
     * 网络状态
     * 连接正常为true,无法连接到互联网为false
     */
    private boolean netState;

    public MenuPanel() {
        //各种数值均由Panel的宽高决定
        //故先进行初始化,设置Panel的Bounds
        init();

        int temp = Math.min(getWidth(), getHeight());
        PORTRAIT_DIAMETER = temp / 2;
        BUTTON_WIDTH = temp * 2 / 3;
        BUTTON_HEIGHT = BUTTON_WIDTH / 3;
        MARGIN = (temp - BUTTON_WIDTH) / 2;
        PADDING = BUTTON_HEIGHT;

        createUIComponents();
        addListeners();
    }

    /**
     * 初始化
     */
    private void init() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        this.setBounds(0, 0, MainFrame.MENU_WIDTH, getMainFrame().getHeight());
        this.setLayout(null);
        this.setOpaque(false);

        loadPortrait();
        netConnect();
    }

    /**
     * 创建UI组件
     */
    private void createUIComponents() {
        options = new JPanel();
        options.setBackground(new Color(140, 140, 140));
        options.setBorder(new BevelBorder(BevelBorder.LOWERED));
        //默认不可见
        options.setVisible(false);

        btnPortfolio = new JButton("自  选");
        btnPicture = new JButton("行  情");
        btnSH_SZ = new JButton("沪 深");
        btnUS = new JButton("美 股");
        btnMarketIndex = new JButton("大盘指数");
        btnHistory = new JButton("历  史");
        btnSettings = new JLabel();
        btnSkin = new JLabel();

        btnSettings.setToolTipText("系统设置");
        btnSkin.setToolTipText("更换皮肤");

        btnPortfolio.setBounds(MARGIN, PORTRAIT_DIAMETER + PADDING * 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        btnPicture.setBounds(MARGIN, btnPortfolio.getY() + PADDING * 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        btnMarketIndex.setBounds(MARGIN, btnPicture.getY() + PADDING * 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        btnHistory.setBounds(MARGIN, btnMarketIndex.getY() + PADDING * 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        btnSettings.setBounds(MARGIN, getHeight() - MARGIN * 2 - BUTTON_HEIGHT,
                BUTTON_HEIGHT, BUTTON_HEIGHT);
        btnSkin.setBounds(getWidth() - MARGIN - BUTTON_HEIGHT, btnSettings.getY(),
                BUTTON_HEIGHT, BUTTON_HEIGHT);
        btnSettings.setIcon(new ImageIcon(ImageLoader.settings));
        btnSkin.setIcon(new ImageIcon(ImageLoader.skin));

        this.add(options);
        this.add(btnPortfolio);
        this.add(btnPicture);
        this.add(btnMarketIndex);
        this.add(btnHistory);
        this.add(btnSettings);
        this.add(btnSkin);
    }

    private void addListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                assignment();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getX() >= PADDING && e.getX() <= PADDING + PORTRAIT_DIAMETER &&
                        e.getY() >= PADDING && e.getY() <= PADDING + PORTRAIT_DIAMETER) {
                    chooseImage();
                }
            }
        });

        btnPortfolio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (netState) {
                    getMainFrame().addOperationPanel(new PortfolioPanel());
                } else {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "请检查网络连接！");
                }
            }
        });

        btnPicture.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!options.isVisible()) {
                    showPictureOptions();
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!options.isVisible()) {
                    showPictureOptions();
                }
            }
        });

        btnSH_SZ.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (netState) {
                    getMainFrame().addOperationPanel(new PicturePanel());
                } else {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "请检查网络连接！");
                }
            }
        });

        btnUS.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });

        btnMarketIndex.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (netState) {
                    getMainFrame().addOperationPanel(new MarketIndexPanel());
                } else {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "请检查网络连接！");
                }
            }
        });

        btnHistory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (netState) {
                    getMainFrame().addOperationPanel(new HistoryPanel());
                } else {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "请检查网络连接！");
                }
            }
        });

        btnSettings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SettingsDialog().setVisible(true);
            }
        });

        btnSkin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //TODO 换肤
            }
        });
    }

    /**
     * 界面大小发生变化时，对组件重新布局
     */
    private void assignment() {
        btnPortfolio.setBounds(MARGIN, PORTRAIT_DIAMETER + PADDING * 2,
                BUTTON_WIDTH, BUTTON_HEIGHT);
        btnPicture.setBounds(MARGIN, btnPortfolio.getY() + PADDING * 2,
                BUTTON_WIDTH, BUTTON_HEIGHT);
        btnMarketIndex.setBounds(MARGIN, btnPicture.getY() + PADDING * 2,
                BUTTON_WIDTH, BUTTON_HEIGHT);
        btnHistory.setBounds(MARGIN, btnMarketIndex.getY() + PADDING * 2,
                BUTTON_WIDTH, BUTTON_HEIGHT);
        btnSettings.setBounds(MARGIN, getHeight() - MARGIN * 2 - BUTTON_HEIGHT,
                BUTTON_HEIGHT, BUTTON_HEIGHT);
        btnSkin.setBounds(getWidth() - MARGIN - BUTTON_HEIGHT, btnSettings.getY(),
                BUTTON_HEIGHT, BUTTON_HEIGHT);

        repaint();
    }

    /**
     * 显示行情选项——美股、沪深
     */
    private void showPictureOptions() {
        options.setVisible(true);

        new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < BUTTON_HEIGHT * 2 + 20; i++) {
                        Thread.sleep(10);
                        options.setBounds(btnPicture.getX() + 10,
                                btnPicture.getY() + BUTTON_HEIGHT, BUTTON_WIDTH - 20, i);
                        btnMarketIndex.setBounds(MARGIN, options.getY() + i + PADDING / 2,
                                BUTTON_WIDTH, BUTTON_HEIGHT);
                        btnHistory.setBounds(MARGIN, btnMarketIndex.getY() + PADDING * 2,
                                BUTTON_WIDTH, BUTTON_HEIGHT);
                        repaint();
                    }

                    options.add(btnSH_SZ);
                    options.add(btnUS);

                    revalidate();
                    repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean temp = e.getX() >= options.getX()
                        && e.getX() <= options.getX() + options.getHeight()
                        && e.getY() >= options.getY()
                        && e.getY() <= options.getY() + options.getWidth();
                if (!temp) {
                    hidePictureOptions();
                }
            }
        });
    }

    /**
     * 隐藏行情选项——美股、沪深
     */
    private void hidePictureOptions() {
        new Thread() {
            @Override
            public void run() {
                //动画时间持续3秒
                try {
                    for (int i = BUTTON_HEIGHT + 20; i >= 0; i--) {
                        Thread.sleep(10);
                        options.setBounds(btnPicture.getX() + 10,
                                btnPicture.getY() + BUTTON_HEIGHT, BUTTON_WIDTH - 20, i);
                        btnMarketIndex.setBounds(MARGIN, options.getY() + i + PADDING / 2,
                                BUTTON_WIDTH, BUTTON_HEIGHT);
                        btnHistory.setBounds(MARGIN, btnMarketIndex.getY() + PADDING * 2,
                                BUTTON_WIDTH, BUTTON_HEIGHT);
                        repaint();
                    }

                    options.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 加载当前头像
     */
    private void loadPortrait() {
        try {
            portraitImage = ImageIO.read(PORTRAIT);
        } catch (IOException e) {
            portraitImage = null;
        }
    }

    /**
     * 检查网络连接
     */
    private void netConnect() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        boolean temp = NetWorkState.netConnect();

                        if (temp != netState) {
                            netState = temp;
                            repaint();
                        }
                        Thread.sleep(100);
                    }
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }

    /**
     * 选择原始图片
     */
    private void chooseImage() {
        try {
            //修改文件选择器风格
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        //文件选择器
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("请选择图像文件...");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        ExampleFileFilter filter = new ExampleFileFilter();
        filter.addExtension("jpg");
        filter.addExtension("gif");
        filter.addExtension("png");
        filter.setDescription("JPG & GIF & PNG Images");
        chooser.setFileFilter(filter);

        int result = chooser.showOpenDialog(getMainFrame());

        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                image = ImageIO.read(chooser.getSelectedFile());
                portrait = new Portrait(this, image);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 获得经头像选择器处理过的头像
     */
    public void getPortrait() {
        portraitImage = portrait.getPortrait();

        if (portraitImage != null) {
            //存储更改后头像
            storePortrait();
        } else {
            loadPortrait();
        }

        repaint();
    }


    /**
     * 存储更改后头像
     */
    private void storePortrait() {
        try {
            //删除原头像
            PORTRAIT.delete();

            ImageIO.write(portraitImage, "png", PORTRAIT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        //绘制菜单栏
        graphics2D.setColor(Color.gray);
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
        //绘制头像
        graphics2D.fillOval(MARGIN, MARGIN, PORTRAIT_DIAMETER, PORTRAIT_DIAMETER);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        graphics2D.drawImage(portraitImage, MARGIN, MARGIN, PORTRAIT_DIAMETER, PORTRAIT_DIAMETER, null);

        if (!netState) {//若当前未连接互联网，绘制黑色遮罩头像
            graphics2D.setColor(Color.black);
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
            graphics2D.fillOval(MARGIN, MARGIN, PORTRAIT_DIAMETER, PORTRAIT_DIAMETER);
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        }
    }
}


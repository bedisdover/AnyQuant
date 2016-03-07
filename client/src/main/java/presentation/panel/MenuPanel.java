package presentation.panel;

import presentation.panel.operation.HistoryPanel;
import presentation.panel.operation.MarketIndexPanel;
import presentation.panel.operation.PicturePanel;
import presentation.panel.operation.PortfolioPanel;
import presentation.settings.SettingsDialog;
import presentation.util.ExampleFileFilter;
import presentation.util.ImageLoader;
import presentation.util.Portrait;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static presentation.frame.MainFrame.getMainFrame;

/**
 * Created by song on 16-3-2.
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

    public MenuPanel() {
        init();

        PORTRAIT_DIAMETER = getWidth() / 2;
        BUTTON_WIDTH = getWidth() * 2 / 3;
        BUTTON_HEIGHT = BUTTON_WIDTH / 3;
        MARGIN = (getWidth() - BUTTON_WIDTH) / 2;
        PADDING = BUTTON_HEIGHT;

        createUIComponents();
        addListeners();

        loadPortrait();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        //绘制菜单栏
        graphics2D.setColor(Color.gray);
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
        //绘制头像
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        graphics2D.fillOval(MARGIN, MARGIN, PORTRAIT_DIAMETER, PORTRAIT_DIAMETER);
        graphics2D.drawImage(portraitImage, MARGIN, MARGIN, PORTRAIT_DIAMETER, PORTRAIT_DIAMETER, null);
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
        this.setLayout(null);
        this.setBounds(0, 0, getMainFrame().getWidth() / 5,
                getMainFrame().getHeight());
        this.setOpaque(false);
    }

    /**
     * 创建UI组件
     */
    private void createUIComponents() {
        btnPortfolio = new JButton("自  选");
        btnPicture = new JButton("行  情");
        btnMarketIndex = new JButton("大盘指数");
        btnHistory = new JButton("历  史");
        btnSettings = new JLabel();
        btnSkin = new JLabel();

        btnPortfolio.setBounds(MARGIN, PORTRAIT_DIAMETER + PADDING * 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        btnPicture.setBounds(MARGIN, btnPortfolio.getY() + PADDING * 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        btnMarketIndex.setBounds(MARGIN, btnPicture.getY() + PADDING * 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        btnHistory.setBounds(MARGIN, btnMarketIndex.getY() + PADDING * 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        btnSettings.setBounds(MARGIN, getHeight() - MARGIN - BUTTON_HEIGHT, BUTTON_HEIGHT, BUTTON_HEIGHT);
        btnSkin.setBounds(getWidth() - MARGIN - BUTTON_HEIGHT, getHeight() - MARGIN - BUTTON_HEIGHT,
                BUTTON_HEIGHT, BUTTON_HEIGHT);
        btnSettings.setIcon(new ImageIcon(ImageLoader.settings));
        btnSkin.setIcon(new ImageIcon(ImageLoader.skin));

        this.add(btnPortfolio);
        this.add(btnPicture);
        this.add(btnMarketIndex);
        this.add(btnHistory);
        this.add(btnSettings);
        this.add(btnSkin);
    }

    private void addListeners() {
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
                getMainFrame().addOperationPanel(new PortfolioPanel());
            }
        });

        btnPicture.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getMainFrame().addOperationPanel(new PicturePanel());
            }
        });

        btnMarketIndex.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getMainFrame().addOperationPanel(new MarketIndexPanel());
            }
        });

        btnHistory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getMainFrame().addOperationPanel(new HistoryPanel());
            }
        });

        btnSettings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SettingsDialog().setVisible(true);
            }
        });

        btnSettings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
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
}


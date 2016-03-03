package presentation;

import presentation.panel.HistoryPanel;
import presentation.panel.MarketIndexPanel;
import presentation.panel.PicturePanel;
import presentation.panel.PortfolioPanel;
import presentation.util.ExampleFileFilter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by song on 16-3-2.
 * <p>
 * 菜单面板
 */
public class MenuPanel extends JPanel {

    /**
     * 头像直径
     */
    private static final int PORTRAIT_DIAMETER = 80;

    /**
     * 内边距
     */
    private static final int PADDING = 30;

    /**
     * 头像文件
     */
    private static final File PORTRAIT = new File("portrait.png");

    /**
     * 原始图片
     */
    private BufferedImage image;

    /**
     * 头像图片
     */
    private BufferedImage portraitImage;

    /**
     * 操作面板
     */
    private JPanel operationPanel;

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
     * 头像选择器
     */
    private Portrait portrait;

    public MenuPanel() {
        this.setLayout(null);

        createUIComponents();
        addListeners();

        loadPortrait();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        // 消除锯齿
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(new Color(121, 106, 75));
        graphics2D.drawImage(AllImage.menu, 0, 0, this.getWidth(), this.getHeight(), null);
        graphics2D.setColor(Color.gray);
        graphics2D.fillOval(PADDING, PADDING, PORTRAIT_DIAMETER, PORTRAIT_DIAMETER);

        graphics2D.drawImage(portraitImage, PADDING, PADDING, PORTRAIT_DIAMETER, PORTRAIT_DIAMETER, null);
    }

    /**
     * 创建UI组件
     */
    private void createUIComponents() {
        btnPortfolio = new JButton("自  选");
        btnPicture = new JButton("行  情");
        btnMarketIndex = new JButton("大盘指数");
        btnHistory = new JButton("历  史");

        btnPortfolio.setBounds(PADDING, PORTRAIT_DIAMETER + PADDING * 2, 110, PADDING);
        btnPicture.setBounds(PADDING, btnPortfolio.getY() + PADDING * 2, 110, PADDING);
        btnMarketIndex.setBounds(PADDING, btnPicture.getY() + PADDING * 2, 110, PADDING);
        btnHistory.setBounds(PADDING, btnMarketIndex.getY() + PADDING * 2, 110, PADDING);

        this.add(btnPortfolio);
        this.add(btnPicture);
        this.add(btnMarketIndex);
        this.add(btnHistory);
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
                operationPanel = new PortfolioPanel();
            }
        });

        btnPicture.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                operationPanel = new PicturePanel();
            }
        });

        btnMarketIndex.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                operationPanel = new MarketIndexPanel();
            }
        });

        btnHistory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                operationPanel = new HistoryPanel();

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

        int result = chooser.showOpenDialog(MainFrame.getMainFrame());

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


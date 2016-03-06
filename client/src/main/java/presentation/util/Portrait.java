package presentation.util;

import presentation.frame.MainFrame;
import presentation.panel.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by song on 16-3-2.
 * <p>
 * 头像选择器类
 * 实现选择头像功能
 */
public class Portrait extends JPanel {

    private static final int DEFAULT_DIAMETER = 100;

    /**
     * 头像以外部分的透明度
     */
    private static final float TRANSPARENCY = 0.5f;

    /**
     * 源图片
     */
    private Image image;

    /**
     * 窗体宽度,高度
     */
    private int width, height;

    /**
     * 目标头像直径,半径
     */
    private int diameter, radius;

    /**
     * 目标头像中心坐标
     */
    private int x, y;

    /**
     * 目标头像图片
     */
    private BufferedImage portrait;

    private JFrame frame;
    private MenuPanel menuPanel;

    public Portrait(MenuPanel menuPanel, Image image) {
        this.menuPanel = menuPanel;
        this.image = image;

        init();
        showTips();
        showFrame();
        addListener();
    }

    /**
     * 初始化
     */
    private void init() {
        width = MainFrame.getMainFrame().getWidth();
        height = width * image.getHeight(this) / image.getWidth(this);

        diameter = DEFAULT_DIAMETER;
        radius = diameter / 2;

        x = width / 2;
        y = height / 2;

        setLayout(null);
        setPreferredSize(new Dimension(width, height));
    }


    /**
     * 显示操作提示
     */
    private void showTips() {
        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "点击回车确认图片\n点击Esc取消选择");
        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "通过\"+\"及\"-\"调整头像大小");
        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "通过\"↑、↓、←、→\"调节头像位置");
    }

    /**
     * 显示头像选择界面
     */
    private void showFrame() {
        frame = new JFrame();
        frame.setContentPane(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.setVisible(true);
    }

    /**
     * 添加事件监听器
     */
    private void addListener() {
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();

                if (code == KeyEvent.VK_ENTER) {
                    portrait = getSegmentedImage();
                    frame.dispose();
                } else if (code == KeyEvent.VK_ESCAPE) {
                    frame.dispose();
                } else if (code == KeyEvent.VK_EQUALS) {
                    diameter += 10;
                } else if (code == KeyEvent.VK_MINUS) {
                    diameter -= 10;
                } else if (code == KeyEvent.VK_UP) {
                    y -= 10;
                } else if (code == KeyEvent.VK_DOWN) {
                    y += 10;
                } else if (code == KeyEvent.VK_LEFT) {
                    x -= 10;
                } else if (code == KeyEvent.VK_RIGHT) {
                    x += 10;
                }

                repaint();
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                menuPanel.getPortrait();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x = e.getX();
                y = e.getY();

                repaint();
            }
        });
    }

    /**
     * 预处理源图片,调节切割范围之外图片的透明度,区分切割部分
     * 为方便观察,绘制等距离虚线
     *
     * @return 目标图片
     */
    private BufferedImage getRenderedImage() {
        BufferedImage result = new BufferedImage(
                width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = result.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);

        Ellipse2D round = new Ellipse2D.Double(x - diameter / 2, y - diameter / 2, diameter, diameter);
        Area clear = new Area(new Rectangle2D.Double(0, 0, width, height));
        clear.subtract(new Area(round));

        //设置透明度
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, TRANSPARENCY);
        g.setComposite(ac);

        //消除锯齿
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.fill(clear);

        //绘制虚线
        g.setColor(Color.lightGray);
        g.setStroke(new BasicStroke(5));
        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j <= width / 20; j++) {
                g.drawLine(j * 20, height / 4 * i, j * 20 + 10, height / 4 * i);
            }
            for (int j = 0; j <= height / 20; j++) {
                g.drawLine(width / 4 * i, j * 20, width / 4 * i, j * 20 + 10);
            }
        }

        g.dispose();

        return result;
    }

    /**
     * 切割图片,获得以光标所在坐标为圆心,具有指定直径的圆形图片
     *
     * @return 目标图片
     */
    private BufferedImage getSegmentedImage() {
        BufferedImage result = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);

        //TODO BUG 头像位置偏左上
        Graphics2D g = result.createGraphics();
        g.drawImage(image, 0, 0, diameter, diameter, x - diameter / 2, y - diameter / 2, x + diameter, y + diameter, null);

        Rectangle2D rectangle = new Rectangle2D.Double(0, 0, diameter, diameter);
        Ellipse2D round = new Ellipse2D.Double(0, 0, diameter, diameter);
        Area clear = new Area(rectangle);
        clear.subtract(new Area(round));

        g.setComposite(AlphaComposite.Clear);

        //消除锯齿
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.fill(clear);
        g.dispose();

        return result;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(getRenderedImage(), 0, 0, null);
    }

    public BufferedImage getPortrait() {
        return portrait;
    }
}

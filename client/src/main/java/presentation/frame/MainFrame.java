package presentation.frame;

import config.FrameConfig;
import config.SystemConfig;
import org.dom4j.DocumentException;
import presentation.panel.BackgroundPanel;
import presentation.panel.MenuPanel;
import presentation.panel.operation.PicturePanel;
import presentation.util.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by 宋益明 on 16-3-2.
 * <p>
 * 主界面
 */
public final class MainFrame extends JFrame {

    /**
     * 窗体默认宽度,高度
     */
    public static final int DEFAULT_WIDTH, DEFAULT_HEIGHT;

    /**
     * 菜单栏宽度固定
     * 菜单栏高度随窗口大小变化而变化
     */
    public static final int MENU_WIDTH;

    private static final MainFrame frame;

    private static final BackgroundPanel backgroundPanel;

    private static JPanel operationPanel;

    private static FrameConfig frameConfig;

    private MainFrame() {
    }

    static {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        DEFAULT_WIDTH = screen.width / 2;
        DEFAULT_HEIGHT = DEFAULT_WIDTH * 3 / 4;

        MENU_WIDTH = DEFAULT_WIDTH / 5;

        frame = new MainFrame();
        backgroundPanel = new BackgroundPanel(ImageLoader.background);

        try {
            frameConfig = new SystemConfig().getFrameConfig();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        init();
        createUIComponents();
        addListeners();
    }

    public static MainFrame getMainFrame() {
        return frame;
    }

    /**
     * 初始化窗体
     */
    private static void init() {
        frame.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        frame.setMinimumSize(frameConfig.getMinimumSize());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(backgroundPanel);
        frame.setLayout(null);
        frame.setIconImage(ImageLoader.icon);

        backgroundPanel.setLayout(null);

        operationPanel = new PicturePanel();
    }

    /**
     * 创建组件
     */
    private static void createUIComponents() {
        MenuPanel menuPanel = new MenuPanel();
        backgroundPanel.add(menuPanel);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                menuPanel.setBounds(0, 0, MENU_WIDTH, frame.getHeight());
                operationPanel.setBounds(MENU_WIDTH, 0,
                        frame.getWidth() - MENU_WIDTH, frame.getHeight());
            }
        });
    }

    private static void addListeners() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frameConfig.storeBounds(frame.getBounds());
            }
        });
    }

    /**
     * 添加操作面板
     *
     * @param panel 操作面板
     */
    public void addOperationPanel(JPanel panel) {
        backgroundPanel.remove(operationPanel);
        operationPanel = panel;
        panel.setBounds(MENU_WIDTH, 0, frame.getWidth() - MENU_WIDTH, frame.getHeight());
        backgroundPanel.add(panel);

        repaint();
    }
}


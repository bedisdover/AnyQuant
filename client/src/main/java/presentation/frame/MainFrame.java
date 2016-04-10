package presentation.frame;

import config.FrameConfig;
import config.SystemConfig;
import org.dom4j.DocumentException;
import presentation.panel.BackgroundPanel;
import presentation.panel.MenuPanel;
import presentation.panel.info.LocationValue;
import presentation.util.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;

/**
 * Created by 宋益明 on 16-3-2.
 * <p>
 * 主界面
 */
public final class MainFrame extends JFrame {

    /**
     * 窗体默认宽度
     */
    public static final int DEFAULT_WIDTH;

    /**
     * 菜单栏宽度固定(可自动隐藏)
     * 菜单栏高度随窗口大小变化而变化
     */
    public static int MENU_WIDTH;

    /**
     * 主窗体对象
     */
    private static final MainFrame frame;

    /**
     * 背景面板
     */
    private static final BackgroundPanel backgroundPanel;

    /**
     * 菜单面板
     */
    public static MenuPanel menuPanel;

    /**
     * 操作面板
     */
    public static JPanel operationPanel;

    /**
     * 界面配置对象
     */
    private static FrameConfig frameConfig;

    private MainFrame() {
    }

    static {
        //默认宽度固定不变
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        DEFAULT_WIDTH = screen.width / 2;

        frame = new MainFrame();
        backgroundPanel = new BackgroundPanel(ImageLoader.background);

        try {
            frameConfig = SystemConfig.getFrameConfig();
        } catch (DocumentException | MalformedURLException e) {
            e.printStackTrace();
        }

        MENU_WIDTH = (int) (frameConfig.getDefaultBounds().getWidth() / 5);

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
        frame.setMinimumSize(frameConfig.getMinimumSize());
        frame.setBounds(frameConfig.getLastBounds());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(backgroundPanel);
        frame.setLayout(null);
        frame.setIconImage(ImageLoader.icon);

        try {//采用BeautyEye样式时，不宜改变窗体大小
            String style = SystemConfig.getStyle();
            if (style.equals("BeautyEye")) {
                frame.setResizable(false);
            }
        } catch (MalformedURLException | DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建组件
     */
    private static void createUIComponents() {
        menuPanel = new MenuPanel();
        backgroundPanel.add(menuPanel);
        frame.showMenuPanel();

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (operationPanel != null) {
                    operationPanel.setBounds(MENU_WIDTH, 0,
                            frame.getWidth() - MENU_WIDTH, frame.getHeight());
                }

                notifyPanel();
            }
        });
    }

    /**
     * 添加事件监听器
     */
    private static void addListeners() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frameConfig.storeBounds(frame.getBounds());
            }
        });
    }

    /**
     * 界面大小发生变化时，通知面板更新数据
     */
    private static void notifyPanel() {
        LocationValue.updateValue();
    }

    /**
     * 显示菜单栏
     */
    public void showMenuPanel() {
        if (menuPanel.getWidth() != 0) {
            return;
        }

        new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i <= MENU_WIDTH; i++) {
                        Thread.sleep(1);
                        menuPanel.setBounds(0, 0, i, frame.getHeight());

                        if (operationPanel != null) {
                            operationPanel.setBounds(i, 0, frame.getWidth() - i, frame.getHeight());
                        }

                        repaint();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 隐藏菜单栏
     */
    public void hideMenuPanel() {
        if (menuPanel.getWidth() != MENU_WIDTH) {
            return;
        }

        new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = MENU_WIDTH; i >= 0; i--) {
                        Thread.sleep(1);
                        menuPanel.setBounds(0, 0, i, frame.getHeight());

                        if (operationPanel != null) {
                            operationPanel.setBounds(i, 0, frame.getWidth() - i, frame.getHeight());
                        }

                        repaint();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 添加操作面板
     *
     * @param panel 操作面板
     */
    public void addOperationPanel(JPanel panel) {
        if (operationPanel != null) {
            backgroundPanel.remove(operationPanel);
        }

        operationPanel = panel;

        panel.setBounds(menuPanel.getWidth(), 0,
                frame.getWidth() - menuPanel.getWidth(), frame.getHeight());
        backgroundPanel.add(panel);

        backgroundPanel.revalidate();
        backgroundPanel.repaint();
        backgroundPanel.updateUI();

        frame.revalidate();
        frame.repaint();
    }

    /**
     * panel切换
     */
    private void switchover() {
        new Thread() {
            @Override
            public void run() {
                //TODO panel切换
            }
        }.start();
    }
}


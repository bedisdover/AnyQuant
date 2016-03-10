package presentation.frame;

import presentation.panel.BackgroundPanel;
import presentation.panel.MenuPanel;
import presentation.panel.operation.PortfolioPanel;
import presentation.util.ImageLoader;

import javax.swing.*;
import java.awt.*;

/**
 * Created by song on 16-3-2.
 * <p>
 * 主界面
 */
public final class MainFrame extends JFrame {

    private static final int width;//683
    private static final int height;//512

    private static final MainFrame frame;

    private static final BackgroundPanel backgroundPanel;

    private static JPanel operationPanel;

    private MainFrame() {
    }

    static {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        width = screen.width / 2;
        height = width * 3 / 4;

        frame = new MainFrame();
        backgroundPanel = new BackgroundPanel(ImageLoader.background);

        init();
        createUIComponents();
    }

    public static MainFrame getMainFrame() {
        return frame;
    }

    /**
     * 初始化窗体
     */
    private static void init() {
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(backgroundPanel);
        frame.setLayout(null);
        frame.setIconImage(ImageLoader.icon);

        backgroundPanel.setLayout(null);

        operationPanel = new PortfolioPanel();
    }

    /**
     * 创建组件
     */
    private static void createUIComponents() {
        MenuPanel menuPanel = new MenuPanel();
        menuPanel.setBounds(0, 0, width / 5, height);
        backgroundPanel.add(menuPanel);

        operationPanel.setBounds(width / 5, 0, width, height);
    }

    /**
     * 添加操作面板
     *
     * @param panel 操作面板
     */
    public void addOperationPanel(JPanel panel) {
        backgroundPanel.remove(operationPanel);
        operationPanel = panel;
        panel.setBounds(width / 5, 0, width, height);
        backgroundPanel.add(panel);

        repaint();
    }
}


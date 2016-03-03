package presentation;

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

    private static final JPanel panel;

    private static Dimension screen;

    private MainFrame() {
    }

    static {
        screen = Toolkit.getDefaultToolkit().getScreenSize();

        width = screen.width / 2;
        height = width * 3 / 4;

        frame = new MainFrame();
        panel = new JPanel();

        init();
        createUIComponents();
    }

    public static JFrame getMainFrame() {
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
        frame.setContentPane(panel);
        frame.setLayout(null);
    }

    private static void createUIComponents() {
        MenuPanel menuPanel = new MenuPanel();
        menuPanel.setBounds(0, 0, frame.getWidth() / 4, frame.getHeight());

        panel.add(menuPanel);
    }
}


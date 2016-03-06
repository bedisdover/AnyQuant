package presentation.frame;

import javax.swing.*;

import java.awt.*;

import static presentation.frame.MainFrame.getMainFrame;

/**
 * Created by 宋益明 on 16-3-6.
 *
 * 设置窗体
 */
public final class SettingsFrame extends JDialog {

    private JPanel panel;

    public SettingsFrame() {
        super(getMainFrame(), "设 置", true);
        panel = new JPanel();

        init();
        createUIComponents();
    }

    private void init() {
        setSize(getMainFrame().getWidth(), getMainFrame().getHeight());
        setLocationRelativeTo(getMainFrame());
        setContentPane(panel);
    }

    private void createUIComponents() {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.lightGray);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}

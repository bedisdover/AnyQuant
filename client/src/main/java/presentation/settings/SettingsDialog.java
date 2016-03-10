package presentation.settings;

import javax.swing.*;

import static presentation.frame.MainFrame.getMainFrame;

/**
 * Created by 宋益明 on 16-3-7.
 * <p>
 * 设置窗体,拥有者为MainFrame
 */
public class SettingsDialog extends JDialog {

    /**
     * Create the dialog.
     */
    public SettingsDialog() {
        super(getMainFrame(), true);

        init();
    }

    /**
     * 初始化
     */
    private void init() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(450, 340);
        setLocationRelativeTo(getMainFrame());
        setResizable(false);

        SettingsPanel panel = new SettingsPanel();
        setContentPane(panel);
    }
}

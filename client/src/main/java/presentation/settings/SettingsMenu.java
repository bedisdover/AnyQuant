package presentation.settings;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by 宋益明 on 16-3-7.
 *
 * 设置窗体菜单栏
 */
public class SettingsMenu extends JPanel {

    private JButton btnUI;

    SettingsMenu() {
        init();
        createUIComponents();
        addListeners();
    }

    private void init() {
        setLayout(null);
        setBackground(Color.gray);
    }

    private void createUIComponents() {
        btnUI = new JButton("界  面");
    }

    private void addListeners() {
        
    }
}

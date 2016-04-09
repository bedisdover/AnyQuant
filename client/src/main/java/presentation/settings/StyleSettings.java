package presentation.settings;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by 宋益明 on 16-4-9.
 *
 * 系统风格设置
 */
class StyleSettings extends JPanel {

    StyleSettings() {
        init();
        createUIComponents();
        addListeners();
    }

    private void init() {
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setLayout(new BorderLayout());
    }

    private void createUIComponents() {
        addItemPanel();
    }

    private void addItemPanel() {
        JPanel itemPanel = new JPanel();
        add(itemPanel, BorderLayout.NORTH);
        itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));

        JLabel label = new JLabel("风 格：");
        itemPanel.add(label);

        JCheckBox nimbus = new JCheckBox(" Nimbus");
        itemPanel.add(nimbus);

        JCheckBox beautyEye = new JCheckBox(" 古典");
        itemPanel.add(beautyEye);
    }

    private void addListeners() {

    }
}

package presentation.settings;

import config.SystemConfig;
import org.dom4j.DocumentException;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.net.MalformedURLException;

/**
 * Created by 宋益明 on 16-4-9.
 *
 * 系统风格设置
 */
public class StyleSettings extends JPanel {

    StyleSettings() {
        init();
        createUIComponents();
        addListeners();
    }

    private void init() {
        setLayout(new BorderLayout());
        setBorder(new BevelBorder(BevelBorder.LOWERED));
    }

    private void createUIComponents() {
        addItemPanel();
        addPreviewPanel();
    }

    /**
     * 添加选项面板
     */
    private void addItemPanel() {
        JPanel itemPanel = new JPanel();
        add(itemPanel, BorderLayout.NORTH);
        itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        JLabel label = new JLabel("风 格：");
        itemPanel.add(label);

        JRadioButton nimbus = new JRadioButton(" Nimbus");
        itemPanel.add(nimbus);

        JRadioButton beautyEye = new JRadioButton(" 古典");
        itemPanel.add(beautyEye);

        ButtonGroup group = new ButtonGroup();

        group.add(nimbus);
        group.add(beautyEye);

        try {
            String style = SystemConfig.getStyle();
            if (style.equals("Nimbus")) {
                nimbus.setSelected(true);
            } else if (style.equals("BeautyEye")) {
                beautyEye.setSelected(true);
            }
        } catch (MalformedURLException | DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加预览面板
     */
    private void addPreviewPanel() {

    }

    private void addListeners() {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        System.out.println(1);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);

        System.out.println(2);
    }
}

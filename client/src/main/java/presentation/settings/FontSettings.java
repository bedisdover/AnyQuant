package presentation.settings;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by 宋益明 on 16-3-11.
 * <p>
 * 字体设置面板
 * 可设置字体样式,大小
 * 并可进行字体预览
 */
public class FontSettings extends JPanel {

    /**
     * 字体列表
     */
    private JComboBox<String> fontList;

    /**
     * 字体大小
     */
    private JTextField fontSize;

    /**
     * 字体预览框
     */
    private JTextArea preview;

    FontSettings() {
        init();
        createUIComponents();
        addListeners();
    }

    /**
     * 初始化
     */
    private void init() {
        setLayout(null);
        setBorder(new BevelBorder(BevelBorder.LOWERED));
    }

    /**
     * 创建组件
     */
    private void createUIComponents() {
        JLabel labelName = new JLabel("字 体");
        JLabel labelSize = new JLabel("大 小");
        fontList = new JComboBox<>(GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames());
        fontSize = new JTextField(16);

        preview = new JTextArea(
                "我希望每天早晨叫我起床的不是闹钟而是梦想\n\n" +
                "abcdefghijklmnopqrstuvwxyz 0123456789 (){}[]\n" +
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ +-*/= .,;:!? #&$%@|^\n\n"
        );

        add(labelName);
        add(labelSize);
    }

    /**
     * 添加事件监听器
     * 当字体发生变化时,在预览框中显示字体效果
     */
    private void addListeners() {

    }
}

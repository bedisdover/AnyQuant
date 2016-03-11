package presentation.settings;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
        fontSize = new JTextField();

        preview = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(preview,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        labelName.setBounds(20, 20, 50, 30);
        labelSize.setBounds(220, 20, 50, 30);
        fontList.setBounds(60, 20, 140, 30);
        fontSize.setBounds(260, 20, 80, 30);
        scrollPane.setBounds(20, 80, 320, 180);

        //TODO 字体
        fontList.setSelectedItem("Courier 10 Pitch");
        fontSize.setText("16");

        preview.setText(
                "我希望每天早晨叫我起床的不是闹钟而是梦想\n" +
                        "---------------------------------\n" +
                        "努力不是因为性格使然而是因为以前装过的B\n\n" +
                        "abcdefghijklmnopqrstuvwxyz 0123456789 (){}[]\n" +
                        "ABCDEFGHIJKLMNOPQRSTUVWXYZ +-*/= .,;:!? #&$%@|^\n\n"
        );


        add(labelName);
        add(labelSize);
        add(fontList);
        add(fontSize);
        add(scrollPane);
    }

    /**
     * 添加事件监听器
     * 当字体发生变化时,在预览框中显示字体效果
     */
    private void addListeners() {
        fontList.addItemListener(e -> changeFont());

        fontSize.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changeFont();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeFont();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeFont();
            }
        });
    }

    /**
     * 改变预览框内的字体
     */
    private void changeFont() {
        try {
            int size = Integer.parseInt(fontSize.getText());
            preview.setFont(new Font((String) fontList.getSelectedItem(),
                    Font.PLAIN, size));
        } catch (NumberFormatException e){
            e.printStackTrace();
        }

        repaint();
    }
}

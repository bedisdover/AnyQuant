package presentation;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

/**
 * Created by 宋益明 on 16-3-11.
 * <p>
 * 测试设置全局字体
 */
public class FontTestFrame extends JFrame {

    public FontTestFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        JMenu m = new JMenu("文件");
        m.add(new JMenuItem("打开"));
        menuBar.add(m);
        this.setJMenuBar(menuBar);
        this.add(new JButton("测试 "));
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Font font = new Font("宋体", Font.PLAIN, 40);
        UIManager.put("Button.font", font);
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);
        new FontTestFrame();
    }

    /**
     * 为全部组件设置字体
     * @param font
     */
    public static void initGlobalFontSetting(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource)
                UIManager.put(key, fontRes);
        }
    }
}

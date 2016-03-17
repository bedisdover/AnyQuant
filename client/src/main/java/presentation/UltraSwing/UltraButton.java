package presentation.UltraSwing;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 宋益明 on 16-3-17.
 * <p>
 * 自定义按钮
 * 可以根据所显示的文本自动调节大小
 */
public class UltraButton extends JButton {

    /**
     * 初始文本
     */
    private String text;

    /**
     * 按钮的宽高
     */
    public int width, height;

    public UltraButton(String text) {
        super(text);

        this.text = text;

        init();
    }

    /**
     * 初始化
     */
    private void init() {
        Font font = getFont();
        width = text.length() * font.getSize() + 30;
        height = font.getSize() + 15;
        setPreferredSize(new Dimension(width, height));
    }
}

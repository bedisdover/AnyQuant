package presentation.UltraSwing;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 宋益明 on 16-4-5.
 * <p>
 * 自定义Label对象
 */
public class UltraLabel extends JLabel {

    /**
     * 字体大小
     */
    private int fontSize;

    /**
     * 文本
     */
    private String text;

    public UltraLabel() {
        this("");
    }

    public UltraLabel(String text) {
        this(text, 12);
    }

    public UltraLabel(Icon icon) {
        super(icon);
    }

    /**
     * 创建具有指定文本和字体大小的Label
     *
     * @param text 显示文本
     * @param fontSize 字体大小
     */
    public UltraLabel(String text, int fontSize) {
        super(text);

        this.text = text;
        this.fontSize = fontSize;

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(fontSize * text.length() * 2 / 3, fontSize + 10);
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        this.text = text;
    }
}

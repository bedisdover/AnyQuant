package config;

import main.AnyQuant;
import org.dom4j.Element;

import java.awt.*;

/**
 * Created by 宋益明 on 16-3-23.
 * <p>
 * 字体配置
 */
public class FontConfig {

    private Element fontInfo;

    /**
     * 字体名称
     */
    private String name;

    /**
     * 字体样式
     * 与Font一致,用int存储
     */
    private int style;

    /**
     * 字体大小
     */
    private int size;

    FontConfig(Element font) {
        this.fontInfo = font;
    }

    public Font getFontInfo() {
        try {
            name = fontInfo.attributeValue("name");
            style = Integer.parseInt(fontInfo.attributeValue("style"));
            size = Integer.parseInt(fontInfo.attributeValue("size"));
        } catch (Exception e) {
            return new Font("宋体", Font.PLAIN, 14);
        }

        return new Font(name, style, size);
    }

    public void changeFont(Font font) {
        fontInfo.addAttribute("name", font.getName());
        fontInfo.addAttribute("style", font.getStyle() + "");
        fontInfo.addAttribute("size", font.getSize() + "");

        AnyQuant.initGlobalFontSetting(font);
    }
}

package config;

import org.dom4j.Attribute;
import org.dom4j.Element;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 宋益明 on 16-3-6.
 * <p>
 * 窗体配置类
 */
public class FrameConfig {

    /**
     * mainFrame节点
     */
    private Element element;

    /**
     * 构造函数
     * 访问权限为protected,只能在SystemConfig中声明FrameConfig对象
     * 程序其他包可使用
     * FrameConfig frameConfig = SystemConfig.getFrameConfig();
     * 来获取此类对象
     *
     * @param element 从SystemConfig中传递过来的配置信息
     * @see SystemConfig
     */
    FrameConfig(Element element) {
        this.element = element;
    }

    /**
     * 获得主窗体默认边界大小
     *
     * @return 主窗体大小
     */
    public Rectangle getDefaultBounds() {
        Element defaultBounds = element.element("default");
        int x, y, width, height;
        try {
            x = Integer.parseInt(defaultBounds.attributeValue("x"));
            y = Integer.parseInt(defaultBounds.attributeValue("y"));
            width = Integer.parseInt(defaultBounds.attributeValue("width"));
            height = Integer.parseInt(defaultBounds.attributeValue("height"));
        } catch (NumberFormatException e) {
            return new Rectangle(getMinimumSize());
        }

        return new Rectangle(x, y, width, height);
    }

    /**
     * 获得上次关闭程序时,主窗体边界大小
     *
     * @return 主窗体大小
     */
    public Rectangle getLastBounds() {
        Element lastBounds = element.element("last");
        int x, y, width, height;
        try {
            x = Integer.parseInt(lastBounds.attributeValue("x"));
            y = Integer.parseInt(lastBounds.attributeValue("y"));
            width = Integer.parseInt(lastBounds.attributeValue("width"));
            height = Integer.parseInt(lastBounds.attributeValue("height"));
        } catch (NumberFormatException e) {
            return new Rectangle(getMinimumSize());
        }

        return new Rectangle(x, y, width, height);
    }

    /**
     * 获得窗体最小尺寸
     *
     * @return 窗体最小尺寸
     */
    public Dimension getMinimumSize() {
        Element minimumSize = element.element("minimumSize");
        int width, height;
        try {
            width = Integer.parseInt(minimumSize.attributeValue("width"));
            height = Integer.parseInt(minimumSize.attributeValue("height"));
        } catch (NumberFormatException e) {
            return new Dimension(683, 512);
        }

        return new Dimension(width, height);
    }

    /**
     * 存储主窗体边界大小
     *
     * @param bounds 关闭程序时,窗体的边界
     */
    public void storeBounds(Rectangle bounds) {
        Element lastBounds = element.element("last");
        Attribute x = lastBounds.attribute("x");
        Attribute y = lastBounds.attribute("y");
        Attribute width = lastBounds.attribute("width");
        Attribute height = lastBounds.attribute("height");

        x.setValue(bounds.getX() + "");
        y.setValue(bounds.getY() + "");
        width.setValue(bounds.getWidth() + "");
        height.setValue(bounds.getHeight() + "");

        List<Attribute> list = new ArrayList<>();

        list.add(x);
        list.add(y);
        list.add(width);
        list.add(height);

        lastBounds.setAttributes(list);
    }
}
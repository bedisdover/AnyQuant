package config;

import org.dom4j.Attribute;
import org.dom4j.Element;

import java.awt.*;
import java.util.Iterator;

/**
 * Created by 宋益明 on 16-3-6.
 * <p>
 * 窗体配置类
 */
public class FrameConfig {
    private Element element;

    /**
     * 构造函数
     * 访问权限为protected,只能在SystemConfig中声明FrameConfig对象
     * 程序其他包可使用
     *      FrameConfig frameConfig = SystemConfig.getFrameConfig();
     * 来获取此类对象
     *
     * @param element 从SystemConfig中传递过来的配置信息
     * @see SystemConfig
     */
    FrameConfig(Element element) {
        this.element = element;
    }

    public Rectangle getBounds() {
        Iterator<Attribute> iterator = element.attributeIterator();
        int x = Integer.parseInt(iterator.next().getValue());
        int y = Integer.parseInt(iterator.next().getValue());
        int width = Integer.parseInt(iterator.next().getValue());
        int height = Integer.parseInt(iterator.next().getValue());

        return new Rectangle(x, y, width, height);
    }
}

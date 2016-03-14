package config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Created by 宋益明 on 16-3-14.
 *
 * 系统配置类
 */
public class SystemConfig {
    private static Element root;

    private void init() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read("src/main/resources/config.xml");
        root = document.getRootElement();
    }

    public FrameConfig getFrameConfig() throws DocumentException {
        init();
        Element element = root.element("presentation").element("mainFrame");
        return new FrameConfig(element);
    }
}

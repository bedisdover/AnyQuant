package config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.net.MalformedURLException;

/**
 * Created by 宋益明 on 16-3-14.
 * <p>
 * 系统配置类
 */
public class SystemConfig {

    /**
     * 配置文件路径
     */
    private static final File FILE_NAME = new File("client/src/main/resources/config.xml");

    /**
     * 不知为何,单元测试和运行程序时使用的文件路径居然不一致
     * 我也是醉了,只能强行写两个文件路径了,为此又添加了一个
     * 布尔类型变量判断是否处于测试阶段......
     */
    private static final File TEST_FILE = new File("src/main/resources/config.xml");

    /**
     * 强行加上去的变量
     */
    boolean test = false;

    /**
     * 根节点
     */
    private static Element root;

    /**
     * 初始化
     *
     * @throws DocumentException
     */
    private void init() throws DocumentException, MalformedURLException {
        SAXReader reader = new SAXReader();
        Document document;
        if (test) {
            document = reader.read(TEST_FILE);
        } else {
            document = reader.read(FILE_NAME);
        }

        root = document.getRootElement();
    }

    /**
     * 获得主界面配置
     *
     * @return 主界面配置领域对象
     * @throws DocumentException
     */
    public FrameConfig getFrameConfig() throws DocumentException, MalformedURLException {
        init();

        Element presentation = root.element("presentation");
        Element mainFrame = presentation.element("mainFrame");

        return new FrameConfig(mainFrame);
    }
}

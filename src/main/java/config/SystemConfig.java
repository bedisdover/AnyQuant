package config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
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
    private static final File FILE_NAME = new File("src/main/resources/config.xml");

    /**
     * 不知为何,单元测试和运行程序时使用的文件路径居然不一致
     * 我也是醉了,只能强行写两个文件路径了,为此又添加了一个
     * 布尔类型变量判断是否处于测试阶段......
     */
    private static final File TEST_FILE = new File("src/main/resources/config.xml");

    /**
     * 强行加上去的变量
     */
    public static boolean test = false;

    /**
     * config.xml对应的document对象
     */
    private static Document document;

    /**
     * 根节点
     */
    private static Element root;

    private SystemConfig() {}

    /**
     * 初始化
     *
     * @throws DocumentException
     */
    private static void init() throws DocumentException, MalformedURLException {
        SAXReader reader = new SAXReader();
        if (test) {
            document = reader.read(TEST_FILE);
        } else {
            document = reader.read(FILE_NAME);
        }

        root = document.getRootElement();
    }

    /**
     * 获取系统样式
     *
     * @return 样式名称
     */
    public static String getStyle() throws MalformedURLException, DocumentException {
        init();

        Element presentation = root.element("presentation");

        return presentation.attributeValue("style");
    }


    /**
     * 获得主界面配置
     *
     * @return 主界面配置领域对象
     * @throws DocumentException
     */
    public static FrameConfig getFrameConfig() throws DocumentException, MalformedURLException {
        init();

        Element presentation = root.element("presentation");
        Element mainFrame = presentation.element("mainFrame");

        return new FrameConfig(mainFrame);
    }

    /**
     * 获得字体设置，可设置字体样式、大小
     *
     * @return 字体设置领域对象
     * @throws MalformedURLException
     * @throws DocumentException
     */
    public static FontConfig getFontConfig() throws MalformedURLException, DocumentException {
        init();

        Element presentation = root.element("presentation");
        Element font = presentation.element("font");

        return new FontConfig(font);
    }

    /**
     * 获得菜单栏配置，可自动隐藏菜单栏
     *
     * @return 菜单栏配置领域对象
     * @throws MalformedURLException
     * @throws DocumentException
     */
    public static MenuPanelConfig getMenuPanelConfig()
            throws MalformedURLException, DocumentException {

        init();

        Element presentation = root.element("presentation");
        Element panel = presentation.element("panel");
        Element menuPanel = panel.element("MenuPanel");

        return new MenuPanelConfig(menuPanel);
    }

    /**
     * 获得股票数据配置
     *
     * @return 数据配置领域对象
     * @throws MalformedURLException
     * @throws DocumentException
     */
    public static StockDataConfig getStockDataConfig() throws MalformedURLException, DocumentException {
        init();

        Element presentation = root.element("presentation");
        Element data = presentation.element("data");

        return new StockDataConfig(data.element("StockData"));
    }

    /**
     * 获得大盘指数数据配置
     *
     * @return 数据配置领域对象
     * @throws MalformedURLException
     * @throws DocumentException
     */
    public static IndexDataConfig getIndexDataConfig() throws MalformedURLException, DocumentException {
        init();

        Element presentation = root.element("presentation");
        Element data = presentation.element("data");

        return new IndexDataConfig(data.element("IndexData"));
    }

    /**
     * 存储各种配置
     */
    public static void storeXML() {
        try {
            FileWriter file = new FileWriter(FILE_NAME);
            XMLWriter writer = new XMLWriter(file);

            writer.write(document);

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

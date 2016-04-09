package config;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.dom4j.Element;

/**
 * Created by 宋益明 on 16-4-9.
 *
 * 菜单栏配置类
 */
public class MenuPanelConfig {

    private Element menuPanel;

    private Boolean autoHidden;

    MenuPanelConfig(Element config) {
        menuPanel = config;

        autoHidden = Boolean.valueOf(config.attributeValue("autoHidden"));
    }

    public boolean isAutoHidden() {
        return autoHidden;
    }

    /**
     * 改变自动隐藏属性
     */
    public void changeAutoHidden() {
        autoHidden = !autoHidden;
        menuPanel.addAttribute("autoHidden", autoHidden + "");
    }
}

package presentation;

import java.awt.*;

/**
 * Created by 宋益明 on 16-3-11.
 *
 * 测试获取系统所有字体
 */
public class FontTest {

    public static void getFonts() {

        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames();//获得当前系统字体

        for (int i = 0; i < fontNames.length; i++) {//输出所有字体
            System.out.println(fontNames[i]);
        }
    }

    public static void main(String[] args) {
        getFonts();
    }
}

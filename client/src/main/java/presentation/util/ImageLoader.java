package presentation.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by 宋益明 on 16-3-3.
 * <p>
 * 图片加载类,负责所有图片的加载
 */
public class ImageLoader {
    private static final String IMAGE_PATH = "client/src/main/resources/images/";

    /**
     * 设置
     */
    public static Image settings;
    public static Image skin;
    public static Image icon;
    public static Image background;
    public static Image on;
    public static Image off;
    public static Image nothing;

    /**
     * 添加自选图标
     */
    public static ImageIcon addPortfolio;

    /**
     * “涨”图标
     */
    public static ImageIcon increase;

    static {
        try {
            settings = ImageIO.read(new File(IMAGE_PATH + "settings.png"));
            skin = ImageIO.read(new File(IMAGE_PATH + "skin.png"));
            icon = ImageIO.read(new File(IMAGE_PATH + "icon.png"));
            background = ImageIO.read(new File(IMAGE_PATH + "bg.jpg"));
            on = ImageIO.read(new File(IMAGE_PATH + "on.png"));
            off = ImageIO.read(new File(IMAGE_PATH + "off.png"));
            nothing = ImageIO.read(new File(IMAGE_PATH + "nothing.png"));

            addPortfolio = new ImageIcon(IMAGE_PATH + "addPortfolio.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

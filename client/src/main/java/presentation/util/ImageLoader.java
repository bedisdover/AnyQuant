package presentation.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by song on 16-3-3.
 * <p>
 * 图片加载类,负责所有图片的加载
 */
public class ImageLoader {
    private static final String IMAGE_PATH = "client/src/main/resources/images/";

    public static Image settings;
    public static Image skin;
    public static Image icon;
    public static Image background;
    public static Image on;
    public static Image off;
    public static Image nothing;

    static {
        try {
            settings = ImageIO.read(new File(IMAGE_PATH + "settings.png"));
            skin = ImageIO.read(new File(IMAGE_PATH + "skin.png"));
            icon = ImageIO.read(new File(IMAGE_PATH + "icon.png"));
            background = ImageIO.read(new File(IMAGE_PATH + "bg.jpg"));
            on = ImageIO.read(new File(IMAGE_PATH + "on.png"));
            off = ImageIO.read(new File(IMAGE_PATH + "off.png"));
            nothing = ImageIO.read(new File(IMAGE_PATH + "nothing.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

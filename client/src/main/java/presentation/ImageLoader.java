package presentation;

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

    public static Image menu;
    public static Image settings;
    public static Image skin;

    static {
        try {
            menu = ImageIO.read(new File(IMAGE_PATH + "menu.gif"));
            settings = ImageIO.read(new File(IMAGE_PATH + "settings.png"));
            skin = ImageIO.read(new File(IMAGE_PATH + "skin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

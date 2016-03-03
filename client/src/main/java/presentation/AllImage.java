package presentation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by song on 16-3-3.
 */
public class AllImage {
    private static final String IMAGE_PATH = "client/src/main/resources/images/";

    public static Image menu;
    public static Image settings;

    static {
        try {
            menu = ImageIO.read(new File(IMAGE_PATH + "menu.gif"));
            settings = ImageIO.read(new File(IMAGE_PATH + "settings.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

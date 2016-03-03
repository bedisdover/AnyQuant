package presentation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by song on 16-3-3.
 */
public class AllImage {
    public static Image menu;

    static {
        try {
            menu = ImageIO.read(new File("client/src/main/resources/menu.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

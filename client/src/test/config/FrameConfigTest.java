package config;

import org.dom4j.DocumentException;
import org.junit.Test;

import java.awt.*;
import java.net.MalformedURLException;

import static org.junit.Assert.assertEquals;

/**
 * Created by 宋益明 on 16-3-14.
 *
 * 测试成功
 */
public class FrameConfigTest {
    private static FrameConfig frameConfig;

    static {
        try {
            SystemConfig systemConfig = new SystemConfig();
            systemConfig.test = true;
            frameConfig = systemConfig.getFrameConfig();
        } catch (DocumentException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetDefaultBounds() throws Exception {
        Rectangle rectangle = frameConfig.getDefaultBounds();
        assertEquals(0.0, rectangle.getX(), 0);
        assertEquals(0.0, rectangle.getY(), 0);
        assertEquals(683, rectangle.getWidth(), 0);
        assertEquals(512, rectangle.getHeight(), 0);
    }

    /**
     * 程序运行一次后,lastBounds可能会变化
     *
     * @throws Exception
     */
    @Test
    public void testGetLastBounds() throws Exception {
        Rectangle lastBounds = frameConfig.getLastBounds();
        assertEquals(342, lastBounds.getX(), 0);
        assertEquals(140, lastBounds.getY(), 0);
        assertEquals(683, lastBounds.getWidth(), 0);
        assertEquals(512, lastBounds.getHeight(), 0);
    }

    @Test
    public void testGetMinimumSize() throws Exception {
        Dimension minimumSize = frameConfig.getMinimumSize();
        assertEquals(683, minimumSize.getWidth(), 0);
        assertEquals(512, minimumSize.getHeight(), 0);
    }
}
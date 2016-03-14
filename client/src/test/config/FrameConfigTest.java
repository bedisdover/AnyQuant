package config;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by 宋益明 on 16-3-14.
 *
 * 测试成功
 */
public class FrameConfigTest {
    @Test
    public void testGetBounds() throws Exception {
        FrameConfig frameConfig = new SystemConfig().getFrameConfig();
        Rectangle rectangle = frameConfig.getBounds();
        assertEquals(0.0, rectangle.getX(), 0);
        assertEquals(0.0, rectangle.getY(), 0);
        assertEquals(632, rectangle.getWidth(), 0);
        assertEquals(512, rectangle.getHeight(), 0);
    }
}
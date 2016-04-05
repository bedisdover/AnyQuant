package presentation.UltraSwing;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by 宋益明 on 16-4-5.
 *
 * 测试UltraLabel
 */
public class UltraLabelTest {

    @Test
    public void testPreferredSize() throws Exception {
        UltraLabel label = new UltraLabel("test");
        assertEquals(label.getPreferredSize(), new Dimension(100, 100));
    }
}
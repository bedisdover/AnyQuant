package UITest;

import javax.swing.*;
import java.awt.*;

/**
 * Created by song on 16-3-3.
 */
public class PanelTest extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}

package presentation.panel.info;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * Created by 宋益明 on 16-4-12.
 */
public class DetailedInfoPanelTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setBounds(100, 100, 700, 500);

        frame.add(new DetailedInfoPanel((JPanel) frame.getContentPane(), "sh600015"));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
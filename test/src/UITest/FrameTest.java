package UITest;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by song on 16-3-3.
 */
public class FrameTest extends JFrame {
    public static void main(String[] args) {
        FrameTest frame = new FrameTest();

        frame.setVisible(true);
    }

    public FrameTest() {
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        createUIComponents();
        addListener();
    }

    public void createUIComponents() {
        panel = new PanelTest();

        add(panel);
    }

    public void addListener() {
        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                showPanel();
//            }

            @Override
            public void mouseClicked(MouseEvent e) {
//                showPanel();
                panel.setBounds(100, 0, 100 + 1 * 10, 300);
//                repaint();
            }
        });
    }

    public void showPanel() {
        new ThreadTest().run();
    }

    private class ThreadTest extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    panel.setBounds(100, 0, 100 + i * 10, 300);
                    repaint();
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private PanelTest panel;
}

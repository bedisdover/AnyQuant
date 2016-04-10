package presentation.panel;

import config.SystemConfig;
import org.dom4j.DocumentException;
import presentation.frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;

/**
 * Created by 宋益明 on 16-3-5.
 *
 * 背景面板
 * 负责绘制最底层的背景,可通过getBackdrop方法和setBackdrop方法获取或设置背景
 */
public class BackgroundPanel extends JPanel {

    /**w
     * 背景图片
     */
    private Image backdrop;

    private final float TRANSPARENCY = 0.5f;

    public BackgroundPanel(Image image) {
        this.backdrop = image;
        addListeners();
    }

    /**
     * 添加事件监听器
     */
    private void addListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (e.getX() < MainFrame.getMainFrame().getWidth() / 10) {
                    MainFrame.getMainFrame().showMenuPanel();
                }
            }

            public void mouseClicked(MouseEvent e) {
                try {//若未选择自动隐藏，结束
                    if (!SystemConfig.getMenuPanelConfig().isAutoHidden()) {
                        return;
                    }
                } catch (MalformedURLException | DocumentException e1) {
                    e1.printStackTrace();
                }
                if (e.getX() > MainFrame.MENU_WIDTH) {
                    MainFrame.getMainFrame().hideMenuPanel();
                }
            }
        });

        registerKeyboardAction(e -> MainFrame.getMainFrame().showMenuPanel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_M, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        registerKeyboardAction(e -> {
                try {//若未选择自动隐藏，结束
                    if (!SystemConfig.getMenuPanelConfig().isAutoHidden()) {
                        return;
                    }
                } catch (MalformedURLException | DocumentException e1) {
                    e1.printStackTrace();
                }

                MainFrame.getMainFrame().hideMenuPanel();
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        g.drawImage(backdrop, 0, 0, getWidth(), getHeight(), null);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, TRANSPARENCY));
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
    }

    public Image getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(Image backdrop) {
        this.backdrop = backdrop;
    }
}

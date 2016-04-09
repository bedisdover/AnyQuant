package presentation.panel;

import config.SystemConfig;
import org.dom4j.DocumentException;
import presentation.frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                MainFrame.getMainFrame().showMenuPanel();
            }
        });
    }

    /**
     * 隐藏菜单栏
     */
    public void hideMenu() {
        try {//若未选择自动隐藏，结束
            if (!SystemConfig.getMenuPanelConfig().isAutoHidden()) {
                return;
            }
        } catch (MalformedURLException | DocumentException e) {
            e.printStackTrace();
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getX() > MainFrame.MENU_WIDTH) {
                    MainFrame.getMainFrame().hideMenuPanel();
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    MainFrame.getMainFrame().hideMenuPanel();
                }
            }
        });
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

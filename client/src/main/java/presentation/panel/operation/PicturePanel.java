package presentation.panel.operation;

import presentation.frame.MainFrame;
import presentation.util.DateChooser;

import javax.swing.*;
import java.awt.*;

/**
 * Created by song on 16-3-2.
 *
 * 行情面板
 */
public class PicturePanel extends OperationPanel {
    /**
     * 日期距离菜单栏的位置
     */
    private static final int LOCATION_X = 70;
    /**
     * 日期距离上边框的距离
     */
    private static final int LOCATION_Y = 50;
    /**
     * 按钮等组件的宽度
     */
    private static final int WIDTH = 100;
    /**
     * 按钮等组件的高度
     */
    private static final int HEIGHT = 30;
    /**
     * 组件之间的距离
     */
    private static final int DISTANCE = 10;
    /**
     * 菜单栏的宽度
     */
    private static final int MENU_WIDTH = MainFrame.getMainFrame().getWidth() / 5;


    private JTextField searchInput;
    private JButton search;
    private DateChooser dateChooser;

    public PicturePanel() {
        init();
        createUIComponents();
        addListeners();
    }

    private void init() {

        this.setBounds(MENU_WIDTH, 0, MainFrame.getMainFrame().getWidth(), MainFrame.getMainFrame().getHeight());
    }

    private void createUIComponents() {
    }

    private void addListeners() {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }

}

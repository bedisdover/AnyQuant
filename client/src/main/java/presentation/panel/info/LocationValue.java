package presentation.panel.info;

import presentation.frame.MainFrame;

import java.awt.*;

/**
 * Created by 宋益明 on 16-4-5.
 *
 * 界面常量
 */
public class LocationValue {

    /**
     * 当前信息面板宽度，高度
     * 高度保持不变，宽度随界面变化而变化
     */
    public static int INFO_PANEL_WIDTH, INFO_PANEL_HEIGHT;

    /**
     * 名称面板的宽度，高度与INFO_PANEL_HEIGHT相同
     */
    public static int NAME_PANEL_WIDTH;

    /**
     * 外边距
     */
    public static final int MARGIN;

    /**
     * 内边距
     */
    public static final int PADDING;

    /**
     * 按钮宽度
     */
    public static final int BUTTON_WIDTH;

    /**
     * 按钮高度
     */
    public static final int BUTTON_HEIGHT;

    /**
     * 文本框宽度
     * 高度与按钮高度相同
     */
    public static final int TEXT_FIELD_WIDTH;

    static {
        //以下各值均为常量
        //当界面大小改变时,无需再次赋值
//        MARGIN = MainFrame.DEFAULT_WIDTH / 50;
//        PADDING = MainFrame.DEFAULT_WIDTH / 40;
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        if(dimension.width>1366){
            MARGIN = 20;
            PADDING = 22;
        }
        else{
            MARGIN = 13;
            PADDING = 17;
        }
        BUTTON_WIDTH = (PADDING + MARGIN) * 3;
        BUTTON_HEIGHT = MARGIN * 2;
        TEXT_FIELD_WIDTH = BUTTON_WIDTH + PADDING * 2;

        INFO_PANEL_HEIGHT = MARGIN * 5;
        NAME_PANEL_WIDTH = MARGIN * 10;
    }

    /**
     * 界面大小发生变化时，接到MainFrame通知，更新数值
     *
     * @see MainFrame
     */
    public static void updateValue() {
        INFO_PANEL_WIDTH = MainFrame.getMainFrame().getWidth() - MainFrame.menuPanel.getWidth();
    }
}

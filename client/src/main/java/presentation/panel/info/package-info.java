/**
 * Created by 宋益明 on 16-4-2.
 * <p>
 * 股票信息模块
 */
package presentation.panel.info;

import presentation.frame.MainFrame;

/**
 * 界面常量
 */
class LocationValue {

    /**
     * 主要信息面板宽度，高度
     */
    static int PANEL_WIDTH, PANEL_HEIGHT;

    /**
     * 外边距
     */
    static final int MARGIN;

    /**
     * 内边距
     */
    static final int PADDING;

    /**
     * 按钮宽度
     */
    static final int BUTTON_WIDTH;

    /**
     * 按钮高度
     */
    static final int BUTTON_HEIGHT;

    /**
     * 文本框宽度
     * 高度与按钮高度相同
     */
    static final int TEXT_FIELD_WIDTH;

    static {
        //以下各值均为常量
        //当界面大小改变时,无需再次赋值
        MARGIN = MainFrame.DEFAULT_WIDTH / 50;
        PADDING = MainFrame.DEFAULT_WIDTH / 40;
        BUTTON_WIDTH = (PADDING + MARGIN) * 3;
        BUTTON_HEIGHT = MARGIN * 2;
        TEXT_FIELD_WIDTH = BUTTON_WIDTH + PADDING * 2;

        PANEL_WIDTH = MARGIN * 2 + BUTTON_WIDTH;
        PANEL_HEIGHT = MARGIN * 5;
    }
}
package presentation.panel.info;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by 宋益明 on 16-3-31.
 *
 * 大盘指数简要信息面板
 */
public class IndexBriefInfoPanel extends JPanel {

}

/**
 * 大盘指数名称面板
 * 包含中文名称与代码
 */
class IndexNamePanel extends JPanel {
    IndexNamePanel(String name, String id) {
        setLayout(new FlowLayout(FlowLayout.LEADING, 30, 10));
        setBorder(new BevelBorder(BevelBorder.RAISED,
                Color.black, Color.white, Color.red, Color.blue));

        createUIComponents(name, id);
    }

    private void createUIComponents(String name, String id) {
        JLabel labelName = new JLabel(name);
        JLabel labelID = new JLabel(id);

//        labelName.setBounds();

        add(labelName);
        add(labelID);
    }
}

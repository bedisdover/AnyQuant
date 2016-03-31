package presentation.panel.info;

import bl.ShowIndexData;
import vo.IndexVO;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by 宋益明 on 16-3-28.
 * <p>
 * 大盘指数面板
 * 用于展示大盘指数的详细数据
 */
public class IndexDataPanel extends DataPanel {

    public IndexDataPanel(JPanel parent) {
        super(parent);

        showData();
    }

    private void showData() {
        IndexVO index = null;
        try {
            index = new ShowIndexData().getLatestIndexData();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(IndexDataPanel.this, "请检查网络连接！");
        }
        createTable(index);
    }
}

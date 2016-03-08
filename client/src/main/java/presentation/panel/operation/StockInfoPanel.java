package presentation.panel.operation;

import presentation.panel.info.InfoPanel;
import vo.StockVO;

import javax.swing.*;

/**
 * Created by 宋益明 on 16-3-8.
 * <p>
 * 股票详细信息面板
 * 通过表格展现股票的所有信息
 * 通过图表展示数据变化情况
 */
public class StockInfoPanel extends InfoPanel {

    public StockInfoPanel(JPanel parent, StockVO stock) {
        super(parent, stock);
    }
}

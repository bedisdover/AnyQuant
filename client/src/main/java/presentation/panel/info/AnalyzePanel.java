package presentation.panel.info;

import bl.CalculateIndex;
import presentation.panel.operation.OperationPanel;
import vo.IndexVO;
import vo.StockVO;
import vo.TheIndexVO;

/**
 * Created by 宋益明 on 16-4-13.
 *
 * 分析面板
 * 包含各种指标及分析
 */
public class AnalyzePanel extends OperationPanel {

    /**
     * 指标值对象
     */
    private TheIndexVO index;

    public AnalyzePanel(StockVO stock) {
        this.index = new CalculateIndex().getTheIndex(stock);
    }

    public AnalyzePanel(IndexVO index) {
        this.index = new CalculateIndex().getTheIndex(index);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void createUIComponents() {

    }
}

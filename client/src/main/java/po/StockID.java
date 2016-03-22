package po;

import java.util.StringTokenizer;

/**
 * Created by 宋益明 on 16-3-22.
 *
 * 股票"代码-名称"集合对象
 * 包括股票代码,中文名称以及是否在榜单显示
 * 其中代码和名称无法修改
 */
public class StockID {

    /**
     * 股票代码
     */
    private final String id;

    /**
     * 股票名号
     */
    private final String name;

    /**
     * 是否在榜单显示
     */
    private boolean display;

    /**
     * 根据原始记录(代码 名称 是否显示)构造对象
     *
     * @param stock 记录信息
     */
    public StockID(String stock) {
        StringTokenizer tokenizer = new StringTokenizer(stock);

        id = tokenizer.nextToken();
        name = tokenizer.nextToken();
        display = Boolean.valueOf(tokenizer.nextToken());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isDisplay() {
        return display;
    }

    /**
     * 改变显示状态
     */
    public void exchangeDisplay() {
        this.display = !display;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + display + "\n";
    }
}
package bl;

import database.SelfSelectStockManage;

import java.util.List;

/**
 * Created by zcy on 2016/5/16.
 * 对用户自选股进行管理
 */
public class ManageSelfSelectStock {
    /**
     * @param userid 用户账号
     * @return List<String>
     * 得到该用户的所有自选股
     */
    public List<String> getAllInterestedStock(String userid){
        SelfSelectStockManage selfSelectStockManage = new SelfSelectStockManage();
        List<String> stocks = selfSelectStockManage.getAllInterestedStock(userid);
        return stocks;
    }

    /**
     * @param userid 用户账号
     * @param stockid 股票代号
     * 添加自选股
     */
    public void addSelfSelectStock(String userid,String stockid){
        SelfSelectStockManage selfSelectStockManage = new SelfSelectStockManage();
        selfSelectStockManage.addSelfSelectStock(userid,stockid);
    }

    /**
     * @param userid 用户账号
     * @param stockid 股票代号
     * 删除自选股
     */
    public void removeSelfSelectStock(String userid,String stockid){
        SelfSelectStockManage selfSelectStockManage = new SelfSelectStockManage();
        selfSelectStockManage.removeSelfSelectStock(userid,stockid);
    }
}

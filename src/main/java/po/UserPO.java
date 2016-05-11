package po;

import java.util.List;

/**
 * Created by zcy on 2016/5/9.
 *
 */
public class UserPO {
    /**
     * 用户账号
     */
    private String id;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户的自选股
     */
    private List<String> stockList;

    public UserPO(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getStockList() {
        return stockList;
    }

    public void setStockList(List<String> stockList) {
        this.stockList = stockList;
    }
}

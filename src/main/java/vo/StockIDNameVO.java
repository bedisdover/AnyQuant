package vo;

import java.io.Serializable;

/**
 * Created by zcy on 2016/5/14.
 *
 */
public class StockIDNameVO implements Serializable{
    /**
     * 股票id
     */
    private String id;
    /**
     * 股票名称
     */
    private String name;

    public StockIDNameVO(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package vo;

import po.BusinessNewsPO;

import java.io.Serializable;

/**
 * Created by zcy on 2016/5/14.
 *
 */
public class BusinessNewsVO implements Serializable{
    /**
     * 行业新闻标题
     */
    private String title;
    /**
     * 行业新闻正文
     */
    private String content;
    /**
     * 行业新闻日期
     */
    private String date;

    public BusinessNewsVO(){

    }

    public BusinessNewsVO(BusinessNewsPO businessNewsPO){
        title = businessNewsPO.getTitle();
        content = businessNewsPO.getContent();
        date = businessNewsPO.getDate();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

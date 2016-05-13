package po;

/**
 * Created by zcy on 2016/5/12.
 *
 */
public class StockNewsPO {
    /**
     * 对应的股票ID
     */
    private String id;
    /**
     * 新闻标题
     */
    private String title;
    /**
     * 新闻正文
     */
    private String content;
    /**
     * 新闻时间
     */
    private String date;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

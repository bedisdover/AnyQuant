package dataservice;

import java.io.IOException;

/**
 * Created by user on 2016/3/19.
 */
public interface ReadDataService {
    public String getData(String url) throws IOException;
    public String getCurrentData(String url) throws IOException;
    public String parseJSON(String jsonStr, String key);
    public String[] parseJSON_array(String jsonStr, String key);
    public String[] parseJson(String jsonStr, String key, String subKey);
    public String[] parseJson2(String jsonStr, String key, String subKey);
}

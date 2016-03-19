package dataservice;

import po.IndexPO;

import java.io.IOException;

/**
 * Created by user on 2016/3/9.
 */
public interface GetIndexDataService {
    IndexPO getLatestIndexData() throws IOException;
}

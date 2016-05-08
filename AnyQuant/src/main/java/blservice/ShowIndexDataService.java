package blservice;

import vo.IndexVO;

import java.io.IOException;

/**
 * Created by user on 2016/3/19.
 */
public interface ShowIndexDataService {
    public IndexVO getLatestIndexData() throws IOException;
}

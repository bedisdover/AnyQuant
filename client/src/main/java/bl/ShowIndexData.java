package bl;

import blservice.ShowIndexDataService;
import data.GetIndexData;
import dataservice.GetIndexDataService;
import po.IndexPO;
import vo.IndexVO;

import java.io.IOException;

/**
 * Created by user on 2016/3/9.
 */
public class ShowIndexData implements ShowIndexDataService{

    public IndexVO getLatestIndexData() throws IOException {
        GetIndexDataService getIndexDataService = new GetIndexData();
        IndexPO indexPO = getIndexDataService.getLatestIndexData();
        IndexVO indexVO = new IndexVO(indexPO);
        return indexVO;
    }

    public static void main(String[] args){
//        ShowIndexData s = new ShowIndexData();
//        System.out.println(s.getLatestIndexData().getDate().length);
    }
}

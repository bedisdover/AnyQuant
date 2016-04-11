import bl.ShowIndexData;
import vo.IndexVO;

import java.io.IOException;

/**
 * Created by 宋益明 on 16-4-11.
 */
public class IndexVOTest {
    public static void main(String[] args) throws IOException {
        IndexVO indexVO = new ShowIndexData().getLatestIndexData();
        long[] temp = indexVO.getVolume();
        System.out.println(temp[temp.length - 1]);
        System.out.println(indexVO.getDealNum());
        System.out.println(indexVO.getDealAmount());
    }
}

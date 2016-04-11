import bl.ShowIndexData;
import vo.IndexVO;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by 宋益明 on 16-4-11.
 */
public class IndexVOTest {
    public static void main(String[] args) throws IOException {
        System.out.println(getDealAmount());
    }

    public static String getDealAmount() {
        StringBuffer temp = new StringBuffer("123456789");
        int length = temp.length();

        if (temp.indexOf(".") != -1) {
            temp.delete(temp.indexOf("."), temp.length());
        }

        if (length >= 9) {//9位数，数量级为亿,保留到百万位
            temp.delete(temp.length() - 6, temp.length());
            temp.insert(temp.length() - 2, '.');
            temp.append("亿");
        } else if (length >= 5) {//5位数，数量级为万，保留到百位
            temp.delete(temp.length() - 2, temp.length());
            temp.insert(temp.length() - 2, '.');
            temp.append("万");
        }

        return temp.toString();
    }
}
